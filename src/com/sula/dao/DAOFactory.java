package com.sula.dao;

import com.sula.dao.MemberDAO;
import com.sula.dao.daoimpl.*;
import com.sula.resources.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;


public class DAOFactory {

    public static AnnotationConfigApplicationContext ctx;


    static {


        ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

    }


    public static MemberDAOImpl getMemberDAO(){


        return ctx.getBean(MemberDAOImpl.class);
    }

    public static BookDAOImpl getBookDAO(){


        return ctx.getBean(BookDAOImpl.class);
    }

    public static BorrowDAOImpl getBorrowDAO(){


        return ctx.getBean(BorrowDAOImpl.class);
    }

    public static ReturnDAOImpl getReturnDAO(){



        return ctx.getBean(ReturnDAOImpl.class);
    }

    public static QueryDAOImpl getQueryDAO(){

        return ctx.getBean(QueryDAOImpl.class);
    }



}
