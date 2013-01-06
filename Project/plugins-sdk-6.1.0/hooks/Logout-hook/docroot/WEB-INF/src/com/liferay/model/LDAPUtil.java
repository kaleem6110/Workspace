/**
 * 
 */
package com.liferay.model;

/**
 * @author kaleem.mohammed
 *
 */

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.List;

import java.util.ArrayList;
import javax.naming.*;
import javax.naming.directory.*;


import com.liferay.portal.model.Contact;

/*
 * Retrieve several attributes of a particular entry.
 *
 * [equivalent to getattrs.c in Netscape SDK]
 */
public class LDAPUtil 
{
	public static String screenName=null;
	
	public static DirContext  getLDAPContext( )
	{
		System.out.println(" ############## START  LDAPUtil.getLDAPContext #####################");
		Hashtable env = new Hashtable(5, 0.75f);	
		
		DirContext ctx = null;

		// env.put(LdapContext.CONTROL_FACTORIES,
		// conf.getProperty("ldap.factories.control"));
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap-dev.globalepic.lu:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,
				"cn=wfp-write,ou=ldapAccess,dc=emergency,dc=lu");
		env.put(Context.SECURITY_CREDENTIALS, "My3CatsOnATree");
		env.put(Context.STATE_FACTORIES, "PersonStateFactory");
		env.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL,
				"ldap://ldap-dev.globalepic.lu:389/dc=emergency,dc=lu");

		// env.put(Context.INITIAL_CONTEXT_FACTORY, Env.INITCTX);
		/* Specify host and port to use for directory service */
		// env.put(Context.PROVIDER_URL, Env.MY_SERVICE);
		try 
		{
			/* get a handle to an Initial DirContext */
			//DirContext ctx = new InitialDirContext(env);	
			System.out.println(" ############## END  LDAPUtil.getLDAPContext #####################");
			ctx=  new InitialDirContext(env);
		}
		catch(Exception e){	e.printStackTrace();}
		
		return ctx;

	}

	public  LDAPUserInfo getLDAPUserInfo( String userName )
	{
		
		System.out.println(" ############## START  LDAPUtil.getLDAPUser #####################");
		
		LDAPUserInfo ldapUserInfo = new LDAPUserInfo();
		
		/* get a handle to an Initial DirContext */
		DirContext ctx = getLDAPContext( );			
		
		//Attributes attrs = sr.getAttributes();			
		Attributes attrs = getAllAttributes ( ctx, userName );
		
		populateLDAPUser(ldapUserInfo, attrs ) ;
		
		System.out.println(" ############## END  LDAPUtil.getLDAPUser #####################");
		return ldapUserInfo;
	}
	
	public static Attributes getAllAttributes(DirContext ctx, String userName)
	{
		System.out.println(" ############## START  LDAPUtil.getAllAttributes ##################### screenName :"+screenName);
		// Specify the search filter
		Attributes attrs = null;
		try
		{
		String FILTER = "(&(objectClass=top) ((uid=" + userName + ")))";

		// limit returned attributes to those we care about
		String[] attrIDs = { "sn", "cn", "mobile", "postalCode",
				"telephoneNumber", "street", "communicationUri", "o", "c" ,"uid"};

		SearchControls ctls = new SearchControls();
		ctls.setReturningAttributes(attrIDs);
		ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		System.out.println(" 1");
		// Search for objects using filter and controls
		NamingEnumeration answer = ctx.search(
				"ldap://ldap-dev.globalepic.lu:389/uid=" + screenName
						+ ",ou=users,ou=people,dc=emergency,dc=lu", FILTER,
				ctls);
		System.out.println("2");
		SearchResult sr = (SearchResult) answer.next();
		System.out.println(" 3");
		
		 attrs = sr.getAttributes();
		}
		catch(Exception e) { e.printStackTrace(); }
		
		System.out.println(" ############## END  LDAPUtil.getAllAttributes #####################");
		
		return attrs;
	}
	public static void populateLDAPUser(LDAPUserInfo ldapUserInfo, Attributes attrs ) 
	{	
		System.out.println(" ############## START  LDAPUtil.populateLDAPUser #####################");		
		try
		{
			//screenName = attrs.get("uid").toString();
			ldapUserInfo.surName = attrs.get("sn").toString();
			ldapUserInfo.givenName = attrs.get("cn").toString();
			
			Object mobileObj = attrs.get("mobile");
			Object postalCodeObj = attrs.get("postalCode");
			Object organizationObj = attrs.get("mobile");
			Object streetObj = attrs.get("street");
			Object countryObj = attrs.get("street");
			
			if( mobileObj!=null) ldapUserInfo.mobile = mobileObj.toString();
			if( postalCodeObj!=null) ldapUserInfo.postalCode = postalCodeObj.toString();
			if( organizationObj!=null) ldapUserInfo.organization = organizationObj.toString();
			if( streetObj!=null) ldapUserInfo.street = streetObj.toString();
			if( countryObj!=null) ldapUserInfo.country = countryObj.toString();
						
			System.out.println(" ############## ldapUserInfo  "+ldapUserInfo );
			System.out.println(" ############## ldapUserInfo.surName  "+ldapUserInfo.surName + " communicationUri :"+ attrs.get("communicationUri") );
			
			List<String> commuriList = null;
			
			if( attrs.get("communicationUri")!=null )
			{
				commuriList = getCommUrlList( attrs );				
			}			
			for (int j = 0; commuriList!=null&& j < commuriList.size(); j++)
			{
				String temp = commuriList.get(j);	
				System.out.println(" ############## temp "+temp );
				if (temp.indexOf("gtalk") != -1) {
					ldapUserInfo.gtalk = temp.replace("gtalk:chat?jid=","");					
				} else if (temp.indexOf("msnim") != -1) {
					ldapUserInfo.msn = temp.replace("msnim:chat?contact=","");					
				} else if (temp.indexOf("skype") != -1) {
					ldapUserInfo.skype = temp.replace("skype:","");					
				} else if (temp.indexOf("sip") != -1) {
					ldapUserInfo.sip = temp.replace("sip:","");					
				}
			}
		}
		catch(Exception e) { e.printStackTrace(); }
		System.out.println(" ############## END  LDAPUtil.populateLDAPUser #####################");
		
	}
	public static List<String> getCommUrlList(Attributes attrs )
	{
		System.out.println(" ############## START  LDAPUtil.getCommUrlList #####################");
		List<String> commuriList = new ArrayList<String>();		
		int i=0;
		try
		{
			NamingEnumeration nString = attrs.get("communicationUri").getAll();			
			while (nString.hasMore()) 
			{
				commuriList.add(attrs.get("communicationUri").get(i).toString());
				nString.next();		
				i=i+1;
			}
		}
		catch(Exception e){ e.printStackTrace(); }
		System.out.println(" ############## END  LDAPUtil.getCommUrlList #####################");
		return commuriList;
	}
	
}
