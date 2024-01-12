package com.shinhan.sbproject.vo;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_boards_review")
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "BOARD_SEQ_REVIEW", // 매핑할 데이터베이스 시퀀스 이름
		initialValue = 1, allocationSize = 1)
public class BoardVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BOARD_SEQ_GENERATOR")
	private Long bno;
	
	@NonNull // 생성시 반드시 값이 있어야한다.(lombok), java에서 null 허용안함
	@Column(nullable = false)
//DB에 칼럼이 NOT NULL
//칼럼이름은 예약어불가, 카멜표기법으로 작성된 이름은 DB칼럼은 언더바로 변경  
	private String title;
	
	@Column(length = 100)
	private String content;
	
	private String writer;
	
	@CreationTimestamp // insert시 시각이 입력
	private Timestamp regDate;
	
	@UpdateTimestamp // insert, Update시 수정시각입력
	private Timestamp updateDate;
}
