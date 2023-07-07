package com.softtek.reposity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtek.entity.UserInfo;

public interface IUserDetailsRepo extends JpaRepository<UserInfo, Integer> {

	public Optional<UserInfo> findByUname(String username);
}
