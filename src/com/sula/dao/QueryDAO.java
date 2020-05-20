package com.sula.dao;

import com.sula.entity.CustomEntity;

import javax.sql.DataSource;
import java.util.List;

public interface QueryDAO {


    public List<CustomEntity> getCustomEntityList();

}
