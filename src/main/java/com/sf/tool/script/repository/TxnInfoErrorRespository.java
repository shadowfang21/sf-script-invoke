package com.sf.tool.script.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sf.tool.script.entity.TxnInfoError;

public interface TxnInfoErrorRespository extends JpaRepository<TxnInfoError, Long> {
    
    @Query(value="select u.* from TB_TXN_INFO_ERROR u where to_char(u.created_Time,'yyyyMMdd') = ?1 and "
            + "( length(?2) = 0 or u.message like %?2% ) ", nativeQuery=true)
    public List<TxnInfoError> findByFahGuidOrCreatedTime(String createdTime, String message, Pageable pageable);
    
}
