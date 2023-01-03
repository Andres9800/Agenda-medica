


 async function addAdmin(){
    admin = {
        id: $("#usuarioAdmin").val(),
        contrasena: $("#contraAdmin").val()
    };
    try {
            const response = await fetch(backend + "/usuarios/addAdmin", {
            method: 'POST',
            headers: { 'Content-Type': "application/json"},
            body: JSON.stringify(admin)
        });
        console.log(response);
        if(response.status===200 || response.status === 204){ // arreglar
            document.location = url + "/index.html";
        }
    } catch(e){
        console.log(e);
    }   
  } 


function loaded() {
    //fetchAndList();
    //$("loginButton").click(login);
    //$("loginButton").off("click").on("click",login);
    $("#botonRegistroAdmin").on("click", addAdmin);
}

$(loaded);

