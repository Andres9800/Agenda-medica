var idCitac = 0;


var idPaciT = 0;
var citaPantallaT = {
    id: 0,
    day: "",
    estado: "",
    signos: "",
    diagnostico: "",
    prescripciones: "",
    motivo: ""
};
function getCodeT() {
    idCitac = document.location.search.substring(1);
}

function terminarCita(idCita) {
    let request = new Request(backend + '/citasTerminar/' + idCita, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        citaPantallaT = await response.json();
        console.log(citaPantallaT);
        idPaciT = citaPantallaT.paciente.idPaciente;
        console.log(idPaciT);
        renderCita();
    })();
}


function renderCita() {
    $("#id").val(citaPantallaT.id);
    $("#day").val(citaPantallaT.day);
    $("#estado").val(citaPantallaT.estado);
    $("#signos").val(citaPantallaT.signos);
    $("#diagnostico").val(citaPantallaT.diagnostico);
    $("#prescripciones").val(citaPantallaT.prescripciones);
    $("#motivo").val(citaPantallaT.motivo);
}
function load() {
    citaPantallaT = Object.fromEntries((new FormData($("#formulario").get(0))).entries());

}

function updateCita() {
    load();
    let request = new Request(backend + '/citasTerminar', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(citaPantallaT)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {return;}
        document.location = url + "presentation/citasPaciente.html?" + idPaciT;
        //console.log("se editooooooo");
    })();
}

function loaded() {
    getCodeT();
    console.log(idCitac);
    terminarCita(idCitac);
    //listarCitas(code);
    //loadMenu($("#menucontainer"));
    //agendaCreate();
    $("#botonTerminaCita").on("click", updateCita);
    //$("#apply").on("click", add);
}

$(loaded);