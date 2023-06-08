package dictionary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dictionary.dto.WordDto;

@Service
public interface WordService {
	
	// 단어장 목록 조회
	public List<WordDto> selectWordList() throws Exception;
	
	// 단어 상세 조회
	public WordDto selectWordDetail(int wordIdx) throws Exception;
	
	// 단어 추가
	public int insertWord(WordDto wordDto) throws Exception;
	
	// 단어 삭제
	public void deleteWord(int wordIdx) throws Exception;

}
