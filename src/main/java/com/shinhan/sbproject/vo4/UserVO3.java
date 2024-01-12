package com.shinhan.sbproject.vo4;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//object references an unsaved transient instance - save the transient instance before flushing
//부모 객체에 추가하는 자식 객체가 아직 db에 저장되지 않아 생긴 에러이다. 즉 영속성 전이를 해야한다.
//cascade = CascadeType.ALL

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user3_ejjj")
public class UserVO3 {

	@Id
	@Column(name = "user_id")
	String userid;

	String username;

//양방향...주테이블의 키를 식별자사용하는 경우 
	@OneToOne(mappedBy = "user2", cascade = CascadeType.ALL)
	UserCellPhoneVO3 phone;

}
