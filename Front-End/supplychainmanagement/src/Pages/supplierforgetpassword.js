import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { NotificationManager } from "react-notifications";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import Tooltip from "react-bootstrap/Tooltip";

function encode(data){
  const md5 = require('md5')
  let Data = md5(data);
  return Data;
}

function SupplierForgetPassword() {
  const [supplierEmail, setSupplierEmail] = useState("");
  const [supplierPassword, setSupplierPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const navigate = useNavigate();

  const setSuEmail = (e) => {
    setSupplierEmail(e.target.value);
  };
  const setPass = (e) => {
    setSupplierPassword(e.target.value);
  };

  const setConfPass = (e) => {
    setConfirmPassword(e.target.value);
  };

  //main logic
  const getSupplier = async (supplierEmail, supplierPassword) => {
    const url = "http://localhost:3002/supplier/findByEmail/" + supplierEmail;
    const supplier = await axios.get(url).then((response) => response.data);

    const updateUrl =
      "http://localhost:3002/supplier/updateByEmail/" + supplierEmail;
    const resp = await axios.put(updateUrl, {
      supplierId: supplier.supplierId,
      supplierName:supplier.supplierName,
      supplierEmail: supplier.supplierEmail,
      supplierPassword: encode(supplierPassword),
    });
    console.log(resp.data);
    NotificationManager.success(
      "Relogin to continue",
      "Your password has been updated!",
      2000
    );
    navigate("/SupplierLogin");
  };

  const forgetPassword = (e) => {
    e.preventDefault();

    let data = {
      supplierEmail: supplierEmail,
      supplierPassword: encode(supplierPassword),
      confirmPassword: encode(confirmPassword),
    };

    console.log(data);
    if (data.supplierPassword !== data.confirmPassword) {
      NotificationManager.error("Passwords do not match!", "Error", 3000);
    } else {
      getSupplier(supplierEmail, supplierPassword);
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
            <h3 className="Auth-form-title">Supplier Reset Password</h3>

            <div className="form-group mt-3">
              <input
                className="form-control mt-1 input"
                type="text"
                id="supplierEmail"
                placeholder="Supplier Email"
                onChange={(e) => setSuEmail(e)}
                pattern="[a-z0-9]+@[a-z]+\.[a-z]{2,3}"
                required
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
                id="supplierPassword"
                placeholder="Supplier Password"
                onChange={(e) => setPass(e)}
                pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                required
              />
            </div>
            </OverlayTrigger>
            <div className="form-group mt-3">
              <input
                className="form-control mt-1 input"
                type="password"
                id="supplierPassword"
                placeholder="Retype Password"
                onChange={(e) => setConfPass(e)}
                required
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

export default SupplierForgetPassword;
