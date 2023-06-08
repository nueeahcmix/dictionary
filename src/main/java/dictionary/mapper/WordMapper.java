package dictionary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dictionary.dto.WordDto;

@Mapper
public interface WordMapper {
	// 단어장 목록 조회
	List<WordDto> selectWordList() throws Exception;
	
	// 단어 상세 조회
	WordDto selectWordDetail(int wordIdx) throws Exception;
	
	// 단어 추가
	int insertWord(WordDto wordDto) throws Exception;
	
	// 단어 삭제
	void deleteWord(int wordIdx) throws Exception;

}
