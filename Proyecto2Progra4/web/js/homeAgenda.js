var cantCitas = 0;
var horarios = [];
var slotsPropios = [];
var horario = {day: "", estado: 0, desde: 8, hasta: 13};
var horarioAux = {day: "", estado: 0, desde: 8, hasta: 13};
var pacientesH = new Array();
var usuarioHome = {
    id: '',
    contrasena: '',
    estado: '',
    rol: 0
};
var citaTemp = {
};
var citaH = {
    id: 0,
    day: "",
    estado: "",
    signos: "",
    diagnostico: "",
    prescripciones: "",
    motivo: ""
};
var citaPantallaH = {
    id: 0,
    day: "",
    estado: "",
    signos: "",
    diagnostico: "",
    prescripciones: "",
    motivo: ""
};
var prueba1 = {day: "Mon", estado: 1, desde: 8, hasta: 18};
var prueba2 = {day: "Tue", estado: 1, desde: 8, hasta: 14};
var prueba3 = {day: "Wed", estado: 0, desde: 0, hasta: 0};
var prueba4 = {day: "Thu", estado: 0, desde: 0, hasta: 0};
var prueba5 = {day: "Fri", estado: 0, desde: 0, hasta: 0};
var dias = ["Mon", "Tue", "Wed", "Thu", "Fri"];
var date = monday();
var fecha = "";
var hora = "";
var cita = {e: 0, id: 0, date: "", pId: "", estado: 0, signos: "", prescripcion: "", diagnostico: "", tipo: "", motivo: ""};

var citas = [];

var citasH= [];

var paciente1 = {
    idPaciente: 1,
    nombre: "Julio",
    sexo: '',
    contacto: "",
    enfermedades: "",
    alergias: "",
    cirugias: ""
};
var paciente2 = {
    idPaciente: 2,
    nombre: "Maribel",
    sexo: '',
    contacto: "",
    enfermedades: "",
    alergias: "",
    cirugias: ""
};
var doctorPaciH = {
    idMedico: 0,
    contrasena: "",
    nombre: "",
    especialidad: "",
    costo: 0,
    localidad: "",
    duracion: 0
};


var pacientesH = [];

var doctor = {id: "", password: "", name: "", speciality: "", fee: "", location: "", frecuencia: 60, horarios: []};

if (verificaSesion()) {
    
     //pacientesH.push(paciente1);
     //pacientesH.push(paciente2);
//doctor=JSON.parse(localStorage.getItem("doctor"));
//    horarios.push(prueba1);
//    horarios.push(prueba2);
//    horarios.push(prueba3);
//    horarios.push(prueba4);
//    horarios.push(prueba5);

}

function verificaSesion() {
    var sesion = sessionStorage.getItem("doctor");
    return sesion;
}

  function listarPacientesH(idMedic){
    let request = new Request(backend+'/pacientes/'+ idMedic, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        pacientes = await response.json();
        pacientesH = pacientes;
        //traerMedico(pacientes);
        //console.log(doctorPaci);
        //listarP();        
        //console.log(pacientes);
    })();    
  }
var i = 5;
var j = 12;
function traerSlots(idMediquito) {
    let request = new Request(backend + '/agenda/' + idMediquito, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        slotsPropios = await response.json();
        console.log(slotsPropios);
        horarios = cambiaHorarioHome(slotsPropios);
        listarCitasD(usuarioHome.id);
        listarPacientesH(usuarioHome.id);
        console.log(horarios);
        loadDates(0);
    })();
}
function cambiaHorarioHome(slots) {

    horariosFinalhome = new Array();
    
    
    for (var j = 0; j < 5; j++) {
        horarioTemporal = {
            day: "",
            estado: 0,
            desde: 0,
            hasta: 0
        };
        
        if (slots[j]!== undefined){
        switch (slots[j].day) {
            case 1:
                horarioTemporal.desde = slots[j].startHour;
                horarioTemporal.hasta = slots[j].endHour;
                horarioTemporal.estado = 1;
                horarioTemporal.day = "Mon";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 2:
                horarioTemporal.desde = slots[j].startHour;
                horarioTemporal.hasta = slots[j].endHour;
                horarioTemporal.estado = 1;
                horarioTemporal.day = "Tue";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 3:
                horarioTemporal.desde = slots[j].startHour;
                horarioTemporal.hasta = slots[j].endHour;
                horarioTemporal.estado = 1;
                horarioTemporal.day = "Wed";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 4:
                horarioTemporal.desde = slots[j].startHour;
                horarioTemporal.hasta = slots[j].endHour;
                horarioTemporal.estado = 1;
                horarioTemporal.day = "Thu";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 5:
                horarioTemporal.desde = slots[j].startHour;
                horarioTemporal.hasta = slots[j].endHour;
                horarioTemporal.estado = 1;
                horarioTemporal.day = "Fri";
                horariosFinalhome.push(horarioTemporal);
                break;
        }
    }else{
                switch (j+1) {
            case 1:
                horarioTemporal.desde = 0;
                horarioTemporal.hasta = 0;
                horarioTemporal.estado = 0;
                horarioTemporal.day = "Mon";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 2:
                horarioTemporal.desde = 0;
                horarioTemporal.hasta = 0;
                horarioTemporal.estado = 0;
                horarioTemporal.day = "Tue";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 3:
                horarioTemporal.desde = 0;
                horarioTemporal.hasta = 0;
                horarioTemporal.estado = 0;
                horarioTemporal.day = "Wed";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 4:
                horarioTemporal.desde = 0;
                horarioTemporal.hasta = 0;
                horarioTemporal.estado = 0;
                horarioTemporal.day = "Thu";
                horariosFinalhome.push(horarioTemporal);
                break;
            case 5:
                horarioTemporal.desde = 0;
                horarioTemporal.hasta = 0;
                horarioTemporal.estado = 0;
                horarioTemporal.day = "Fri";
                horariosFinalhome.push(horarioTemporal);
                break;
        }
        
        
    }
    
    }
    return horariosFinalhome;
}






function monday() {
    const date = new Date();
    switch (date.getDay()) {
        case 1:
            return date;
            break;
        case 2:
            date.setDate(date.getDate() - 1);
            return date;
            break;
        case 3:
            date.setDate(date.getDate() - 2);
            return date;
            break;
        case 4:
            date.setDate(date.getDate() - 3);
            return date;
            break;
        case 5:
            date.setDate(date.getDate() - 4);
            return date;
            break;
        case 6:
            date.setDate(date.getDate() + 2);
            return date;
            break;
        case 0:
            date.setDate(date.getDate() + 1);
            return date;
            break;
    }
}
  function listarPacientesH(idMedic){
    let request = new Request(backend+'/pacientes/'+ idMedic, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        pacientesH = await response.json();     
        console.log(pacientesH);
    })();    
  }
function preAgenda(e){
		hora=e.target.dataset.time;
		fecha=e.target.dataset.date;
                
		var auxDate=new Date(fecha+" "+hora);
		console.log(fecha+" "+hora);
                cita.e=e;                                            // borrar
                
		
                citaPantallaH.day = auxDate;
                
      
		
		document.getElementById("popup").classList.toggle("active");
		document.getElementById("overlay").classList.toggle("active");
		
		var select=document.getElementById("select");
		select.innerHTML="";
		
		pacientesH.forEach((p)=>{
			select.innerHTML+=`<option value="`+p.idPaciente+`">`+p.nombre+`</option>`;
		});
		


}

  function crearCiTaH(citaPantalla){

    let request = new Request(backend+'/citasTerminar', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(citaPantalla)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        
            console.log("SE AGREGOOOOOOOOOOOOOO");
            traerCitaM(citaPantallaH.motivo);
    })();    
  }
  
  function listarCitasD(idMeH) {
    let request = new Request(backend + '/agendaCitas/' + idMeH, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        citasH = await response.json();
        
        //console.log(doctorPaci);
        //listarC();
        console.log(citasH);
    })();
}

function traerCitaM(motivoH) {
    let request = new Request(backend + '/agendaCitas2/' + motivoH, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        citaTemp = await response.json();
        //traerMedico(pacientes);
        //console.log(doctorPaci);
        
        console.log(citaTemp);
    })();
}



function calendarClick(e){
	if("time" in e.target.dataset) preAgenda(e);
}

function back() {
    loadDates(2);
}

function forward() {
    loadDates(1);
}

function agendar(){
	
	cita.e.target.classList.add("date-selected");
        cita.e.target.removeAttribute('data-time');
         cita.e.target.removeAttribute('data-date');
        
        
	document.getElementById("popup").classList.toggle("active");
	document.getElementById("overlay").classList.toggle("active");
        

	citaPantallaH.motivo=document.getElementById("motivo").value;

	
	var selection = document.getElementById("select");
	var id=(selection.selectedOptions[0].value);
	var pAux=pacientesH.find((p)=>p.idPaciente==id);
	
	cita.e.target.innerHTML=pAux.nombre+`<br>ID:`+pAux.idPaciente +`     |     `;
        

        
        citaPantallaH.id = usuarioHome.id;
        citaPantallaH.estado = pAux.idPaciente;
        

        
        
        

    
         crearCiTaH(citaPantallaH);
     
     
         setTimeout(()=>{
             cita.e.target.innerHTML += '<a  style="font-weight: 600; color:#A70002; "href="'+url+'presentation/terminarCita.html?'+citaTemp.id+'">    Finalizar</a>';
         },100);
     


            
         
        

        
        
        
     
	
}

function loaded(event) {
    usuarioHome = JSON.parse(sessionStorage.getItem("doctor"));
    traerSlots(usuarioHome.id);
    
    

    document.getElementById("right-arrow").addEventListener("click", forward);
    document.getElementById("left-arrow").addEventListener("click", back);
    document.getElementById("btn-agendar").addEventListener("click",agendar);
    document.getElementById("calendar").addEventListener("click",(e)=>{calendarClick(e);});


}

document.addEventListener("DOMContentLoaded", loaded);
//function loaded() {
//    //loadMenu($("#menucontainer"));
//    //agendaCreate();
//    $("#botonRegresar").on("click", logout);
//    //$("#apply").on("click", add);
//}
//$(loaded);
