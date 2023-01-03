var admins = new Array();

var doctor = {
    id: 0,
    contrasena: '',
    rol: 0,
    estado: ''
};
function row(listado, doctor) {
    var tr = $("<tr />");
    tr.html("<td>" + doctor.id + "</td>" +
            "<td>" + doctor.estado + "</td>" +
            "<td id='edit'><img src='../images/edit.png' width='60'></td>"
            );
    tr.find("#edit").on("click", () => {
        edit(doctor.id);
    });
    listado.append(tr);
}

function edit(cedula) {
    let request = new Request(backend + '/admins', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(cedula)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#add-modal #errorDiv"));
            return;
        }
        fetchAndList();
    })();
}

function list() {
    $("#listado").html("");
    admins.forEach((p) => {
        row($("#listado"), p);
    });
}
function fetchAndList() {
    let request = new Request(backend + '/admins', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        admins = await response.json();
        console.log(admins);
        list();
    })();
}

function loaded() {
    fetchAndList();
    //$("loginButton").click(login);
    //$("#loginButton").on("click", login);
    //$("#botonRegresar").on("click", logout);

}



$(loaded);




function verificaSesion() {
    var sesion = sessionStorage.getItem("doctor");
    return sesion;
}