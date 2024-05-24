import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import { useLogout } from "../hooks/useLogout";
import { useContext } from "react";
import { Auth } from "./Auth";

const LogOutButton = () => {
  const { user } = useContext(Auth);
  const { logout } = useLogout();
  const navigate = useNavigate();
  const handleLogout = () => {
    logout();
  };
  const routeChange = () => {
    navigate("/login");
  };

  return (
    <Button
      variant="primary"
      className="float-right"
      style={{ float: "right", padding: "10px", margin: "10px" }}
      type="submit"
      onClick={handleLogout}
    >
      LogOut
    </Button>
  );
};

export { LogOutButton };
