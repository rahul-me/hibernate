package com.concretepage.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserInfoDAO;
import com.concretepage.entity.UserInfo;

@Service
public class MyAppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserInfoDAO userInfoDao;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo activeUserInfo = userInfoDao.getActiveUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		UserDetails userDetails = (UserDetails) new User(activeUserInfo.getUsername(), activeUserInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
