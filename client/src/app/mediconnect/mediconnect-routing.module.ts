import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppointmentCreateComponent } from "./components/appointment/appointment.component";

const routes: Routes = [
  {path: '/appointment', component: AppointmentCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MediConnectRoutingModule {}
