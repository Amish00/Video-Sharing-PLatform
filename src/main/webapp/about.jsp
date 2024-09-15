<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>About Page</title>
    <meta
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
      name="viewport"
    />
    <link
      rel="icon"
      href="assets/img/favicon.ico"
      type="image/x-icon"
    />

    <!-- Fonts and icons -->
    <script src="assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
      WebFont.load({
        google: { families: ["Public Sans:300,400,500,600,700"] },
        custom: {
          families: [
            "Font Awesome 5 Solid",
            "Font Awesome 5 Regular",
            "Font Awesome 5 Brands",
            "simple-line-icons",
          ],
          urls: ["assets/css/fonts.min.css"],
        },
        active: function () {
          sessionStorage.fonts = true;
        },
      });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/css/plugins.min.css" />
    <link rel="stylesheet" href="assets/css/kaiadmin.min.css" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="assets/css/demo.css" />
    <style>
        .card-title, .card-text {
            text-shadow: none; /* Remove text shadow */
        }
        
        .bg-gray {
    background-color: #f5f5f5; /* Light gray */
}
        .about-section {
    background-image: url('about.jpg'); /* Replace with your image URL */
    background-size: cover;
    background-position: center;
    position: relative;
    height: 25vh; /* Adjust height as needed */
    display: flex;
    align-items: center;
    justify-content: center;
}

.overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent overlay for better text visibility */
    z-index: 1;
}

.about-section .container {
    position: relative;
    z-index: 2;
}

    </style>
  </head>
  <body>
    <div class="wrapper">
      <!-- Sidebar -->
      <div class="sidebar" data-background-color="dark">
        <div class="sidebar-logo">
          <!-- Logo Header -->
          <div class="logo-header" data-background-color="dark">
            <a href="home" class="logo">
              <img
                src="assets/img/logo-light.svg"
                alt="navbar brand"
                class="navbar-brand"
                height="35"
              />
            </a>
            <div class="nav-toggle">
              <button class="btn btn-toggle toggle-sidebar">
                <i class="gg-menu-right"></i>
              </button>
              <button class="btn btn-toggle sidenav-toggler">
                <i class="gg-menu-left"></i>
              </button>
            </div>
            <button class="topbar-toggler more">
              <i class="gg-more-vertical-alt"></i>
            </button>
          </div>
          <!-- End Logo Header -->
        </div>
        <div class="sidebar-wrapper scrollbar scrollbar-inner">
          <div class="sidebar-content">
            <ul class="nav nav-secondary">
              <li class="nav-item ">
                <a  href="home" >
                  <i class="fas fa-home"></i>
                  <p>Home</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="explore">
                    <i class="fas fa-globe"></i>
                  <p>Explore</p>
                </a>
              </li>
              <li class="nav-item ">
                <a href="myvideos">
                  <i class="fas fa-video"></i>
                  <p>My Videos</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="videoHistory">
                  <i class="fas fa-history"></i>
                  <p>History</p>
                </a>
              </li>
              <li class="nav-item ">
                <a href="uploadServlet">
                  <i class="fas fa-upload"></i>
                  <p>Uploads</p>
                </a>
              </li>
              <li class="nav-item active">
                <a href="about">
                  <i class="fas fa-address-card"></i>
                  <p>About</p>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- End Sidebar -->

      <div class="main-panel">
        <div class="main-header">
          <div class="main-header-logo">
            <!-- Logo Header -->
            <div class="logo-header" data-background-color="dark">
              <a href="home" class="logo">
                <img
                  src="assets/img/lo"
                  alt="navbar brand"
                  class="navbar-brand"
                  height="20"
                />
              </a>
              <div class="nav-toggle">
                <button class="btn btn-toggle toggle-sidebar">
                  <i class="gg-menu-right"></i>
                </button>
                <button class="btn btn-toggle sidenav-toggler">
                  <i class="gg-menu-left"></i>
                </button>
              </div>
              <button class="topbar-toggler more">
                <i class="gg-more-vertical-alt"></i>
              </button>
            </div>
            <!-- End Logo Header -->
          </div>
          <!-- Navbar Header -->
          <nav
            class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom"
          >
            <div class="container-fluid">
              <nav
                class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex"
              >
                <div class="input-group">
                  <div class="input-group-prepend">
                    <button type="submit" class="btn btn-search pe-1">
                      <i class="fa fa-search search-icon"></i>
                    </button>
                  </div>
                  <input
                    type="text"
                    placeholder="Search ..."
                    class="form-control"
                  />
                </div>
              </nav>

              <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
                <li
                  class="nav-item topbar-icon dropdown hidden-caret d-flex d-lg-none"
                >
                  <a
                    class="nav-link dropdown-toggle"
                    data-bs-toggle="dropdown"
                    href="#"
                    role="button"
                    aria-expanded="false"
                    aria-haspopup="true"
                  >
                    <i class="fa fa-search"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-search animated fadeIn">
                    <form class="navbar-left navbar-form nav-search">
                      <div class="input-group">
                        <input
                          type="text"
                          placeholder="Search ..."
                          class="form-control"
                        />
                      </div>
                    </form>
                  </ul>
                </li>
                
                  

               <li class="nav-item topbar-user dropdown hidden-caret">
    <a class="dropdown-toggle profile-pic" data-bs-toggle="dropdown" href="#" aria-expanded="false">
        <div class="avatar-sm">
            <!-- Check if user profile photo exists -->
            <c:if test="${not empty loggedInUser.base64ProfilePhoto}">
                <img src="data:image/jpeg;base64,${loggedInUser.base64ProfilePhoto}" alt="User Profile" class="avatar-img rounded-circle" />
            </c:if>
            <!-- Default profile image if no photo exists -->
            <c:if test="${empty loggedInUser.base64ProfilePhoto}">
                <img src="assets/img/profile.jpg" alt="Default Profile" class="avatar-img rounded-circle" />
            </c:if>
        </div>
        <span class="profile-username">
            <span class="op-7">Hi,</span>
            <span class="fw-bold">
                <c:choose>
                    <c:when test="${empty loggedInUser.firstName and empty loggedInUser.lastName}">
                        <c:out value="User" />
                    </c:when>
                    <c:otherwise>
                        <c:out value="${loggedInUser.firstName} ${loggedInUser.lastName}" />
                    </c:otherwise>
                </c:choose>
            </span>
        </span>
    </a>
    <ul class="dropdown-menu dropdown-user animated fadeIn">
        <div class="dropdown-user-scroll scrollbar-outer">
            <li>
                <div class="user-box">
                    <div class="avatar-lg">
                        <c:if test="${not empty loggedInUser.base64ProfilePhoto}">
                            <img src="data:image/jpeg;base64,${loggedInUser.base64ProfilePhoto}" alt="Profile Image" class="avatar-img rounded" />
                        </c:if>
                        <c:if test="${empty loggedInUser.base64ProfilePhoto}">
                            <img src="assets/img/profile.jpg" alt="Default Profile" class="avatar-img rounded" />
                        </c:if>
                    </div>
                    <div class="u-text">
                        <h4>
                        <c:choose>
                                <c:when test="${empty loggedInUser.firstName and empty loggedInUser.lastName}">
                                    <c:out value="User" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${loggedInUser.firstName} ${loggedInUser.lastName}" />
                                </c:otherwise>
                            </c:choose>
                        </h4>
                        <p class="text-muted"><c:out value="${loggedInUser.email}" /></p>
                        <a href="account" class="btn btn-xs btn-secondary btn-sm">View Profile</a>
                    </div>
                </div>
            </li>
            <li>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="account">My Profile</a>
                <a class="dropdown-item" href="#">My Balance</a>
                <a class="dropdown-item" href="#">Inbox</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="logout">Logout</a>
            </li>
        </div>
    </ul>
</li>
              </ul>
            </div>
          </nav>
          <!-- End Navbar -->
        </div>

        <div class="container">
          <div class="page-inner">

            <section class="text-center py-5 position-relative text-black about-section bg-gray shadow">
			    <div class="overlay"></div>
			    <div class="container position-relative">
			        <h3>About Us</h3>
			        <p>We are the ultimate digital network for all things movies, serving more than 60 million unique visitors per month.</p>
			        <a href="explore" class="btn btn-primary">Explore Now</a>
			    </div>
			</section>

          
                <!-- Why You'll Love Us Section -->
                <section class="why-love-us py-5">
                  <div class="container">
                      <h3 class="text-center mb-4">Why You'll Love Us</h3>
                      <div class="row">
                          <div class="col-md-4 mb-4">
                              <div class="card text-center shadow h-100">
                                  <div class="card-body">
                                      <i class="fas fa-tags fa-3x mb-3"></i>
                                      <h5 class="card-title">The Best Deals</h5>
                                      <p class="card-text">Many exclusive discounts including personalized offers and Mix & Match bundles.</p>
                                  </div>
                              </div>
                          </div>
                          <div class="col-md-4 mb-4">
                              <div class="card text-center shadow h-100">
                                  <div class="card-body">
                                      <i class="fas fa-tv fa-3x mb-3"></i>
                                      <h5 class="card-title">More Content On More Devices</h5>
                                      <p class="card-text">Even more movies & TV shows to choose from, on all your favorite viewing devices.</p>
                                  </div>
                              </div>
                          </div>
                          <div class="col-md-4 mb-4">
                              <div class="card text-center shadow h-100">
                                  <div class="card-body">
                                      <i class="fas fa-video fa-3x mb-3"></i>
                                      <h5 class="card-title">Largest Selection Of 4K UHD Content</h5>
                                      <p class="card-text">So many 4K UHD titles to choose from, with options including Dolby Vision®, Dolby Atmos®, and HDR10.</p>
                                  </div>
                              </div>
                          </div>
                          <div class="col-md-4 mb-4">
                              <div class="card text-center shadow h-100">
                                  <div class="card-body">
                                      <i class="fas fa-box fa-3x mb-3"></i>
                                      <h5 class="card-title">Everything Your Digital Library Needs</h5>
                                      <p class="card-text">Many exclusive discounts including personalized offers and Mix & Match bundles.</p>
                                  </div>
                              </div>
                          </div>
                          <div class="col-md-4 mb-4">
                              <div class="card text-center shadow h-100">
                                  <div class="card-body">
                                      <i class="fas fa-user-friends fa-3x mb-3"></i>
                                      <h5 class="card-title">Family-friendly Features</h5>
                                      <p class="card-text">Many exclusive discounts including personalized offers and Mix & Match bundles.</p>
                                  </div>
                              </div>
                          </div>
                          <div class="col-md-4 mb-4">
                              <div class="card text-center shadow h-100">
                                  <div class="card-body">
                                      <i class="fas fa-star fa-3x mb-3"></i>
                                      <h5 class="card-title">Be Entertained, Be Enriched</h5>
                                      <p class="card-text">Many exclusive discounts including personalized offers and Mix & Match bundles.</p>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                </section>

                <section class="contact-section py-5">
                  <div class="container shadow-lg rounded p-4">
                    <div class="row">
                      <!-- Contact Info -->
                      <div class="col-md-6 contact-info">
                        <h3>Contact Info</h3>
                        <p>Review the contact options below to ensure we get your information to the right member of our team.</p>
                        <div class="contact-details">
                          <h5>Head Office</h5>
                          <p>Patan Dhoka, Lalitpur, Nepal</p>
                
                          <h5>Support</h5>
                          <p>support@virinchicollege.edu.np</p>
                
                          <h5>Press Inquiries</h5>
                          <p>pr@virinchicollege.edu.np</p>
                
                          <h5>Call Us</h5>
                          <p>Support line: +977-1-5260911</p>
                
                          <h5>Email Us</h5>
                          <p>hello@virinchicollege.edu.np</p>
                
                          <h5>Advertising</h5>
                          <p>ad_proposal@virinchicollege.edu.np</p>
                        </div>
                      </div>
                
                      <!-- Google Map Section -->
                      <div class="col-md-6 p-0">
                        <iframe
                          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3533.442888146253!2d85.3185455!3d27.672703300000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb19cb05097d61%3A0x66d083a187176a11!2sVIRINCHI%20COLLEGE!5e0!3m2!1sen!2snp!4v1723003492217!5m2!1sen!2snp"
                          width="100%"
                          height="400"
                          frameborder="0"
                          style="border:0;"
                          allowfullscreen=""
                          aria-hidden="false"
                          tabindex="0"
                        ></iframe>
                      </div>
                    </div>
                  </div>
                </section>
                
                
                  </div>
              </div>
          </div>


          </div>
        </div>

        
      </div>
      <!-- End Custom template -->
    </div>
    <!--   Core JS Files   -->
    <script src="assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="assets/js/core/popper.min.js"></script>
    <script src="assets/js/core/bootstrap.min.js"></script>

    <!-- jQuery Scrollbar -->
    <script src="assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
   

    <!-- Kaiadmin JS -->
    <script src="assets/js/kaiadmin.min.js"></script>

    <!-- Kaiadmin DEMO methods, don't include it in your project! -->
    <script src="assets/js/setting-demo.js"></script>
    <script src="assets/js/demo.js"></script>
  </body>
</html>
