import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {

  credentials = {
    userid: '',
    upassword: ''
  }
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }
  onSubmit()
  {
    if((this.credentials.userid!='' && this.credentials.upassword!='') && (this.credentials.userid!=null && this.credentials.upassword!=null))
    {
        //token generate
        // console.log("Sent to server");
        
        this.loginService.generateToken(this.credentials).subscribe(
          
          (response:any)=>{
            //succes
            console.log(response);
            this.loginService.loginUser(response.authToken);
            window.location.href="/home";  
          },
          error=>{
            //error
            alert(this.credentials.userid); 
            alert("Wrong credential")
            console.log(error);
          }
        )
    }
    else
    {
        alert("Fields are empty!");
    }
  }

}
