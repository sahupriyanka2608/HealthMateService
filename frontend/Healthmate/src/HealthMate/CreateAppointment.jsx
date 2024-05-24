import { useLocation, useNavigate } from "react-router-dom";
import { useState, useContext } from "react";
import { Auth } from "./Auth";
import Button from "react-bootstrap/Button";
function CreateAppointment() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { user } = useContext(Auth);
  console.log(state);
  const [selectedTimeSlot, setSelectedTimeSlot] = useState(null);
  const handleSelectTimeSlot = (e) => {
    const selectedSlot = e.target.value;
    console.log(selectedSlot);
    setSelectedTimeSlot(selectedSlot);
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      doctorId: state.licenseNumber,
      hospitalId: state.hospitalId,
      pincode: state.pincode,
      appointmentTime: selectedTimeSlot,
      apppointmentDate: state.appointmentDate,
    };
    try {
      console.log("FormData", formData);
      const response = await fetch(
        "https://sjs1somt2e.execute-api.us-west-2.amazonaws.com/prod",
        {
          method: "POST",
          headers: {
            token: user.token,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );
      const result = await response.json();
      console.log("Create appointment response", result);

      if (response.ok) {
        alert("Appointment is confirmed.");
        navigate("/appointments/list");
      }
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="row align-items-center justify-content-center">
      <div className="col-6">
        <h3>Book Your Appointment</h3>
        <ul>
          <p>
            Doctor Name:{state.firstName} {state.lastName}
          </p>
          <p>Hospital Name:{state.hospitalName}</p>
          <p>Hospital Address:{state.hospitalAddress}</p>
          <label>Select a time slot:</label>
          <select value={selectedTimeSlot} onChange={handleSelectTimeSlot}>
            <option value="">Select a time slot</option>
            {state.availableSlots.map((slot, index) => (
              <option key={index} value={slot.startTime}>
                {slot.startTime}
              </option>
            ))}
          </select>
          <Button variant="primary" type="submit" onClick={handleSubmit}>
            Confirm
          </Button>
        </ul>
      </div>
    </div>
  );
}
export { CreateAppointment };
