import { useState } from "react";
import { useLocation,useNavigate } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import { Nav } from "react-bootstrap"
import Navbar from 'react-bootstrap/Navbar';
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios  from "axios";
import { NotificationManager } from "react-notifications";


function AddProduct(){

    const location = useLocation();
    const navigate = useNavigate();

    const[supplierId]=useState(location.state.supplierId);
    const[supplierName]=useState(location.state.supplierName);
    const[supplierEmail]=useState(location.state.supplierEmail);

    const[productName,setProductName]=useState("");
    const[productPrice,setProductPrice]=useState("");
    const[productQuantity,setProductQuantity]=useState("");
    const[productType,setProductType]=useState("");

    const navigateSupplier=()=>{
        navigate("/supplier",{
            state:{
                supplierId: supplierId,
                supplierName:supplierName,
                supplierEmail: supplierEmail
            }
        })
    }

    const handleProductAdd=(e)=>{
        e.preventDefault();
        axios.post("http://localhost:3003/inventory/add",{
            productName: productName,
            productPrice: productPrice,
            productQuantity:productQuantity,
            productType:productType
        }).then(response=>response.data).then(response=>{
            if(response.length===0){
                NotificationManager.error("The product already exists!","Error!!",4000);
                navigateSupplier();
            }else{
                NotificationManager.success("Product Added!","Success!!",4000);
                navigateSupplier();
            }
        })
    }

    return(
        <div className="Container">
        <Navbar id='navbar'>
          <Container>
            <Navbar.Brand id='navtitle' href="/">S C M</Navbar.Brand>    
            
          </Container>
          <Nav className="me-auto">
          <Nav.Link id="navlink" onClick={() => navigate("/supplier", {
            state: {
              supplierId: supplierId,
              supplierName: supplierName,
            supplierEmail:supplierEmail
                    }
          })}>Go Back</Nav.Link>
          </Nav>
        </Navbar>
        <div className="Auth-form-container">
          <Form className='Auth-form' onSubmit={(e) => handleProductAdd(e)}>
  
            <Form.Group className="mb-3">
              <Form.Label>Product Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter Product Name"
                onChange={(e) => setProductName(e.target.value)}
                required
              />
            </Form.Group>
  
            <Form.Group className="mb-3">
              <Form.Label>Product Price(Per Unit)</Form.Label>
              <Form.Control
                type="number"
                placeholder="Input Price Per Unit"
                onChange={(e) => setProductPrice(e.target.value)}
                required
              />
            </Form.Group>
  
            <Form.Group className="mb-3">
              <Form.Label>Product Quantity</Form.Label>
              <Form.Control
                type="number"
                placeholder="Input Product Quantity"
                onChange={(e) => setProductQuantity(e.target.value)}
                required
              />
            </Form.Group>
  
            <Form.Group className="mb-3">
              <Form.Label>Product Type</Form.Label>
              <Form.Control
                type="text"
                placeholder="Input Product Type"
                onChange={(e) => setProductType(e.target.value)}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit" >
              Submit
            </Button>
          </Form>
        </div>
      </div>
    )
}

export default AddProduct;