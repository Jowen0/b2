package org.zerock.b2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b2.entity.Memo;
import org.zerock.b2.repsitory.MemoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemoRepositoryTests {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void test1() {

        log.info(memoRepository.getClass().getName());

    }

    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);

        });

    }

    @Test
    public void testSelect() {

        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);
        log.info("==========================================");

        if(result.isPresent()) {

            log.info(result.get());

        }

    }

    @Transactional
    @Test
    public void testSelect2() {

        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);

        log.info("===============================");
        log.info(memo); //이것 때문에 트랜잭션 테두리 필요

    }

    @Test
    public void testUpdate() {

        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);

        if (result.isPresent()) {

            Memo memo = result.get();

            memo.changeText("Update....100");

            memoRepository.save(memo);

        }

    }

    @Test
    public void testPage1() {

        Sort sort1 = Sort.by("mno").descending();

        //4페이지
        Pageable pageable = PageRequest.of(3,10, sort1);

        Page<Memo> result = memoRepository.findAll(pageable);

        log.info(result);

        //내용물
        result.getContent().forEach(memo -> log.info(memo));

        Pageable prev =  result.previousPageable();

        System.out.println(prev);
    }

    @Test
    public void testQuery1() {

        List<Memo> list = memoRepository.findByMemoTextContaining("1");

        list.forEach(memo -> log.info(memo));

    }

    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMemoTextContaining("1", pageable);

        result.get().forEach(memo -> log.info(memo));

    }

    @Test
    public void testQuery3() {

        memoRepository.getList1("1").forEach(memo -> log.info(memo));

    }

    @Test
    public void testQuery4() {

        Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());

        memoRepository.getListPage("1", pageable).get().forEach(memo -> log.info(memo));

    }

}