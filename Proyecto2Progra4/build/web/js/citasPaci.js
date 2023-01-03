
var code = 0;

var citas = new Array();
var cita = {
    id: 0,
    day: "",
    estado: "",
    signos: "",
    diagnostico: "",
    prescripciones: "",
    motivo: ""
};


function getCode() {
    code = document.location.search.substring(1);
    //loadMenu($("#menucontainer"));
    //agendaCreate();

    //$("#apply").on("click", add);
}
function listarC() {
    $("#listado").html("");
    citas.forEach((p) => {
        row($("#listado"), p);
    });
}
function listarCitas(idCita) {
    let request = new Request(backend + '/citasPacientes/' + idCita, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        citas = await response.json();
        //traerMedico(pacientes);
        //console.log(doctorPaci);
        listarC();
        console.log(citas);
    })();
}
function row(listado, cita) {
    var tr = $("<tr />");
    tr.html("<td>" + cita.id + "</td>" +
            "<td>" + cita.day + "</td>" +
            "<td>" + cita.estado + "</td>" +
            "<td>" + cita.motivo + "</td>" +
            "<td>" + cita.signos + "</td>" +
            "<td>" + cita.diagnostico + "</td>" +
            "<td>" + cita.prescripciones + "</td>" +
            "<td id='terminar'><img src='../images/citafinalizada.png' style='cursor:pointer'></td>" +
            "<td id='pfd'><img src='../images/PDF.png' style='cursor:pointer'></td>"
            );
    tr.find("#terminar").on("click", () => {
        terminarCita(cita.id);
    });
    tr.find("#pfd").on("click", () => {
        pdfCita(cita.id);
    });
    
    listado.append(tr);
}
function pdfCita(cita) {
    document.location = url + "presentation/pdfCita.html?" + cita;
}



function terminarCita(cita) {
    document.location = url + "presentation/terminarCita.html?" + cita;
}


function loaded() {
    getCode();
    console.log(code);
    listarCitas(code);
    //loadMenu($("#menucontainer"));
    //agendaCreate();
    //$("#botonRegresar").on("click", logout);
    //$("#apply").on("click", add);
}

$(loaded);