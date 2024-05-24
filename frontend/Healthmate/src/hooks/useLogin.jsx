import { useContext, useState } from "react";
import { Auth } from "../HealthMate/Auth";
import { api } from "../services/api";

export const useLogin = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const { dispatch } = useContext(Auth);

  const login = async (email, password) => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await api.login(email, password);
      const result = await response.json();
      // const json = {
      //   token: "samya@gmail.com",
      // };
      // const response = {
      //   ok: true,
      // };

      if (result.name === "Error") {
        setError(result.message);
        setIsLoading(false);
      }

      if (!response.ok) {
        setIsLoading(false);
        setError(result);
      }

      if (response.ok) {
        console.log("Setting username and token", email);
        const userData = {
          userName: email,
          token: result.token,
        };
        // Save the user and token in the localstorage
        localStorage.setItem("user", JSON.stringify(userData));

        // Updating the global Auth context
        dispatch({ type: "LOGIN", payload: userData });

        setIsLoading(false);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return { error, isLoading, login };
};
