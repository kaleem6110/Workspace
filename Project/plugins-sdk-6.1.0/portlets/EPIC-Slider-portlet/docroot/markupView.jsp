<div class="wrappers clearfix">
	<div class="carousel clearfix">
		<div class="panel">
			<div class="details_wrapper">
				<div class="details">
					<% for( Slide slid : slideList){%>
					<div class="detail">
						<h2>
							<a href="#"><%=slid.getTitle()%></a>
						</h2>
						<%=slid.getText()%>
						<p>
							<a href="#" title="Read more" class="more">Read more</a>
					</div>
					<% }%>
				</div>
			</div>
			<div class="paging">
				<div id="numbers"></div>
				<a href="javascript:void(0);" class="previous" title="Previous">Previous</a>
				<a href="javascript:void(0);" class="next" title="Next">Next</a>
			</div>
			<a href="javascript:void(0);" class="play" title="Turn on autoplay">Play</a>
			<a href="javascript:void(0);" class="pause" title="Turn off autoplay">Pause</a>
		</div>
		<div id="slider-photo-container">
			<div class="backgrounds">
				<% for( Slide slid : slideList){%>
				<div class="item item_1" style="width:<%=param_sliderWidth%>">
					<img alt="<%=param_imgALT%>" class="<%=param_imgCSS%>"
					
						onclick="pausse()" src="<%=slid.getImgUrl()%>" />
				</div>
				<% }%>
			</div>
		</div>
	</div>
</div>
