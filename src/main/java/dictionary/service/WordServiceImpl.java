package dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dictionary.dto.WordDto;
import dictionary.mapper.WordMapper;

@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	public WordMapper wordMapper;
	
	@Override
	public List<WordDto> selectWordList() throws Exception {
		return wordMapper.selectWordList();
	}

	@Override
	public WordDto selectWordDetail(int wordIdx) throws Exception {
		return wordMapper.selectWordDetail(wordIdx);
	}

	@Override
	public int insertWord(WordDto wordDto) throws Exception {
		wordMapper.insertWord(wordDto);
		return wordDto.getWordIdx();
	}

	@Override
	public void deleteWord(int wordIdx) throws Exception {
		wordMapper.deleteWord(wordIdx);
	}

}