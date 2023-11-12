package com.example.dao.impl;

import com.example.dao.LoginDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item findItemById(String id) {
        return mongoTemplate.findById(id,Item.class);
    }
}
