var usuario = {
    id: '',
    contrasena: '',
    estado: '',
    rol: 0
};

var cant = 0;
var schedule = new Array();
var horario = {day: "", estado: 0, desde: "", hasta: ""};
var dias = ["Monday", "Tuesday", "Wed", "Thursday", "Friday", "Saturday", "Sunday"];
var i = 0;
var mode = '';
var selectDesde = '</div><div class="schedule"><label>Desde:</label><select id="desde">';
var selectHasta = '<select id="hasta">';

dias.forEach(() => {
    horario = {day: dias[i], estado: 0};
    schedule.push(horario);
    i++;
}
);

for (i = 0; i < 24; i++) {
    if (i < 12) {
        if (i < 10) {
            selectDesde += '<option value="' + i + '">0' + i + 'am</option>';
            selectHasta += '<option value="' + i + '">0' + i + 'am</option>';
        } else {
            selectDesde += '<option value="' + i + '">' + i + 'am</option>';
            selectHasta += '<option value="' + i + '">' + i + 'am</option>';
        }
    } else {
        selectDesde += '<option value="' + i + '">' + i + 'pm</option>';
        selectHasta += '<option value="' + i + '">' + i + 'pm</option>';
    }
}

selectDesde += '</select><label>Hasta:</label>';
selectHasta += '</select></select></div>';

var divSelects = selectDesde + selectHasta;





var doctor = {
    idMedico: 0,
    contrasena: "",
    nombre: "",
    especialidad: "",
    costo: 0,
    localidad: "",
    duracion: 0,
    schedule: schedule
};

function cargar() {
    doctor.nombre = $("#nombreMedico").val();
    doctor.idMedico = $("#idMedico").val();
    doctor.especialidad = $("#espeMedico").val();
    doctor.costo = $("#costoMedico").val();
    doctor.localidad = $("#lugarMedico").val();
    doctor.duracion = $("#duraMedico").val();
    doctor.contrasena = $("#contraMedico").val();
}

function schedules() {
    borrar();
    render();
    document.getElementById("popup").classList.toggle("active");
    $("#d1 .day").on("click", function (e) {
        e.target.parentNode.parentNode.querySelector(".schedule").classList.toggle("active");
    });
}

function guardar() {
    document.getElementById("popup").classList.toggle("active");

    var i = 0;
    doctor.schedule = [];
    var desde = document.querySelectorAll("#desde");
    var hasta = document.querySelectorAll("#hasta");
    var checkboxes = "";

    i = 0;

    desde.forEach(() => {
        var dia = "#day" + i;
        checkboxes = $(dia);

        if (checkboxes.prop('checked')) {
            schedule[i].estado = 1;
        } else {
            schedule[i].estado = 0;
        }
        schedule[i].desde = desde[i].value;
        schedule[i].hasta = hasta[i].value;

        i++;
    });

    doctor.schedule = schedule;
    doctor.schedule = cambiaHorario(doctor.schedule);

    console.log(doctor.schedule);
    //console.log("OTROOOOOOOOOOOOOOOO");
    //console.log(cambiaHorario(doctor.horarios));
    borrar();
}
function cambiaHorario(schedule) {
    horarioFinal = {
        day: 0,
        startHour: 0,
        endHour: 0
    };
    horariosFinal = new Array();


    for (var j = 0; j < schedule.length; j++) {
        horarioTemporal = {
            day: 0,
            startHour: 0,
            endHour: 0
        };

        if (schedule[j].estado === 1) {

            horarioTemporal.startHour = schedule[j].desde;
            horarioTemporal.endHour = schedule[j].hasta;

            horarioTemporal.day = j + 1;

            horariosFinal.push(horarioTemporal);
        }
    }
    return horariosFinal;

}



function render() {
    parent = document.getElementById("schedules");
    var i = 0;

    doctor.schedule.forEach(() => {
        d = document.createElement("div");
        d.className = "schedule-container";
        d.id = "schedule-container";
        d.innerHTML = '';

        if (doctor.schedule[i].estado === 0) {
            d.innerHTML = '<div id="d1" class="day-input"><input class="day" id="day' + i + '" type="checkbox" value="' + i + '">' + doctor.schedule[i].day + '';

            d.innerHTML += divSelects;
            parent.appendChild(d);
        }
        if (doctor.schedule[i].estado === 1) {
            d.innerHTML = '<div id="d1" class="day-input"><input class="day" id="day' + i + '" checked type="checkbox" value="' + doctor.schedule[i].day + '">' + doctor.schedule[i].day + '</div>';

            d.innerHTML += selecteds(i);
            parent.appendChild(d);
        }
        i = i + 1;
    }
    );
    var checkeds = document.querySelectorAll("#scl");
    var i = 0;

    checkeds.forEach(() => {
        checkeds[i].classList.toggle("active");
        i++;
    });
}

function borrar() {
    var checkeds = document.querySelectorAll(".schedule-container");
    i = 0;
    checkeds.forEach(() => {
        checkeds[i].remove();
        i++;
    });
}

function selecteds(i) {
    var selectDesde2 = '</div><div id="scl" class="schedule"><label>Desde:</label><select id="desde">';
    var selectHasta2 = '<select id="hasta">';

    for (j = 0; j < 24; j++) {
        if (j < 12) {
            if (j < 10) {
                if (j == doctor.schedule[i].desde) {
                    selectDesde2 += '<option selected value="' + j + '">0' + j + 'am</option>';
                } else {
                    selectDesde2 += '<option value="' + j + '">0' + j + 'am</option>';
                }
                if (j == doctor.schedule[i].hasta) {
                    selectHasta2 += '<option selected value="' + j + '">0' + j + 'am</option>';
                } else {
                    selectHasta2 += '<option value="' + j + '">0' + j + 'am</option>';
                }
            } else {
                if (j == doctor.schedule[i].desde) {
                    selectDesde2 += '<option selected value="' + j + '">' + j + 'am</option>';
                } else {
                    selectDesde2 += '<option value="' + j + '">' + j + 'am</option>';
                }
                if (j == doctor.schedule[i].hasta) {
                    selectHasta2 += '<option selected value="' + j + '">' + j + 'am</option>';
                } else {
                    selectHasta2 += '<option value="' + j + '">' + j + 'am</option>';
                }
            }

        } else {
            if (j == doctor.schedule[i].desde) {
                selectDesde2 += '<option selected value="' + j + '">' + j + 'pm</option>';
            } else {
                selectDesde2 += '<option value="' + j + '">' + j + 'pm</option>';
            }

            if (j == doctor.schedule[i].hasta) {
                selectHasta2 += '<option selected value="' + j + '">' + j + 'pm</option>';
            } else {
                selectHasta2 += '<option value="' + j + '">' + j + 'pm</option>';
            }
        }

    }

    selectDesde2 += '</select><label>Hasta:</label>';
    selectHasta2 += '</select></select></div>';

    return selectDesde2 + selectHasta2;
}



async function addMedico() {
    cargar();
    try {
        const response = await fetch(backend + "/usuarios", {
            method: 'POST',
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(doctor)
        });
        console.log(doctor);
        if (response.status === 200 || response.status === 204) { // arreglar
            document.location = url + "/index.html";
        }
    } catch (e) {
        console.log(e);
    }
}








function cargarFormulario() {

    //console.log(doctor);
    document.getElementById("idMedico").value = doctor.idMedico;
    document.getElementById("contraMedico").value = doctor.contrasena;
    document.getElementById("nombreMedico").value = doctor.nombre;
    document.getElementById("espeMedico").value = doctor.especialidad;
    document.getElementById("duraMedico").value = doctor.duracion;
    document.getElementById("costoMedico").value = doctor.costo;
    document.getElementById("lugarMedico").value = doctor.localidad;
}

//function resetearFormulario(){
//    document.getElementById("idMedico").value = "";
//    document.getElementById("contraMedico").value = "";
//    document.getElementById("nombreMedico").value = "";
//    document.getElementById("espeMedico").value = "";
//    document.getElementById("duraMedico").value = "";  // talvez error
//    document.getElementById("costoMedico").value = ""; // talvez error
//    document.getElementById("lugarMedico").value = ""; 
//}

function editMedico(){
    cargar();
    let request = new Request(backend+'/usuarios', {method: 'PUT', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(doctor)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal #errorDiv"));return;}
            cargarMedico(dataMedico.id);
            alert("Doctor Actualizado");   
    })();     
  }

  
  

function cargarMedico(idMedico) {

    const request = new Request(backend + '/admins/' + idMedico, {method: 'GET', headers: {}});
    (async () => {
        try {
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error("Invalid doctor");
            }
            doctor = await response.json();
            if (doctor) {
                console.log(doctor);
                document.getElementById("idMedico").value = doctor.idMedico;
                document.getElementById("contraMedico").value = dataMedico.contrasena;
                document.getElementById("nombreMedico").value = doctor.nombre;
                document.getElementById("espeMedico").value = doctor.especialidad;
                document.getElementById("duraMedico").value = doctor.duracion;
                document.getElementById("costoMedico").value = doctor.costo;
                document.getElementById("lugarMedico").value = doctor.localidad;

            } else {
                throw new Error("Invalid doctor");
            }
        } catch (e) {
            //alert("Doctor No existe");
        }
    }
    )();
}








function verificaSesion() {
    var sesion = sessionStorage.getItem("doctor");
    return sesion;
}







function loaded() {
    //document.getElementById("botonCalendario").addEventListener("click", schedules);
    //document.getElementById("save-button").addEventListener("click", guardar);
    $("#save-button").on("click", guardar);
    $("#botonCalendario").on("click", schedules);
    $("#botonRegistroMedico").on("click", addMedico);
    if (verificaSesion) {
        //document.getElementById("botonActualizaMedico").addEventListener("click", editMedico);
        $("#botonActualizaMedico").on("click", editMedico);
        dataMedico = JSON.parse(sessionStorage.getItem("doctor"));
        cargarMedico(dataMedico.id);
    } else {
        $("#botonRegistroMedico").on("click", addMedico);
    }
}

$(loaded);

