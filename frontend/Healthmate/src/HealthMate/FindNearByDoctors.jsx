import { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import * as React from "react";
import "./Homepage.css";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import { api } from "../services/api";
import { Auth } from "./Auth";

const GetNearByDoctors = (props) => {
  const { doctors, date } = props;
  console.log("doctorsList to print", doctors);
  const navigate = useNavigate();
  const handleSubmit = (doctor) => {
    //e.preventDefault();
    console.log("resdoctor", doctor);
    const result = { ...doctor, appointmentDate: date };
    console.log(" beforeadding", result);
    // setResult({ ...result, [date]: date });
    // console.log("result", result);

    navigate("appointments/create", { state: result });
  };
  return (
    <div>
      {doctors == null || doctors.length === 0 ? (
        <div>No doctors available</div>
      ) : (
        <div>
          <ul className="doctors">
            {doctors.map((doctor) => (
              <li key={doctor.licenseNumber}>
                <p>
                  Name:{doctor.firstName} {doctor.lastName}
                </p>
                <p>About:{doctor.about}</p>
                <p>Department:{doctor.department}</p>
                <p>Hospital:{doctor.hospitalName}</p>
                <p>Hospital Address:{doctor.hospitalAddress}</p>

                <Button
                  variant="primary"
                  type="submit"
                  data-doctor={doctor}
                  onClick={() => handleSubmit(doctor)}
                >
                  Book Appointment
                </Button>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};
const FindNearByDoctors = () => {
  const [pincode, setPincode] = useState("");
  const [date, setDate] = useState("");
  const [department, setDepartment] = useState("");
  const [isDoctorsAvailable, setIsDoctorsAvailable] = useState(false);
  const [doctorsList, setDoctorsList] = useState([]);
  const { user } = useContext(Auth);
  const getDoctors = async (pincode, newDate, department, userData) => {
    try {
      const response = await api.getAvailableDoctors(
        pincode,
        newDate,
        department,
        userData
      );
      console.log("Doctor Response", response);
      const result = await response.json();
      console.log(result);
      if (response.ok) {
        setDoctorsList(result.doctorDetails);
        setIsDoctorsAvailable(true);
      }
    } catch (error) {
      alert(error.message);
    }
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    getDoctors(pincode, date, department, user);
  };
  return (
    <>
      {!isDoctorsAvailable && (
        <div className="row align-items-center justify-content-center">
          <Form className="col-6">
            <h3
              style={{
                fontStyle: "oblique",
                fontFamily: "sans-serif",
                padding: "10px",
              }}
            >
              Get NearBy Available Doctors
            </h3>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>PinCode</Form.Label>
              <Form.Control
                type="pincode"
                placeholder="Enter Pincode"
                value={pincode}
                onChange={(e) => setPincode(e.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicSelect">
              <Form.Label>Department</Form.Label>
              <Form.Select
                aria-label="Default select example"
                value={department}
                onChange={(e) => setDepartment(e.target.value)}
              >
                <option>Select Department</option>
                <option value="Cardiology" placeholder="Select Department">
                  Cardiology
                </option>
                <option value="Dermatology" placeholder="Select Department">
                  Dermatology
                </option>
                <option value="Neurology" placeholder="Select Department">
                  Neurology
                </option>
                <option
                  value="Pediatric Neurology"
                  placeholder="Select Department"
                >
                  Pediatric Neurology
                </option>
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicDate">
              <Form.Label>Date</Form.Label>

              <Form.Control
                type="date"
                formTarget="yyyy-mm-dd"
                placeholder="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
              />
            </Form.Group>
            <Button variant="primary" type="submit" onClick={handleSubmit}>
              Submit
            </Button>
          </Form>
        </div>
      )}
      {isDoctorsAvailable && (
        <GetNearByDoctors doctors={doctorsList} date={date} />
      )}
    </>
  );
};

const GetNearByDoctorsButton = () => {
  const navigate = useNavigate();
  const routeChange = (event) => {
    navigate(event.target.dataset.path);
  };

  return (
    <Button
      variant="primary"
      className="float-right"
      style={{ float: "right", padding: "10px", margin: "10px" }}
      type="submit"
      data-path="/"
      onClick={routeChange}
    >
      GetNearByDoctors
    </Button>
  );
};
export { GetNearByDoctors, FindNearByDoctors, GetNearByDoctorsButton };
