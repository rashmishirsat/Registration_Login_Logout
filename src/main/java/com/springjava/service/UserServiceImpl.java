package com.springjava.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springjava.dto.UserRegisterDto;
import com.springjava.entity.Role;
import com.springjava.entity.User;
import com.springjava.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null){
		throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));	}

	
	@Override
	public User save(UserRegisterDto userRegDto) {
		User user = new User();
		user.setFirstName(userRegDto.getFirstName());
		user.setLastName(userRegDto.getLastName());
		user.setEmail(userRegDto.getEmail());
		user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
		List<Role> roles=new ArrayList<Role>();
		Role role=new Role();
		role.setName("ROLE_USER");
		roles.add(role);
		user.setRoles(roles);
		return userRepo.save(user);
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		// TODO Auto-generated method stub
		return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	

}