
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';


export interface Part{
  partId:number,
  partDescription:string,
  partSpecification:string,
  stockInHand:number
}
export interface partreorderdetails{
  prrid:number,
  reorderstatus:string,
  date:Date,
  part:Part
}
export interface Result{
  result:string;
}
@Injectable({
  providedIn: 'root'
})
export class PlantService {

  constructor(private http:HttpClient,private loginService:LoginService) { }

  id:number=0;
  setId(i:number){
    this.id=i;
  }
  details={partId:0,minQuantity:0,maxQuantity:0}
  token = this.loginService.getToken()
  getPartReorderDetails():Observable<Part[]>{
    return this.http.get<Part[]>("http://localhost:8082/plantapp/viewpartsreorder",
    { headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+this.token})
  });
  }

  getStockDetails(i:number):Observable<Part>{
    return this.http.get<Part>("http://localhost:8082/plantapp/viewstockinhand/"+i,
    { headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+this.token})
  });
  }

  updateMinAndMax(id:number,min:number,max:number):Observable<Result>{
    this.details={partId:id,minQuantity:min,maxQuantity:max}
    return this.http.post<Result>("http://localhost:8082/plantapp/updateminmaxquantity",this.details, //+"/"+min+"/"+max
    {headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+localStorage.getItem("token")})
  });
  }
}
