package com.sf.tool.script.repository.transform;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

public class EntryMapKeyResultTransformer  extends AliasedTupleSubsetResultTransformer {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final EntryMapKeyResultTransformer INSTANCE = new EntryMapKeyResultTransformer();
    
    private EntryMapKeyResultTransformer() {
        
    }
    
    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Map<String, Object> result = new HashMap<>(tuple.length + 1);
        for (int i = 0 ; i < tuple.length ; i++) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }
        
        result.put("_keys_", aliases);
        
        return result;
    }
    
    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    /**
     * @return {@link #iNSTANCE}
     */
    public static final EntryMapKeyResultTransformer getINSTANCE() {
        return INSTANCE;
    }

}
