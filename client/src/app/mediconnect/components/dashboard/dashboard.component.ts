import { Component, OnInit } from '@angular/core'; 
import { MediConnectService } from '../../services/mediconnect.service'; 

@Component({ 
  selector: 'app-dashboard', 
  templateUrl: './dashboard.component.html', 
  styleUrls: ['./dashboard.component.scss'] 
}) 
export class DashboardComponent implements OnInit { 
  role: string = ''; 
  doctorId!: number; 
  doctorDetails: any; 
  clinics: any[] = []; 
  appointments: any[] = []; 
  patientId!: number; 
  constructor(private service: MediConnectService) {} 
  
  ngOnInit(): void { 
    this.role = localStorage.getItem('role') || ''; 
    
    if (this.role === 'DOCTOR') { 
      this.doctorId = Number(localStorage.getItem('user_id')); 
      this.loadDoctorData(); 
    } 
  } 

  // deletePatient(): void {
  //       if (confirm('Are you sure you want to delete your profile?')) {
  //         this.mediConnectService.deletePatient(this.patientId).subscribe(() => {
  //           this.patientDetails = null;
  //         });
  //       }
  //     }


    loadDoctorData(): void { 
      this.service.getDoctorById(this.doctorId).subscribe({ next: (doctor) => (
        this.doctorDetails = doctor), error: () => (this.doctorDetails = undefined) }); 
        this.service.getClinicsByDoctorId(this.doctorId).subscribe({ next: (clinics) => (
          this.clinics = clinics) 
        }); 
  } 
        
    deleteDoctor(): void { 
      if (confirm('Are you sure you want to delete your profile?')) { 
        this.service.deleteDoctor(this.doctorId).subscribe(() => { 
          this.doctorDetails = null; 
        }); 
      } 
  } 
    deleteClinic(clinicId: number): void { 
      if (confirm('Are you sure you want to delete this clinic?')) { 
        this.service.deleteClinic(clinicId).subscribe(() => { 
          this.clinics = this.clinics.filter((c) => c.clinicId !== clinicId); 
        }); 
      } 
  } 
            
    cancelAppointment(appointment: any): void { 
      if (confirm('Are you sure you want to cancel this appointment?')) { 
        appointment.status = 'Cancelled'; 
          this.service.updateAppointment(appointment).subscribe(); 
      } 
    } 
}