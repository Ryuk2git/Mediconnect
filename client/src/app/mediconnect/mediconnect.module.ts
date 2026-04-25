import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { MediConnectRoutingModule } from "./mediconnect-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { PatientCreateComponent } from "./components/patientcreate/patientcreate.component";
// import { DoctorCreateComponent } from "./components/doctorcreate/doctorcreate.component";
import { DoctorArrayComponent } from "./components/doctorarray/doctorarray.component";

@NgModule({
  declarations: [
    PatientCreateComponent,
    DoctorArrayComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  exports: [
    
  ]
})
export class MediconnectModule {}
