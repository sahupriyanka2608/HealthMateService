import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Homepage from "./Homepage";

const RegisterButton = () => {
  const navigate = useNavigate();
  const routeChange = (event) => {
    console.log(event.target.dataset.path);
    navigate(event.target.dataset.path);
  };

  return (
    <Button
      variant="primary"
      type="submit"
      className="float-right"
      style={{ float: "right", padding: "10px", margin: "10px" }}
      data-path="/register"
      onClick={routeChange}
    >
      Register
    </Button>
  );
};

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    contact: "",
  });
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log("FormData", formData);
      const response = await fetch(
        "https://rv9qgme204.execute-api.us-west-2.amazonaws.com/prod",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );
      console.log(response);

      if (response.ok) {
        navigate("/login");
      }
    } catch (error) {
      alert(error.message);
    }
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    console.log(formData);
  };

  return (
    <>
      <Homepage />
      <div className="row align-items-center justify-content-center">
        <Form className="col-6">
          <Form.Group className="mb-3" controlId="formFirstName">
            <Form.Label>First Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter First Name"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formLastName">
            <Form.Label>Last Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter Last Name"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              name="password"
              value={formData.password}
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formContact">
            <Form.Label>Contact</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter Contact"
              name="contact"
              value={formData.contact}
              onChange={handleChange}
            />
          </Form.Group>

          <Button variant="primary" type="submit" onClick={handleSubmit}>
            Submit
          </Button>
        </Form>
      </div>
    </>
  );
};

export { Register, RegisterButton };
