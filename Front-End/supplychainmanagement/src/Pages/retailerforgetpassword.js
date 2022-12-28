import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { NotificationManager } from "react-notifications";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import Tooltip from "react-bootstrap/Tooltip";

function RetailerforgetPassword() {
  const [retailerEmail, setRetailerEmail] = useState("");
  const [retailerPassword, setRetailerPassword] = useState("");
  const [confPass, setConfPass] = useState("");

  const navigate = useNavigate();

  const setReEmail = (e) => {
    setRetailerEmail(e.target.value);
  };
  const setPass = (e) => {
    setRetailerPassword(e.target.value);
  };
  const setConfirmPassword = (e) => {
    setConfPass(e.target.value);
  };

  function encode(data) {
    const md5 = require("md5");
    let Data = md5(data);
    return Data;
  }

  //main logic
  const getRetailer = async (retailerEmail, retailerPassword) => {
    const url = "http://localhost:3001/retailer/findByEmail/" + retailerEmail;
    const retailer = await axios.get(url).then((response) => response.data);
    console.log(retailer);

    const updateUrl =
      "http://localhost:3001/retailer/updateByEmail/" + retailerEmail;

    const resp = await axios.put(updateUrl, {
      retailerId: retailer.retailerId,
      retailerName: retailer.retailerName,
      retailerEmail: retailerEmail,
      retailerPassword: encode(retailerPassword),
      retailerCompany: retailer.retailerCompany,
      retailerContactNumber: retailer.retailerContactNumber,
    });
    console.log(resp.data);
    NotificationManager.success(
      "Password Updated Successfully! Relogin to continue",
      "Successful!",
      3000
    );
    navigate("/RetailerLogin");
  };

  const forgetPassword = (e) => {
    e.preventDefault();
    let data = {
      retailerEmail: retailerEmail,
      retailerPassword: retailerPassword,
      confirmPassword: confPass,
    };
    console.log(data);

    if (data.retailerPassword !== data.confirmPassword) {
      NotificationManager.error(
        "Passwords do not match",
        "Type CorrectPassword!",
        2000
      );
    } else {
      getRetailer(retailerEmail, retailerPassword);
    }
  };

  const renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
      Password must be atleast 8 characters long, Should contain atleast 1
      lowercase,1 uppercase character
    </Tooltip>
  );

  return (
    <div className="Container">
      <Navbar id="navbar">
        <Container>
          <Navbar.Brand id="navtitle" href="/">
            S C M
          </Navbar.Brand>
        </Container>
      </Navbar>
      <div className="Auth-form-container">
        <form className="Auth-form" onSubmit={(e) => forgetPassword(e)}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Retailer Reset Password </h3>

            <div className="form-group mt-3">
              <input
                className="form-control mt-1 input"
                type="text"
                id="retailerEmail"
                placeholder="Enter Email"
                pattern="[a-z0-9]+@[a-z]+\.[a-z]{2,3}"
                onChange={(e) => setReEmail(e)}
              />
            </div>
            <OverlayTrigger
              placement="right"
              delay={{ show: 250, hide: 400 }}
              overlay={renderTooltip}
            >
            <div className="form-group mt-3">
              <input
                className="form-control mt-1 input"
                type="password"
                id="retailerPassword"
                placeholder="Enter New Password"
                onChange={(e) => setPass(e)}
                pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
              />
            </div>
            </OverlayTrigger>
            <div className="form-group mt-3">
              <input
                className="form-control mt-1 input"
                type="password"
                id="retailerPassword"
                placeholder="Retype Password"
                onChange={(e) => setConfirmPassword(e)}
              />
            </div>
            <div className="d-grid gap-2 mt-3 justify-content-center align-content-center">
              <button className="btn btn-primary " id="btn" type="submit">
                Reset Password
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default RetailerforgetPassword;
