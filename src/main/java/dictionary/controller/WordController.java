package dictionary.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import dictionary.dto.WordDto;
import dictionary.service.WordService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WordController {
	
	@Autowired
	public WordService wordService;
	
	// 네이버 백과사전 검색 api
	@GetMapping("/api/dictionary/{word}")
	public String dictionary(@PathVariable("word") String word) {

		URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.naver.com")
				.path("/v1/search/encyc.json")
				.queryParam("query", word)
				.queryParam("display", 10)
				.queryParam("start",1)
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-Naver-Client-Id", "1HZ7s0M_N4Asc16VfLmS")
				.header("X-Naver-Client-Secret", "BakQuVwgNC")
				.build();
		ResponseEntity<String> result = restTemplate.exchange(req, String.class);
		return result.getBody();
	}
	
	//나의 단어장 목록
	@GetMapping("/api/word")
	public ResponseEntity<List<WordDto>> selectWordList() throws Exception {
		List<WordDto> list = wordService.selectWordList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	//단어장에 단어 추가
	@PostMapping("/api/add/word")
	public ResponseEntity<String> insertWord(
			@RequestBody WordDto wordDto) throws Exception {
		int wordIdx = 0;
		try {
			wordIdx = wordService.insertWord(wordDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록오류");
		}
		return ResponseEntity.status(HttpStatus.OK).body("" + wordIdx);
	}
	
	//단어 상세 조회
	@GetMapping("/api/word/{wordIdx}")
	public ResponseEntity<WordDto> selectWordDetail(@PathVariable("wordIdx") int wordIdx) throws Exception {
		WordDto wordDto = wordService.selectWordDetail(wordIdx);
		if (wordDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(wordDto);
		}
	}
	
	//단어 삭제
	@DeleteMapping("/api/word/{wordIdx}")
	public ResponseEntity<Integer> deleteWord(@PathVariable("wordIdx") int wordIdx) throws Exception {
		try {
			wordService.deleteWord(wordIdx);
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
}


