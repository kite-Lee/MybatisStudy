# ==速览各配置文件最终内容==

#### `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lee</groupId>
    <artifactId>ssmbuild</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <!--导入依赖-->
    <!--数据库驱动 连接池 servlet jsp mybatis mybatis-spring spring Junit-->
    <dependencies>

        <!--  mysql 驱动  -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>
        <!--  数据库连接池 c3p0  -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.5</version>
        </dependency>


        <!--  servlet  -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <!--  jsp  -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <!--  jstl  -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>


        <!--  mybatis  -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>
        <!--  mybatis-spring  -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>


        <!--  Spring  -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.13</version>
        </dependency>
        <!--  spring-jdbc  -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.18</version>
        </dependency>


        <!--  junit  -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!--  lombok  -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>

    </dependencies>


    <!--  静态资源导出问题  -->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

</project>
```

#### `mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!-- 核心配置文件 -->
<configuration>
    <!--  配置数据源，交给 Spring 去做  -->

    <!--  日志功能  -->
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>

    <typeAliases>
        <package name="com.lee.pojo"/>
    </typeAliases>


    <!--  可以被 spring-dao 中的配置所取代  -->

<!--    <mappers>-->
<!--        <mapper class="com.lee.dao.BookMapper"/>-->
<!--    </mappers>-->

</configuration>
```

#### `database.properties`

```properties
# c3p0
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8
jdbc.username=root
jdbc.password=ll546546

# spring jdbc
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8
username=root
password=ll546546
```

#### `applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--总的 spring 配置包 -->
    <import resource="classpath:spring-dao.xml"/>
    <import resource="classpath:spring-service.xml"/>
    <import resource="classpath:springmvc-servlet.xml"/>
</beans>
```

#### `spring-dao.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">


    <!-- spring数据库 配置 -->
<!--    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">-->
<!--        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>-->
<!--        <property name="url" value="jdbc:mysql://localhost:3306/ssmbuild?useSSL=false&amp;-->
<!--                                useUnicode=true&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"/>-->
<!--        <property name="username" value="root"/>-->
<!--        <property name="password" value="ll546546"/>-->
<!--    </bean>-->


    <!--  1. 关联数据库配置文件  -->
    <context:property-placeholder location="classpath:database.properties"/>
    <!--  2. 连接池  -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <!--  c3p0 连接池的私有属性  -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--  关闭连接后不自动commit  -->
        <property name="autoCommitOnClose" value="false"/>
        <!--  获取连接超时时间  -->
        <property name="checkoutTimeout" value="10000"/>
        <!--  当获取连接失败重试次数  -->
        <property name="acquireRetryAttempts" value="2"/>

    </bean>


    <!--  3. SqlSessionFactory  -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--  绑定 mybatis配置文件  -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>


    <!--  4. 配置 dao接口扫描包，动态的实现了 dao 接口可以注入到 Spring 容器中   -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--  注入 SQLSessionFactory  -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--  要扫描的包  -->
        <property name="basePackage" value="com.lee.dao"/>      <!-- 替代 mapper 映射 -->
    </bean>

</beans>
```

#### `spring-service.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">


    <!--  1. 扫描 service 下的包  -->
    <context:component-scan base-package="com.lee.service"/>    <!-- BookServiceImpl 添加 @Service 就可以扫描到  -->

    <!--  2.  将所有的业务类注入到 Spring , 可以通过配置 或者注解实现  -->

    <bean id="bookServiceImpl" class="com.lee.service.BookServiceImpl">
<!--    <bean id="bookServiceImpl" class="com.lee.service.BookServiceImpl">-->

        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <!--  3. 声明式事务配置  -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--  注入数据源  -->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--  4. aop 事务支持  -->

</beans>
```

#### `springmvc-servlet.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--  1. 注解驱动  -->
    <mvc:annotation-driven/>
    <!------------------------------------------------------------------------------>
    <!------------------------------------------------------------------------------>
 	<!--  1. 若使用 JSON 还要加入 JSON乱码过滤  -->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
    <!------------------------------------------------------------------------------>
    <!------------------------------------------------------------------------------>
    
 
    <!--  2. 静态资源过滤  -->
    <mvc:default-servlet-handler/>

    <!--  3. 扫描包 controller -->
    <context:component-scan base-package="com.lee.controller"/>

    <!--  4. 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

#### `web.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--  DispatcherServlet  -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!--  乱码过滤  -->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!--  session  -->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>

</web-app>
```

# SSM模板

## SSM整合-mybatis

### 新建maven 项目

### 导入依赖、解决静态资源导出问题

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lee</groupId>
    <artifactId>ssmbuild</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <!--导入依赖-->
    <!--数据库驱动 连接池 servlet jsp mybatis mybatis-spring spring Junit-->
    <dependencies>

        <!--  mysql 驱动  -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>
        <!--  数据库连接池 c3p0  -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.5</version>
        </dependency>


        <!--  servlet  -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <!--  jsp  -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <!--  jstl  -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>


        <!--  mybatis  -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>
        <!--  mybatis-spring  -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>


        <!--  Spring  -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.13</version>
        </dependency>
        <!--  spring-jdbc  -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.18</version>
        </dependency>


        <!--  junit  -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        
        <!--  lombok  -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>
        
    </dependencies>

    <!--  静态资源导出问题  -->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
    
</project>
```

### 连接数据库

```mysql
create database ssmbuild;
use ssmbuild;

create table books (
    bookID int(50) not null auto_increment comment '书id',
    bookName varchar(100) not null comment '书名',
    bookCounts int(11) not null comment '数量',
    detail varchar(200) not null comment '描述',
    key bookID (bookID)
) engine=innodb default charset =utf8;

insert into books (bookID, bookName, bookCounts, detail)
VALUES (1, 'java', 1, '从入门到放弃'),
       (2, 'MySQL', 10, '从删库到跑路'),
       (3, 'Linux', 5, '从进门到进牢');
```

​	`...`

### 新建配置文件	(spring/mybatis/db)

#### `applicationContext.xml`（Spring）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--总的 spring 配置包 -->
    <import resource="classpath:spring-dao.xml"/>
    <import resource="classpath:spring-service.xml"/>
    <import resource="classpath:springmvc-servlet.xml"/>
</beans>
```

#### `mybatis-config.xml`（mybatis）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!-- 核心配置文件 -->
<configuration>
    <!--  配置数据源，交给 Spring 去做  -->

    <typeAliases>
        <package name="com.lee.pojo"/>
    </typeAliases>


</configuration>
```

#### `database.properties`	 (连接数据库信息)

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8
jdbc.username=root
jdbc.password=ll546546
```

### pojo

#### 新建实体类 Book

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookID;
    private String bookName;
    private int bookCounts;
    private String detail;
}
```

### dao	(mapper)

#### `BookMapper.java` 

```java
import com.lee.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    // 增加一本书
    int addBook(Book book);

    // 删除一本书
    int deleteBook(@Param("bookId") int id);

    // 更新一本书
    int updateBook(Book book);

    // 查询一本书
    Book getBookById(@Param("bookId") int id);

    // 查询全部的书
    List<Book> getAllBooks();

}
```

#### `BookMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace 绑定一个对应的 Dao / Mapper 接口-->

<mapper namespace="com.lee.dao.BookMapper">

    <!--  增加一本书  -->
    <insert id="addBook" parameterType="Book">
        insert into ssmbuild.books (bookName, bookCounts, detail)
        VALUES (#{bookName}, #{bookCounts}, #{detail});
    </insert>

    <!--  删除一本书  -->
    <delete id="deleteBook" parameterType="int">
        delete from ssmbuild.books where bookID = #{bookId};
    </delete>
    
    <!--  更新一本书  -->
    <update id="updateBook" parameterType="Book">
        update ssmbuild.books set bookName = #{bookName}, bookCounts = #{bookCounts}, detail = #{detail}
        where bookID = #{bookId};
    </update>

    <!--  查询一本书  -->
    <select id="getBookById" resultType="Book">
        select * from ssmbuild.books where bookID = #{bookId};
    </select>


    <!--  查询全部的书  -->
    <select id="getAllBooks" resultType="Book">
        select * from ssmbuild.books;
    </select>


</mapper>
```

#### mybatis-config.xml  新增mapper映射

```xml
<mappers>
	<mapper class="com.lee.dao.BookMapper"/>
</mappers>
```

增加后：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!-- 核心配置文件 -->
<configuration>
    <!--  配置数据源，交给 Spring 去做  -->

    <typeAliases>
        <package name="com.lee.pojo"/>
    </typeAliases>

    <mappers>
        <mapper class="com.lee.dao.BookMapper"/>	<!--  可以被 spring-dao 中的配置所取代  -->
    </mappers>

</configuration>
```

### service

`BookService.java`

```java
package com.lee.service;

import com.lee.pojo.Book;

import java.util.List;

public interface BookService {

    // 增加一本书
    int addBook(Book book);

    // 删除一本书
    int deleteBook(int id);

    // 更新一本书
    int updateBook(Book book);

    // 查询一本书
    Book getBookById(int id);

    // 查询全部的书
    List<Book> getAllBooks();
}
```

`BookServiceImpl.java`

```java
package com.lee.service;

import com.lee.dao.BookMapper;
import com.lee.pojo.Book;

import java.util.List;

// BookServiceImpl 添加 @Service 就可以被 spring-service 扫描到  
public class BookServiceImpl implements BookService{

    // service 调用 dao
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book getBookById(int id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

}
```

## SSM整合-Spring

### 新建 `spring-dao.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">


    <!-- spring数据库 配置 -->
<!--    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">-->
<!--        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>-->
<!--        <property name="url" value="jdbc:mysql://localhost:3306/ssmbuild?useSSL=false&amp;-->
<!--                                useUnicode=true&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"/>-->
<!--        <property name="username" value="root"/>-->
<!--        <property name="password" value="ll546546"/>-->
<!--    </bean>-->


    <!--  1. 关联数据库配置文件  -->
    <context:property-placeholder location="classpath:database.properties"/>
    <!--  2. 连接池  -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <!--  c3p0 连接池的私有属性  -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--  关闭连接后不自动commit  -->
        <property name="autoCommitOnClose" value="false"/>
        <!--  获取连接超时时间  -->
        <property name="checkoutTimeout" value="10000"/>
        <!--  当获取连接失败重试次数  -->
        <property name="acquireRetryAttempts" value="2"/>

    </bean>


    <!--  3. SqlSessionFactory  -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--  绑定 mybatis配置文件  -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>
    
    <!--  4. 配置 dao接口扫描包，动态的实现了 dao 接口可以注入到 Spring 容器中   -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--  注入 SQLSessionFactory  -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--  要扫描的包  -->
        <property name="basePackage" value="com.lee.dao"/>      <!-- 替代 mapper 映射 -->
    </bean>

</beans>
```

### spring-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">


    <!--  1. 扫描 service 下的包  -->
    <context:component-scan base-package="com.lee.service"/>    <!-- BookServiceImpl 添加 @Service 就可以扫描到  -->

    <!--  2.  将所有的业务类注入到 Spring , 可以通过配置 或者注解实现  -->
    <bean id="bookServiceImpl" class="com.lee.service.BookServiceImpl">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <!--  3. 声明式事务配置  -->
    <bean id="tr" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--  注入数据源  -->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--  4. aop 事务支持  -->

</beans>
```



## SSM整合-SpringMVC

### 添加 web 支持

### 配置web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--  DispatcherServlet  -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        <!--
		后续修改为：
            <param-value>classpath:applicationContext.xml</param-value>
		-->
           
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!--  乱码过滤  -->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!--  session  -->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>

</web-app>
```

### 新建 `springmvc-servlet.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--  1. 注解驱动  -->
    <mvc:annotation-driven/>

    <!--  2. 静态资源过滤  -->
    <mvc:default-servlet-handler/>

    <!--  3. 扫描包 controller -->
    <context:component-scan base-package="com.lee.controller"/>

    <!--  4. 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

### 在WEB-INF目录下新建文件夹 jsp

### controller

#### 新建 BookController.java

```java
package com.lee.controller;

import com.lee.pojo.Book;
import com.lee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // controller 调用 service
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    // 查询全部的书 并返回到一个数据展示页面
    @RequestMapping("/allBooks")
    public String listBook(Model model) {

        List<Book> bookList = bookService.getAllBooks();

        model.addAttribute("list", bookList);

        return "allBooks";
    }


}

```

#### WEB/jsp/ 下新建 `allBooks.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>

</head>
<body>

<h1>书籍展示</h1>

</body>
</html>
```

#### 修改欢迎页 `index.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <a href="${pageContext.request.contextPath}/book/allBooks">进入书籍页面</a>
  </body>
</html>
```

### Test

```java
package com.lee;

import com.lee.pojo.Book;
import com.lee.service.BookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test01() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService service = context.getBean("bookServiceImpl",BookService.class);
        List<Book> bookList = service.getAllBooks();

        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
```

### 修改 web.xml  `classpath:applicationContext.xml`

```xml
<!--  DispatcherServlet  -->
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

### ==至此配置基本完成==

### 美化

#### 美化 `index.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <style>
      a {
        text-decoration: none;
        color: black;
        font-size: 18px;

      }
      h3 {
        width: 180px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background: cornflowerblue;
        border-radius: 5px;


      }
    </style>
  </head>
  <body>
  <h3>
    <a href="${pageContext.request.contextPath}/book/allBooks">进入书籍页面</a>
  </h3>
  </body>
</html>
```

#### 美化 allBooks.jsp 并添加 新增书籍 请求

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <%--  BootStrap 美化界面  --%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表--显示所有书籍</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>

                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>

```



### ![image-20220501214146725](https://s2.loli.net/2022/05/01/jXJy8QTVf69Ggol.png)插入功能

#### 在 WEB-INF/jsp下新建 `addBook.jsp`

```jsp
<%--
  Created by IntelliJ IDEA.
  User: KITE
  Date: 22/05/01
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增书籍</title>
    <%--  BootStrap 美化界面  --%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>新增书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/addBook" method="post">

        <div class="form-group">
            <label for="book_name">书籍名称</label>
            <input type="text" class="form-control" id="book_name" name="bookName" required>
        </div>
        <div class="form-group">
            <label for="book_counts">书籍数量</label>
            <input type="text" class="form-control" id="book_counts" name="bookCounts" required>
        </div>
        <div class="form-group">
            <label for="detail">书籍详情</label>
            <input type="text" class="form-control" id="detail" name="detail" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="添加">
        </div>
    </form>
</div>



</body>
</html>

```

#### 跳转至新增页面、新增书籍  BookController

```java
// 跳转至新增书籍页面
@RequestMapping("/toAddBook")
public String toAddBookPage() {
    return "addBook";
}

// 新增书籍
@RequestMapping("/addBook")
public String addBook(Book book, Model model) {
    bookService.addBook(book);
    return "redirect:/book/allBooks";
}
```

![image-20220501214219259](https://s2.loli.net/2022/05/01/jPiTeCfrukFpGaq.png)

### 修改删除功能

#### allBooks.jsp增加 修改删除 请求

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <%--  BootStrap 美化界面  --%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表--显示所有书籍</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                            <td>
                                <a href=
     "${pageContext.request.contextPath}/book/toUpdateBook?id=${book.bookID}">修改</a>
                                &nbsp; | &nbsp;
                                <a href=
      "${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>
```

![image-20220501231318908](https://s2.loli.net/2022/05/01/Vk2jIG3wfWHFxlX.png)

#### 新建 `updateBook.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <%--  BootStrap 美化界面  --%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <input type="hidden" name="bookID" value="${GotBook.bookID}">
        <div class="form-group">
            <label>书籍名称</label>
            <input type="text" class="form-control" name="bookName" value="${GotBook.bookName}" required>
        </div>
        <div class="form-group">
            <label>书籍数量</label>
            <input type="text" class="form-control" name="bookCounts" value="${GotBook.bookCounts}" required>
        </div>
        <div class="form-group">
            <label>书籍详情</label>
            <input type="text" class="form-control" name="detail" value="${GotBook.detail}" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="确认修改">
        </div>
    </form>
</div>



</body>
</html>
```

![image-20220501231334362](https://s2.loli.net/2022/05/01/3SecW7Bn462xCys.png)

#### BookController

```java
// 跳转至修改书籍页面
@RequestMapping("/toUpdateBook")
public String toUpdateBookPage(int id, Model model) {
    // System.out.println("toUpdateBook");
    Book book = bookService.getBookById(id);

    model.addAttribute("GotBook", book);
    return "updateBook";
}

// 修改书籍
@RequestMapping("/updateBook")
public String updateBook(Book book) {
    bookService.updateBook(book);
    return "redirect:/book/allBooks";
}


// 删除书籍
@RequestMapping("/deleteBook/{bookId}")
public String updateBook(@PathVariable("bookId") int id) {
    bookService.deleteBook(id);
    return "redirect:/book/allBooks";
}
```

### 查询功能

#### BookMapper.java 添加查询方法接口 

```java
// 查询
List<Book> getBookByName(String bookName);
```

#### BookMapper.xml 编写sql

```xml
<!--  查询  -->
<select id="getBookByName" resultType="Book">
    select * from ssmbuild.books where bookName = #{bookName};
</select>
```

#### BookService.java 添加查询方法接口

```java
// 根据书名查询
List<Book> getBookByName(String bookName);
```

#### BookServiceImpl.java 实现查询方法接口

```java
@Override
public List<Book> getBookByName(String bookName) {
    return bookMapper.getBookByName(bookName);
}
```

#### BookController 实现页面跳转及结果反馈

```java
// 查询书籍
@RequestMapping("/getBookByName")
public String getBook(String bookName, Model model) {
    System.out.println("--------------------" + bookName);
    List<Book> bookList = bookService.getBookByName(bookName);
    if (bookList.isEmpty()) {
        model.addAttribute("error", "true");
    } else {
        model.addAttribute("list", bookList);
    }
    return "allBooks";
}
```

#### allBooks.jsp 添加 查询请求以及查询为空时的提醒

```jsp
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <%--  BootStrap 美化界面  --%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表--显示所有书籍</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/allBooks">返回书籍目录</a>
            </div>
            <div class="col-md-8 column">
                <form action="${pageContext.request.contextPath}/book/getBookByName" method="post"
                      class="form-inline" style="float: right">
                            <input type="text" name="bookName" class="form-control" placeholder="请输入要查询的书籍名称">
                            <input type="submit" value="查询" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 column">
                <div>
                    
                    <%--  搜索为空时的三种解决方案  --%>
                    <c:if test="${true eq error}">
                        
                        <%--   提示窗口    --%>
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h4 style="color: red; text-align: center">
                                <strong>注意! </strong>查询内容为空！请<a href="${pageContext.request.contextPath}/book/allBooks" >返回书籍目录</a>
                            </h4>
                        </div>
                        
                        <%--   alert 弹窗  --%>
                        <%out.print("<script>alert('查找内容为空');</script>");%>

                        <%--  重定向首页   --%>
                        <%response.sendRedirect(request.getContextPath()+"/book/allBooks");%>
                        
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/book/toUpdateBook?id=${book.bookID}">修改</a>
                                &nbsp; | &nbsp;
                                <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>
```

### 增加日志功能

#### mybatis-config.xml 

```xml
<!--  日志功能  -->
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

