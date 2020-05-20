package com.sula.dao;

import com.sula.entity.Member;

import java.util.List;
import javax.sql.DataSource;

public interface MemberDAO {
   /** 
      * This is the method to be used to initialize
      * database com.sula.resources ie. connection.
   */
   
   /** 
      * This is the method to be used to list down
      * all the records from the Student table.
   */

   public void create(String id, String name, String contact, String address);

   /**
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public Member getMember(String id);

   /**
    * This is the method to be used to list down
    * all the records from the Student table.
    */
   public List<Member> listMembers();

   /**
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public void delete(String id);

   /**
    * This is the method to be used to update
    * a record into the Student table.
    */
   public void update(String id, String name, String contact, String address);

   public Member getLastMember();

}