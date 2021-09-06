package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/***
 * @see
 */
@SpringBootApplication
public class SpringBootPackageApplication extends SpringBootServletInitializer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // return super.configure(builder);
        return builder.sources(SpringBootPackageApplication.class);
    }

    /**
     * 注意事项: 原本 spring boot 项目在 intellij 中进行启动测试时,访问路径为: http://localhost:${port1}
     *
     * 但是,部署到外部 tomcat 之后,访问路径则需要添加项目名称,即: http://localhost:${port2}/${projectName}
     *
     * port1 : spring boot 配置文件中(yml) 设置的服务端口号
     *
     * port2: 服务器 tomcat 的访问端口号
     *
     * projectName: 部署到服务器 tomcat 的项目名称(tomcat 的 webapp 下的项目文件夹名称)
     *
     * 使用 tomcat 部署后的项目访问路径 : http://localhost:8080/{projectName}/
     * */
    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        // 本地调试是启用内嵌Tomcat的端口, 和部署到线上启用外置Tomcat的监听端口不一致
        // 优先级: 代码的设置会覆盖配置文件里面 server.port 的设置
        server.setPort(8083);
    }

    /**
     * 此项目用 Tomcat 9 可以顺利发布, 用 Tomcat 10 则不行, Tomcat 10 相对于 9 改动很大, 对于原有服务有迁移成本在
     * */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootPackageApplication.class, args);
    }

}

