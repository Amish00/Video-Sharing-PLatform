<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="icon" href="Admin/assets/img/favicon.ico" type="image/x-icon" />
    <title>Forgot Password</title>
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap');

body{
    font-family: 'Poppins', sans-serif;
    background: #ececec;
}

.box-area{
    width: 930px;
}

.right-box{
    padding: 40px 30px 40px 40px;
}

::placeholder{
    font-size: 16px;
}

.rounded-4{
    border-radius: 20px;
}
.rounded-5{
    border-radius: 30px;
}

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

    <div class="container d-flex justify-content-center align-items-center min-vh-100">

       <div class="row border rounded-5 p-3 bg-white shadow box-area">

       <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" style="background: #103cbe;">
           <div class="featured-image mb-3 mt-3">
            <img src="https://img.freepik.com/free-vector/reset-password-concept-illustration_114360-7886.jpg?ga=GA1.1.1410142790.1725386331&semt=ais_hybrid" class="img-fluid rounded" style="width: 250px;">
           </div>
           <p class="text-white fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">Forgot Password</p>
           <small class="text-white text-wrap text-center mb-2" style="width: 17rem;font-family: 'Courier New', Courier, monospace;">Please enter the OTP sent to your email to reset your password.</small>
       </div> 

       <div class="col-md-6 right-box">
          <div class="row align-items-center">
                <div class="header-text ">
                     <h2>Reset Password</h2>
                     <p>Please enter the new Password to access all new video.</p>
                </div>
                <p style="color:green">${status}</p>
                <p style="color:red">${error}</p>
                <form action="resetPassword" method="post">
			    <div class="input-group mb-3">
			        <input type="password" id="password" class="form-control form-control-lg bg-light fs-6" placeholder="Password" name="password" required>
			        <span class="input-group-text bg-light">
			            <i class="bi bi-eye-slash" id="togglePassword" style="cursor: pointer;"></i>
			        </span>
			    </div>
			    <div class="input-group mb-3">
			        <input type="password" id="confirmPassword" class="form-control form-control-lg bg-light fs-6" placeholder="Confirm Password" name="confPassword" required>
			        <span class="input-group-text bg-light">
			            <i class="bi bi-eye-slash" id="toggleConfirmPassword" style="cursor: pointer;"></i>
			        </span>
			    </div>
			
			    <div class="input-group mb-3">
			        <button class="btn btn-lg btn-primary w-100 fs-6" type="submit">Reset Password</button>
			    </div>
			</form>

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