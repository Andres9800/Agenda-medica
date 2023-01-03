var idPdf = 0;
var citaPDF = {
    id: 0,
    day: "",
    estado: "",
    signos: "",
    diagnostico: "",
    prescripciones: "",
    motivo: ""
};

function getCodepdf() {
    idPdf = document.location.search.substring(1);
}

function addPDF(){
    var pdfData = new FormData();
    pdfData.append("cedula", idPdf);
    pdfData.append("pdf", $("#pdf").get(0).files[0]); 
    let request = new Request(backend+'/varios/'+idPdf+"/pdf", {method: 'POST',body: pdfData});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {return;}
        dibujarFinal();
    })();    
  }
    function dibuja(listado,id){
	var tr =$("<tr />");
	tr.html(
        "<td  ><iframe style = 'width:600px; height: 70vh' src='"+url+"api/agenda/"+id+"/pdf'></iframe></td>"

                );

	listado.append(tr);           
  }
  
  function dibujarFinal(){
    $("#listado").html("");
    dibuja($("#listado"),idPdf);
  }  
  

  
function loaded() {
    getCodepdf();
    console.log(idPdf);

    //listarCitas(code);
    //loadMenu($("#menucontainer"));
    //agendaCreate();
    $("#subirPDF").on("click", addPDF);
    //$("#apply").on("click", add);
}

$(loaded);