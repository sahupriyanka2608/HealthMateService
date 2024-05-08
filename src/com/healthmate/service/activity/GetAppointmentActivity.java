package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.AppointmentDao;
import com.healthmate.service.dynamodb.DoctorDao;
import com.healthmate.service.dynamodb.HospitalDao;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.Hospital;
import com.healthmate.service.dynamodb.models.LocalDateMapper;
import com.healthmate.service.models.requests.GetAppointmentRequest;
import com.healthmate.service.models.response.AppointmentDetails;
import com.healthmate.service.models.response.GetAppointmentResponse;
import com.healthmate.service.util.JwtUtils;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetAppointmentActivity implements RequestHandler<GetAppointmentRequest, GetAppointmentResponse> {
    private AppointmentDao appointmentDao;
    private DoctorDao doctorDao;
    private HospitalDao hospitalDao;
    @Inject
    public GetAppointmentActivity(AppointmentDao appointmentDao,DoctorDao doctorDao, HospitalDao hospitalDao) {
        this.appointmentDao = appointmentDao;
        this.doctorDao = doctorDao;
        this.hospitalDao = hospitalDao;
    }
    @Override
    public GetAppointmentResponse handleRequest(final GetAppointmentRequest getAppointmentRequest, Context context) {
        if(!JwtUtils.validateToken(getAppointmentRequest.getToken())) {
            throw new IllegalArgumentException("Invalid User Credentials.Please Login");
        }
        String email = JwtUtils.extractEmail(getAppointmentRequest.getToken());
        List<AppointmentDetails> result = new ArrayList<>();
        List<Appointment> appointments = appointmentDao.getAppointmentsByUser(email);
        for (Appointment appointment:appointments) {
            AppointmentDetails details = new AppointmentDetails();
            String[] time = appointment.getAppointmentTime().split("#");
            Hospital hospital = hospitalDao.get(appointment.getPincode(), appointment.getHospitalId());
            result.add(AppointmentDetails.builder()
                            .appointmentDate(time[0])
                            .appointmentTime(time[1])
                            .bookingTime(time[2])
                            .doctorName(doctorDao.getDoctor(appointment.getDoctorId()).getFirstName())
                            .hospitalName(hospital.getName())
                            .hospitalAddress(hospital.getAddress())
                            .status(appointment.getStatus().name())
                            .isCancellable(LocalDate.now().isBefore(new LocalDateMapper(time[0]).convertToLocalDate()))
                    .build());
        }
        return new GetAppointmentResponse(email, result);
    }

}
