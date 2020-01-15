package com.sid.service;

import com.sid.entities.AppRole;
import com.sid.entities.AppUser;

public interface AccountService {
	
	public AppUser saveUser(String username, String password, String confirm);
	public AppRole saveRole(AppRole role);
	public AppUser LoadUserByUsername(String username);
	public void AddRoleToUser(String username, String rolename);

}
