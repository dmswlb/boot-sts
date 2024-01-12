package com.shinhan.sbproject.vo4;

import java.util.Set;

import com.shinhan.sbproject.vo.MemberRole;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_enum_ejjj")
public class EnumTypeVO {

	@Id
	private String mid;
	private String mpassword;
	private String mname;

	@ElementCollection(targetClass = MemberRole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "tbl_enum_mroles_ejjj", joinColumns = @JoinColumn(name = "mid2"))
	@Enumerated(EnumType.STRING)
	private Set<MemberRole> mrole;
}
