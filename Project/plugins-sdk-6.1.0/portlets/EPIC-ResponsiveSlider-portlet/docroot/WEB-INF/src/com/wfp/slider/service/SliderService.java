/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wfp.slider.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import javax.portlet.PortletPreferences;
import com.wfp.slider.util.AppConstants;
import com.wfp.slider.util.Slide;
/**
 * <a href="SliderService.java.html"><b><i>View Source</i></b></a>
 * 
 * @author MOHAMMED KALEEM
 * 
 */

public class SliderService  {
	private static Log _log = LogFactoryUtil.getLog(SliderService.class);
	
	public static String editSlide(String preferencesText , Slide slide)
	{
		_log.info(" !!!!!!START   SliderService editSlide !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");		
		String newPreference = "";
		if( preferencesText!=null && preferencesText!="")
		{
			String slideArray[]= preferencesText.split ( AppConstants.SLIDE_DELIMITER) ;
			for(int i=0;i<slideArray.length;i++)
			{
				String slideStr = slideArray[i];
				String fieldArray [] =slideStr.split( AppConstants.FIELD_DELIMITER);
				String field = fieldArray[0];				
				if(field.trim().equals(slide.getPrevTitle() )==false)
				{				
					newPreference += slideStr;					
				}
				else
				{
					newPreference += slide.getTitle()+ AppConstants.FIELD_DELIMITER +slide.getText() 
							+AppConstants.FIELD_DELIMITER + slide.getImgUrl();
				}
				if(i<(slideArray.length-1)) newPreference +=AppConstants.SLIDE_DELIMITER;
			}			
		}
		
	
		_log.info(" !!!!!!END   SliderService editSlide !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		return newPreference;
	}
	
	public static String deleteSlide(String preferencesText , String title)
	{
		_log.info(" !!!!!!START   SliderService deleteSlide !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		String newPreference = "";
		if( preferencesText!=null && preferencesText!="")
		{
			String slideArray[]= preferencesText.split ( AppConstants.SLIDE_DELIMITER) ;
			for(int i=0;i<slideArray.length;i++)
			{
				String slide = slideArray[i];
				String fieldArray [] =slide.split( AppConstants.FIELD_DELIMITER);
				String field = fieldArray[0];				
				if(field.trim().equals(title )==false)
				{				
					newPreference += slide;
					if(i<(slideArray.length-1)){_log.info( field + "@@@@"  ); newPreference +=AppConstants.SLIDE_DELIMITER;}
				}
			}			
		}
			
		_log.info(" !!!!!!END   SliderService deleteSlide !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		return newPreference;		
	}
	public static String shuffleSlides(int position,int direction, String preferencesText  )
	{	_log.info(" !!!!!!START   SliderService shuffleSlides !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		String newPreference = "";
		String slideArray[]= preferencesText.split ( AppConstants.SLIDE_DELIMITER) ;
		int pos = position+1;
		if( direction > 0 ) pos = position-1;
		String temp = slideArray[position];
		slideArray[position] =slideArray[pos];
		slideArray[pos]=temp;
		for(int i=0;i<slideArray.length;i++)
		{
			String slide = slideArray[i];
			String fieldArray [] =slide.split( AppConstants.FIELD_DELIMITER);					
			newPreference += slide;
			if(i<(slideArray.length-1)) newPreference +=AppConstants.SLIDE_DELIMITER;
			
		}
		_log.info(" !!!!!!END   SliderService shuffleSlides !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
		return newPreference;
		
	}
	public static String moveSlide(String preferencesText , String title,String directionStr )
	{
		_log.info(" !!!!!!START   SliderService moveSlide !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		String newPreference = "";
		int direction = Integer.parseInt( directionStr);
		if( preferencesText!=null && preferencesText!="")
		{
			String slideArray[]= preferencesText.split ( AppConstants.SLIDE_DELIMITER) ;
			for(int i=0;i<slideArray.length;i++)
			{
				String slide = slideArray[i];
				String fieldArray [] =slide.split( AppConstants.FIELD_DELIMITER);
				String field = fieldArray[0];				
				if(field.trim().equals(title ))
				{				
					if( ( i==0 && direction>0 )|| ( (i==(slideArray.length-1)) && direction<0) ) 
						return null;
					else { newPreference = shuffleSlides(i, direction, preferencesText  ); break ; }
				}
			}			
		}
		_log.info(" !!!!!!END   SliderService moveSlide !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
		return newPreference;
		
	}
}
