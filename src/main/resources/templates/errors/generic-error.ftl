[#include '*/commons/page-structure.ftl' /]
[@main_errors]

<h2>Error [#if status??]${status}[#else] no controlado [/#if] - [@spring.message "app.nombre"/]</h2>

<div class="panel panel-default">
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12">
				<div class="alert alert-danger">
					<span class="glyphicon glyphicon-exclamation-sign"></span>
						<p style="margin-top: 10px;"><strong>Se produjo un error interno de aplicación. (genérico)</strong></p>
						<p>
						En este momento el sistema está presentando dificultades, estamos
						trabajando para dar una pronta solución, por favor inténtelo
						nuevamente más tarde. Si el problema persiste, por favor contáctese con el administrador del sistema o a
						<a href="mailto:mesadeayuda@mineduc.cl">mesadeayuda@mineduc.cl</a> detallando claramente el problema. 
					</p>
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