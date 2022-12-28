import { useState, useEffect } from "react";
import axios from "axios";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { Table } from "react-bootstrap";
import { Nav } from "react-bootstrap"
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

function OrderHistory() {
  const location = useLocation();
  const [retailerId] = useState(location.state.retailerId);
  const [retailerName] = useState(location.state.retailerName);
  const [retailerEmail] = useState(location.state.retailerEmail);
  const [retailerPassword] = useState(location.state.retailerPassword);
  const [retailerCompany] = useState(location.state.retailerCompany);
  const [retailerContactNumber] = useState(location.state.retailerContactNumber);
  
  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();



  async function getOrdersOfRetailer() {
    // e.preventDeafult();
    const url = "http://localhost:3004/order/getorderbyretailername/" + retailerName;
    console.log(url)
    const response = await axios.get(url).then((response) => response.data);
    console.log(response);
    setOrders(response);
  }

  useEffect(() => {
    getOrdersOfRetailer();
  });

  return (
    <div className="Container">
      <Navbar id='navbar'>
        <Container>
          <Navbar.Brand id='navtitle' href="/">S C M</Navbar.Brand>     
          <Nav.Link id="navlink" onClick={() => navigate("/Retailer", {
            state: {
              retailerId: retailerId,
              retailerName: retailerName,
              retailerEmail: retailerEmail,
              retailerPassword: retailerPassword,
              retailerContactNumber: retailerContactNumber,
              retailerCompany : retailerCompany
                    }
          })}>Go Back</Nav.Link>
        </Container>
      </Navbar>
      <div id="content">
        {orders.map((order, key) =>
        {
          return (
            <Card className="card-item">
              <Card.Header key={key}>
                <center>Order Number: {order.orderNumber}</center>
              </Card.Header>
              <ListGroup variant="flush">
                <ListGroup.Item key={key}>
                  <b>Description : </b> <i>{order.orderDescription}</i>
                </ListGroup.Item>
                <ListGroup.Item key={key}>
                  <b>Billing:</b> <i>{order.orderBill}</i>
                </ListGroup.Item>
                <ListGroup.Item key={key}>
                  <b>Order Status:</b> <i>{order.orderStatus}</i>
                </ListGroup.Item>
                <ListGroup.Item>
                Cart Items : <br /><br />
                  <Table className="cartItems">
                    
                    <tr>
                      <th>Name</th>
                      <th>Quantity</th>
                      <th>Price Per Unit</th>
                      <th>Total Price</th>
                    </tr>
                  {order.cartItems.map((item) =>
                  {
                    return (
                      
                          <tr>
                            <td>{item.productName}    </td>
                            <td>{item.productQuantity}  </td>
                            <td>{item.productPrice}</td>
                            <td>{item.productQuantity*item.productPrice}</td>
                          </tr>
                        
                    );
                  })}
                  </Table>
                  
                </ListGroup.Item>
              </ListGroup>
            </Card>
          );
        })}
      </div>
    </div>
  );
}

export default OrderHistory;
