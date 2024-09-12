<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to VSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Background image and overlay */
        .welcome-section {
            background: url('assets/img/welcome.jpg') no-repeat center center;
            background-size: cover;
            position: relative;
            height: 100vh;
        }
        .welcome-section::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.8); /* Dark overlay */
        }
        /* Text content on top of the overlay */
        .welcome-content {
            position: relative;
            z-index: 1;
            color: white;
        }
    </style>
</head>
<body>

    <!-- Welcome Section -->
    <section class="welcome-section d-flex align-items-center justify-content-center">
        <div class="welcome-content text-center">
            <!-- Logo -->
            <img src="assets/img/logo-light.svg" alt="VSP Logo" class="mb-4" style="width: 150px;">

            <!-- Welcome Note -->
            <h1 class="mb-3">Welcome to Virishare</h1>
            <p class="lead mb-5">Video Streaming Platform - Enjoy and explore endless content</p>

            <!-- Start Button -->
            <a href="login" class="btn btn-primary btn-lg">Start</a>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
