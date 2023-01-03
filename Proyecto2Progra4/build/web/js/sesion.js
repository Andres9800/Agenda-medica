var personas = new Array();
var data = {
    /*  Esta variable guarda la  id del usuario.
     *  Se actualiza de manera automática.
     */
    id: 0,
    /*  Esta variable guarda la contraseña del usuario.
     *  Se actualiza de manera automática.
     */
    contrasena: '',

    rol: 0,

    estado: 'pendiente'
            /*  Esta variable guarda el rol del usuario.
             *  Se actualiza de manera automática.
             */
};
"use strict";
function reset() {
    data = {id: 0, contrasena: '', rol: 0, estado: 'pendiente'};
    var m = document.getElementById("email");
    var b = document.getElementById("password");
    m.value = "";
    b.value = "";
}

function load(bandera) {
    if (bandera) {
        data = {
            id: document.getElementById("email").value,
            contrasena: document.getElementById("password").value,
            rol: 2
        };
    } else {
        data = {
            id: document.getElementById("email").value,
            contrasena: document.getElementById("password").value
        };
    }
}

function validar(bandera) {
    var contraseña = document.getElementById("password");
    var error = false;
    var cedula = document.getElementById("email");
    if (data.contrasena.length == 0) {
        contraseña.classList.add("invalid");
        error = true;
    }
    cedula.classList.remove("invalid");
    if (data.id.length == 0) {
        cedula.classList.add("invalid");
        error = true;
    }
    return !error;
}



function login() {
    doctor = {
        id: $("#email").val(),
        password: $("#password").val()
    };

    var idpara = doctor.id;
    const request = new Request(backend + '/usuarios/' + idpara, {method: 'GET', headers: {}});
    (async () => {
        try {
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error("Invalid doctor");
            }
            doctor = await response.json();
            if (doctor) {
                sessionStorage.setItem("doctor", JSON.stringify(doctor));
                switch (doctor.rol) {
                    case 1:
                        if(doctor.estado==="pendiente"){
                            document.location = url + "presentation/espera.html";
                        }else{
                            document.location = url + "presentation/homeAgenda.html";
                        }
                        break;
                    case 3:
                        document.location = url + "presentation/pantallaAdmin.html";
                        break;
                }
            } else {
                throw new Error("Invalid doctor");
            }
        } catch (e) {
            alert("Doctor No existe");
        }
    }
    )();
}







 async function logout(){
    try {
            const response = await fetch(backend + "/usuarios", {
            method: 'DELETE',
            headers: { 'Content-Type': "application/json"}
            
        });
        //console.log(response);
        //if(response.status===200 || response.status === 204){ // arreglar
         if(response.status > 199 && response.status < 300){ // arreglar
            sessionStorage.removeItem('doctor');
            document.location = url + "/index.html";
        }
    } catch(e){
        console.log(e);
    }   
  } 











function loaded() {
    //fetchAndList();
    //$("loginButton").click(login);
    $("#loginButton").on("click", login);
    $("#botonRegresar").on("click", logout);
  
}



$(loaded);




function verificaSesion() {
   var sesion = sessionStorage.getItem("doctor");
   return sesion;
}