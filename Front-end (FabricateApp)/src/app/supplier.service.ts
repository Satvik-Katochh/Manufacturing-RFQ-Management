import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
export interface suppliers{
  supplier_id:number,
  supplierName:string,
  email:string,
  phone:number,
  location:string,
  feedback:number
}

@Injectable({
  providedIn: 'root'
})

export class SupplierService {

  constructor(private http:HttpClient,private loginService:LoginService) { }
  token = this.loginService.getToken();
  addSupplier(s:suppliers){
    // console.log("called");
    // alert("called");
    return this.http.post<suppliers[]>('http://localhost:8083/supplierapp/addSupplier',
    s,
   { headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+this.token})
  });
  }

  getsupplier():Observable<suppliers[]>{
    return this.http.get<suppliers[]>("http://localhost:8083/supplierapp/getsupplier",
    { headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+this.token})
  });
  }

  updatesupplier(s:suppliers){  //(i:number) +i
    console.log("called");
    return this.http.post<suppliers[]>('http://localhost:8083/supplierapp/editSupplier',
    s,
   { headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+this.token})
  });
  }

  updateFeedback(s:suppliers){
    return this.http.post<suppliers[]>('http://localhost:8083/supplierapp/updateFeedback',
    s,
   { headers:new HttpHeaders({'Content-Type':'application/json','Authorization':'Bearer '+this.token})
  });
  }
  
}
