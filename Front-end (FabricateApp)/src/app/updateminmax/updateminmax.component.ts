import { Component, OnInit } from '@angular/core';
import { PlantService, Result } from '../plant.service';

@Component({
  selector: 'app-updateminmax',
  templateUrl: './updateminmax.component.html',
  styleUrls: ['./updateminmax.component.css']
})
export class UpdateminmaxComponent implements OnInit {

  partId:number=0;
  minQuantity:number=0;
  maxQuantity:number=0;
  result:Result={result:""};
  constructor(private service:PlantService) { }

  ngOnInit(): void {
  }

  updateMinMax(){
    alert("Updated");
    this.service.updateMinAndMax(this.partId,this.minQuantity,this.maxQuantity).subscribe((data:any)=>{
      this.result=data;
      console.log(data);
      
      
      
    },
    (error:any)=>{
      //error
      console.log(error);
    });
  }
  
}
