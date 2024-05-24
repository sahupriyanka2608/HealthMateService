const getComments = async (ID) => {
  const response = await fetch(`http://127.0.0.1:8000/posts/comment/${ID}`, {
    method: "GET",
    mode: "cors",
  });

  return response;
};

const login = async (email, password) => {
  const response = await fetch(
    "https://b1evy6c81f.execute-api.us-west-2.amazonaws.com/prod",
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    }
  );
  return response;
};

const register = async () => {};

const getAllAppointments = async (user) => {
  console.log(user);
  const response = await fetch(
    "https://7j4jgxwxlf.execute-api.us-west-2.amazonaws.com/prod",
    {
      method: "GET",
      headers: {
        token: user.token,
      },
    }
  );
  return response;
};
const getAvailableDoctors = async (pincode, date, department, user) => {
  console.log(pincode, date, department, user);
  const response = await fetch(
    `https://lgzzh8h5o4.execute-api.us-west-2.amazonaws.com/prod?pincode=${pincode}&department=${department}&date=${date}`,
    {
      method: "GET",
      headers: {
        token: user.token,
      },
    }
  );
  return response;
};

const cancelAppointment = async (data, user) => {
  const response = await fetch(
    "https://1mavu130h3.execute-api.us-west-2.amazonaws.com/prod",
    {
      method: "POST",
      headers: {
        token: user.token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    }
  );
  return response;
};

const getNearByDoctors = async () => {};

const createAppointment = async () => {};

export const api = {
  getComments,
  login,
  register,
  getAllAppointments,
  cancelAppointment,
  getAvailableDoctors,
  createAppointment,
};
