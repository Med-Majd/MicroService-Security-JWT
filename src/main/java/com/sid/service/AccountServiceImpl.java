package com.sid.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sid.dao.AppRoleRepository;
import com.sid.dao.AppUserRepository;
import com.sid.entities.AppRole;
import com.sid.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private AppRoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bcpe;

	@Override
	public AppUser saveUser(String username, String password, String confirm) {
		
		AppUser user = userRepository.findByUsername(username);
		if(user!=null) throw new RuntimeException("Username already exists!");
		if(!password.equals(confirm)) throw new RuntimeException("Please confirm your password");
		AppUser u = new AppUser();
		u.setUsername(username);
		u.setActive(true);
		u.setPassword(bcpe.encode(password));
		userRepository.save(u);
		AddRoleToUser(username, "USER");
		return u;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public AppUser LoadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void AddRoleToUser(String username, String rolename) {
		AppUser user = userRepository.findByUsername(username);
		AppRole role = roleRepository.findByRoleName(rolename);
		user.getRoles().add(role);
	}

}
