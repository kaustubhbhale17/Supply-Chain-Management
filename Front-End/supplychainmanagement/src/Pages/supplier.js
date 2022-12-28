import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";
import { useLocation, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import axios from "axios";

import DataTable from "react-data-table-component";
import "bootstrap-icons/font/bootstrap-icons.css";
import Swal from "sweetalert2";

function Supplier() {
  //view supplier profile
  const location = useLocation();
  const [supplierId] = useState(location.state.supplierId);
  console.log(supplierId)
  const [supplierName] = useState(location.state.supplierName);
  const [supplierEmail] = useState(location.state.supplierEmail);

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  //data table fields
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");
  const [filterProducts, setFilterProducts] = useState([]);

  async function getInventory() {
    try {
      const url = "http://localhost:3003/inventory/findall";
      const response = await axios.get(url).then((resp) => resp.data);
      setProducts(response);
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    getInventory();
  }, []);

  //filter the data
  useEffect(() => {
    const result = products.filter((product) => {
      return product.productName.toLowerCase().match(search.toLowerCase());
    });

    setFilterProducts(result);
  }, [search, products]);

  const navigate = useNavigate();

  //navigate to update product page
  const updateProduct = (e, product) => {
    e.preventDefault();
    navigate("/updateProduct", {
      state: {
        productId: product.productId,
        productName: product.productName,
        productType: product.productType,
        productQuantity: product.productQuantity,
        productPrice: product.productPrice,
        supplierId: supplierId,
      supplierName: supplierName,
      supplierEmail: supplierEmail
      },
    });
  };



  
  const deleteProduct = (e, productName) => {
    e.preventDefault();
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.isConfirmed) {
        axios
          .delete(
            "http://localhost:3003/inventory/deleteProduct/" + productName
          )
          .then((response) => console.log(response));
        Swal.fire("Deleted!", "Your file has been deleted.", "success");
        
      }
      window.location.reload(false);  
    }
      
    );
     
  };

  const columns = [
    // {
    //   name: "ProductId",
    //   selector: (row) => row.productId,
    //   width: "300px",
    // },
    {
      name: "ProductName",
      selector: (row) => row.productName,
      sortable: true,
    },
    {
      name: "Type",
      selector: (row) => row.productType,
      sortable: true,
    },
    {
      name: "Quantity",
      selector: (row) => row.productQuantity,
      sortable: true,
    },
    {
      name: "Price(Per Unit)",
      selector: (row) => row.productPrice,
      sortable: true,
    },
    {
      name: "Update product",
      selector: (row) => (
        <i class="bi bi-pencil-fill" onClick={(e) => updateProduct(e, row)}></i>
      ),
    },
    {
      name: "Delete Product",
      selector: (row) => (
        <i
          class="bi bi-trash3-fill"
          onClick={(e) => deleteProduct(e, row.productName)}
        ></i>
      ),
    },
  ];

  const navigateOrder = () => {
    navigate("/orders", {
      state: {
        supplierId: supplierId,
      supplierName: supplierName,
      supplierEmail: supplierEmail
      },
    });
  };

  const navigateAddProduct=()=>{
    navigate("/addproduct",{
      state:{
        supplierId: supplierId,
      supplierName: supplierName,
      supplierEmail: supplierEmail
      },
    });
  }
  
  return (
    <div className="dashboard-Container">
      
      <Navbar id='navbar'>
          <Container>
                <Navbar.Brand id='navtitle' href="/">S C M</Navbar.Brand>
                <Navbar.Toggle />

                <Nav className="me-auto">
            <Nav.Link onClick={navigateOrder} id="navlink">Orders</Nav.Link>
              </Nav>
                  <Navbar.Collapse className="justify-content-end">
            <Nav className="me-auto">
            <Nav.Link onClick={navigateAddProduct} id="navlink">Add Product</Nav.Link>
              </Nav>
                

            <Nav id="navmargin">
              
              <NavDropdown title="Profile" id="collasible-nav-dropdown">
                <NavDropdown.Item onClick={handleShow}>
                  View My Profile
                </NavDropdown.Item>
                </NavDropdown>
                
              <Nav></Nav>
              <Nav.Link href="/supplierlogin">Logout</Nav.Link>
            </Nav>
          </Navbar.Collapse>
          </Container>
      </Navbar>
      

      <DataTable
        title="Inventory"
        columns={columns}
        data={filterProducts}
        className="data-table"
        pagination
        fixedHeader
        highlightOnHover
        subHeader
        subHeaderComponent={
          <input
            type="text"
            placeholder="Enter the product to be searched"
            value={search}
            className="form-control"
            onChange={(e) => setSearch(e.target.value)}
          />
        }
      />

      {/* view Modal */}
      <Modal show={show} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>
            <center>Profile Summary</center>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Id: {supplierId}
          <br />
          Name : {supplierName}
          <br />
          Email : {supplierEmail}
          <br />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Supplier;
