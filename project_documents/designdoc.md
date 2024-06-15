# Design Document :

## HealthMate Service Design

## 1. Problem Statement

*In rural areas, residents face significant challenges when seeking healthcare 
services, particularly when it comes to booking hospital appointments. Many 
individuals from rural areas are compelled to travel long distances to urban 
centers solely for this purpose. This inconvenience arises from a lack of awareness 
regarding doctor availability and lack of information regarding which hospital 
can provide optimal treatment. As a result, patients often find themselves 
navigating a confusing and time-consuming process, leading to delays in receiving 
essential medical attention.

The design document outlines the HealthMate Service, aimed at providing a list of 
available doctors for a specified pin code. With this service, users gain the 
ability to conveniently access doctor availability for specific dates, also 
allowing them to schedule appointments based on their flexibility*


## 2. Top Questions to Resolve in Review

1. To solve the problem,I have designed to get the nearby doctors across all 
   the hospitals based on pincode. Should I implement more complicate design 
   to fetch nearby doctors?
2. To get the availability of doctors,I am dividing a day into 15minute timewindow. 
   I am storing availability of doctors on day basis in a different table in 
   database. Is there any better way to figure out availability of doctor on a 
   particular date?

## 3. Use Cases


U1. *As a user, I can see the availability of doctors on a particular date across all hospitals based on pincode.*

U2. *As a user, I can book appointment for a specific doctor based on the availability.*

U3. *As a user, I can cancel the appointment.*

U4. *As a user, I can see the appointment details.*


## 4. Project Scope

### 4.1. In Scope

* Getting the availability of doctors across all hospitals present in a 
  particular pincode.
* Book and cancel an appointment.

### 4.2. Out of Scope

* Getting the availability of nearby doctors based on GPS location.

# 5. Proposed Architecture Overview

*We will use API Gateway and Lambda to create endpoints (`Get Nearby Available Doctors`,
`Book an Appointment`, `Cancel an appointment`)
that will handle getting the availability of doctors and based on that to create,
and cancel appointment to satisfy user requirements.*

*We will store the hospital details in a table in database.To get doctors, we 
will store list of doctors present in each department for each hospital.For simpler
retrieval of doctors availability for a particular date, we will store the 
availability of doctors in a different table.We will store the appointment details in 
a different table.*

# 6. API

## 6.1. *Register User*

* Accepts `POST` requests to `createUser/:email/:pwd`
* email, pwd : Required Parameter
* Accepts data to create a user account and save the user details in database.
    * If user already exist, will throw a
      `EmailAlreadyRegisteredException`
    * If email or pwd is Invalid, will throw a
      `InvalidInputException`
![Client sends form to website Register page.Website Register page sends
  request to CreateUserActivity.The CreateUserActivity save the user in users
  database.](images/design_document/registeruserSD.png)

## 6.2. *Get Nearby Available Doctors*

* Accepts `GET` requests to `/doctors/:pincode/:date/:department`
* pincode, date, department : Required Parameter
* Accepts pincode, date and show all the available doctors on the particular date across all hospitals based on pincode.
    * If pincode, date or department is not given, will throw a
      `InvalidInputException`
    * If no doctor is present, will throw a
      `NoDoctorPresentException`
    * If no doctor is available on the date, will throw a
      `NoDoctorAvailableOnThisDateException`
![Client sends  form to Website FindNearByDoctorsPage.Website 
  FindNearByDoctorsPage sends a request to GetDoctorActivity.
  GetDoctorActivity send request to hospitals database to get nearby
  doctors based on pincode and to doctor_availability database to 
  get the availability of doctors.The database returns the 
  List of nearby available doctors to GetDoctorActivity. GetDoctorActivity
  send the same to FindNearByDoctorsPage](images/design_document/get-nearbyDoctors-byAvailabilitySD.png)

## 6.3 *Book an Appointment*

* Accepts `POST` requests to `/createappointment/:doctorId/:date/:time`
* doctorId. date. time : Required Parameter
* Accepts data to create a new appointment with a given userid, doctorid, date, time.
  Returns a new appointment with unique id.
   * If doctorId or date or time is not given, will throw a
     `InvalidInputException`
   * If doctorid is not found, will throw a
     `DoctornotfoundException`
   * If userid is not found, will throw a 
     `Usernotfoundexception`
![Client submits the create appointment form to the Website Book Appointment page.
  The website BookAppointment page sends a create appointment request
  to the CreateAppointmentActivity. The CreateAppointmentActivity save
  the new appointment in the appointments database.](images/design_document/createappointmentSD.png)

## 6.4 *Get Appointment Details*

* Accepts `GET` requests to `appointments/userid`
* userId : Required Parameter
* Accepts user id and show the details of appointment for the user.
    * If userid is not found, will throw a
      `Usernotfoundexception`
![Client visit the ShowAppointmentsPage on website.The website 
  ShowAppointmentsPage sends a getAppointment request to 
  getAppointmentActivity.The getAppointmentActivity call the appointments
  database to load the appointments for the Client.The appointments database
  returns a List<Appointment> to getAppointmentActivity.Then the website page
  show list of appointments to the Client.>](images/design_document/get-appointmentSD.png)

## 6.5 *Cancel an appointment*

* Accepts `DELETE` requests to `appointment/:doctorId/:date/:time`
* doctorId,date,time : Required parameter
* Accepts data and delete the appointment from database.
  * If appointment is not found, will throw a
     `AppointmentnotfoundException`
  * If userId is not found, will throw a
     `UserNotFoundException`
    ![Client submits the cancel appointment form to the Website 
     CancelAppointment page.The website CancelAppointment page sends a 
     delete appointment request to the CancelAppointmentActivity. The CancelAppointmentActivity
     delete the appointment from appointments database.](images/design_document/cancelappointmentSD.png)

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

# 7. Tables

### 7.1. `users`

```
id // string
emailid // partition key, string
firstname // string
lastname // string
contact // string
password // string
```
### 7.2. `hospitals`

```
id // sort key, string
name // string
address // string
contact // string
pincode // partition key, string
doctors_present_in_all_department // dict(key:string,value:List<String>)
```
### 7.3. `doctors`

```
id // partition key, string
name // string
department // string
contact // string
about // string
```
### 7.4. `appointments`

```
userid // partition key, string
doctorid:appointmentdate:time // sort key, string
hospitalid // string
status // string
```
### 7.5. `doctor_availability`
```
doctorid // partition key, string
date //sort key, string
available_time_slot // List<Object>
```

# 8. Pages

![](images/design_document/WhatsApp%20Image%202024-04-08%20at%2012.28.44%20PM.jpeg)
