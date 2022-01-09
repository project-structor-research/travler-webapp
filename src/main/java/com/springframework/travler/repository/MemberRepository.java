package com.springframework.travler.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springframework.travler.models.Member;


@Repository
public class MemberRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public Member list() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("P_USERNAME", "yong80211@gmail.com");
		Member member = sqlSession.selectOne("member.list", parameters);
		return member;
	}
}
