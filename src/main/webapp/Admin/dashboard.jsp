<%@page import="com.Model.User"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Admin Dashboard</title>
    <meta
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
      name="viewport"
    />
    <link rel="icon" href="Admin/assets/img/favicon.ico" type="image/x-icon" />

    <!-- Fonts and icons -->
    <script src="Admin/assets/js/plugin/webfont/webfont.min.js"></script>
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
          urls: ["Admin/assets/css/fonts.min.css"],
        },
        active: function () {
          sessionStorage.fonts = true;
        },
      });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="Admin/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="Admin/assets/css/plugins.min.css" />
    <link rel="stylesheet" href="Admin/assets/css/kaiadmin.min.css" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="Admin//assets/css/demo.css" />
  </head>
 <body>
    <div class="wrapper">
      <!-- Sidebar -->
      <div class="sidebar" data-background-color="dark">
        <div class="sidebar-logo">
          <!-- Logo Header -->
          <div class="logo-header" data-background-color="dark">
            <a href="dash" class="logo">
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
                <a href="dash">
                  <i class="fas fa-home"></i>
                  <p>Dashboard</p>
                </a>
              </li>
              <li class="nav-item">
                <a data-bs-toggle="collapse" href="#tables">
                  <i class="fas fa-table"></i>
                  <p>Tables</p>
                  <span class="caret"></span>
                </a>
                <div class="collapse" id="tables">
                  <ul class="nav nav-collapse">
                    <li>
                      <a href="usertable">
                        <span class="sub-item">User Table</span>
                      </a>
                    </li>
                    <li>
                      <a href="approval">
                        <span class="sub-item">Approval Table</span>
                      </a>
                    </li>
                    <li>
                      <a href="videotable">
                        <span class="sub-item">Video Table</span>
                      </a>
                    </li>
                    <li>
                      <a href="history">
                        <span class="sub-item">History Table</span>
                      </a>
                    </li>
                  </ul>
                </div>
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
              <a href="index.html" class="logo">
                <img
                  src="Admin/assets/img/logo-light.svg"
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
                <li class="nav-item topbar-icon dropdown hidden-caret">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="messageDropdown"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <i class="fa fa-envelope"></i>
                  </a>
                  <ul
                    class="dropdown-menu messages-notif-box animated fadeIn"
                    aria-labelledby="messageDropdown"
                  >
                    <li>
                      <div
                        class="dropdown-title d-flex justify-content-between align-items-center"
                      >
                        Messages
                        <a href="#" class="small">Mark all as read</a>
                      </div>
                    </li>
                    <li>
                      <div class="message-notif-scroll scrollbar-outer">
                        <div class="notif-center">
                          <a href="#">
                            <div class="notif-img">
                              <img
                                src="Admin/assets/img/jm_denis.jpg"
                                alt="Img Profile"
                              />
                            </div>
                            <div class="notif-content">
                              <span class="subject">Jimmy Denis</span>
                              <span class="block"> How are you ? </span>
                              <span class="time">5 minutes ago</span>
                            </div>
                          </a>
                          <a href="#">
                            <div class="notif-img">
                              <img
                                src="Admin/assets/img/chadengle.jpg"
                                alt="Img Profile"
                              />
                            </div>
                            <div class="notif-content">
                              <span class="subject">Chad</span>
                              <span class="block"> Ok, Thanks ! </span>
                              <span class="time">12 minutes ago</span>
                            </div>
                          </a>
                          <a href="#">
                            <div class="notif-img">
                              <img
                                src="Admin/assets/img/mlane.jpg"
                                alt="Img Profile"
                              />
                            </div>
                            <div class="notif-content">
                              <span class="subject">Jhon Doe</span>
                              <span class="block">
                                Ready for the meeting today...
                              </span>
                              <span class="time">12 minutes ago</span>
                            </div>
                          </a>
                          <a href="#">
                            <div class="notif-img">
                              <img
                                src="Admin/assets/img/talha.jpg"
                                alt="Img Profile"
                              />
                            </div>
                            <div class="notif-content">
                              <span class="subject">Talha</span>
                              <span class="block"> Hi, Apa Kabar ? </span>
                              <span class="time">17 minutes ago</span>
                            </div>
                          </a>
                        </div>
                      </div>
                    </li>
                    <li>
                      <a class="see-all" href="javascript:void(0);"
                        >See all messages<i class="fa fa-angle-right"></i>
                      </a>
                    </li>
                  </ul>
                </li>
                <li class="nav-item topbar-icon dropdown hidden-caret">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="notifDropdown"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <i class="fa fa-bell"></i>
                    <span class="notification">4</span>
                  </a>
                  <ul
                    class="dropdown-menu notif-box animated fadeIn"
                    aria-labelledby="notifDropdown"
                  >
                    <li>
                      <div class="dropdown-title">
                        You have 4 new notification
                      </div>
                    </li>
                    <li>
                      <div class="notif-scroll scrollbar-outer">
                        <div class="notif-center">
                          <a href="#">
                            <div class="notif-icon notif-primary">
                              <i class="fa fa-user-plus"></i>
                            </div>
                            <div class="notif-content">
                              <span class="block"> New user registered </span>
                              <span class="time">5 minutes ago</span>
                            </div>
                          </a>
                          <a href="#">
                            <div class="notif-icon notif-success">
                              <i class="fa fa-comment"></i>
                            </div>
                            <div class="notif-content">
                              <span class="block">
                                Rahmad commented on Admin
                              </span>
                              <span class="time">12 minutes ago</span>
                            </div>
                          </a>
                          <a href="#">
                            <div class="notif-img">
                              <img
                                src="Admin/assets/img/profile2.jpg"
                                alt="Img Profile"
                              />
                            </div>
                            <div class="notif-content">
                              <span class="block">
                                Reza send messages to you
                              </span>
                              <span class="time">12 minutes ago</span>
                            </div>
                          </a>
                          <a href="#">
                            <div class="notif-icon notif-danger">
                              <i class="fa fa-heart"></i>
                            </div>
                            <div class="notif-content">
                              <span class="block"> Farrah liked Admin </span>
                              <span class="time">17 minutes ago</span>
                            </div>
                          </a>
                        </div>
                      </div>
                    </li>
                    <li>
                      <a class="see-all" href="javascript:void(0);"
                        >See all notifications<i class="fa fa-angle-right"></i>
                      </a>
                    </li>
                  </ul>
                </li>
                <li class="nav-item topbar-icon dropdown hidden-caret">
                  <a
                    class="nav-link"
                    data-bs-toggle="dropdown"
                    href="#"
                    aria-expanded="false"
                  >
                    <i class="fas fa-layer-group"></i>
                  </a>
                  <div class="dropdown-menu quick-actions animated fadeIn">
                    <div class="quick-actions-header">
                      <span class="title mb-1">Quick Actions</span>
                      <span class="subtitle op-7">Shortcuts</span>
                    </div>
                    <div class="quick-actions-scroll scrollbar-outer">
                      <div class="quick-actions-items">
                        <div class="row m-0">
                          <a class="col-6 col-md-4 p-0" href="#">
                            <div class="quick-actions-item">
                              <div class="avatar-item bg-danger rounded-circle">
                                <i class="far fa-calendar-alt"></i>
                              </div>
                              <span class="text">Calendar</span>
                            </div>
                          </a>
                          <a class="col-6 col-md-4 p-0" href="#">
                            <div class="quick-actions-item">
                              <div
                                class="avatar-item bg-warning rounded-circle"
                              >
                                <i class="fas fa-map"></i>
                              </div>
                              <span class="text">Maps</span>
                            </div>
                          </a>
                          <a class="col-6 col-md-4 p-0" href="#">
                            <div class="quick-actions-item">
                              <div class="avatar-item bg-info rounded-circle">
                                <i class="fas fa-file-excel"></i>
                              </div>
                              <span class="text">Reports</span>
                            </div>
                          </a>
                          <a class="col-6 col-md-4 p-0" href="#">
                            <div class="quick-actions-item">
                              <div
                                class="avatar-item bg-success rounded-circle"
                              >
                                <i class="fas fa-envelope"></i>
                              </div>
                              <span class="text">Emails</span>
                            </div>
                          </a>
                          <a class="col-6 col-md-4 p-0" href="#">
                            <div class="quick-actions-item">
                              <div
                                class="avatar-item bg-primary rounded-circle"
                              >
                                <i class="fas fa-file-invoice-dollar"></i>
                              </div>
                              <span class="text">Invoice</span>
                            </div>
                          </a>
                          <a class="col-6 col-md-4 p-0" href="#">
                            <div class="quick-actions-item">
                              <div
                                class="avatar-item bg-secondary rounded-circle"
                              >
                                <i class="fas fa-credit-card"></i>
                              </div>
                              <span class="text">Payments</span>
                            </div>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </li>

                <li class="nav-item topbar-user dropdown hidden-caret">
                  <a
                    class="dropdown-toggle profile-pic"
                    data-bs-toggle="dropdown"
                    href="#"
                    aria-expanded="false"
                  >
                    <div class="avatar-sm">
                       <c:if test="${not empty loggedInUser.base64ProfilePhoto}">
                <img src="data:image/jpeg;base64,${loggedInUser.base64ProfilePhoto}" alt="User Profile" class="avatar-img rounded-circle" />
		            </c:if>
		            <!-- Default profile image if no photo exists -->
		            <c:if test="${empty loggedInUser.base64ProfilePhoto}">
		                <img src="Admin/assets/img/profile.jpg" alt="Default Profile" class="avatar-img rounded-circle" />
		            </c:if>
                    </div>
                    <span class="profile-username">
                      <span class="op-7">Hi,</span>
                      <span class="fw-bold"><c:choose>
                    <c:when test="${empty loggedInUser.firstName and empty loggedInUser.lastName}">
                        <c:out value="User" />
                    </c:when>
                    <c:otherwise>
                        <c:out value="${loggedInUser.firstName}" />
                    </c:otherwise>
                </c:choose></span>
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
                            <h4><c:choose>
                                <c:when test="${empty loggedInUser.firstName and empty loggedInUser.lastName}">
                                    <c:out value="Admin" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${loggedInUser.firstName} ${loggedInUser.lastName}" />
                                </c:otherwise>
                            </c:choose></h4>
                            <p class="text-muted"><c:out value="${loggedInUser.email}" /></p>
                            <a
                              href="adminaccount"
                              class="btn btn-xs btn-secondary btn-sm"
                              >View Profile</a
                            >
                          </div>
                        </div>
                      </li>
                      <li>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="adminaccount">My Profile</a>
                        <a class="dropdown-item" href="#">My Balance</a>
                        <a class="dropdown-item" href="chartData">Inbox</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Account Setting</a>
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
		        <div class="row">
		            <!-- Cards for Total Users, Total Approved Videos, Total Pending Approval Videos -->
		            <div class="col-md-4">
		                <div class="card card-round">
		                    <div class="card-header">
		                        <div class="card-title">Total Users</div>
		                    </div>
		                    <div class="card-body">
		                        <h2 class="text-center">${totalUsers}</h2> <!-- Add total user count here -->
		                    </div>
		                </div>
		            </div>
		            <div class="col-md-4">
		                <div class="card card-round">
		                    <div class="card-header">
		                        <div class="card-title">Total Approved Videos</div>
		                    </div>
		                    <div class="card-body">
		                        <h2 class="text-center">${totalApprovedVideos}</h2> <!-- Add total approved videos count here -->
		                    </div>
		                </div>
		            </div>
		            <div class="col-md-4">
		                <div class="card card-round">
		                    <div class="card-header">
		                        <div class="card-title">Total Pending Approval Videos</div>
		                    </div>
		                    <div class="card-body">
		                        <h2 class="text-center">${totalPendingVideos}</h2> <!-- Add total pending videos count here -->
		                    </div>
		                </div>
		            </div>
		        </div>
		
		        <div class="row">
		            <div class="col-md-12">
		                <div class="card card-round">
		                    <div class="card-header">
		                        <div class="card-head-row">
		                            <div class="card-title">User Statistics</div>
		                            <div class="card-tools">
		                                <a href="#" class="btn btn-label-success btn-round btn-sm me-2">
		                                    <span class="btn-label"><i class="fa fa-pencil"></i></span>
		                                    Export
		                                </a>
		                                <a href="#" class="btn btn-label-info btn-round btn-sm">
		                                    <span class="btn-label"><i class="fa fa-print"></i></span>
		                                    Print
		                                </a>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="card-body">
		                        <div class="chart-container" style="min-height: 375px">
		                            <canvas id="statisticsChart"></canvas>
		                        </div>
		                        <div id="myChartLegend"></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		
		        <div class="d-flex justify-content-between">
		            <div class="col-md-6">
		                <div class="card">
		                    <div class="card-header">
		                        <div class="card-title">Bar Chart</div>
		                    </div>
		                    <div class="card-body">
		                        <div class="chart-container">
		                            <canvas id="barChart"></canvas>
		                        </div>
		                    </div>
		                </div>
		            </div>
		
		            <div class="col-md-6" style="margin-left: 10px;">
		                <div class="card">
		                    <div class="card-header">
		                        <div class="card-title">Pie Chart</div>
		                    </div>
		                    <div class="card-body">
		                        <div class="chart-container">
		                            <canvas id="pieChart" style="width: 50%; height: 50%"></canvas>
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
                  <a class="nav-link" href="#"> Accounts </a>
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

      <!-- Custom template | don't include it in your project! -->
     
        <div class="custom-toggle">
          <i class="icon-settings"></i>
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

    <!-- Chart JS -->
    <script src="assets/js/plugin/chart.js/chart.min.js"></script>

    <!-- jQuery Sparkline -->
    <script src="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

    <!-- Chart Circle -->
    <script src="assets/js/plugin/chart-circle/circles.min.js"></script>

    <!-- Datatables -->
    <script src="assets/js/plugin/datatables/datatables.min.js"></script>

    <!-- jQuery Vector Maps -->
    <script src="assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
    <script src="assets/js/plugin/jsvectormap/world.js"></script>

    <!-- Sweet Alert -->
    <script src="assets/js/plugin/sweetalert/sweetalert.min.js"></script>

    <!-- Kaiadmin JS -->
    <script src="assets/js/kaiadmin.min.js"></script>

    <!-- Kaiadmin DEMO methods, don't include it in your project! -->
    <script src="assets/js/setting-demo.js"></script>
    <script src="assets/js/demo.js"></script>
    <script>
 // Weekly Views Data from the server
    var weeklyViews = <%= request.getAttribute("weeklyViews") %>;
    
    // Pie Chart Data from the server (Likes, Dislikes, Views)
    var pieChartData = <%= request.getAttribute("pieChartData") %>;

    // Bar Chart
    var myBarChart = new Chart(document.getElementById('barChart'), {
        type: 'bar',
        data: {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            datasets: [{
                label: 'Weekly Views',
                backgroundColor: 'rgb(23, 125, 255)',
                borderColor: 'rgb(23, 125, 255)',
                data: weeklyViews  // Data from the server
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    // Pie Chart
    var myPieChart = new Chart(document.getElementById('pieChart'), {
        type: 'pie',
        data: {
            datasets: [{
                data: pieChartData,  // Data from the server
                backgroundColor: ['#1d7af3', '#f3545d'],
                borderWidth: 0
            }],
            labels: ['Likes', 'Dislikes']
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                position: 'bottom',
                labels: {
                    fontColor: 'rgb(154, 154, 154)',
                    fontSize: 11,
                    usePointStyle: true,
                    padding: 20
                }
            },
            pieceLabel: {
                render: 'percentage',
                fontColor: 'white',
                fontSize: 14
            },
            tooltips: false,
            layout: {
                padding: {
                    left: 20,
                    right: 20,
                    top: 20,
                    bottom: 20
                }
            }
        }
    });
    
    var htmlLegendsChart = document.getElementById("statisticsChart").getContext("2d");

    var gradientStroke = htmlLegendsChart.createLinearGradient(500, 0, 100, 0);
    gradientStroke.addColorStop(0, "#177dff");
    gradientStroke.addColorStop(1, "#80b6f4");

    var gradientFill = htmlLegendsChart.createLinearGradient(500, 0, 100, 0);
    gradientFill.addColorStop(0, "rgba(23, 125, 255, 0.7)");
    gradientFill.addColorStop(1, "rgba(128, 182, 244, 0.3)");

    var gradientStroke2 = htmlLegendsChart.createLinearGradient(500, 0, 100, 0);
    gradientStroke2.addColorStop(0, "#f3545d");
    gradientStroke2.addColorStop(1, "#ff8990");

    var gradientFill2 = htmlLegendsChart.createLinearGradient(500, 0, 100, 0);
    gradientFill2.addColorStop(0, "rgba(243, 84, 93, 0.7)");
    gradientFill2.addColorStop(1, "rgba(255, 137, 144, 0.3)");

    var gradientStroke3 = htmlLegendsChart.createLinearGradient(500, 0, 100, 0);
    gradientStroke3.addColorStop(0, "#fdaf4b");
    gradientStroke3.addColorStop(1, "#ffc478");

    var gradientFill3 = htmlLegendsChart.createLinearGradient(500, 0, 100, 0);
    gradientFill3.addColorStop(0, "rgba(253, 175, 75, 0.7)");
    gradientFill3.addColorStop(1, "rgba(255, 196, 120, 0.3)");

    var myHtmlLegendsChart = new Chart(htmlLegendsChart, {
        type: "line",
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [
                {
                    label: "Subscribers",
                    borderColor: gradientStroke2,
                    pointBackgroundColor: gradientStroke2,
                    pointRadius: 0,
                    backgroundColor: gradientFill2,
                    legendColor: "#f3545d",
                    fill: true,
                    borderWidth: 1,
                    data: [154, 184, 175, 203, 210, 231, 240, 278, 252, 312, 320, 374],
                },
                {
                    label: "New Visitors",
                    borderColor: gradientStroke3,
                    pointBackgroundColor: gradientStroke3,
                    pointRadius: 0,
                    backgroundColor: gradientFill3,
                    legendColor: "#fdaf4b",
                    fill: true,
                    borderWidth: 1,
                    data: [256, 230, 245, 287, 240, 250, 230, 295, 331, 431, 456, 521],
                },
                {
                    label: "Active Users",
                    borderColor: gradientStroke,
                    pointBackgroundColor: gradientStroke,
                    pointRadius: 0,
                    backgroundColor: gradientFill,
                    legendColor: "#177dff",
                    fill: true,
                    borderWidth: 1,
                    data: [542, 480, 430, 550, 530, 453, 380, 434, 568, 610, 700, 900],
                },
            ],
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                display: false,
            },
            tooltips: {
                bodySpacing: 4,
                mode: "nearest",
                intersect: 0,
                position: "nearest",
                xPadding: 10,
                yPadding: 10,
                caretPadding: 10,
            },
            layout: {
                padding: { left: 15, right: 15, top: 15, bottom: 15 },
            },
            scales: {
                yAxes: [
                    {
                        ticks: {
                            fontColor: "rgba(0,0,0,0.5)",
                            fontStyle: "500",
                            beginAtZero: false,
                            maxTicksLimit: 5,
                            padding: 20,
                        },
                        gridLines: {
                            drawTicks: false,
                            display: false,
                        },
                    },
                ],
                xAxes: [
                    {
                        gridLines: {
                            zeroLineColor: "transparent",
                        },
                        ticks: {
                            padding: 20,
                            fontColor: "rgba(0,0,0,0.5)",
                            fontStyle: "500",
                        },
                    },
                ],
            },
            legendCallback: function (chart) {
                var text = [];
                text.push('<ul class="' + chart.id + '-legend html-legend">');
                for (var i = 0; i < chart.data.datasets.length; i++) {
                    text.push('<li><span style="background-color:' + chart.data.datasets[i].legendColor + '"></span>');
                    if (chart.data.datasets[i].label) {
                        text.push(chart.data.datasets[i].label);
                    }
                    text.push("</li>");
                }
                text.push("</ul>");
                return text.join("");
            },
        },
    });

    // Generate HTML legend
    var myLegendContainer = document.getElementById("myChartLegend");
    myLegendContainer.innerHTML = myHtmlLegendsChart.generateLegend();

    // Bind onClick event to all LI-tags of the legend
    var legendItems = myLegendContainer.getElementsByTagName("li");
    for (var i = 0; i < legendItems.length; i++) {
        legendItems[i].addEventListener("click", legendClickCallback, false);
    }

    </script>
  </body>
</html>
