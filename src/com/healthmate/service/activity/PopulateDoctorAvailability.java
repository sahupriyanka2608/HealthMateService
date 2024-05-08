package com.healthmate.service.activity;

import com.healthmate.service.dynamodb.models.Availability;
import com.healthmate.service.dynamodb.models.Day;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.LocalDateMapper;
import com.healthmate.service.dynamodb.models.TimeRange;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PopulateDoctorAvailability  {

    public static void setAvailability(Doctor doctor) {
        Map<LocalDateMapper,List<Availability>> availability = doctor.getAvailableSlot();
        LocalDate currentDate = LocalDate.now();
        LocalDateMapper currentDateMapper = new LocalDateMapper(currentDate.toString());
        for (int i = 0; i < 30; i++) {
            if (!availability.containsKey(currentDateMapper)) {
                DayOfWeek currentDay = currentDate.getDayOfWeek();
                Day day = Day.getDay(currentDay);
                ArrayList<Availability> slots = new ArrayList<>(96);
                for (int j = 0; j < 96; j++) {
                    slots.add(Availability.NOT_AVAILABLE);
                }
                List<TimeRange> availableTime = doctor.getSchedule().get(day);
                if(availableTime != null) {
                    for (TimeRange range : availableTime) {
                        int startTime = range.toLocalStartTime().toSecondOfDay() / 60;
                        int endTime = range.toLocalEndTime().toSecondOfDay() / 60;
                        for (int start = startTime / 15; start <= endTime / 15; start++) {
                            if ((start*15)+15 <= endTime) {
                                slots.set(start, Availability.AVAILABLE);
                            }
                        }
                    }
                }
                availability.put(currentDateMapper, slots);
            }
            currentDate = currentDate.plusDays(1);
            currentDateMapper = new LocalDateMapper(currentDate.toString());
        }
        doctor.setAvailableSlot(availability);
    }
}
