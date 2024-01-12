package com.shinhan.sbproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.MemberDTO;
import com.shinhan.sbproject.vo.ProfileDTO;

public interface ProfileRepository extends CrudRepository<ProfileDTO, Long>, QuerydslPredicateExecutor<ProfileDTO>{
	List<ProfileDTO> findByMember(MemberDTO member);
	List<ProfileDTO> findByCurrentYnIsTrue();
	
	@Query(value="select m.mname, p.fname from tbl_members_ejjj m left outer join tbl_profile_ejjj p on(m.mid = p.member_mid)", nativeQuery = true)
	List<Object[]> selectByJoin();
}
