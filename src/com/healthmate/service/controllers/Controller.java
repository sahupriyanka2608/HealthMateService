package com.healthmate.service.controllers;

import com.healthmate.service.App;
import com.healthmate.service.activity.CancelAppointmentActivity;
import com.healthmate.service.activity.CreateAppointmentActivity;
import com.healthmate.service.activity.CreateUserActivity;
import com.healthmate.service.activity.GetAppointmentActivity;
import com.healthmate.service.activity.GetNearByDoctorActivity;
import com.healthmate.service.activity.LoginUserActivity;
import com.healthmate.service.activity.RegisterDoctorActivity;
import com.healthmate.service.activity.RegisterHospitalActivity;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.Hospital;
import com.healthmate.service.dynamodb.models.LocalDateMapper;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.models.requests.CancelAppointmentRequest;
import com.healthmate.service.models.requests.CreateAppointmentRequest;
import com.healthmate.service.models.requests.CreateUserRequest;
import com.healthmate.service.models.requests.GetAppointmentRequest;
import com.healthmate.service.models.requests.GetDoctorRequest;
import com.healthmate.service.models.requests.LoginUserRequest;
import com.healthmate.service.models.requests.RegisterDoctorRequest;
import com.healthmate.service.models.requests.RegisterHospitalRequest;
import com.healthmate.service.models.response.CancelAppointmentResponse;
import com.healthmate.service.models.response.CreateAppointmentResponse;
import com.healthmate.service.models.response.GetAppointmentResponse;
import com.healthmate.service.models.response.GetDoctorResponse;
import com.healthmate.service.models.response.LoginUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
public class Controller {

    private static final ServiceComponent component = App.serviceComponent;
//    @Inject
//    private GetAppointmentActivity appointmentActivity;
    @PostMapping(value = "/user/register", consumes = {"application/json"}, produces = {"application/json"})
    public User registerUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserActivity createUserActivity = component.provideCreateUserActivity();
            CreateUserRequest request = CreateUserRequest.builder().email(createUserRequest.getEmail()).firstName(createUserRequest.getFirstName())
                    .contact(createUserRequest.getContact()).password(createUserRequest.getPassword())
                    .lastName(createUserRequest.getLastName()).build();
            User response = createUserActivity.handleRequest(request,null);
            return response;
        }
    @PostMapping(value = "/user/login", consumes = {"application/json"}, produces = {"application/json"})
    public LoginUserResponse loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        LoginUserActivity loginUserActivity = component.provideLoginUserActivity();
        LoginUserRequest request = LoginUserRequest.builder().email(loginUserRequest.getEmail())
                .password(loginUserRequest.getPassword()).build();
        LoginUserResponse response = loginUserActivity.handleRequest(request,null);
        return response;
    }

    @PostMapping(value = "/hospital/register", consumes = {"application/json"}, produces = {"application/json"})
    public Hospital registerHospital(@Valid @RequestBody RegisterHospitalRequest registerHospitalRequest) {
        RegisterHospitalActivity registerHospitalActivity = component.provideRegisterHospitalActivity();
        RegisterHospitalRequest request = RegisterHospitalRequest.builder().name(registerHospitalRequest.getName())
                .hospitalId(registerHospitalRequest.getHospitalId()).doctorsInDept(registerHospitalRequest.getDoctorsInDept())
                .address(registerHospitalRequest.getAddress()).pincode(registerHospitalRequest.getPincode())
                .contact(registerHospitalRequest.getPincode())
                .build();
        Hospital response = registerHospitalActivity.handleRequest(request,null);
        return response;
    }
    @PostMapping(value = "/doctor/register", consumes = {"application/json"}, produces = {"application/json"})
    public Doctor registerDoctor(@Valid @RequestBody RegisterDoctorRequest registerDoctorRequest) {
        RegisterDoctorActivity registerDoctorActivity = component.provideRegisterDoctorActivity();
        RegisterDoctorRequest request = RegisterDoctorRequest.builder().firstName(registerDoctorRequest.getFirstName())
                .lastName(registerDoctorRequest.getLastName()).about(registerDoctorRequest.getAbout())
                .schedule(registerDoctorRequest.getSchedule()).department(registerDoctorRequest.getDepartment())
                .contact(registerDoctorRequest.getContact()).licenseNumber(registerDoctorRequest.getLicenseNumber())
                .email(registerDoctorRequest.getEmail())
                .build();
        Doctor response = registerDoctorActivity.handleRequest(request,null);
        return response;
    }
    @GetMapping(value = "/appointments", produces = {"application/json"})
    public GetAppointmentResponse getAppointment(@RequestHeader("token") String token) {
        GetAppointmentActivity appointmentActivity = component.provideGetAppointmentActivity();
        GetAppointmentRequest request = GetAppointmentRequest.builder().token(token).build();
        GetAppointmentResponse response = appointmentActivity.handleRequest(request,null);
        return response;
    }
    @PostMapping(value = "/appointment/create", consumes = {"application/json"}, produces = {"application/json"})
    public CreateAppointmentResponse createAppointment(@Valid @RequestBody CreateAppointmentRequest appointment,@RequestHeader("token") String token) {
        CreateAppointmentActivity appointmentActivity = component.provideCreateAppointmentActivity();
        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
                .doctorId(appointment.getDoctorId())
                .appointmentTime(appointment.getAppointmentTime())
                .apppointmentDate(appointment.getApppointmentDate())
                .pincode(appointment.getPincode())
                .hospitalId(appointment.getHospitalId())
                .token(token)
                .build();
        CreateAppointmentResponse response = appointmentActivity.handleRequest(request,null);
        return response;
    }
    @GetMapping(value = "/getNearByDoctors/{pincode}/{department}/{date}", produces = {"application/json"})
    public GetDoctorResponse getDoctor(@RequestHeader String token,@PathVariable String pincode,@PathVariable String department,@PathVariable String date) {
        GetNearByDoctorActivity getNearByDoctorActivity = component.provideGetNearByDoctorActivity();
        GetDoctorRequest request = GetDoctorRequest.builder().token(token)
                .date(new LocalDateMapper(date)).department(department).pincode(pincode).build();
        GetDoctorResponse response = getNearByDoctorActivity.handleRequest(request,null);
        return response;
    }
    @PutMapping(value = "/appointment/cancel", consumes = {"application/json"}, produces = {"application/json"})
    public CancelAppointmentResponse cancelAppointment(@Valid @RequestBody CancelAppointmentRequest cancelAppointmentRequest, @RequestHeader("token") String token) {
        CancelAppointmentActivity cancelAppointmentActivity = component.provideCancelAppointmentActivity();
        CancelAppointmentRequest request = CancelAppointmentRequest.builder().token(token)
                .appointmentTime(cancelAppointmentRequest.getAppointmentTime())
                .appointmentDate(cancelAppointmentRequest.getAppointmentDate())
                .bookingTime(cancelAppointmentRequest.getBookingTime())
                .build();
        CancelAppointmentResponse response = cancelAppointmentActivity.handleRequest(request,null);
        return response;

    }


}
