package com.example.generator.logic;

import com.example.generator.mapper.LikesMapper;
import com.example.generator.mapper.OperationLogMapper;
import com.example.generator.model.Likes;
import com.example.generator.model.OperationLog;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class OperationLogLogic {

    @Autowired
    private OperationLogMapper operationLogMapper;

    public List<OperationLog> queryList(OperationLog log) {
        Example example=new Example(OperationLog.class);
        if (!StringUtils.isEmpty(log.getUserName())){
        example.createCriteria().andEqualTo("userName",log.getUserName());}
        return operationLogMapper.selectByExample(example);
    }



    public OperationLog insert(OperationLog log) {
        operationLogMapper.insertSelective(log);
        return operationLogMapper.selectByPrimaryKey(log.getId());
    }

}
