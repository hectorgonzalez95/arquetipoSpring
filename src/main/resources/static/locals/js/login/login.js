$(document).ready(function() {
	
	$('#ingresar').click(function() {
		var i = 0;
		if($("#form_name").val() == ''){
			alert("Debe ingresar el nombre de usuario.");
			i++;
		}else{
			if($("#form_passw").val() == ''){
				alert("Debe ingresar la contraseña.");
				i++;
			}
		}
		if(i == 0){
			$("#form").submit();
		}
	});
	
	$("#form_passw").keypress(function(e) {
        if(e.which == 13) {
            jQuery(this).blur();
            jQuery('#ingresar').focus().click();
        }
	});
	
});