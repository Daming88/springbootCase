package com.daming.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseConnection;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.integration.spring.SpringResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    @Qualifier("test2Liquibase")
    SpringLiquibase test2Liquibase;

    @Autowired
    @Qualifier("test1liquibase")
    SpringLiquibase test1liquibase;

    @RequestMapping("/test")
    public String test() {
        try {
            // test1
            ResourceAccessor huaqiaoresourceAccessor = new SpringResourceAccessor(test2Liquibase.getResourceLoader());
            DatabaseConnection huaqiaoconnection = new JdbcConnection(test2Liquibase.getDataSource().getConnection());
            // 指明配置文件位置
            Liquibase huaqiaoliquibase = new Liquibase("liquibase/master.xml", huaqiaoresourceAccessor, huaqiaoconnection);
            // 第一个参数，用于指定对应的版本，第三个参数用于指定配置文件中changeSet标签中标注的contexts
            huaqiaoliquibase.rollback("2", null, new Contexts(test2Liquibase.getContexts()), new LabelExpression());
            huaqiaoliquibase.close();

            // test2
            ResourceAccessor jieyangresourceAccessor = new SpringResourceAccessor(test1liquibase.getResourceLoader());
            DatabaseConnection jieyangconnection = new JdbcConnection(test1liquibase.getDataSource().getConnection());
            // 指明配置文件位置
            Liquibase jieayngliquibase = new Liquibase("liquibase/master.xml", jieyangresourceAccessor, jieyangconnection);
            // 第一个参数，用于指定对应的版本，第三个参数用于指定配置文件中changeSet标签中标注的contexts
            jieayngliquibase.rollback("2", null, new Contexts(test1liquibase.getContexts()), new LabelExpression());
            jieayngliquibase.close();
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
        return "Ok";
    }

}
