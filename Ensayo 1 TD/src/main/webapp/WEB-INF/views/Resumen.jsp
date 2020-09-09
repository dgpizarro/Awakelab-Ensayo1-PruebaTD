<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Resumen</title>
<!-- Tipos Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Righteous&display=swap"
        rel="stylesheet">
     <script src="https://kit.fontawesome.com/14907c5069.js" crossorigin="anonymous"></script>
<!-- DataTable -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" type="text/css"
        href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css" />
    <link rel="stylesheet" type="text/css"
        href="https://cdn.datatables.net/responsive/2.2.5/css/responsive.dataTables.min.css" />
    <script type="text/javascript"
        src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script type="text/javascript"
        src="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js"></script> 
<!-- Estilo Dialog -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
  crossorigin="anonymous"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<!-- Etilo CSS General -->        
    <link href="<c:url value="/resources/css/estilo_general.css" />" rel="stylesheet">
</head>
<body>

<main>

    <input id="alerta" type="hidden" value="${open}">

     <div class="col-3 titulo-pag" id="uno">
        <p>Listado de ciudades y ayuda</p>
     </div>
     
    <div class="col-3 cont-text-pag-tabla" id="dos" >
         <div id="contenedor1">
                    <table id="tabla1" class="display responsive" style="width:100%">
                        <thead>
                            <tr>
                                <th>Ciudad</th>
                                <th>Tipo de ayuda </th>
                                <th>Monto</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${ayudas}">
                                <tr>
                                    <td><c:out value="${a.getBeneficiario().getCiudad().getNombreciudad()}" /> </td>
                                    <td><c:out value="${a.getMotivo()}" /> </td>
                                     <td><c:out value="${a.getMonto()}" /> </td>                              
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>        
                </div>
         </div>
     
     <div class="col-4 titulo-pag" id="tres">
            <p>Agregar ayuda</p>
     </div>
     
     <div class="col-4 cont-text-pag" id="cuatro">
            <form:form action="${pageContext.request.contextPath}/agregarAyuda" method="POST" class="formContacto">
                                          
                <span>Beneficiario</span>
                <form:select path="beneficiario.beneficiarioid" class="listadoDestinatarios" required="true" style="width: 100% !important; font-size: 1em !important;">
                             <c:forEach var="b" items="${beneficiarios}">
                                 <form:option value="${b.getBeneficiarioid()}"> <c:out value="${b.getNombre()}"/></form:option>
                             </c:forEach>        
                </form:select>

                <span>Monto</span>
                <br>
                <form:input type="number" path="monto" required="true" placeholder="$0" min="0" />
                <br>
                
                <span>Motivo</span>
                <br>
                <form:input path="motivo" placeholder="Ingrese motivo" required="true" maxlength="25" />
                <br>
                                                
                <button type="submit" class="btnForm">Agregar</button>
            </form:form>

        </div>
        
        <div id="dialog2" style="display: none;" >
       <div style="display:flex; ">
            <div style="width: 20%; margin: auto;  font-size: 3em;">
                <span> <i class="far fa-check-circle"></i></span>
            </div>
            <div style="width: 77%; margin: auto; text-align: center;">
                <span>¡Nueva ayuda registrada correctamente!</span>
            </div>
        </div>
   </div>

    <div id="dialog3" style="display: none;" >
       <div style="display:flex; ">
            <div style="width: 20%; margin: auto;  font-size: 3em;">
                <span> <i class="fas fa-exclamation-triangle"></i></span>
            </div>
            <div style="width: 77%; margin: auto; text-align: center;">
                <span>¡Ocurrió un problema en la transacción!</span>
            </div>
        </div>
   </div>

</main>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#dialog2").css('display','block');  
    $( "#dialog2" ).dialog({
        closeText: "", modal: true, autoOpen: false, draggable: false, resizable: false,
        minHeight: 0, dialogClass: "chica",
        buttons: [{ text: "OK", click: function() { $( this ).dialog( "close" );} } ]
    });
    
    $("#dialog3").css('display','block');  
    $( "#dialog3" ).dialog({
        closeText: "", modal: true, autoOpen: false, draggable: false, resizable: false,
        minHeight: 0, dialogClass: "chica",
        buttons: [{ text: "OK", click: function() { $( this ).dialog( "close" );} } ]
    });
    
    var alerta = $("#alerta").val();
    if (alerta === "ok") {
        $("#dialog2").dialog("open");
   }
    if (alerta === "wrong") {
        $("#dialog3").dialog("open");
   }
    
    $('#tabla1').DataTable({
        "order": [[ 0, "asc" ]],
        "language" : {"url" : "https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"}
    });
 
	 $('#contenedor1').css("width", "100%");
	  $('#contenedor1').css("margin", "0");
	  $('#contenedor1').css("background-color","rgb(255, 255, 255)");
	  $('#contenedor1').css("font-size", "0.9em");
	
	
});

</script>

</body>
</html>