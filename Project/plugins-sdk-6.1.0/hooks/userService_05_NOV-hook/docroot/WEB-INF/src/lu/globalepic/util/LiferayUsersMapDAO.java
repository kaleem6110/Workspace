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

package lu.globalepic.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;


/**
 * <a href="LiferayUsersMapDAO.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jose Miguel Trinchan
 *
 */

public class LiferayUsersMapDAO {

	
	private static final String _UPDATE_PASSWORD = "update pink_elephant set plain=? where userid=?;";
	private static final String _INSERT_PASSWORD = "insert into pink_elephant (plain,userid) values (?,?);";
	private static final String _GET_PASSWORD_BY_ID ="select plain from pink_elephant where userid=?;"; 
	private static final String _UPDATE_ORIGINAL_PASSWORD ="update user_ set password_= ? where userid=?;";
	private static String algorithm = "DESede";
	private static String DB_NAME = "liferay-dev";
	private static String DB_USER_NAME = "kmohammed";
	private static String DB_PWD = "welcome";//"F2JcodZyf29KQNnJNa3T";//"welcome";//;
	private static String connectionURL = "jdbc:postgresql://localhost:5432/liferay-portal";

	

	public static boolean storePassword( long userId, String pwd )
	{
		System.out.println(" ################################################");
		System.out.println(" START LiferayUsersMapDAO.storePassword ######## pwd : "+pwd);
		System.out.println(" ################################################");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isStored = false;		
		try 
		{
			Class.forName("org.postgresql.Driver");
//			System.out.println(" #11111111");	
			con = DriverManager.getConnection (connectionURL,DB_USER_NAME,DB_PWD );
//			System.out.println(" #con"+con);
			//con = LPortalConnectionPool.getConnection();
			//Context ctx = new InitialContext();   
			//DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Liferay");   
			//con = ds.getConnection(); 
			
			ps = con.prepareStatement(_UPDATE_PASSWORD);
			System.out.println(" #ps"+ps);
			
			// Key key = KeyGenerator.getInstance(algorithm).generateKey();
			// Cipher  cipher = Cipher.getInstance(algorithm);
			 
		    //  byte[] encryptionBytes = encrypt(pwd.trim(),key, cipher);
		    //  String encryptedPwd = new String(encryptionBytes,"UTF-8");
		      
		      
			ps.setString(1, pwd.trim() );
			System.out.println(" kkkkk");
			ps.setLong(2, userId  );

			System.out.println(" lllllps:"+ps);
			int res =ps.executeUpdate();

			System.out.println(" mmmmm");
			
			System.out.println("���������  res : "+res);
			
			if( res >= 0 )
			{
				System.out.println(" updated @@@ :"+ps);
			}
			else
			{
				ps = con.prepareStatement(_INSERT_PASSWORD);
				ps.setString(1, pwd.trim() );
				ps.setLong(2, userId  );
				res =ps.executeUpdate();				
				System.out.println(" inserted @@@  : res"+res);	
			}
			ps.close();
			con.close();
			isStored= true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//finally {LPortalConnectionPool.cleanUp(con, ps, rs);}
		System.out.println(" ################################################");
		System.out.println(" END LiferayUsersMapDAO.storePassword ######## UserID : "+userId);
		System.out.println(" ################################################");
		
		return isStored;
		
	}
	public static String getPlainPassword( long userId )
	{
		System.out.println(" ################################################");
		System.out.println(" START LiferayUsersMapDAO.getPlainPassword ######## UserID : "+userId);
		System.out.println(" ################################################");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String encryptionBytesFromDB = null;
		
		try 
		{
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection (connectionURL,DB_USER_NAME,DB_PWD );
			//con = LPortalConnectionPool.getConnection();
			//Context ctx = new InitialContext();   
			//DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Liferay");   
			//con = ds.getConnection(); 
			ps = con.prepareStatement(_GET_PASSWORD_BY_ID );
			
			ps.setLong(1, userId  );
	
			rs=ps.executeQuery();
			while (rs.next()) 
			{				
				 encryptionBytesFromDB = rs.getString(1);
				System.out.println(" encryptionBytesFromDB "+encryptionBytesFromDB);
				
			//	Key  key = KeyGenerator.getInstance(algorithm).generateKey();
				//Cipher  cipher = Cipher.getInstance(algorithm);
			//	String pwd = decrypt(encryptionBytesFromDB.getBytes(), key, cipher);
				//System.out.println("���������  res  pwd : "+pwd );
				//ps.close();
				//con.close();
				
						
			}
			
			System.out.println("���������  res : "+rs);

			ps.close();
			con.close();
			
					
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//finally {LPortalConnectionPool.cleanUp(con, ps, rs);}
		System.out.println(" ################################################");
		System.out.println(" END LiferayUsersMapDAO.getPlainPassword ######## UserID : "+userId);
		System.out.println(" ################################################");
		
		return encryptionBytesFromDB;
		
	}
	public static boolean updateOriginalPassword( long userId, String pwd )
	{
		System.out.println(" ################################################");
		System.out.println(" START LiferayUsersMapDAO.updateOriginalPassword ######## UserID : "+userId);
		System.out.println(" ################################################");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdated = false;
		
		try 
		{
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection (connectionURL,DB_USER_NAME,DB_PWD );
			//con = LPortalConnectionPool.getConnection();
			//Context ctx = new InitialContext();   
			//DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Liferay");   
			//con = ds.getConnection(); 
			
			ps = con.prepareStatement(_UPDATE_ORIGINAL_PASSWORD);
		
		      
		      
			ps.setString(1, pwd.trim() );
			ps.setLong(2, userId  );
			int res =ps.executeUpdate();
			
			System.out.println("���������  res : "+res);
			
			if( res >= 0 )
			{

				ps.close();
				con.close();
				isUpdated= true;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//finally {LPortalConnectionPool.cleanUp(con, ps, rs);}
		System.out.println(" ################################################");
		System.out.println(" END LiferayUsersMapDAO.updateOriginalPassword ######## UserID : "+userId);
		System.out.println(" ################################################");
		
		return isUpdated;
		
	}
	 private static byte[] encrypt(String pwd,Key key, Cipher cipher) throws InvalidKeyException, BadPaddingException,
     IllegalBlockSizeException
     {
		 System.out.println(" ########## START LiferayUsersMapDAO encrypt ################pwd"+pwd);
		 cipher.init(Cipher.ENCRYPT_MODE, key);
		 byte[] inputBytes = pwd.getBytes();
		 System.out.println(" ############### END LiferayUsersMapDAO encrypt ##########pwd encrypted"+inputBytes);
		 
		 return cipher.doFinal(inputBytes);
     }
	 private static String decrypt(byte[] encryptionBytes, Key key, Cipher cipherr) throws InvalidKeyException,
     BadPaddingException, IllegalBlockSizeException 
     {
		 System.out.println(" ########## START LiferayUsersMapDAO decrypt ################encryptionBytes:"+encryptionBytes);
		 cipherr.init(Cipher.DECRYPT_MODE, key);
		 byte[] recoveredBytes = cipherr.doFinal(encryptionBytes);
		 System.out.println(" recoveredBytes "+recoveredBytes );
		 String recovered = new String(recoveredBytes);
		 System.out.println(" ########## START LiferayUsersMapDAO decrypt ################recovered"+recovered);
		 
		 return recovered;
      }
	

}
