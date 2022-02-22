package com.sf.tool.script.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sf.tool.script.entity.TxnBasicInfo;

public interface TxnBasicInfoRespository extends JpaRepository<TxnBasicInfo, Long> {
    
    @Modifying
    @Query(value="update tb_txn_basic_info set sub_txn_no = to_char(txn_basic_info_seq_no) where txn_basic_info_seq_no = :seqNo ",nativeQuery=true)
    void updateTxnBasicSubTxnNo(@Param("seqNo") long txnBasicSeqNo);
    
    @Modifying
    @Query(value="update tb_acct_entry set sub_txn_no = to_char(:seqNo) "
            + " where acct_date = :acctDate "
            + " and fah_guid= :fahGuid "
            + " and sub_txn_no = :subTxnNo", nativeQuery=true)
    void updateAcctEntry(@Param("seqNo") long txnBasicSeqNo, 
            @Param("acctDate") Date acctDate, 
            @Param("fahGuid") String fahGuid, 
            @Param("subTxnNo") String subTxnNo);
    
    @Modifying
    @Query(value="update tb_txn_statement_result set sub_txn_no = to_char(:seqNo) "
            + " where acct_date = :acctDate "
            + " and fah_guid= :fahGuid "
            + " and sub_txn_no = :subTxnNo", nativeQuery=true)
    void updateTxnStatementResult(@Param("seqNo") long txnBasicSeqNo, 
            @Param("acctDate") String acctDate, 
            @Param("fahGuid") String fahGuid, 
            @Param("subTxnNo") String subTxnNo);
    
}
