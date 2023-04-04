import { Component, OnInit } from '@angular/core';
import { rfq, RfqService } from '../rfq.service';

@Component({
  selector: 'app-getrfq',
  templateUrl: './getrfq.component.html',
  styleUrls: ['./getrfq.component.css']
})
export class GetrfqComponent implements OnInit {

  constructor(private service: RfqService) { }
  rfqdetails: any;
  ngOnInit(): void {
    this.showRfqDetails()
  }

partId: number = this.service.getRfqId();

  showRfqDetails() {
    this.service.getrfqofplant(this.partId).subscribe((data: any) => {
      console.log(data);
      this.rfqdetails = data;
      
    },
      (error: any) => {
        //error
        //console.log(error);
      });

  }
}