var idCitaC = 0;
var usuario = {
    id: '',
    contrasena: '',
    estado: '',
    rol: 0
};

//var idPaciT = 0;
var citaPantallaC = {
    id: 0,
    day: "",
    estado: "",
    signos: "",
    diagnostico: "",
    prescripciones: "",
    motivo: ""
};

function getCodeCita() {
    idCitaC = document.location.search.substring(1);
}






function load() {
    //citaPantallaC = Object.fromEntries((new FormData($("#formulario").get(0))).entries());
    usuario = JSON.parse(sessionStorage.getItem("doctor"));
    hor = $("#hora").val();
    fec = $("#fecha").val();
    var auxDate = new Date(fec+" "+hor);
    citaPantallaC.motivo = $("#motivo").val();
    citaPantallaC.day =   auxDate;
    citaPantallaC.id = usuario.id;
    citaPantallaC.estado= idCitaC;
}

  function crearCiTa(){
    load();
    //if(!validarPaci()) return;
    let request = new Request(backend+'/citasTerminar', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(citaPantallaC)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        document.location = url + "presentation/pacientes.html";
            console.log("SE AGREGOOOOOOOOOOOOOO");           
    })();    
  }



function loaded() {
    getCodeCita();
    console.log(idCitaC);

    $("#botonCrearCita").on("click", crearCiTa);
    
}

$(loaded);