package com.sula.dao.daoimpl;

import com.sula.dao.mapper.CustomEntityMapper;
import com.sula.dao.QueryDAO;
import com.sula.entity.CustomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Qualifier("querydaoimpl")
public class QueryDAOImpl implements QueryDAO {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<CustomEntity> getCustomEntityList() {
        String SQL = "SELECT B.borrow_id, B.date, R.date, F.amount FROM ((borrow B INNER JOIN book_return R ON B.borrow_id = R.borrow_id) INNER JOIN fee F ON R.borrow_id = F.borrow_id)";
        List <CustomEntity> customEntities = jdbcTemplate.query(SQL, new CustomEntityMapper());
        return customEntities;
    }
}
