package com.shinhan.sbproject.vo3;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table (name="tbl_freeboard_ejjj")
public class FreeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bno;
	
	@Nonnull
	@Column(nullable = false)
	String title;
	
	String writer;
	String content;
	@CreationTimestamp
	Timestamp regdate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	@JsonIgnore	//무한 loop가 되지 않도록 FeeBoard -> FreeBoardReply -> 다시 FreeBoard로 가기는 막아야한다.	
	//자바객체가 Browser로 내려갈때 JSON data로 변경되어 내려간다. com.fasterxml.jackson.databind 오류때문에 추가
	//연관관계 설정하기_하나의 board에 댓글이 여러개 있음
	@OneToMany ( mappedBy = "board2", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<FreeBoardReply> replies;
}
