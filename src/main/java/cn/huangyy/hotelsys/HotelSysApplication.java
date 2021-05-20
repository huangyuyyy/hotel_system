package cn.huangyy.hotelsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.huangyy")
@MapperScan("cn.huangyy.hotelsys.mapper")
public class HotelSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelSysApplication.class,args);
    }
}
