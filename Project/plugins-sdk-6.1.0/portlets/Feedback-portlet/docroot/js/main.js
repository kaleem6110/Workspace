jQuery(document).ready( function()
{
				function launch() 
				{
					jQuery('#feedback').lightbox_me({centered: true, onLoad: function() { jQuery('#feedback').find('input:first').focus()}});
				}

				jQuery('#try-1').click(function(e)
				{
					jQuery("#feedback").lightbox_me({centered: true, onLoad: function()
					{
						jQuery("#feedback").find("input:first").focus();
						}
					
					}); 
					e.preventDefault();

				});
			
});