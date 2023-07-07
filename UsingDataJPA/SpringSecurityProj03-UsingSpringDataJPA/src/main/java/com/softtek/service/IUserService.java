package com.softtek.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.softtek.entity.UserInfo;

public interface IUserService extends UserDetailsService {

	public String register(UserInfo details);

}
