import { useContext } from "react";
import { Auth } from "../HealthMate/Auth";
export const useLogout = () => {
  const { dispatch } = useContext(Auth);

  const logout = () => {
    // delete user from the localstorage
    localStorage.removeItem("user");
    // Wipe out the Auth context (user:null) / dipatch 'LOGOUT'
    dispatch({ type: "LOGOUT" });
  };

  return { logout };
};
