
<div class="tabcontent" id="view2">
<form action='<liferay-portlet:actionURL  />' method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
<div id="tab1_statusMessage" style="display:none" class="portlet-msg-success" >Your request completed successfully. </div>
	<div id="sliderDisplaySettings">
	<div id="imageSliderSetting">
		<h3>Image Setting</h3>
		<div class="sliderRow">
			<div class="column-1">Enter image width(in px) :</div>
			<div class="column-2">
					<input type="text" id="imgWidth" name="imgWidth" value="<%=param_imgWidth%>"></input>
			 </div>
		</div>
		<div class="sliderRow">
			<div class="column-1">Enter image height(in px) :</div>
			<div class="column-2">
					<input type="text" id="imgHeight" name="imgHeight" value="<%=param_imgHeight%>"></input>
			 </div>
		</div>
		<div class="sliderRow">
			<div class="column-1">Enter ALT text  :</div>
			<div class="column-2">
					<input type="text" id="imgALT" name="imgALT" value="<%=param_imgALT%>"></input>
			 </div>
		</div>
		<div class="sliderRow">
			<div class="column-1">Enter CSS classnames  :</div>
			<div class="column-2">
					<input type="text" id="imgCSS" name="imgCSS" value="<%=param_imgCSS%>"></input>
			 </div>
		</div>
		<div class="sliderRow">
			<div class="column-1"><input style="margin-right: 30px" type="button"  onclick="<portlet:namespace />validateDisplaySettings()" value='<liferay-ui:message key="Save" />' /> </div>
			<div class="column-2">
					<input type="button" style="display:none"  value='<liferay-ui:message key="Cancel" />' />
			 </div>
		</div>
		</div>
		<div id="sliderSetting">
		<h3>Slider Setting</h3>
		<div class="sliderRow">
			<div class="column-1">Enter slider width(in px) :</div>
			<div class="column-2">
					<input type="text" name="sliderWidth" id="sliderWidth" value="<%=param_sliderWidth%>"></input>
			 </div>
		</div>
		<div class="sliderRow">
			<div class="column-1">Enter slider height(in px) :</div>
			<div class="column-2">
					<input type="text" name="sliderHeight" id="sliderHeight" value="<%=param_sliderHeight%>"></input>
			 </div>
		</div>
		
		
		<div class="sliderRow">
			<div class="column-1"><input type="hidden" id="cmd" name="cmd" 	value="savePreference"></input></div>
			<div class="column-2">
					&nbsp;<input type="hidden" id="uniqueWidthId"
				name="uniqueWidth" value=""></input> <input type="hidden"
				id="uniqueHeightId" name="uniqueHeightId" value=""> <input
				type="hidden" id="uniqueURLId" name="uniqueURLId" value="">
			 </div>
		</div>
		</div>
	</div><!-- END of sliderDisplaySettings -->
	
	<!-- ------------------------------------------------ -->
</form>	
</div>