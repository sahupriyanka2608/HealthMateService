import { useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";

import { Auth } from "./Auth";
import { api } from "../services/api";
import Table from "react-bootstrap/Table";

const ShowAppointmentsButton = () => {
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
      data-path="/appointments/list"
      onClick={routeChange}
    >
      My Appointments
    </Button>
  );
};

const ShowAppointments = () => {
  const { user } = useContext(Auth);
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    console.log("Getting all appointments of the user");
    getAllAppointments(user);
  }, []);
  const getAllAppointments = async () => {
    console.log("Getting all appointments of the user");
    try {
      const response = await api.getAllAppointments(user);
      console.log(response);
      const result = await response.json();
      console.log(result);
      if (response.ok) {
        setAppointments(result.appointmentList);
      }
    } catch (error) {
      alert(error.message);
    }
  };

  const cancelAppointment = async (row_data) => {
    // const row_data = event.target.dataset.row;
    console.log("Row data", row_data);
    const input = {
      appointmentTime: row_data.appointmentTime,
      appointmentDate: row_data.appointmentDate,
      bookingTime: row_data.bookingTime,
    };
    try {
      const response = await api.cancelAppointment(input, user);
      const result = await response.json();
      console.log("Cancel Appointment Response", input, result);
      if (response.ok) {
        getAllAppointments();
      }
    } catch (e) {
      alert("Appointment Not cancelled");
    }
  };

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          <th>Hospital Name</th>
          <th>Doctor Name</th>
          <th>Appointment Date</th>
          <th>Appointment Time</th>
          <th>Status</th>
          <th>Cancel</th>
        </tr>
      </thead>
      <tbody>
        {appointments.length > 0 &&
          appointments.map((item) => {
            return (
              <tr>
                <td>{item.hospitalName}</td>
                <td>{item.doctorName}</td>
                <td>{item.appointmentDate}</td>
                <td>{item.appointmentTime}</td>
                <td>{item.status}</td>
                <td>
                  <Button
                    variant="primary"
                    type="submit"
                    data-row={item}
                    disabled={
                      !item.isCancellable || item.status === "CANCELLED"
                    }
                    onClick={() => cancelAppointment(item)}
                  >
                    Cancel
                  </Button>
                </td>
              </tr>
            );
          })}
      </tbody>
    </Table>
  );
};

export { ShowAppointments, ShowAppointmentsButton };
