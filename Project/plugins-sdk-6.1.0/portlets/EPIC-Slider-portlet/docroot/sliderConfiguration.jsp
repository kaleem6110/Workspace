<div class="tabcontent" id="view1">
	<form action='<liferay-portlet:actionURL  />' method="post"
		name="<portlet:namespace />fm2" id="<portlet:namespace />fm2">
		
		<div id="configContainer">
			<div id="configSlider">
				<h3>Slides</h3>
				<div id="tab2_1_statusMessage" style="display: none"
					class="portlet-msg-success">Your request completed
					successfully.</div>
				<div id="contentHolder">
					<table class="taglib-search-iterator">
						<thead>
							<tr class="results-row">
								<th style="width: 30px">S.No</th>
								<th>Title</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<% int i=1;
    for(Slide tempSlide : slideList ){
    	String slideString = tempSlide.getTitle()+":DELIM:"+tempSlide.getText()+":DELIM:"+tempSlide.getImgUrl();
   %>

							<tr class="results-row" id="results-row_<%=i+1%>">
								<td><%=i%></td>
								<td><%=tempSlide.getTitle() %></td>
								<td headers="jtqn_col-3" colspan="1"
									class="align-center col-3 last valign-middle" id=""><span
									id="aui_3_4_0_1_257"><a href="#"
										onclick="<portlet:namespace />moveSlide('<%=tempSlide.getTitle()%>', 1)"><img
											title="Move Up" alt="Move Up"
											src="/html/themes/classic/images/common/top.png" class="icon"
											id="aui_3_4_0_1_256"> </a> </span> <span id="aui_3_4_0_1_263"><a
										href="#"
										onclick="<portlet:namespace />moveSlide('<%=tempSlide.getTitle()%>',-1)"><img
											title="Move Down" alt="Move Down"
											src="/html/themes/classic/images/common/bottom.png"
											class="icon" id="aui_3_4_0_1_262"> </a> </span> <span
									id="aui_3_4_0_1_265"><a href="#"
										onclick="<portlet:namespace />editSlide('<%=slideString%>')"><img
											title="Edit" alt="Edit"
											src="/html/themes/classic/images/common/edit.png"
											class="icon" id="aui_3_4_0_1_264"> </a> </span> <span
									id="aui_3_4_0_1_268"><a href="#"
										onclick="<portlet:namespace />deleteSlide('<%=tempSlide.getTitle()%>')"><img
											class="icon"
											src="/html/themes/classic/images/common/delete.png"
											alt="Delete" title="Delete" id="aui_3_4_0_1_266"> </a> </span> <!-- liferay-ui:icon image="delete" label="" url="deleteURL" / -->

								</td>
							</tr>

							<%i++;
      }
      %>
						</tbody>
					</table>
				</div>

				<div id="SlideButton">
					<input id="AddSlide" style="margin-top: 30px"
						onclick="<portlet:namespace />appendSlide()" type="button"
						value='<liferay-ui:message key="ADD NEW" />' />
				</div>
			</div>
			<div id="addSlider">
				<h3>Slide Detail</h3>
				<div id="tab2_2_statusMessage" style="display: none"
					class="portlet-msg-success">Your request completed
					successfully.</div>
				<div id="addSlider_column1">
					<table>

						<tr class="label">
							<td>Enter Title</td>
						</tr>
						<tr class="slideBody">
							<td><input type="text" id="addTitle" /></td>
						</tr>
						<tr class="label">
							<td>Enter Text</td>
						</tr>
						<tr class="slideBody" style="height:80px">
							<td><textarea  id="addText" >&nbsp;</textarea></td>
						</tr>
						<tr class="slideBody">
							<td><input id="saveSlideButton"
								onclick="<portlet:namespace />validateNewSlide()"
								style="margin-right: 30px" type="button"
								value='<liferay-ui:message key="Save" />' /> <input
								style="display: none" id="updateSlideButton"
								onclick="<portlet:namespace />validateEditSlide()"
								style="margin-right: 30px" type="button"
								value='<liferay-ui:message key="Update" />' /> <input
								style="display: none" id="backButton"
								onclick="<portlet:namespace />goBack()"
								style="margin-right: 30px" type="button"
								value='<liferay-ui:message key="Back" />' /><input
								type="hidden" id="cmd" name="cmd" value="saveconfig" /></td>
						</tr>

					</table>
				</div>
				<div id="addSlider_column2">
					<br> <b>Image Preview</b> <br> <input name="imageButton"
						type="button" value="Choose Image" onClick="selectImage()" /> <br>
					<img id="imagePreview" src="<%=slideImage%>" width="170"
						height="100" style="display: block; margin: 10px 0px;" />
				</div>
			</div>

		</div>
	</form>

</div>




