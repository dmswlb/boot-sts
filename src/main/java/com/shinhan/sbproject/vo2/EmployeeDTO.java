package com.shinhan.sbproject.vo2;

import jakarta.persistence.Entity;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode (of = "empId")

@Entity
@Table (name = "tbl_emp_ejjj")
public class EmployeeDTO {
	@Id
	Long empId;
	String empName;
}
