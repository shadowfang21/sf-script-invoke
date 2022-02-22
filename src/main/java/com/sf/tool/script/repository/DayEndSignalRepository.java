package com.sf.tool.script.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sf.tool.script.entity.DayEndSignal;

public interface DayEndSignalRepository extends CrudRepository<DayEndSignal, Long>{
    
    @Query(value="select max(day_end_signal_seq_no) + 1 from tb_day_end_signal ", nativeQuery=true)
    Long getMaxSeqNo();
    
}
