package com.tangpian.sna.security;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangpian.sna.dao.UserDao;
import com.tangpian.sna.model.User;

public class SecurityRealm extends AuthorizingRealm {

	private UserDao userDao;
	private static Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

	public enum SaltStyle {
		NO_SALT, EXTERNAL
	};

	protected SaltStyle saltStyle = SaltStyle.EXTERNAL;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		// null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}

		String username = (String) getAvailablePrincipal(principals);

		boolean flag = userDao.existbyUsername(username);

		if (flag) {
			Set<String> roles = new HashSet<String>();
			roles.add("user");

			Set<String> permissions = new HashSet<String>();
			permissions.add("oper");
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
			info.setStringPermissions(permissions);
			return info;
		} else {
			throw new AuthorizationException("cant find user!");
		}

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		// Null username is invalid
		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}

		Connection conn = null;
		SimpleAuthenticationInfo info = null;

		User user = userDao.findByUsername(username);

		String password = user.getPassword();

		String salt = null;
		switch (saltStyle) {
		case NO_SALT:
			break;
		case EXTERNAL:
			salt = user.getUsername();
		}

		if (password == null) {
			throw new UnknownAccountException("No account found for user ["
					+ username + "]");
		}

		info = new SimpleAuthenticationInfo(username, password.toCharArray(),
				getName());

		if (salt != null) {
			info.setCredentialsSalt(ByteSource.Util.bytes(salt));
		}

		return info;

	}

}
