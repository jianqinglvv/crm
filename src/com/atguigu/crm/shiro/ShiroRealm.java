package com.atguigu.crm.shiro;

import java.util.Collection;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atguigu.crm.dao.UserMapper;
import com.atguigu.crm.entity.User;

@Component
public class ShiroRealm extends AuthorizingRealm{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		User user = (User) arg0.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(user.getRoleList());
		
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String username = token.getUsername();
		
		User user = userMapper.getByName(username);
		
		if(user == null){
			throw new UnknownAccountException();
		}if(user.getEnabled()!=1){
			throw new LockedAccountException();
		}
		
		String realmName = getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		Object hashedCredentials = user.getPassword();
		Object principal = user;
		
		AuthenticationInfo info = 
				new SimpleAuthenticationInfo(principal , hashedCredentials, credentialsSalt, realmName);
		
		return info;
	}
	 
	public static void main(String[] args) {
		int hashIterations = 1024;
		Object salt = ByteSource.Util.bytes("db314a8d91bd6f83");//34d3d5a485a906c9  db314a8d91bd6f83 5489e973c2016fc6
		Object source = "123456";
		String algorithmName = "md5";
		SimpleHash result = new SimpleHash(algorithmName, source, salt, hashIterations);
		
		System.out.println(result);
	}
	//设置凭证匹配器
	@PostConstruct
	private void doSomething(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		matcher.setStoredCredentialsHexEncoded(true);
		
		setCredentialsMatcher(matcher);
		
	}

}
