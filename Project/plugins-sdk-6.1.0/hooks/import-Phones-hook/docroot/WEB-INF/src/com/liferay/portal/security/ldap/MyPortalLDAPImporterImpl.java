/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.security.ldap;

import com.liferay.portal.security.ldap.PortalLDAPImporterImpl;

public class MyPortalLDAPImporterImpl extends PortalLDAPImporterImpl 
{
	
	static{
		
		System.out.println(" static MyPortalLDAPImporterImpl ");
		
	}

	/*public void importFromLDAP() throws Exception {
		
		super.importFromLDAP();
		
		System.out.println(" MyPortalLDAPImporterImpl importFromLDAP ");
	}

	public void importFromLDAP(long companyId) throws Exception {
							super.importFromLDAP(companyId);
		
		System.out.println(" MyPortalLDAPImporterImpl importFromLDAP : companyId "+ companyId);
	}

	public void importFromLDAP(long ldapServerId, long companyId)
		throws Exception {

			super.importFromLDAP(ldapServerId, companyId);
		
		System.out.println(" MyPortalLDAPImporterImpl importFromLDAP : ldapServerId "+ ldapServerId +": companyId "+ companyId);
	}

	public User importLDAPUser(
			long ldapServerId, long companyId, LdapContext ldapContext,
			Attributes attributes, String password,
			boolean importGroupMembership)
		throws Exception {
		System.out.println(" MyPortalLDAPImporterImpl importLDAPUser : ldapServerId "+ ldapServerId +": companyId "+ companyId);

		return super.importLDAPUser(ldapServerId, companyId,ldapContext,attributes,password,importGroupMembership);
		
	
	}

	public void setLDAPToPortalConverter(
		LDAPToPortalConverter ldapToPortalConverter) {

		_ldapToPortalConverter = ldapToPortalConverter;
	}

	protected User createLiferayUser(
		long companyId, LDAPUser ldapUser, String password) {

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Adding user to portal " + ldapUser.getEmailAddress());
			}

			Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

			birthdayCal.setTime(ldapUser.getBirthday());

			int birthdayMonth = birthdayCal.get(Calendar.MONTH);
			int birthdayDay = birthdayCal.get(Calendar.DAY_OF_MONTH);
			int birthdayYear = birthdayCal.get(Calendar.YEAR);

			return UserLocalServiceUtil.addUser(
				ldapUser.getCreatorUserId(), companyId,
				ldapUser.isAutoPassword(), password, password,
				ldapUser.isAutoScreenName(), ldapUser.getScreenName(),
				ldapUser.getEmailAddress(), ldapUser.getOpenId(),
				ldapUser.getLocale(), ldapUser.getFirstName(),
				ldapUser.getMiddleName(), ldapUser.getLastName(),
				ldapUser.getPrefixId(), ldapUser.getSuffixId(),
				ldapUser.isMale(), birthdayMonth, birthdayDay, birthdayYear,
				ldapUser.getJobTitle(), ldapUser.getGroupIds(),
				ldapUser.getOrganizationIds(), ldapUser.getRoleIds(),
				ldapUser.getUserGroupIds(), ldapUser.isSendEmail(),
				ldapUser.getServiceContext());

		}
		catch (Exception e) {
			_log.error(
				"Unable to add user with screen name " +
					ldapUser.getScreenName() + " and email address " +
						ldapUser.getEmailAddress(),
				e);

			return null;
		}
	}

	protected User doImportLDAPUser(
			long ldapServerId, long companyId, LdapContext ldapContext,
			Attributes attributes, String password,
			boolean importGroupMembership)
		throws Exception {

		AttributesTransformer attributesTransformer =
			AttributesTransformerFactory.getInstance();

		attributes = attributesTransformer.transformUser(attributes);
		
		Properties userMappings = LDAPSettingsUtil.getUserMappings(
			ldapServerId, companyId);
		
		Properties userExpandoMappings =
			LDAPSettingsUtil.getUserExpandoMappings(
				ldapServerId, companyId);
		Properties contactMappings = LDAPSettingsUtil.getContactMappings(
			ldapServerId, companyId);
		Properties contactExpandoMappings =
			LDAPSettingsUtil.getContactExpandoMappings(ldapServerId, companyId);

		LDAPUser ldapUser = _ldapToPortalConverter.importLDAPUser(
			companyId, attributes, userMappings, userExpandoMappings,
			contactMappings, contactExpandoMappings, password);

		User user = findLiferayUser(companyId, ldapUser);

		if ((user != null) && user.isDefaultUser()) {
			return user;
		}

		if (user != null) {

			// User already exists in the Liferay database. Skip import if user
			// fields have been already synced, if import is part of a scheduled
			// import, or if the LDAP entry has never been modified.

			String modifiedDate = LDAPUtil.getAttributeValue(
				attributes, "modifyTimestamp");

			user = updateLiferayUser(
				companyId, ldapUser, user, password, modifiedDate);
		}
		else {
			user = createLiferayUser(companyId, ldapUser, password);
		}
		
    *//** Import LDAP phones **//*
    importLdapPhones (attributes, user);
				
		updateExpandoAttributes(user, ldapUser);

		if (!importGroupMembership || (user == null)) {
			return user;
		}
		
		importGroupsAndMembershipFromLDAPUser(
			ldapServerId, companyId, ldapContext, attributes, ldapUser,
			user, userMappings);

		return user;
	}
	
  *//**
   * Import phones from LDAP
   *
   * @author Krzysztof Kardasz <krzysztof.kardasz@gmail.com>
   * @param Attributes attributes
   * @param User user
   * @return void
   *//*
  protected void importLdapPhones (Attributes attributes, User user) throws Exception {
      *//** Delete actually phones **//*
      PhoneLocalServiceUtil.deletePhones(user.getCompanyId(), "com.liferay.portal.model.Contact",user.getContact().getPrimaryKey());
  
      *//** Array for match ldap fields **//*
      String [][] attributesPhones = new String[2][2];
      System.out.println(" importLdapPhones MyPortalLDAPImporterImpl ");
      *//** Init array, write ldap fields for match **//*
      attributesPhones[0][0] = "mobile"; // LDAP field for mobile phone
      attributesPhones[0][1] = "11008"; // Liferay phone type (11006: Business, 11007: Business Fax, 11008: Mobile, 11009: Other, 1010: Pager, 11011: Personal, 11012: Personal Fax, 11013: TTY
      attributesPhones[1][0] = "telephoneNumber"; // LDAP field for mobile phone
      attributesPhones[1][1] = "11006"; // Liferay phone type (11006: Business, 11007: Business Fax, 11008: Mobile, 11009: Other, 1010: Pager, 11011: Personal, 11012: Personal Fax, 11013: TTY
  
      *//** Do stuff **//*
      boolean primaryPhone = true;
      String phoneLdap;
      for(String [] phone : attributesPhones) {
          phoneLdap = LDAPUtil.getAttributeValue(attributes, phone[0]);
          if(!phoneLdap.isEmpty()) {
              PhoneLocalServiceUtil.addPhone(user.getUserId(), 
                  "com.liferay.portal.model.Contact", 
                  user.getContact().getPrimaryKey(),
                  phoneLdap, 
                  "", 
                  Integer.parseInt(phone[1]), 
                  primaryPhone);
              primaryPhone = false;
          }
      }
  }

	protected User findLiferayUser(long companyId, LDAPUser ldapUser)
		throws Exception {

		User user = null;

		try {
			String authType = PrefsPropsUtil.getString(
				companyId, PropsKeys.COMPANY_SECURITY_AUTH_TYPE,
				PropsValues.COMPANY_SECURITY_AUTH_TYPE);

			if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				user = UserLocalServiceUtil.getUserByScreenName(
					companyId, ldapUser.getScreenName());
			}
			else {
				user = UserLocalServiceUtil.getUserByEmailAddress(
					companyId, ldapUser.getEmailAddress());
			}
		}
		catch (NoSuchUserException nsue) {
		}

		return user;
	}

	protected void importGroupsAndMembershipFromLDAPUser(
			long ldapServerId, long companyId, LdapContext ldapContext,
			Attributes attributes, LDAPUser ldapUser, User user,
			Properties userMappings)
		throws Exception {

		String userMappingsGroup = userMappings.getProperty("group");

		if (Validator.isNull(userMappingsGroup)) {
			return;
		}

		Attribute attribute = attributes.get(userMappingsGroup);

		if (attribute == null) {
			return;
		}

		attribute.clear();

		Properties groupMappings = LDAPSettingsUtil.getGroupMappings(
			ldapServerId, companyId);

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);

		Binding binding = PortalLDAPUtil.getUser(
			ldapServerId, companyId, ldapUser.getScreenName());

		String fullUserDN = PortalLDAPUtil.getNameInNamespace(
			ldapServerId, companyId, binding);

		StringBundler sb = new StringBundler(9);

		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(StringPool.AMPERSAND);
		sb.append(
			PrefsPropsUtil.getString(
				companyId,
				PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix));
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(groupMappings.getProperty("user"));
		sb.append(StringPool.EQUAL);
		sb.append(fullUserDN);
		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		List<SearchResult> searchResults = PortalLDAPUtil.searchLDAP(
			companyId, ldapContext, 0, baseDN, sb.toString(), null);

		for (SearchResult searchResult : searchResults) {
			String fullGroupDN = PortalLDAPUtil.getNameInNamespace(
				ldapServerId, companyId, searchResult);

			attribute.add(fullGroupDN);
		}

		List<Long> newUserGroupIds = new ArrayList<Long>(attribute.size());

		for (int i = 0; i < attribute.size(); i++) {
			String fullGroupDN = (String) attribute.get(i);

			Attributes groupAttributes = null;

			try {
				groupAttributes = PortalLDAPUtil.getGroupAttributes(
					ldapServerId, companyId, ldapContext, fullGroupDN);
			}
			catch (NameNotFoundException nnfe) {
				_log.error(
					"LDAP group not found with fullGroupDN " + fullGroupDN,
					nnfe);

				continue;
			}

			UserGroup userGroup = importLDAPGroup(
				ldapServerId, companyId, ldapContext, groupAttributes, false);

			if (userGroup != null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Adding " + user.getUserId() + " to group " +
							userGroup.getUserGroupId());
				}

				newUserGroupIds.add(userGroup.getUserGroupId());
			}
		}

		UserGroupLocalServiceUtil.setUserUserGroups(
			user.getUserId(),
			ArrayUtil.toArray(
				newUserGroupIds.toArray(new Long[newUserGroupIds.size()])));
	}

	protected UserGroup importLDAPGroup(
			long ldapServerId, long companyId, LdapContext ldapContext,
			Attributes attributes, boolean importGroupMembership)
		throws Exception {

		AttributesTransformer attributesTransformer =
			AttributesTransformerFactory.getInstance();

		attributes = attributesTransformer.transformGroup(attributes);

		Properties groupMappings = LDAPSettingsUtil.getGroupMappings(
			ldapServerId, companyId);

		LogUtil.debug(_log, groupMappings);

		LDAPGroup ldapGroup = _ldapToPortalConverter.importLDAPGroup(
			companyId, attributes, groupMappings);

		UserGroup userGroup = null;

		try {
			userGroup = UserGroupLocalServiceUtil.getUserGroup(
				companyId, ldapGroup.getGroupName());

			UserGroupLocalServiceUtil.updateUserGroup(
				companyId, userGroup.getUserGroupId(), ldapGroup.getGroupName(),
				ldapGroup.getDescription());
		}
		catch (NoSuchUserGroupException nsuge) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Adding user group to portal " + ldapGroup.getGroupName());
			}

			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				companyId);

			try {
				userGroup = UserGroupLocalServiceUtil.addUserGroup(
					defaultUserId, companyId, ldapGroup.getGroupName(),
					ldapGroup.getDescription());
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to create user group " +
							ldapGroup.getGroupName());
				}

				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		if (!importGroupMembership || (userGroup == null)) {
			return userGroup;
		}

		Attribute attribute = attributes.get(groupMappings.getProperty("user"));

		if (attribute == null) {
			return userGroup;
		}

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);

		StringBundler sb = new StringBundler(7);

		sb.append("(&");
		sb.append(
			PrefsPropsUtil.getString(
				companyId,
				PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix));
		sb.append("(");
		sb.append(groupMappings.getProperty("groupName"));
		sb.append("=");
		sb.append(
			LDAPUtil.getAttributeValue(
				attributes, groupMappings.getProperty("groupName")));
		sb.append("))");

		attribute = PortalLDAPUtil.getMultivaluedAttribute(
			companyId, ldapContext, baseDN, sb.toString(), attribute);

		importUsersAndMembershipFromLDAPGroup(
			ldapServerId, companyId, ldapContext, userGroup.getUserGroupId(),
			attribute);

		return userGroup;
	}

	protected void importUsersAndMembershipFromLDAPGroup(
			long ldapServerId, long companyId, LdapContext ldapContext,
			long userGroupId, Attribute attribute)
		throws Exception {

		List<Long> newUserIds = new ArrayList<Long>(attribute.size());

		for (int i = 0; i < attribute.size(); i++) {
			String fullUserDN = (String)attribute.get(i);

			Attributes userAttributes = null;

			try {
				userAttributes = PortalLDAPUtil.getUserAttributes(
					ldapServerId, companyId, ldapContext, fullUserDN);
			}
			catch (NameNotFoundException nnfe) {
				_log.error(
					"LDAP user not found with fullUserDN " + fullUserDN, nnfe);

				continue;
			}

			try {
				User user = importLDAPUser(
					ldapServerId, companyId, ldapContext, userAttributes,
					StringPool.BLANK, false);

				if (user != null) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Adding " + user.getUserId() + " to group " +
								userGroupId);
					}

					newUserIds.add(user.getUserId());
				}
			}
			catch (Exception e) {
				_log.error("Unable to load user " + userAttributes, e);
			}
		}

		UserLocalServiceUtil.setUserGroupUsers(
			userGroupId,
			ArrayUtil.toArray(newUserIds.toArray(new Long[newUserIds.size()])));
	}

	protected void populateExpandoAttributes(
		ExpandoBridge expandoBridge, Map<String, String> expandoAttributes) {

		for (Map.Entry<String, String> expandoAttribute :
				expandoAttributes.entrySet()) {

			String name = expandoAttribute.getKey();

			if (!expandoBridge.hasAttribute(name)) {
				continue;
			}

			int type = expandoBridge.getAttributeType(name);

			Serializable value = ExpandoConverterUtil.getAttributeFromString(
				type, expandoAttribute.getValue());

			expandoBridge.setAttribute(name, value);
		}
	}

	protected void updateExpandoAttributes(User user, LDAPUser ldapUser) {
		ExpandoBridge userExpandoBridge = user.getExpandoBridge();

		populateExpandoAttributes(
			userExpandoBridge, ldapUser.getUserExpandoAttributes());

		Contact contact = user.getContact();

		ExpandoBridge contactExpandoBridge = contact.getExpandoBridge();

		populateExpandoAttributes(
			contactExpandoBridge , ldapUser.getContactExpandoAttributes());
	}

	protected User updateLiferayUser(
			long companyId, LDAPUser ldapUser, User user,
			String password, String modifiedDate)
		throws Exception {

		Date ldapUserModifiedDate = null;

		try {
			if (Validator.isNull(modifiedDate)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"LDAP entry never modified, skipping user " +
							user.getEmailAddress());
				}

				return user;
			}
			else {
				DateFormat dateFormat =
					DateFormatFactoryUtil.getSimpleDateFormat(
						"yyyyMMddHHmmss");

				ldapUserModifiedDate = dateFormat.parse(modifiedDate);
			}

			if (ldapUserModifiedDate.equals(user.getModifiedDate()) &&
				ldapUser.isAutoPassword()) {

				if (_log.isDebugEnabled()) {
					_log.debug(
						"User is already synchronized, skipping user " +
							user.getEmailAddress());
				}

				return user;
			}
		}
		catch (ParseException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse LDAP modify timestamp " + modifiedDate,
					pe);
			}
		}

		if (Validator.isNull(ldapUser.getScreenName())) {
			ldapUser.setAutoScreenName(true);
		}

		if (ldapUser.isAutoScreenName()) {
			ScreenNameGenerator screenNameGenerator =
				ScreenNameGeneratorFactory.getInstance();

			ldapUser.setScreenName(
				screenNameGenerator.generate(
					companyId, user.getUserId(), ldapUser.getEmailAddress()));
		}

		try {
			Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

			birthdayCal.setTime(user.getContact().getBirthday());

			int birthdayMonth = birthdayCal.get(Calendar.MONTH);
			int birthdayDay = birthdayCal.get(Calendar.DAY_OF_MONTH);
			int birthdayYear = birthdayCal.get(Calendar.YEAR);

			if (ldapUser.isUpdatePassword()) {
				UserLocalServiceUtil.updatePassword(
					user.getUserId(), password, password,
					ldapUser.isPasswordReset(), true);
			}

			user = UserLocalServiceUtil.updateUser(
				user.getUserId(), password, StringPool.BLANK,
				StringPool.BLANK, ldapUser.isPasswordReset(),
				ldapUser.getReminderQueryQuestion(),
				ldapUser.getReminderQueryAnswer(), ldapUser.getScreenName(),
				ldapUser.getEmailAddress(), ldapUser.getOpenId(),
				ldapUser.getLanguageId(), ldapUser.getTimeZoneId(),
				ldapUser.getGreeting(), ldapUser.getComments(),
				ldapUser.getFirstName(), ldapUser.getMiddleName(),
				ldapUser.getLastName(), ldapUser.getPrefixId(),
				ldapUser.getSuffixId(), ldapUser.isMale(), birthdayMonth,
				birthdayDay, birthdayYear, ldapUser.getSmsSn(),
				ldapUser.getAimSn(), ldapUser.getFacebookSn(),
				ldapUser.getIcqSn(), ldapUser.getJabberSn(),
				ldapUser.getMsnSn(), ldapUser.getMySpaceSn(),
				ldapUser.getSkypeSn(), ldapUser.getTwitterSn(),
				ldapUser.getYmSn(), ldapUser.getJobTitle(),
				ldapUser.getGroupIds(), ldapUser.getOrganizationIds(),
				ldapUser.getRoleIds(), ldapUser.getUserGroupRoles(),
				ldapUser.getUserGroupIds(), ldapUser.getServiceContext());

			if (ldapUserModifiedDate != null) {
				user = UserLocalServiceUtil.updateModifiedDate(
					user.getUserId(), ldapUserModifiedDate);
			}

			return user;
		}
		catch (Exception e) {
			_log.error(
				"Unable to update user with screen name " +
					ldapUser.getScreenName() + " and email address " +
						ldapUser.getEmailAddress(),
				e);

			return null;
		}
	}

	private static final String _IMPORT_BY_GROUP = "group";

	private static final String _IMPORT_BY_USER = "user";

	private static Log _log = LogFactoryUtil.getLog(
		MyPortalLDAPImporterImpl.class);

	private LDAPToPortalConverter _ldapToPortalConverter;
*/
}