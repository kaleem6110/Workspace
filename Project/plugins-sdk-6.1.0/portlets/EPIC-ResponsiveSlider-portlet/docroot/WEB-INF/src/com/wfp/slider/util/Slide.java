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

package com.wfp.slider.util;

/**
 * <a href="Slide.java.html"><b><i>View Source</i></b></a>
 * 
 * @author MOHAMMED KALEEM
 * 
 */

public class Slide  {
	
private String title;
private String text;
private String imgUrl;
private String prevTitle;

public void setTitle(String title)
{
	this.title = title;
}
public String getTitle()
{
	return this.title;
}
public void setText(String text)
{
	this.text = text;
}
public String getText()
{
	return this.text;
}
public void setImgUrl(String imgUrl)
{
	this.imgUrl = imgUrl;
}
public String getImgUrl()
{
	return this.imgUrl;
}
public void setPrevTitle(String prevTitle)
{
	this.prevTitle = prevTitle;
}
public String getPrevTitle()
{
	return this.prevTitle;
}
}
