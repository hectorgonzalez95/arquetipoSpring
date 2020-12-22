[#include '*/commons/page-structure.ftl' /]
[@main_errors]

<h2>Error [#if status??]${status}[#else] no controlado [/#if] - [@spring.message "app.nombre"/]</h2>

<div class="panel panel-default">
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12">
				<div class="alert alert-danger">
					<span class="glyphicon glyphicon-exclamation-sign"></span>
					<p style="margin-top: 10px;">
						<strong>Se produjo un error interno de aplicación. (Excepción Mineduc)</strong>
					</p>
					<p>Puede volver atrás e intentarlo nuevamente. Si el problema persiste,
						por favor copie el código que aparece entre corchetes [&nbsp;] junto
						con la descripción del error y envíelo por correo electrónico al
						administrador del sistema o a <a href="mailto:mesadeayuda@mineduc.cl">mesadeayuda@mineduc.cl</a></p>
					<p><strong>[#if exception?? && exception.message??]${exception.message}[/#if]</strong></p>
				</div>
				
				<button type="button"  class="btn btn-primary-gradient pull-right" onclick="javascript:history.back();">
					<span class="glyphicon glyphicon-chevron-left" ></span> 
					<span class="font-gob">Volver</span>
				</button>&nbsp;					
			</div>
		</div>
	</div>
</div>

[/@main_errors]