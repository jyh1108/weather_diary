package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JpaMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest(){
        //given
        Memo newMemo = new Memo();
        newMemo.setText("this is jpa memo~");  // ID는 자동 생성되므로 set 하지 않음

        //when
        jpaMemoRepository.save(newMemo);  // 저장

        //then
        List<Memo> memoList = jpaMemoRepository.findAll();  // 전체 메모 목록 조회
        assertTrue(memoList.size() > 0);  // 하나 이상의 메모가 있어야 함
    }

    @Test
    void findByIdTest(){
        //given
        Memo newMemo = new Memo();
        newMemo.setText("jpa");
        //when
        Memo memo = jpaMemoRepository.save(newMemo);
        System.out.println(memo.getId());
        //then
        Optional<Memo> result = jpaMemoRepository.findById(memo.getId());
        assertEquals(result.get().getText(), "jpa");
    }
}