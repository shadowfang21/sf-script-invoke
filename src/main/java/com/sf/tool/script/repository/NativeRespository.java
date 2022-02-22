package com.sf.tool.script.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sf.tool.script.repository.transform.EntryMapKeyResultTransformer;

@Repository
public class NativeRespository {
    
    @Autowired
    private EntityManager entityManager;
    
    public List<Map<String, Object>> query(String sql) {
        final Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQuery.class).setResultTransformer(EntryMapKeyResultTransformer.getINSTANCE());
        
        return (List<Map<String, Object>>) query.getResultList();
    }
    
    public int execute(String sql) {
        return entityManager.createNativeQuery(sql).executeUpdate();
    }
}
