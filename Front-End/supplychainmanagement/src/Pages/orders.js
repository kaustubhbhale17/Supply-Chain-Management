import React,{ useEffect, useState } from "react";
import axios from "axios";
import DataTable from "react-data-table-component";
import "bootstrap-icons/font/bootstrap-icons.css";
import Swal from "sweetalert2";
import NotificationManager from "react-notifications/lib/NotificationManager";
import {useNavigate} from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { Nav } from "react-bootstrap"
import { useLocation } from "react-router-dom";

function Orders()
{
  const location = useLocation();
  console.log(location.state.supplierId,location.state.supplierName)
  const [supplierId] = useState(location.state.supplierId);
  const [supplierName] = useState(location.state.supplierName);
  const [supplierEmail] = useState(location.state.supplierEmail);
  const [orders, setOrders] = useState([]);
  const[search,setSearch]=useState('');
  const[filterOrder,setFilterOrders]=useState([]);
  const navigate = useNavigate();

  const items=[];

  async function getOrders() {
    const data = await axios
      .get("http://localhost:3004/order/findallorders/")
      .then((response) => response.data);
    setOrders(data);
    console.log(data);
  }

  useEffect(() => {
    getOrders();
  }, []);

  //filter orders based on reatiler name
  useEffect(() => {
    const result = orders.filter((order) => {
      return order.retailerName.toLowerCase().match(search.toLowerCase());
    });

    setFilterOrders(result);
  }, [search, orders]);

  
  //display the cart items
  const showCartItems=(e,cartItems)=>{
    e.preventDefault();
    for(let p of cartItems){
      items.push(p);
    }
    console.log(items);
    let cart = "";

    for(let p of items){
      cart=cart+p.productName+"-"+p.productQuantity+"\n";
    }
    
    alert(cart)
    //toast.info({cart});
    //NotificationManager.info(cart);
    cart="";
    items.length=0;
  }


  
  //reject Order
  const rejectOrder=async(e,orderId)=>{
    e.preventDefault();
    const order = await axios.get("http://localhost:3004/order/getorderbyid/" + orderId).then((response) => response.data)
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, Reject it!",
    }).then((result) => {
      if (result.isConfirmed) {
        axios
          .put(
            "http://localhost:3004/order/update/" + orderId, {
              orderNumber: order.orderNumber,
              retailerName: order.retailerName,
              orderDescription:order.orderDescription,
              cartItems:order.cartItems,
              orderStatus:"Rejected!",
              orderBill:0
            }
            
          )
          .then((response) => console.log(response));
        Swal.fire("Rejected!", "Your order has been rejected.", "success");
         
      }
      window.location.reload(false);  
    });
     
  }

  //accept order
  const acceptOrder=async(e,orderId)=>{
    e.preventDefault();
    await axios.get("http://localhost:3003/inventory/acceptRejectOrder/"+orderId)
    .then(response=>response.data)
    
    NotificationManager.success("Order Status updated !"," Refersh the page to check status",2000);

    window.location.reload(false); 
  }

  const columns = [
    {
      name: "RetailerName",
      selector: (row) => row.retailerName,
      width: "150px",
      sortable: true
    },
    {
      name: "Order Description",
      selector: (row) => row.orderDescription,
      width: "300px",
      sortable: true
    },
    {
      name:"Requested Items",
      selector:(row)=><i className="bi bi-cart-check-fill" onClick={e=>showCartItems(e,row.cartItems)}></i>,
      width:"160px"

    },
    {
      name: "Accept Order",
      selector:(row)=><i className="bi bi-check-square-fill" onClick={e=>acceptOrder(e,row.orderNumber)}></i>,
      width:"150px",
    },
    {
      name: "Reject Order",
      selector:(row)=><i className="bi bi-x-square-fill" onClick={e=>rejectOrder(e,row.orderNumber)}></i>,
      width:"150px",
    },
    {
      name: "Order Status",
      selector: (row) => row.orderStatus,
      width: "450px",
      sortable: true
    },
    {
      name: "Order Bill",
      selector: (row) => row.orderBill,
      sortable: true
    },

  ];


  return (
    <div className="Container">
      <Navbar id='navbar'>
        <Container>
          <Navbar.Brand id='navtitle' href="/">S C M</Navbar.Brand>
          <Nav className="me-auto">
          <Nav.Link id="navlink" onClick={() => navigate("/supplier", {
            state: {
              supplierId: supplierId,
              supplierName: supplierName,
            supplierEmail:supplierEmail
                    }
          })}>Go Back</Nav.Link>
          </Nav>
           
        </Container>
      </Navbar>
      <DataTable
        title="Orders"
        columns={columns}
        data={filterOrder}
        className="data-table"
        pagination
        fixedHeader
        highlightOnHover
        subHeader
        subHeaderComponent={
          <input
            type="text"
            placeholder="Enter the Retailer Name"
            value={search}
            className="form-control"
            onChange={(e) => setSearch(e.target.value)}/>}/>
    </div>
  );
}

export default Orders;
