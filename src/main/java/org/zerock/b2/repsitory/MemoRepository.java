package org.zerock.b2.repsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.b2.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMemoTextContaining(String keyword);

    Page<Memo> findByMemoTextContaining(String keyword, Pageable pageable);

    @Query("select m from Memo m where m.memoText like concat('%',:keyword,'%')")
    List<Memo> getList1(@Param("keyword")String keyword);

    @Query("select m from Memo m where m.memoText like concat('%',:keyword,'%')")
    Page<Memo> getListPage(@Param("keyword")String keyword, Pageable pageable);

}
