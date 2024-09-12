<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Accounts Page</title>
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
              <li class="nav-item ">
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
                        <a href="profile.html" class="btn btn-xs btn-secondary btn-sm">View Profile</a>
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

   <div class="container-fluid mt-2 flex-grow-1">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">Personal Information Form</div>
                    </div>
                    <div class="card-body">
                        <form action="account" method="POST" enctype="multipart/form-data">
                            <div class="row">
                                <!-- Form Section -->
                                <div class="col-md-7 col-lg-5">
                                <p style = "color:green">${notify}</p>
								<p style="color:red;">${error} </p>
                                    <div class="form-group mt-2">
                                        <label for="firstName">First Name</label>
                                        <input type="text" class="form-control" id="firstName" name="firstName"
                                               value="${user.firstName}" placeholder="Enter First Name" required>
                                    </div>
                                    <div class="form-group mt-2">
                                        <label for="lastName">Last Name</label>
                                        <input type="text" class="form-control" id="lastName" name="lastName"
                                               value="${user.lastName}" placeholder="Enter Last Name" required>
                                    </div>
                                    <div class="form-group mt-2">
                                        <label for="phoneNumber">Phone Number</label>
                                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                                               value="${user.phoneNumber}" placeholder="Enter Phone Number">
                                    </div>
                                    <div class="form-group mt-2">
                                        <label for="gender">Gender</label><br>
                                        <div class="d-flex">
                                            <div class="form-check me-3 mt-2">
                                                <input class="form-check-input" type="radio" name="gender" id="male"
                                                       value="Male" ${user.gender == 'Male' ? 'checked' : ''}>
                                                <label class="form-check-label" for="male">Male</label>
                                            </div>
                                            <div class="form-check mt-2">
                                                <input class="form-check-input" type="radio" name="gender" id="female"
                                                       value="Female" ${user.gender == 'Female' ? 'checked' : ''}>
                                                <label class="form-check-label" for="female">Female</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group mt-2">
                                        <label for="email">Email Address</label>
                                        <input type="email" class="form-control" id="email" name="email"
                                               value="${user.email}" readonly>
                                        <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                                    </div>
                                    <div class="form-group mt-2">
                                        <label for="bio">Bio</label>
                                        <textarea class="form-control" id="bio" name="bio" rows="3"
                                                  placeholder="Write a short bio">${user.bio}</textarea>
                                    </div>
                                    <div class="form-group mt-2">
                                        <label for="profilePhoto">Profile Photo</label>
                                        <input type="file" class="form-control" id="profilePhoto" name="profilePhoto"
                                               accept="image/*">
                                    </div>
                                </div>

                                <!-- Picture Section -->
                                <div class="col-md-1 col-lg-5 offset-lg-2 d-flex justify-content-center align-items-center">
                                    <c:choose>
                                        <c:when test="${hasProfilePhoto}">
                                            <img src="data:image/jpeg;base64,${profileImage}" alt="Profile Photo"
                                                 class="img-fluid rounded shadow"
                                                 style="width: 400px; height: 400px; object-fit: cover; border: 1px solid #ddd;">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="<%= request.getContextPath() %>/assets/img/profile.jpg"
                                                 alt="Default Profile"
                                                 class="img-fluid rounded shadow"
                                                 style="width: 400px; height: 400px; object-fit: cover; border: 1px solid #ddd;">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary mt-3">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






          </div>
        </div>

        <footer class="footer">
          <div class="container-fluid d-flex justify-content-between">
            <nav class="pull-left">
              <ul class="nav">
                <li class="nav-item">
                  <a class="nav-link" href="#">
                    Accounts
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#"> Help </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#"> Licenses </a>
                </li>
              </ul>
            </nav>
            <div class="copyright">
              <i class="fa fa-copyright"></i>
              2024 Virishare. All Right reserved.
            </div>
          </div>
        </footer>
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
