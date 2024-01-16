package com.shinhan.sbproject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.shinhan.sbproject.vo.MemberDTO;

import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode
public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX="ROLE_";
    private MemberDTO member;   
	public SecurityUser(String name, String password, Collection<? extends GrantedAuthority> authorities) {
		super(name, password, authorities);
		System.out.println("[SS member] " + name);
	}
	public SecurityUser(MemberDTO member) {	
		super(member.getMid(), member.getMpassword(), makeRole(member)  );
		this.member = member;
		System.out.println("SecurityUser member:" + member);
	}
	//Role을 여러개 가질수 있도록 되어있음 
	private static List<GrantedAuthority> makeRole(MemberDTO member) {
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + member.getMrole()));
		return roleList;
	}
	 
	//User class에서 username필드가 있지만 google 인증시 사용되는 필드는 name
	//이를 맞추기위해 함수추가함 
	public String getName() {
		// TODO Auto-generated method stub
		return super.getUsername();
	}
	
	
	
	
	
}