package com.shinhan.sbproject.vo2;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "pid")

@Entity
@Table(name = "tbl_pdsboard_ejjj")

public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	private String pwriter;
	//cascade:영속성전이 PDSBoard변경시 PDSFile변경)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//JPA는 default로 지연로딩을 사용한다.(PDSBoard조회시 PDSFile 조인하지않음) 
    //1)fetch = FetchType.LAZY ....PDSFile과 연결불가, @Query로 해결 
    //2)fetch = FetchType.EAGER....PDSFile과 연결가능 
	@JoinColumn(name = "pdsno") // PDSFile 테이블에 pdsno칼럼이 생성
	private List<PDSFile> files2;
}
