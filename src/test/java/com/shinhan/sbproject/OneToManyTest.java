package com.shinhan.sbproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;

import com.shinhan.firstzone.repository.BoardRepository;
import com.shinhan.sbproject.repository.PDSBoardRepository;
import com.shinhan.sbproject.repository.PDSFileRepository;
import com.shinhan.sbproject.vo2.PDSBoard;
import com.shinhan.sbproject.vo2.PDSFile;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Commit
@Slf4j
@SpringBootTest
public class OneToManyTest {
	@Autowired
	PDSFileRepository frepo;
	
	@Autowired
	PDSBoardRepository brepo;
	
	@Test
	void findSelectall() {
		//frepo.findAll(Sort.by(Direction.DESC, "fno")).forEach(f->log.info(f.toString()));
//		log.info("----------------------------------------------------");
//		Pageable paging = PageRequest.of(0, 3);
//		frepo.findAll(paging).forEach(f->log.info(f.toString()));
		log.info("----------------------------------------------------");
		Pageable paging1 = PageRequest.of(0, 3);
		Page<PDSFile> result = frepo.findAll(paging1);
		
		int cnt = result.getTotalPages();
		for(int i=0; i<cnt; i++) {
			paging1 = PageRequest.of(i, 3);
			frepo.findAll(paging1).forEach(f->log.info(f.toString()));
			log.info("====================================");
		}
		
	}
	
	//@Test
	void deleteBoard() {
		brepo.deleteById(1L);
	}
	
	//@Test
	void getFileByBoard() {
		brepo.findById(1L).ifPresent(b->{
			List<PDSFile> files2 = b.getFiles2();
			for(PDSFile f: b.getFiles2()) {
				log.info(f.toString());
			}
			
		});
	}
	
	//@Test
	void fileDelete() {
		frepo.deleteById(1L);
	}
	
	//@Test
	void searchFile() {
		List<PDSBoard> blist = (List<PDSBoard>) brepo.findAll();
		for(PDSBoard board : blist) {
			List<PDSFile> flist = board.getFiles2();
			flist.forEach(f->{
				if(f.getFno()==5L) {
					f.setPdsfilename("이상해.jpg");
					brepo.save(board);
				}
			});			
		}
	}
	
	//첨부파일수정
	//@Transactional		//test 후에 rollback됨, 이 클래스가 test이기 때문에 DB에 반영되지 않음_반영하려면 class leveldp @Commit을 넣는다
	//@Test
	void UpdateFile2() {
		int result = brepo.updatePDSFile(2L, "이미지성형하기.jpg");
		log.info("결과ㅣ " + result);
	}
	
	//첨부파일수정
	//@Test
	void UpdateFile() {
		PDSFile file = frepo.findById(1L).orElse(null);
		if(file==null) return;
		file.setPdsfilename("파일이름수정");
		frepo.save(file);
	}
	
	//Board 별 File의 count 열기(File->Board) 불가
	//@Test
	void getFileCount() {
		frepo.getFilesCount().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//Board 별 File의 count 열기(File->Board)
	//@Test
	void fileCount2() {
		frepo.getFileCountByBoard().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//Board 별 File의 count 열기(Board->File)
	//@Test
	void fileCount() {
		brepo.findAll().forEach(board->{
			log.info(board.getPname() + "-----" + board.getFiles2().size());
		});
	}
	
	//@Test
	void fileUpdate2() {
		//"eye"가 포함된 첨부파일을 2번 board에 추가하려한다.
		Long pid = 2L;
		PDSBoard board = brepo.findById(pid).orElse(null);

		if(board == null) return;
		List<PDSFile> flist = board.getFiles2();
		frepo.findByPdsfilenameContaining("eye").forEach(f->{			
				flist.add(f);			
		});
		brepo.save(board);
	}
	
	//Board를 통해서 File을 저장했다면 PASFile 테이블의 pdsno가 pid로 들어간다
	//File만 저장했다면 pdsno가 null이 된다.
	//@Test
	void fileUpdate() {
		//11번 첨부파일을 2번 board에 추가하려한다.
		Long fno = 11L;
		Long pid = 2L;
		frepo.findById(fno).ifPresent(f->{
			brepo.findById(pid).ifPresent(b->{
				b.getFiles2().add(f);
				brepo.save(b);
			});
		});
	}
	
	//@Test
	void fileSave() {
		IntStream.rangeClosed(1, 10).forEach(i->{
			PDSFile file = PDSFile.builder()
									.pdsfilename("eyes-"+i)
									.build();
			frepo.save(file);
		});
	}
	
	//@Test
	void selectByBoard() {
		Long pid = 2L;
		brepo.findById(pid).ifPresent(board->{
			log.info("pname: " + board.getPname());
			log.info("writer: " + board.getPwriter());
			log.info("files2: " + board.getFiles2());
			log.info("files2 건수: " + board.getFiles2().size());
			log.info("--------------------------------");
		});
	}
	
	//@Test
	void fileSelect() {
		frepo.findAll().forEach(f->log.info(f.toString()));
	}
	
	//@Test
	void boardSelect() {
		brepo.findAll().forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void insert() {
		//Board(1), File(n): board를 1개 저장하면 file이 n개 저장됨
		List<PDSFile> flist = new ArrayList<>();
		IntStream.rangeClosed(1, 5).forEach(i->{
			PDSFile file = PDSFile.builder()
									.pdsfilename("foot-" +i)
									.build();
			flist.add(file);
		
		});;
		PDSBoard board = PDSBoard.builder()
								.pname("눈이옵니다.")
								.pwriter("지현")
								.files2(flist)
								.build();
		brepo.save(board);
	}
}
