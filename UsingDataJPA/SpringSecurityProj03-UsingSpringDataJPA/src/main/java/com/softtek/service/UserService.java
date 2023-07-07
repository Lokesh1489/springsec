package com.softtek.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.softtek.entity.UserInfo;
import com.softtek.reposity.IUserDetailsRepo;

@Service
public class UserService implements IUserService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private IUserDetailsRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> opt = userRepo.findByUname(username);
		if(opt.isEmpty())
			throw new IllegalArgumentException("user not found");
		else
		{
			UserInfo info = opt.get();
			User user = new User(info.getUname(),info.getPwd(),info.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
			return user;
			}}
		

	@Override
	public String register(UserInfo details) {
		details.setPwd(encoder.encode(details.getPwd()));
		return userRepo.save(details).getUnid() + " UserId is registered";
	}

}
