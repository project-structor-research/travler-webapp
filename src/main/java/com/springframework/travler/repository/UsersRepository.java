package com.springframework.travler.repository;

import java.util.HashMap;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springframework.travler.entity.Users;

@Repository
public class UsersRepository {
    
	@Autowired
	private SqlSession sqlSession;
	
	public Users findByEmail(String email) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("P_EMAIL", email);
    	
    	Users user = sqlSession.selectOne("user.findByEmail", parameters);
		return user;
	}
	
    public boolean existsByEmail(String email) {
    	HashMap<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("P_EMAIL", email);
    	
    	int count = sqlSession.selectOne("user.existsByEmail", parameters);
    	return  1 == count;
    }
    
    public boolean save(Users user) {
    	return true;
    }
}
