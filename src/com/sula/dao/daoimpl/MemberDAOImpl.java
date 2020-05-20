package com.sula.dao.daoimpl;

import com.sula.dao.MemberDAO;
import com.sula.dao.mapper.MemberMapper;
import com.sula.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;


@Transactional
@Repository
@Qualifier("memberdaoimpl")
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;



    public void create(String id, String name, String contact, String address) {
        String SQL = "insert into member (id, name, contact, address) values (?, ?,?,?)";
        jdbcTemplateObject.update( SQL, id, name, contact, address);
        //System.out.println("Created Record Name = " + name + " Age = " + age);
        return;
    }
    public Member getMember(String id) {
        String SQL = "select * from Member where id = ?";
        Member member = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new MemberMapper());

        return member;
    }



    public List<Member> listMembers() {
        String SQL = "select * from Member";
        List <Member> members = jdbcTemplateObject.query(SQL, new MemberMapper());
        return members;
    }
    public void delete(String id) {
        String SQL = "delete from Member where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
        return;
    }
    public void update(String id, String name, String contact, String address){
        String SQL = "update Member set name = ?, contact=?, address=? where id = ?";
        jdbcTemplateObject.update(SQL, id, name, contact, address);
        System.out.println("Updated Record with ID = " + id );
        return;
    }

    @Override
    public Member getLastMember() {
        String SQL = "SELECT * FROM member ORDER BY id DESC LIMIT 1";
        Member member = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{}, new MemberMapper());

        return member;
    }
}
