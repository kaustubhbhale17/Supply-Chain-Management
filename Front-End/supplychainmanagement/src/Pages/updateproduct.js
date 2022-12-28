import { useState } from "react";
import { useLocation } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios from "axios";
import { NotificationManager } from "react-notifications";
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from "react-router-dom";

function UpdateProduct() {
  const location = useLocation();

  const [productId] = useState(location.state.productId);
  const [productName, setProductName] = useState(location.state.productName);
  const [productPrice, setProductPrice] = useState(location.state.productPrice);
  const [supplierId] = useState(location.state.supplierId);
  const [supplierName] = useState(location.state.supplierName);
  const [supplierEmail] = useState(location.state.supplierEmail);
  const [productQuantity, setProductQuantity] = useState(
    location.state.productQuantity
  );
  const navigate = useNavigate();
  const [productType, setProductType] = useState(location.state.productType);
  const handleProductUpdate = (e) => {
    e.preventDefault();
    axios
      .put("http://localhost:3003/inventory/updateProduct/" + productName, {
        productId: productId,
        productName: productName,
        productQuantity: productQuantity,
        productPrice: productPrice,
        productType: productType,
      })
      .then((response) => response.data)
      .then((resp) => {
        if (resp.length === 0) {
          NotificationManager.error(
            "Operation Failed!",
            "Product Details could not be updated",
            2000
          );
        } else {
          NotificationManager.success(
            "Success!",
            "Product Details Updated Successfully!",
            2000
          );
          navigate("/supplier", {
            state: {
              supplierId: supplierId,
              supplierName: supplierName,
              supplierEmail: supplierEmail
            }
          });
        }
      });
  };

  return (
    <div className="Container">
      <Navbar id='navbar'>
        <Container>
          <Navbar.Brand id='navtitle' href="/">S C M</Navbar.Brand>    
          
        </Container>
      </Navbar>
      <div className="Auth-form-container">
        <Form className='Auth-form' onSubmit={(e) => handleProductUpdate(e)}>
          <Form.Group className="mb-3">
            <Form.Label>ProductId</Form.Label>
            <Form.Control
              type="text"
              placeholder="This field is non editable"
              value={productId}
              disabled
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Product Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Input Name"
              value={productName}
              onChange={(e) => setProductName(e.target.value)}
              disabled
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Product Price(Per Unit)</Form.Label>
            <Form.Control
              type="number"
              placeholder="Input updated price"
              value={productPrice}
              onChange={(e) => setProductPrice(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Product Quantity</Form.Label>
            <Form.Control
              type="number"
              placeholder="Input updated quantity"
              value={productQuantity}
              onChange={(e) => setProductQuantity(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Product Type</Form.Label>
            <Form.Control
              type="text"
              placeholder="Input updated type"
              value={productType}
              onChange={(e) => setProductType(e.target.value)}
              disabled
            />
          </Form.Group>
          <Button variant="primary" type="submit" >
            Submit
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default UpdateProduct;
