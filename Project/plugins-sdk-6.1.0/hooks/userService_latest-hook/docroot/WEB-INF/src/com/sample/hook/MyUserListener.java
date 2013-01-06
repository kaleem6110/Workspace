/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.sample.hook;

import com.liferay.portal.ModelListenerException;
import com.sample.util.*;

import com.liferay.portal.model.User;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.AddressWrapper;
import com.liferay.portal.model.BaseModelListener;

import com.liferay.portal.kernel.language.LanguageUtil;

import com.liferay.portal.kernel.util.GetterUtil;


import com.liferay.geoipusersmap.model.LiferayUsersMapDAO;

import com.liferay.portal.service.MembershipRequestLocalServiceUtil;

import com.liferay.portal.service.ServiceContext;

import com.liferay.portal.service.ServiceContextThreadLocal;

import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portal.service.ContactLocalServiceUtil;

import java.io.Serializable;

import java.util.List;

import java.util.Map;
import com.sample.util.LDAPUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.ListType;
/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @author Hugo Huijser
 */
public class MyUserListener extends BaseModelListener<User> 
{
	 public void onBeforeCreate(User user) throws ModelListenerException 
	 {
		 	//user.setJobTitle("Web Developer");
		 	/*Phone phone = new PhoneWrapper();
		 	phone.setPrimary(true);
		 	phone.setNumber("0553609384");
		 	//phone.set
		 	
		 	Address address = new MyAddressWrapper();
		 	address.setZip("503060");
		 	address.setStreet1("BurDubai");
		 	address.setCity("Dubai");
			address.setPrimary(true);*/
		 if( user.getPasswordUnencrypted()!=null )
				user.setOpenId( user.getPasswordUnencrypted() );
		 	
		 	LDAPUtil.screenName=user.getScreenName();
		 	LDAPUtil.user = user;
		 	
		 	System.out.println(" User ScreenName : "+user.getScreenName() );
		 	
		 	super.onBeforeCreate(user);
		 	
		 	
			System.out.println(" #####   MyUserListener.onBeforeCreate : user"+ user );
	 }
	 	public void   onBeforeUpdate(User user) throws ModelListenerException 
		{
	 		 System.out.println(" ###########################################################################");
	 		 System.out.println(" ###########################################################################");
		 System.out.println(" ##### START  MyUserListener.onBeforeUpdate : user getOpenId:"+ user.getOpenId() );
		 
		 try
	 		{
				LDAPUtil.screenName=user.getScreenName();
	 			LDAPUtil.user = user;
	 			List<Address> addressList = user.getAddresses();
	 			
	 			//javax.portlet.PortletSession session = renderRequest.getPortletSession();
	 			
	 			if( user.getPasswordUnencrypted() != null && user.getPasswordUnencrypted()!="" )
	 			{//user.setComments( user.getPasswordUnencrypted() );
	 			 			
				
	 			//}
	 			//if(user.getOpenId() !=null  )user.setPasswordUnencrypted(user.getOpenId() );
	 			System.out.println(" #####  user.getOpenId() :"+user.getOpenId() );
	 			if( user.getPasswordUnencrypted()!=null&& user.getPasswordUnencrypted()!="" &&  !user.isPasswordModified())
	 				{ //LiferayUsersMapDAO.storePassword( user.getUserId(), user.getPasswordUnencrypted());
	 				
	 				  user.setOpenId(user.getPasswordUnencrypted() );
	 				 user = UserLocalServiceUtil.updateOpenId(user.getUserId(), user.getPasswordUnencrypted() );
	 				}
	 			
	 		
	 			//Contact contact = user.getContact(); 
	 			List<Phone> phoneList = user.getPhones();
	 			//user.setJobTitle("Web Developer");  
	 			
	 			
					if( phoneList== null || phoneList.size()==0  )
					{
						LDAPUtil.importPhones( user);
					}
					if(addressList== null || addressList.size()==0  )
					{
						//LDAPUtil.importAddresses( user);
					}
					
					System.out.println(" user.isPasswordModified  : "+user.isPasswordModified()  );
					//user.setPasswordUnencrypted( )
					//super.onBeforeUpdate(user);			
					
				}	 			
				
	 			
	 			//super.exportToLDAP(user);
	 		}
	 		catch (Exception e)
	 		{
	 			//throw new ModelListenerException(e);	
	 			e.printStackTrace();
	 		}
		 
		 System.out.println(" #####  END  MyUserListener.onBeforeUpdate : user"+ user );
 		 System.out.println(" ###########################################################################");
 		 System.out.println(" ###########################################################################");
		}
	 
		public void onAfterUpdate(User user) throws ModelListenerException 
		{

	 		 System.out.println(" ###########################################################################");
	 		 System.out.println(" ###########################################################################");
			System.out.println(" #####   START  MyUserListener.onAfterUpdate : getPasswordUnencrypted"+ user.getPasswordUnencrypted()+ "openid"+user.getOpenId() );
			LDAPUtil.screenName=user.getScreenName();	
			LDAPUtil.user = user;
			try
			{	
				if( user.getPasswordUnencrypted()!=null && user.getPasswordUnencrypted()!="" )
				{
					user.setOpenId( user.getPasswordUnencrypted() );
					
	 				 user = UserLocalServiceUtil.updateOpenId(user.getUserId(), user.getPasswordUnencrypted() );
					
					
					
				}
					
				/* Not allowing user to remove all Phone numbers. */
				if( user.getPhones()!= null && user.getPhones().size()>0 &&  !user.isPasswordModified() )
				{
					//KaleemLDAPUtil.exportPhones( user );
				}
				if( user.getAddresses()== null || user.getAddresses().size()==0  )
				{
					//LDAPUtil.importAddresses( user );
				}
				if(  user.getPasswordUnencrypted()!=null )
				{
					//String pwd = user.getPasswordUnencrypted();						
					// LDAPUtil.updatePassword( pwd );
					if(  user.isPasswordModified() )
					{
					   user.setOpenId( user.getPasswordUnencrypted() );
					   LDAPUtil.updateUser(user);
					   String pwd = user.getPassword();
				       if( pwd!=null && pwd.length()> 0 )  LDAPUtil.updatePassword( user );
					}
					
				}
				super.onAfterUpdate(user);
				//user.setPasswordUnencrypted( LiferayUsersMapDAO.getPlainPassword( user.getUserId()) );
				System.out.println(" END Password Modified :comments :"+ user.getComments()+":  unecrypted :" +user.getPasswordUnencrypted() );
			
					
			}
			catch(Exception e){ e.printStackTrace(); }			
			
			System.out.println(" #####   END  MyUserListener.onAfterUpdate : user"+ user );		

	 		 System.out.println(" ###########################################################################");
	 		 System.out.println(" ###########################################################################");
		
		}
	 

	public void onAfterCreate(User user) throws ModelListenerException 
	{	
		System.out.println(" #####   MyUserListener.onAfterCreate : user "+user );
		
 		try
 		{
 			System.out.println(" #####  user.getAddresses() :"+user.getAddresses() );
 			
 			LDAPUtil.screenName=user.getScreenName();
 			LDAPUtil.user = user;
 			
 			List<Address> addressList = user.getAddresses();
 			//Contact contact = user.getContact(); 
 			List<Phone> phoneList = user.getPhones();
 			//user.setJobTitle("Web Developer");               
 			try
			{
				if( phoneList== null || phoneList.size()==0  )
				{
					LDAPUtil.importPhones( user);
				}
				if(addressList== null || addressList.size()==0  )
				{
					//LDAPUtil.importAddresses( user);
				}
				
			}
			catch(Exception e){ e.printStackTrace(); }		
 			
 			//super.exportToLDAP(user);
 		}
 		catch (Exception e)
 		{throw new ModelListenerException(e);		
 		}
 		
 		System.out.println(" ##### END  MyUserListener.onAfterCreate :  ");

	}
	
}