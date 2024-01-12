package com.shinhan.sbproject.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_members_EJJJ")
public class MemberDTO {
    @Id		//PK
	String mid;
	String mpassword;
	String mname;
	
	@Enumerated(EnumType.STRING)
	MemberRole mrole;
	
}
