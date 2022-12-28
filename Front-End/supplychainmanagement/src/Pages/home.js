import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav'
import Navbar from 'react-bootstrap/Navbar';
import Carousel from 'react-bootstrap/Carousel';

export default function Home()
{
    return (
        <div className='Container'>
            <Navbar id='navbar'>
                <Container>
                    <Navbar.Brand id='navtitle' href="/">S C M</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/supplierlogin" id='navlink'>SUPPLIER</Nav.Link>
                        <Nav.Link href="/retailerlogin" id='navlink'>RETAILER</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            {/* <div className='home-container'>
                
                <h1>Welcome to S C M project</h1><br/>
                <h2>Team Memebers</h2>
                <br/>
                <h4>Kaustubh</h4>
                <h4>Jay</h4>
                <h4>Devendar</h4>
                <h4>Arvinth</h4>
                
                
            </div> */}
            <Carousel fade className='img-container'>
                <Carousel.Item interval={2000}>
                    <h1 id="carousel-text">
                        Team Members
                    </h1>
                </Carousel.Item>
                <Carousel.Item id="carousel-text"  interval={2000}>
                <h1 id="carousel-text">
                        JAY
                    </h1>
                </Carousel.Item>
                <Carousel.Item interval={2000}>
                <h1 id="carousel-text">
                        Kaustubh
                    </h1>
                </Carousel.Item>
                <Carousel.Item interval={2000}>
                <h1 id="carousel-text">
                        Arvinth
                    </h1>
                </Carousel.Item>
                <Carousel.Item interval={2000}>
                <h1 id="carousel-text">
                        Devendar
                    </h1>
                </Carousel.Item>
                </Carousel>
        </div>
    )
}