import { Component, OnInit } from '@angular/core';
import { RfqService } from '../rfq.service';

@Component({
  selector: 'app-rfq',
  templateUrl: './rfq.component.html',
  styleUrls: ['./rfq.component.css']
})
export class RfqComponent implements OnInit {

  constructor(private service: RfqService) { }
  rfqId: number = 0;
  partId: number=0;
  ngOnInit(): void {
  }

  setRfq() {
    this.service.setRfqId(this.rfqId);
  }
  setPartId() {
    this.service.setRfqId(this.partId);
    console.log(this.partId);
  }


}