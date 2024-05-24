import { useState, createRef } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useLogin } from "../hooks/useLogin";
import Homepage from "./Homepage";

const LoginButton = () => {
  const navigate = useNavigate();
  const routeChange = (event) => {
    // event.preventDefault();
    navigate(event.target.dataset.path);
  };

  return (
    <Button
      variant="primary"
      className="float-right"
      style={{ float: "right", padding: "10px", margin: "10px" }}
      type="submit"
      data-path="/login"
      onClick={routeChange}
    >
      Login
    </Button>
  );
};

const Login = () => {
  const { login, isLoading, error } = useLogin();
  console.log("Rendering: Login");
  const [email, setEmail] = useState(null);
  const [password, setPassword] = useState(null);

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log("Login email", email, password);
    await login(email, password);
  };

  return (
    <>
      <Homepage />
      <div className="row align-items-center justify-content-center">
        <Form className="col-6">
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Group>
          <Button variant="primary" type="submit" onClick={handleLogin}>
            Submit
          </Button>
        </Form>
      </div>
    </>
  );
};

export { Login, LoginButton };
