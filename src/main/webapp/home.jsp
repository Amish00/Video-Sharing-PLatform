<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Home Page</title>
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
          urls: ["./Admin/assets/css/fonts.min.css"],
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
    .carousel-inner img {
  width: 100%;
  height: 50vh; /* Half of the viewport height */
  object-fit: cover;
}

.video-card {
  border-radius: 10px;
  overflow: hidden;
  border: 1px solid #ddd;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}
.video-card img {
  width: 100%;
  height: 180px;
  object-fit: cover;
}
.video-card .card-body {
  padding: 15px;
}
.video-card .category {
  font-size: 14px;
  color: #6c757d;
  margin-bottom: 5px;
}
.video-card .title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}
.video-card .views-date {
  font-size: 14px;
  color: #6c757d;
}
.video-card .profile-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
  position: absolute;
  bottom: 15px;
  right: 15px;
  border: 2px solid #fff;
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
              src="Admin/assets/img/logo-light.svg"
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
              <li class="nav-item active">
                <a href="home">
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
              <li class="nav-item">
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
              <li class="nav-item">
                <a href="uploadServlet">
                  <i class="fas fa-upload"></i>
                  <p>Uploads</p>
                </a>
              </li>
              <li class="nav-item">
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
                  src="assets/img/logo-light.svg"
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
              <nav class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex">
                <div class="input-group">
                  <div class="input-group-prepend">
                    <button type="submit" class="btn btn-search pe-1">
                      <i class="fa fa-search search-icon"></i>
                    </button>
                  </div>
                  <input type="text" placeholder="Search ..." class="form-control" />
                </div>
              </nav>

              <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
                <li class="nav-item topbar-icon dropdown hidden-caret d-flex d-lg-none">
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
                
                  

					<!-- Navbar section in home.jsp -->
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
                <a class="dropdown-item" href="allVideo.jsp">My Balance</a>
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
          <p style = "color:green">${notify}</p>
          
		            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
		              <div class="carousel-inner">
		                  <div class="carousel-item active">
		                      <img src="assets/img/love.jpg" class="d-block w-100" alt="...">
		                      <div class="carousel-caption d-none d-md-block">
		                          <h5>Romantic Escapades</h5>
		                          <p>Immerse yourself in heartwarming love stories that will make you believe in romance again.</p>
		                          <a href="explore" class="btn btn-primary">Watch Now</a>
		                      </div>
		                  </div>
		                  <!-- Add more carousel items as needed -->
		                  <div class="carousel-item">
		                      <img src="assets/img/car1.jpg" class="d-block w-100" alt="...">
		                      <div class="carousel-caption d-none d-md-block">
		                          <h5>High-Speed Thrills</h5>
		                          <p>Get your adrenaline pumping with our collection of high-octane car chases and action-packed scenes.</p>
		                          <a href="explore" class="btn btn-primary">Watch Now</a>
		                      </div>
		                  </div>
		                  <!-- Add more carousel items as needed -->
		                  <div class="carousel-item">
		                      <img src="assets/img/ani1.jpg" class="d-block w-100" alt="...">
		                      <div class="carousel-caption d-none d-md-block">
		                          <h5>Animated Adventures</h5>
		                          <p>Join us on a journey through captivating animated worlds filled with wonder and excitement.</p>
		                          <a href="explore" class="btn btn-primary">Watch Now</a>
		                      </div>
		                  </div>
		              </div>
		              <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
		                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		                  <span class="visually-hidden">Previous</span>
		              </button>
		              <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
		                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
		                  <span class="visually-hidden">Next</span>
		              </button>
		          </div>
		
		            <div class="container-fluid mt-4 flex-grow-1">
		              <div class="container my-3">
		                  <div class="d-flex justify-content-between align-items-center">
		                      <h3 class="fw-bold mb-3">Recommendation</h3>
		                      <a href="explore" class="btn btn-sm btn-outline-primary mb-3">See More ></a>
		                  </div>
		                  <div class="row">
		                  <c:forEach var="video" items="${RecommendedVideos}">
		                      <!-- Repeat the card within the grid -->
		                      <div class="col-md-4">
		                      	<a href="video?vid=${video.vid}" style="text-decoration: none; color: inherit;">
		                      
		                          <div class="card video-card">
		                              <div class="position-relative">
		                              <c:if test="${video.base64Thumbnail != null}">
		                                   <img src="data:image/jpeg;base64,${video.base64Thumbnail}" alt="Video Thumbnail" class="card-img-top">
		                               </c:if>
		                               <c:if test="${video.userProfileBase64 != null}">
		                                   <img src="data:image/jpeg;base64,${video.userProfileBase64}" alt="Profile Image" class="profile-img">
		                                </c:if>   
		                              </div>
		                              <div class="card-body">
		                                  <div class="category">Category:${video.category}</div>
					                        <div class="title">${video.title}</div>
					                        <div class="views-date">${video.views} views � ${video.uploadDate}</div>
					                        <div class="uploader">Uploaded By ${video.uploaderName}</div>
		                              </div>
		                          </div>
		                          </a>
		                      </div>
		          			</c:forEach>
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