import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { Part } from './plant.service';
import { suppliers } from './supplier.service';

export interface rfq {
  rfqId: number,
  demandId: number,
  partId: number,
  quantity: number,
  partName: string,
  expectedSupplyDate: number,
  specification: string

}
//headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") })
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Authorization': 'Bearer ' + localStorage.getItem("token")
     

  }),
};
@Injectable({
  providedIn: 'root'
})
export class RfqService {

  constructor(private http: HttpClient, private loginService: LoginService) { }

  token = this.loginService.getToken()
  rfqId: number = 0;
  setRfqId(id: number) {
    this.rfqId = id;
  }

  getRfqId() {
    return this.rfqId;
  }

 public getrfqofplant(partId:number): any {
    //console.log(consumerId);
    return this.http.get<any>(
     "http://localhost:8084/rfqapp/getrfqofplant/"+partId,httpOptions
     

    );
  }

  public getpotentialvendorsofrfq(rfqId:number): any {
    //console.log(consumerId);
    return this.http.get<any>(
     "http://localhost:8084/rfqapp/getpotentialvendorsofrfq/"+rfqId,httpOptions
     

    );
  }


  // getrfqofplant(partId:number): Observable<suppliers> {
  //   return this.http.get<suppliers>("http://localhost:8084/rfqapp/getrfqofplant",
  //   {
  //       headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") })
  //     });
  // }

  // getpotentialvendorsofrfq(): Observable<rfq> {
  //   return this.http.get<rfq>("http://localhost:8084/rfqapp/getpotentialvendorsofrfq" +this.rfqId,
  //     {
  //       headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") })
  //     });
  // }


}
