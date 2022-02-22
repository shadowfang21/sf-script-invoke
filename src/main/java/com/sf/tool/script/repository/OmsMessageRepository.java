package com.sf.tool.script.repository;

import java.util.List;

import com.sf.tool.script.entity.OmsMessage;

public interface OmsMessageRepository {
    public List<OmsMessage> findAllByOrderBySeqNoDesc();
}
