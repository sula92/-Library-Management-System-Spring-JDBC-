package com.sula.dao.daoimpl;

import com.sula.dao.mapper.BorrowMapper;
import com.sula.dao.ReturnDAO;
import com.sula.dao.mapper.ReturnMapper;
import com.sula.entity.Borrow;
import com.sula.entity.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
@Qualifier("returndaoimpl")
public class ReturnDAOImpl implements ReturnDAO {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource=ds;
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(String borowid, Date bordate,int amount) {
        try {
            String SQL = "insert into book_return values(?,?)";
            jdbcTemplate.update( SQL, borowid, bordate);

            String SQL2="insert into fee values(?,?)";
            jdbcTemplate.update(SQL2,borowid,amount);

            return true;

        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public Return getBooksLastReturn(String id) {
        String SQL = "SELECT * from borrow  where book_id=? ORDER BY borrow_id DESC LIMIT 1";
        Return aReturn = jdbcTemplate.queryForObject(SQL,
                new Object[]{id}, new ReturnMapper());

        return aReturn;
    }

    @Override
    public List<Borrow> listBorrows() {

        String SQL = "SELECT * FROM borrow WHERE borrow_id NOT IN (SELECT borrow_id FROM book_return)";
        List <Borrow> borrows = jdbcTemplate.query(SQL,new BorrowMapper());
        return borrows;
    }

    @Override
    public void delete(String id) {

        String SQL = "delete from borrow where borrow_id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
        return;

    }

    @Override
    public void update(String borowid, Date bordate) {
        String SQL = "update book_return set date=? where borrow_id=?";
        jdbcTemplate.update(SQL, bordate,borowid);
        System.out.println("Updated Record with ID = " + borowid );
        return;

    }

    @Override
    public Return getLastReturn() {
        String SQL = "SELECT * from borrow ORDER BY borrow_id DESC LIMIT 1";
        Return aReturn = jdbcTemplate.queryForObject(SQL, new ReturnMapper());

        return aReturn;
    }

    @Override
    public boolean checkReturn(String bid) {
        try {
            String SQL = "SELECT * FROM book_return where borrow_id=?";
            Return aReturn = jdbcTemplate.queryForObject(SQL,
                    new Object[]{bid}, new ReturnMapper());
            return true;

        }
        catch (Exception e){
            return false;
        }

    }


}
