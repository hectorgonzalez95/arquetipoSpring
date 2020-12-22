[#macro _debugContent]
	[#if !isDevelopment][#return][/#if]
	[#assign maxRecursion=8 ]
	[#assign maxItems= 300 ] 
	[#assign ignored=['class','clazz','equals','hashCode','RequestParameters','interface','foreignMarkup','declaringClass','Session'
										,'Application','springMacroRequestContext','Request','JspTaglibs','recaptchaService','fileItem','inputStream','bytes']]
	[#assign execute=['name']][#-- name: to retrieve the name value of enumerations--]
	<link media="screen, projection" href="${context}/locals/css/debug.css" type="text/css" rel="stylesheet" />

  <link media="screen, projection" rel="stylesheet" href="${context}/locals/css/dynatree/ui.dynatree.css" type="text/css" />
	<script src="${context}/locals/js/jquery/jquery.cookie.js" charset="utf-8" type="text/javascript"></script>
	<script src="${context}/locals/js/jquery/jquery.dynatree-1.2.4.js" charset="utf-8" type="text/javascript"></script>
	<script charset="utf-8" type="text/javascript">
		$(document).ready(function(){
			$("#debugTree").dynatree({
				imagePath: "${context}/locals/css/dynatree", 
				noLink: "true",
				generateIds:"true"
      });
		});
	</script>
		
	<div id="debugContainer">
		<h1>Debug Information</h1>
		<div id="debugTree">
			<ul>[@printModel .data_model "Root" "varRoot" 0/]</ul>
		</div>
	</div>
[/#macro]

[#macro printModel model key id recursion]
	[#if recursion > maxRecursion]
		maxRecursion reached: ${maxRecursion}.
		[#return]
	[/#if]
	
	[#if model?is_date]
		[#assign dateFormat = 'dd/MM/yyyy;HH:mm:ss,SS Z']
		<li>${key} = ${model?string(dateFormat)} ('${dateFormat}')</li>
	
	[#elseif model?is_method]
		[@methodContent model key id recursion/]
	
	[#elseif model?is_enumerable]
		[#attempt]
			[@enumerableContent model key id recursion/]
		[#recover]
			${key} > [CAN'T ENUMERATE. Error: ${.error?html}]
		[/#attempt]
	
	[#elseif is_char(model)]
		<li>${key} = ${model?string?html}</li>
	
	[#elseif model?is_hash]
		[@hashContent model key id recursion/]
	
	[#else]
		<li>${key} = ${model?string?html}</li>
		
	[/#if]
[/#macro]

[#macro hashContent model key id recursion]
	<li data="isFolder:true">${key} >
	[#if model?has_content]
		<ul>
		[#list model?keys as k]
			[#if !ignored?seq_contains(k) && !k?contains(".")]
				[#if	k_index < maxItems]
					[@printModel model[k]!"null" k id+k?cap_first recursion+1/]
				[/#if]
			[/#if]
		[/#list]
		</ul>
	[#else]
		[EMPTY]
	[/#if]
	</li>
[/#macro]

[#macro enumerableContent model key id recursion]
	<li data="isFolder:true">${key}(${(model?size)!})>
	[#if model?has_content]
		<ul>
		[#list model as item]
			[@printModel item!"null" item_index "" recursion+1/]
		[/#list]
		</ul>
	[#else]
		EMPTY
	[/#if]
	</li>
[/#macro]

[#macro methodContent model key id recursion]
	[#if execute?seq_contains(key)]
		[@printModel model() key "" recursion+1 /]
	[/#if]
[/#macro]

[#-- utility functions --]
[#function is_char model]
	[#return model?is_hash && 'java.lang.Character' == (model.class.name)!]
[/#function]
