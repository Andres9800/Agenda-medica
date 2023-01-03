var schedule = new Array();
var dataMedico = {
    id: '',
    contrasena: '',
    estado: '',
    rol: 0
};

var idPa = 0;
var  pacientes = new Array();
var paciente = {
    idPaciente: 0,
    nombre: "",
    sexo: '',
    contacto: "",
    enfermedades: "",
    alergias: "",
    cirugias: ""
};
var doctorPaci = {
    idMedico: 0,
    contrasena: "",
    nombre: "",
    especialidad: "",
    costo: 0,
    localidad: "",
    duracion: 0,
    schedule: schedule
};
  var mode='A'; //adding
  
  function row(listado,paciente){
	var tr =$("<tr />");
	tr.html("<td>"+paciente.idPaciente+"</td>"+
                "<td>"+paciente.nombre+"</td>"+
                "<td><img src='../images/"+paciente.sexo+".png' class='icon' ></td>"+
                "<td>"+paciente.contacto+"</td>"+
                "<td>"+paciente.enfermedades+"</td>"+
                "<td>"+paciente.alergias+"</td>"+
                "<td>"+paciente.cirugias+"</td>"+
                "<td><img src='"+url+"api/varios/"+paciente.nombre+"/imagen' class='icon_large' ></td>"+              
                "<td id='edit'><img src='../images/editabla.png' style='cursor:pointer'></td>"+
                "<td id='citasList'><img src='../images/edit3.png' style='cursor:pointer'></td>"+
                "<td id='crearCita'><img src='../images/crearcita.png' style='cursor:pointer'></td>"
                );
        tr.find("#edit").on("click",()=>{editPaci(paciente.idPaciente);});
        tr.find("#citasList").on("click",()=>{mostrarCitas(paciente.idPaciente);});
        tr.find("#crearCita").on("click",()=>{crearCita(paciente.idPaciente);});
	listado.append(tr);           
  }
    function editPaci(paciEn){
    let request = new Request(backend+'/varios/'+ paciEn, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        paciente = await response.json();
        mode='E'; //editing
        idPa = paciEn;
        renderPaci();        
    })();         
  }
    function mostrarCitas(paraMetro){
        document.location = url + "presentation/citasPaciente.html?" + paraMetro;
    }
    function crearCita(paraMetro){
        document.location = url + "presentation/creacionCita.html?" + paraMetro;
    }
    
      function load(){
        paciente = Object.fromEntries( (new FormData($("#formulario").get(0))).entries());
        paciente.idPaciente = dataMedico.id;
    }
//      function validarPaci(){
//    var error=false;
//   $("#formulario input").removeClass("invalid");
//    error |= $("#formulario input[type='text']").filter( (i,e)=>{ return e.value=='';}).length>0;        
//    $("#formulario input[type='text']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
//    error |= $("input[name='sexo']:checked").length==0;
//    if ( $("input[name='sexo']:checked").length==0) $("#formulario input[name='sexo']").addClass("invalid");
//    return !error;
//  }
      
 
  function addPaci(){
    load();
    //if(!validarPaci()) return;
    let request = new Request(backend+'/pacientes', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(paciente)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        addImagen();
        listarPacientes(dataMedico.id);
        reset();
        $('#add-modal').modal('hide');                
    })();    
  }
    function addImagen(){
    var imagenData = new FormData();
    imagenData.append("cedula", paciente.nombre);
    imagenData.append("imagen", $("#imagen").get(0).files[0]); 
    let request = new Request(backend+'/pacientes/'+paciente.nombre+"/imagen", {method: 'POST',body: imagenData});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}              
    })();    
  }
  
    function updatePacie(){
    load();
    paciente.idPaciente = idPa;
    //if(!validar()) return;
    let request = new Request(backend+'/pacientes', {method: 'PUT', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(paciente)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        listarPacientes(dataMedico.id);
        reset();
        $('#add-modal').modal('hide');                
    })();     
  }
  
  
    function renderPaci(){
	//$("#idPaciente").val(paciente.idPaciente);
	$("#nombre").val(paciente.nombre);
        $("input[name='sexo']").val([paciente.sexo]);
        $("#contacto").val(paciente.contacto);
        $("#enfermedades").val(paciente.enfermedades);
        $("#alergias").val(paciente.alergias);
        $("#cirugias").val(paciente.cirugias);
        switch(mode){
            case 'A':
                //$("#idPaciente" ).prop( "readonly", false );
                $('#aplicar').off('click').on('click', addPaci);
                break;
            case 'E':
                $("#nombre" ).prop( "readonly", true );
                $('#aplicar').off('click').on('click', updatePacie);
                break;             
        }
        $("#add-modal #errorDiv").html("");
        $("#add-modal #imagen").val("");        
        $('#add-modal').modal('show');        
  }
  
    function reset(){
        paciente={idPaciente:0, nombre:"",sexo:'',contacto:"",enfermedades:"",alergias:"",cirugias:""}; 
    }
    
      function makenewPaciente(){
      reset();
      mode='A'; //adding
      renderPaci();
    }
  

  
  function listarP(){
    $("#listado").html("");
    pacientes.forEach( (p)=>{row($("#listado"),p);});	
  }  

  function listarPacientes(idMedic){
    let request = new Request(backend+'/pacientes/'+ idMedic, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        pacientes = await response.json();
        traerMedico(pacientes);
        console.log(doctorPaci);
        listarP();        
        console.log(pacientes);
    })();    
  }
  
  
    function traerMedico(pacientes){
        
    for (var i = 0; i < pacientes.length; i++) {
        if(pacientes[i].medico.usuario.id===dataMedico.id){
            doctorPaci = pacientes[i].medico;
        }
   }

  
  }
  
  
  



  function loaded(){
    dataMedico = JSON.parse(sessionStorage.getItem("doctor"));
    listarPacientes(dataMedico.id);
    //listarPacientes(7);
    console.log(pacientes);
    $("#crear").click(makenewPaciente);        
    //$("#buscar").on("click",search);doctorPaci
    
  }
  
  $(loaded);  




