package com.shinhan.sbproject.vo5;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="departments")
public class DeptVO {
	@Id
	private Integer departmentId;
	private String departmentName;
	@Column(nullable = true)
	private Integer managerId;
	@Column(nullable = true)
	private Integer locationId;
}
