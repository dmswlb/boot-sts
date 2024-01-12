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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user_ejjj")
public class UserVO {

	@Id
	@Column(name = "user_id")
	String userid;
	@Column(name = "user_name")
	String username;

	//주 테이블에서 참조하기 
	//cascade(연관 table에 영향을 줌): 주 테이블 DML 수행시 부 테이블에 영향을 줌
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "phone_id")
	UserCellPhoneVO phone;

}
