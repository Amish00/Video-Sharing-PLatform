<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="icon" href="Admin/assets/img/favicon.ico" type="image/x-icon" />
    <title> Signup Page </title>
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap');

body{
    font-family: 'Poppins', sans-serif;
    background: #ececec;
}

/*------------ Login container ------------*/

.box-area{
    width: 930px;
}

/*------------ Right box ------------*/

.right-box{
    padding: 40px 30px 40px 40px;
}

/*------------ Custom Placeholder ------------*/

::placeholder{
    font-size: 16px;
}

.rounded-4{
    border-radius: 20px;
}
.rounded-5{
    border-radius: 30px;
}


/*------------ For small screens------------*/

@media only screen and (max-width: 768px){

     .box-area{
        margin: 0 10px;

     }
     .left-box{
        height: 100px;
        overflow: hidden;
     }
     .right-box{
        padding: 20px;
     }

}
</style>
<body>

    <!----------------------- Main Container -------------------------->

     <div class="container d-flex justify-content-center align-items-center min-vh-100">

    <!----------------------- Sign Up Container -------------------------->

       <div class="row border rounded-5 p-3 bg-white shadow box-area">

    <!--------------------------- Left Box ----------------------------->

       <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" style="background: #103cbe;">
           <div class="featured-image mb-3">
            <img src="https://img.freepik.com/free-vector/sign-concept-illustration_114360-7965.jpg" class="img-fluid" style="width: 400px;">
           </div>
           <p class="text-white fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">Join Us</p>
           <small class="text-white text-wrap text-center" style="width: 17rem;font-family: 'Courier New', Courier, monospace;">Become a part of our community today.</small>
       </div> 

    <!--------------------------- Right Box ---------------------------->
        
       <div class="col-md-6 right-box">
          <div class="row align-items-center">
                <div class="header-text mb-2">
                     <h2>Create Account</h2>
                     <p>We are excited to have you on board.</p>
                </div>
                <form action="signup" method="post">
                <p style="color:red">${errnotify}</p>
                <div class="input-group mb-3">
                    <input type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Username"name="username" required>
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Email address" name="email" required>
                </div>
                <div class="input-group mb-3">
				    <input type="password" id="password" class="form-control form-control-lg bg-light fs-6" placeholder="Password" name="password" required>
				    <span class="input-group-text bg-light">
				        <i class="bi bi-eye-slash" id="togglePassword" style="cursor: pointer;"></i>
				    </span>
				</div>
				<div class="input-group mb-3">
				    <input type="password" id="confirmPassword" class="form-control form-control-lg bg-light fs-6" placeholder="Confirm Password" required>
				    <span class="input-group-text bg-light">
				        <i class="bi bi-eye-slash" id="toggleConfirmPassword" style="cursor: pointer;"></i>
				    </span>
				</div>

                <div class="input-group mb-3">
                    <button class="btn btn-lg btn-primary w-100 fs-6" type="submit" value="signup">Sign Up</button>
                </div>
                </form>
                <div class="input-group mb-3">
                    <button class="btn btn-lg btn-light w-100 fs-6"><img src="Admin/assets/img/google.svg" style="width:20px" class="me-2"><small>Sign Up with Google</small></button>
                </div>
                <div class="row">
                    <small>Already have an account? <a href="login">Login</a></small>
                </div>
          </div>
       </div> 
      </div>
    </div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
<script>
    document.querySelectorAll('.input-group span i').forEach(toggle => {
        toggle.addEventListener('click', function () {
            const input = this.parentElement.previousElementSibling;
            const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
            input.setAttribute('type', type);
            this.classList.toggle('bi-eye');
            this.classList.toggle('bi-eye-slash');
        });
    });
</script>

</html>
