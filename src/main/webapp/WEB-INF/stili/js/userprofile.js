    function showpassword(){
        document.getElementById("password").type==="text" ? 
            document.getElementById("password").type ="password"
        : document.getElementById("password").type ="text";
    }

    function validate(){
        var password = document.getElementById("password").value;
        var icon = document.getElementById("passwordwarning");       
            if(password.length < 8 || !isNaN(password) ){
                icon.style.display = "inline";
            }
            else{ 
                icon.style.display = "none";
            }
    }
    
  