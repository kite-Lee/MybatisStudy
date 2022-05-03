# 1 简介

MyBatis是一款优秀的**持久层框架**，它支持定制化SQL、存储过程以及高级映射。MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。MyBatis可以使用简单的XML或注解来配置和映射原生类型、接口和Java的POJO（Plain Old Java Objects,普通老式Java 对象）为数据库中的记录。

**数据持久化**

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：断电即失
- 数据库(（Jdbc)，io文件持久化。

**持久层**

Dao层， Service层， Controller层…

- 完成持久化工作的代码块
- 层界限十分明显。

**优点**

- 简单易学
- 灵活
- sq和代码的分离，提高了可维护性。
- 提供映射标签，支持对象与数据库的orm字段关系映射
- 提供对象关系映射标签，支持对象关系组建维护
- 提供xml标签，支持编写动态sql

# 2 第一个 mybatis 程序

## 2.1 搭建环境

1. 新建一个普通的maven项目

2. 删除src目录

3. 导入maven依赖

   ```xml
   <!--  导入依赖  -->
   <dependencies>
       <!--  mysql 驱动  -->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.28</version>
       </dependency>
       <!--  mybatis  -->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.7</version>
       </dependency>
       <!--  junit  -->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
           <scope>test</scope>
       </dependency>
   </dependencies>
   ```

   ### 创建一个module

   ==【mybatis-01】==

   #### 创建子项目 mybatis-01

   在resources目录下编写mybatis 核心配置文件`mybatis-config.xml`(官网有模板)

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <!-- 核心配置文件 -->
   <configuration>
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"/>
                   <property name="username" value="root"/>
                   <property name="password" value="ll546546"/>
               </dataSource>
           </environment>
   <!--        <environment id="test">-->
   <!--            <transactionManager type="JDBC"/>-->
   <!--            <dataSource type="POOLED">-->
   <!--                <property name="driver" value="${driver}"/>-->
   <!--                <property name="url" value="${url}"/>-->
   <!--                <property name="username" value="${username}"/>-->
   <!--                <property name="password" value="${password}"/>-->
   <!--            </dataSource>-->
   <!--        </environment>-->
       </environments>
       
   <!--  每一个 Mapper.xml 都需要在 Mybatis 核心配置文件 mybatis-config.xml 中注册  -->
       <mappers>
           <mapper resource="com/lee/dao/UserMapper.xml"/>
       </mappers>
   </configuration>
   ```

### 编写mybatis 工具类 `MybatisUtils`

- 从 XML 中构建 SqlSessionFactory
- SqlSessionFactory 中获取 SqlSession
- SqLSession 完全包含了面向数据库执行 SQL 命令所需的所有方法

```java 

// 从 SqlSessionFactory 中得到 SqlSession
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            // 从 XML 中构建 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // SqlSessionFactory 中获取 SqlSession
    // SqLSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
```



## 2.2 编写代码

### 实现类

pojo > User

```java
public class User {
    private int id;
    private String name;
    private String pwd;
} // 略去构造器/getter/setter/toString
```

### Dao 接口

dao > UserDao

```java
public interface UserDao {List<User> getUserList();}
```

### 接口实现类

dao > UserMapper.xml	(~~UserDaoImpl~~)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace 绑定一个对应的 Dao / Mapper 接口-->
<mapper namespace="com.lee.dao.UserDao">
    <select id="getUserList" resultType="com.lee.pojo.User">
        select * from mybatis.user;
    </select>
</mapper>

```

## 2.3 测试

### test  > dao > UserDaoTest

```Java
package com.lee.dao;

import com.lee.pojo.User;
import com.lee.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test() {
        // 1. 获得 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方法一  推荐使用 getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();

        // 方法二 不推荐使用
        // List<User> userList = sqlSession.selectList("com.lee.dao.UserDao.getUserList");
        
        for (User user : userList) {
            System.out.println(user);
        }
        
        sqlSession.close();

    }
}
```

上述代码推荐格将 `sqlSession.close();` 方在 finally 里

### ==在核心文件中注册 Mapper==

> 每一个 Mapper.xml 都需要在 Mybatis 核心配置文件 mybatis-config.xml 中注册

```xml
<mappers>
    <mapper resource="com/lee/dao/UserMapper.xml"/>
</mappers>
```

方式一：

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <mapper resource="com/kuang/dao/UserMapper.xm1"/>.
</mappers>
```

**方式二：使用class文件绑定注册**

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <mapper class="com. kuang. dao.UserMapper"/>
</mappers>
```

注意点：

- 接口和他的Mapper配置文件必须同名！
- 接口和他的Mapper配置文件必须在同一个包下！

方式三：使用扫描包进行注入绑定

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <package name="com. kuang. dao"/>
</mappers>
```

注意点：

- 接口和他的Mapper配置文件必须同名！
- 接口和他的Mapper配置文件必须在同一个包下！

### 在 父工程 pom 文件中配置resources

> 在build中配置resources.来防止我们资源导出失败的问题

```xml
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
```

`SqlSessionFactoryBuilder`

这个类可以被实例化、使用和丢弃，**一旦创建了SqlSessionFactory，就不再需要它了**。因此SqlSessionFactosyBuilder实例的最佳作用域是方法作用域（也就是局部方法变量）。你可以重用SqlSessionFactoryBuilder来创建多个SqlSessionFactory 实例，但是最好还是不要让其一直存在，以保证所有的XML解析资源可以被释放给更重要的事情。

`SqlSessionFactory`

SqlSessionFactory一旦被创建就应该在应用的运行期间一直存在，**没有任何理由丢弃它或重新创建另一个实例**。使用SqlSessionFactory的最佳实践是在应用运行期间不要重复创建多次，多次重建SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。因此 SqlSessionFactory的最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用**单例模式或者静态单例模式**。

`SqlSession`

每个线程都应该有它自己的SqlSession实例。SqlSession的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。绝对不能将SqlSession实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。也绝不能将SqlSession实例的引用放在任何类型的托管作用域中，比如Servlet框架中的HttpSession。如果你现在正在使用一种 Web框架，要考虑 SqlSession 放在一个和HTTP请求对象相似的作用域中。换句话说，**每次收到的HTTP请求，就可以打开一个SqlSession，返回一个响应，就关闭它。**这个关闭操作是很重要的，你应该把这个关闭操作放到finally块中以确保每次都能执行关闭。

![image-20220421150608395](https://s2.loli.net/2022/04/21/bDzFZKXGS1NYmiE.png)

# 3 mybatis-CRUD

> 修改 UserDao.java  --> UserMapper.java

## 增删改查

新增查询 getUserById

- UserMapper.java 添加接口

```java
// 根据 id 查询用户
User getUserById(int id);
```

- UserMapper.xml

```xml
<!--  select 查询语句  -->
<select id="getUserById"  parameterType="int" resultType="com.lee.pojo.User">
    select * from mybatis.user where id = #{id}
</select>
```

测试getUserById

```Java
@Test
public void getUserByIdTest() {
    // 示例
    int id = 1;

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    User user = userMapper.getUserById(id);
    System.out.println(user);
    sqlSession.close();
}
```

> 需要重新加载内容

同理增加 增删改查的功能

```Java
// 根据 id 查询用户
User getUserById(int id);

// 插入一个新用户
int addUser(User user);

// 修改用户
int updateUser(User user);

// 删除用户
int deleteUser(int id);
```

> 增删改需要提交事务

可能会出现的错误

- 标签不要匹配错
- resource绑定mapper,需要使用路径！
- 程序配置文件必须符合规范！
- NullPointerException，没有注册到资源！
- 输出的xml文件中存在中文乱码问题！
- maven资源没有导出问题！

## 使用 map

> 实体类或者数据库中的表，字段或者参数过多，考虑使用map

Map传参数，直接在sql中取出key即可！【parameterType="map"】

对象传递参数，直接在sql中取对象的属性即可！【parameterType="Object"】

只有一个基本类型参数的情况下，可以直接在sql中取到！

多个参数用Map，或者**注解**！

`UserMapper.java`

```java
// 修改用户信息  update 使用 map
int updateUserByMap(Map<String, Object> map);
// 插入新用户信息  insert 使用 map
int addUserByMap(Map<String, Object> map);
// 查询使用 map
User getUserByMapId(Map<String, Object> map);
```

`UserMapper.xml`

```xml
<!--  update 使用 map  -->
<update id="updateUserByMap" parameterType="map">
    update mybatis.user set name = #{username}, pwd = #{password} where id = 3;
</update>

<!--  insert 使用 map  -->
<insert id="addUserByMap" parameterType="map">
    insert into mybatis.user (id, pwd) values (#{userid}, #{password});
</insert>

<!--  select 使用 map  -->
<select id="getUserByMapId" parameterType="map"  resultType="com.lee.pojo.User">
    select * from mybatis.user where id = #{userid} and name = #{username};
</select>
```

`test UseDaoTest.java`

```java
@Test
public void updateUserByMapTest() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    Map<String, Object> map = new HashMap<>();
    map.put("username", "Kite");
    map.put("password", "password");
    int i = userMapper.updateUserByMap(map);

    if (i > 0) {
        System.out.println("使用map修改用户成功");
    }

    sqlSession.commit();
    sqlSession.close();
}

@Test
public void addUserByMapTest() {

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    Map<String, Object> map = new HashMap<>();
    map.put("userid", 7);
    map.put("password", "546546");

    int i = userMapper.addUserByMap(map);
    if (i > 0) {
        System.out.println("使用map增加用户成功");
    }
    sqlSession.commit();
    sqlSession.close();
}

@Test
public void getUserByMapIdTest() {
    int id = 1;

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    Map<String, Object> map = new HashMap<>();
    map.put("userid", 2);
    map.put("username", "Bob");

    User user = userMapper.getUserByMapId(map);
    System.out.println(user);
    sqlSession.close();
}
```

## 模糊查询

`UserMapper.java`

```java
// 模糊查询
List<User> getUserLike(String value);
```

`UserMapper.xml`

```xml
<!--  模糊查询  -->
<select id="getUserLike" resultType="com.lee.pojo.User">
    select * from mybatis.user where name like #{value};
   <!-- select * from mybatis.user where name like "%"#{value}"%";    可以，但一般不使用，可能会被 SQL 注入 -->
</select>
```

`test UseDaoTest.java`

```java
@Test
public void getUserLike() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


    List<User> userList = userMapper.getUserLike("A%");

    for (User user : userList) {
        System.out.println(user);
    }

    sqlSession.close();
}
```



# 4 MyBatis配置文件

==【mybatis-02】==

Mybatis默认的事务管理器就是JDBC，连接池：POOLED

编写一个`db.properties`

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8
username=root
password=ll546546
```

```xml
<configuration>
    <!--  引入外部配置文件(必须放在configuration 标签下首行)  -->
    <properties resource="db.properties">
        <property name="username" value="wrong"/>
        <property name="password" value="wrongpwd"/>    <!-- 外部配置的优先级高于内部 -->
    </properties>
</configuration>
```

配置的各类属性顺序（错误会报错）

> properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,reflectorFactory?,
> plugins?,environments?,databaseldProvider?manre  r…  …yyr… yr……

configuration（配置）

properties（属性）

settings（设置）

typeAliases（类型别名）

typeHandlers（类型处理器）

objectFactory（对象工厂）

plugins（插件）

environments（环境配置）

environment（环境变量）

transactionManager（事务管理器）

datasource（数据源）

databaseIdProvider（数据库厂商标识）

mappers（映射器）



# 5 结果集映射（重点难点）

==【mybatis-03】==

## 属性名与字段名不一致

```Java
public class User {
    private int id;
    private String name;
    private String password;	// pwd --> password
}
```

结果：

```shell
User{id=1, name='Alice', password='null'}
User{id=2, name='Bob', password='null'}
User{id=3, name='Cindy', password='null'}
```

修改 `UserMapper.xml`	pwd as password

```xml
<!--  select 查询语句  -->
<select id="getUserList" resultType="com.lee.pojo.User">
    select id, name, pwd as password from mybatis.user;		 <!--  pwd as password  -->
</select>
```

结果：

```shell
User{id=1, name='Alice', password='123456'}
User{id=2, name='Bob', password='234567'}
User{id=3, name='Cindy', password='345678'}
```

## ResultMap

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="User">
    <!--  以下两行可以省略  -->
    <result column="id" property="id"/>
    <result column="name" property="name"/>
    
    <result column="pwd" property="password"/>
</resultMap>
<select id="getUserById" resultMap="UserMap">
    # select id, name, pwd as password from mybatis.user where id = #{id};
    select * from mybatis.user where id = #{id};
</select>
```



```xml
<!--  简单方法 -->
<select id="getUserById" resultType="User">
    select id, name, pwd as password from mybatis.user where id = #{id};
</select>


<!--  结果集映射  -->
<resultMap id="UserMap" type="User">
    <!--        <id column="id" property="id"/>-->
    <!--        <result column="name" property="name"/>-->
    <result column="pwd" property="password"/>
</resultMap>
<select id="getUserById" resultMap="UserMap">
    select * from mybatis.user where id = #{id};
</select>
```

# 6 日志

==【mybatis-04】==

## 日志工厂

- SLF4J
- Apache Commons Logging
- Log4j 2
- **Log4j**
- JDK logging
- commons logging
- **stdout logging**
- no logging

## stdout logging

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

```shell
Logging initialized using 'class org.apache.ibatis.logging.stdout.StdOutImpl' adapter.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
Opening JDBC Connection
Created connection 391877669.
Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@175b9425]
==>  Preparing: select * from mybatis.user where id = ?;
==> Parameters: 1(Integer)
<==    Columns: id, name, pwd
<==        Row: 1, Alice, 123456
<==      Total: 1
User{id=1, name='Alice', password='123456'}
Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@175b9425]
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@175b9425]
Returned connection 391877669 to pool.
```

## ==Log4j==

```xml
<!--  导入依赖 log4j  -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

```xml
<!--  mybatis-config.xml  -->
<settings>
    <setting name="logImpl" value="LOG4J"/>
</settings>
```

```properties
#将等级为DEBUG的日志信息输出到console和 file这两个目的地，console粉ile的定义在下面的代码
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/lee.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sq1.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG

```



```java
package com.lee.dao;

import org.apache.log4j.Logger;
import org.junit.Test;

public class UserDaoTest {
    static Logger logger = Logger.getLogger(UserDaoTest.class);		// !!!

    @Test
    public void testLog4j() {
        logger.info("info: 进入了testLog4j");
        logger.debug("debug: 进入了testLog4j");
        logger.error("error: 进入了testLog4j");
    }
}
```

![image-20220422000146738](https://s2.loli.net/2022/04/22/23ZTIEn6BoMVpfL.png)

![image-20220422000238547](https://s2.loli.net/2022/04/22/yupnWogL7UmsawZ.png)



# 7 分页

## limit

UserMapper.java

```java
// 分页
List<User> getUserByLimit(Map<String, Integer> map);
```

UserMapper.xml

```xml
<resultMap id="UserMap" type="User">
    <id column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>

<select id="getUserByLimit" parameterType="map" resultMap="UserMap">
    select * from mybatis.user limit #{startIndex}, #{pageSize};
</select>
```

test

```java
@Test
public void getUserByLimitTest() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    Map<String, Integer> map = new HashMap<>();
    map.put("startIndex", 0);
    map.put("pageSize", 2);
    List<User> userList = userMapper.getUserByLimit(map);

    for (User user : userList) {
        System.out.println(user);
    }

    sqlSession.close();
}
```

## RowBounds

UserMapper.java

```java
    List<User> getUserByRowBounds();
```

UserMapper.xml

```xml
<resultMap id="UserMap" type="User">
    <id column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>

<!--  分页 RowBounds  -->
<select id="getUserByRowBounds" resultMap="UserMap">
    select * from mybatis.user;
</select>
```

test

```java
@Test
public void getUserByRowBoundsTest() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();

    RowBounds rowBounds = new RowBounds(1, 2);
    List<User> userList = sqlSession.selectList("com.lee.dao.UserMapper.getUserByRowBounds", null, rowBounds);
    for (User user : userList) {
        System.out.println(user);
    }

    sqlSession.close();
}
```

## 分页插件PageHelper

# 8 使用注解开发（重点） 

==【mybatis-05】==

大家之前都学过面向对象编程，也学习过接口，但在真正的开发中，很多时候我们会选择面向接口编程

**根本原因：==解耦==，可拓展，提高复用，分层开发中，上层不用管具体的实现，大家都遵守共同的标准，使得开发变得容易，规范性更好**

在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的，对系统设计人员来讲就不那么重要了；

而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。



**关于接口的理解**

接口从更深层次的理解，应是定义（规范，约束）与实现（名实分离的原则）的分离。

接口的本身反映了系统设计人员对系统的抽象理解。

接口应有两类：

- 第一类是对一个个体的抽象，它可对应为一个抽象体（abstract class)；
- 第二类是对一个个体某一方面的抽象，即形成一个抽象面（interface)；

一个体有可能有多个抽象面。抽象体与抽象面是有区别的。

**三个面向区别**

面向对象是指，我们考虑问题时，以对象为单位，考虑它的属性及方法

面向过程是指，我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现。

接口设计与非接口设计是针对复用技术而言的，与面向对象（过程）不是一个问题.更多的体现就是对系统整体的架构



## 注解实现增删改查

`UserMapper.Java`

```java
@Select("select *  from user where id = #{id}")
User getUserById(@Param("id") int id);
// @Param("id") 中的 id 才是传递的参数， int id 中的 id 只是形参

@Insert("insert into user(id, name, pwd) values (#{id}, #{name}, #{password})")
int addUser(User user);

@Update("update user set name = #{name}, pwd = #{password} where id = #{id}")
int updateUser(User user);

@Delete("delete from user where id = #{uid}")
int deleteUser(@Param("uid") int id);
```

删除了 `UserMapper.xml`

`mybatis-config.xml`

```xml
<configuration>
<mappers>
        <mapper class="com.lee.dao.UserMapper"/>
             
        <!--  使用UserMapper.xml的时候 -->
        <!--  <mapper resource="com/lee/dao/UserMapper.xml"/>  -->
    </mappers>
</configuration>
```

test

```java
@Test
    public void getUsersTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.getUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void getUserByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(2);

        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void addUserTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int i = userMapper.addUser(new User(5,"Like", "567890"));
        System.out.println(i > 0);

        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void updateUserTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.updateUser(new User(5, "Dislike", "567890"));


        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void deleteUserTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.deleteUser(5);

        sqlSession.commit();
        sqlSession.close();
    }
```

>关于@Param()注解
>
>- 基本类型的参数或者String类型，需要加上
>- 引用类型不需要加
>- 如果只有一个基本类型的话，可以忽略，但是建议大家都加上！
>- 我们在SQL中引用的就是我们这里的@Param()中设定的属性名！
>
>#{ }  与 ${ }区别



# 9 Lombok

常用注解：

@Setter ：注解在类或字段，注解在类时为所有字段生成setter方法，注解在字段上时只为该字段生成setter方法。

@Getter ：使用方法同上，区别在于生成的是getter方法。

@ToString ：注解在类，添加toString方法。

@EqualsAndHashCode： 注解在类，生成hashCode和equals方法。

@NoArgsConstructor： 注解在类，生成无参的构造方法。

@RequiredArgsConstructor： 注解在类，为类中需要特殊处理的字段生成构造方法，比如final和被@NonNull注解的字段。

@AllArgsConstructor： 注解在类，生成包含类中所有字段的构造方法。

@Data： 注解在类，生成 无参构造， setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。

@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger

@Builder

@Singular

@Delegate

@Value

@Accessors

@Wither

@SneakyThrows

```xml
<!-- 导入依赖lombok  -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.22</version>
    <scope>provided</scope>
</dependency>
```

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
}
```

# 10 多对一的处理（难点）

==【mybatis-06】==

学生对老师

### 环境搭建

`Teacher.java` 

```Java
@Data
public class Teacher {

    private int id;
    private String name;

}
```

建立 Mapper 接口

`TeacherMapper.java` 

```Java
@Select("select * from teacher where id = #{tid}")
Teacher getTeacher(@Param("tid") int id);
```

在核心配置文件中绑定注册Mapper接口或者文件

`TeacherMapper.xml` 

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lee.dao.TeacherMapper">

</mapper>
```

Test

```java
@Test
public void getTeacher() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
    Teacher teacher = teacherMapper.getTeacher(1);
}
```

### 按照查询嵌套处理

通过学生查询其老师

`Student.java` 

```java 
@Data
public class Student {
    private int id;
    private String name;
    private Teacher teacher;
}
```

`StudentMapper.java` 

```java
// 查询所有学生的信息，以及对应老师的信息
public List<Student> getStudent();
```

`StudentMapper.xml` 

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lee.dao.StudentMapper">


    <select id="getStudent" resultMap="StudentTeacher">
        select * from student
    </select>

    <resultMap id="StudentTeacher" type="Student">
        <!--    简单属性    -->
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <!--    复杂属性    -->
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>

    <select id="getTeacher" resultType="Teacher">
        select * from teacher where id = #{id}
    </select>

</mapper>
```

Test

```Java
@Test
public void getStudentTest() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

    List<Student> studentList = studentMapper.getStudent();

    for (Student student : studentList) {
        System.out.println(student);
    }
}
```

### 按照结果嵌套查询

`StudentMapper.java` 

```java
// 查询所有学生的信息，以及对应老师的信息 按照结果嵌套查询
public List<Student> getStudent2();
```

`StudentMapper.xml` 

```xml
<!--  按照结果嵌套查询  -->
<select id="getStudent2" resultMap="StudentTeacher2">
    select s.id sid, s.name sname, t.name tname from student s, teacher t where s.tid = t.id;
</select>


<resultMap id="StudentTeacher2" type="Student">
    <id property="id" column="sid"/>
    <result property="name" column="tname"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
    </association>
</resultMap>
```

Test

```Java
@Test
public void getStudent2Test() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

    List<Student> studentList = studentMapper.getStudent2();

    for (Student student : studentList) {
        System.out.println(student);
    }
}
```

#  11 一对多的处理（难点）

==【mybatis-07】==

老师对学生

实体类修改

`Teacher.java`

```Java
@Data
public class Teacher {
    private int id;
    private String name;
    private List<Student> students;
}
```

`Student.java`

```java
@Data
public class Student {
    private int id;
    private String name;
    private int tid;
}
```

`TeacherMapper.java`

```java
// 获得指定老师名下的所有学生及老师的信息
// 按查询嵌套
Teacher getTeacherAllInfo(@Param("tid") int id);

// 获得指定老师名下的所有学生及老师的信息
// 按结果嵌套
Teacher getTeacherAllInfo2(@Param("tid") int id);
```

`TeacherMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lee.dao.TeacherMapper">


<!--  按查询嵌套  -->
    <select id="getTeacherAllInfo" parameterType="int" resultMap="TeacherStudent">
        select *  from  mybatis.teacher  where id = #{tid};
    </select>

    <resultMap id="TeacherStudent" type="Teacher">
        <id property="id" column="id"/>
<!--        <result property="name" column="name"/>-->
        <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherId" column="id"/>
    </resultMap>

    <select id="getStudentByTeacherId" resultType="Student">
        select * from mybatis.student where tid = #{tid}
    </select>

<!--  按结果嵌套  -->
    <select id="getTeacherAllInfo2" parameterType="int" resultMap="TeacherStudent2">
        select t.id t_id, t.name tname, s.name sname from student s, teacher t where s.tid = t.id and t.id = #{tid};
    </select>

    <resultMap id="TeacherStudent2" type="Teacher">
        <result property="id" column="t_id"/>
        <result property="name" column="tname"/>
        <collection property="students" ofType="Student">
            <id property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="t_id"/>
        </collection>
    </resultMap>
</mapper>
```

Test

```java
@Test
public void getTeacherAllInfoTest() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
    Teacher teacher =  teacherMapper.getTeacherAllInfo(1);

    System.out.println(teacher);
}

@Test
public void getTeacherAllInfo2Test() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
    Teacher teacher =  teacherMapper.getTeacherAllInfo2(1);

    System.out.println(teacher);
}
```

>关联-association 【多对一】
>
>集合-collection【一对多】
>
>JavaType 用来指定实体类中属性的类型
>
>ofType 用来指定映射到List或者集合中的**pojo类型**，泛型中的约束类型！

# 12 动态SQL（重点）

==【mybatis-08】==

```mysql
create table blog (
    id varchar(50) not null comment '博客ID',
    title varchar(100) not null comment '博客标题',
    author varchar(30) not null comment '博客作者',
    create_time datetime not null comment '创建时间',
    view int(30) not null comment '浏览量'
) engine=innodb default char set =utf8;
```

```java
List<Blog> queryBlogIF(Map map);

List<Blog> queryBlogWhere(Map map);

List<Blog> queryBlogChoose(Map map);
```

### if

> if 标签之前一般要添加 `where 1 = 1` ，多个标签可同时出现在一条语句中

```xml
<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from mybatis.blog where 1 = 1
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</select>
```

### where

>where元素只会在至少有一个子元素的条件返回SQL子句的情况下才去插入“WHERE”子句。而且，若语句的开头为 AND或 OR，where元素也会将它们去除。

```xml
<select id="queryBlogWhere" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <if test="title != null">
            title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </where>
</select>
```

### choose

> 选择第一条满足的语句执行，不会同时使用多个条件
>
> **(where) choose  when  otherwise**
>
> ```xml
> <choose>
>     <when test="title != null">
>         And title = #{title}
>     </when>
>     <otherwise>
>         and views = #{views}
>     </otherwise>
> </choose>
> ```

```xml
<select id="queryBlogChoose" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <choose>
            <when test="title != null">
                And title = #{title}
            </when>
            <when test="author != null">
                And author = #{author}
            </when>
            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>
```

### set

> set 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）

```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author = #{author},
        </if>
    </set>
    where id = #{id}
</update>
```

### trim

prefixOverrides属性会忽略通过管道分隔的文本序列（注意此例中的空格也是必要的）。它的作用是移除所有指定在prefixOverrides属性中的内容，并且插入prefix属性中指定的内容

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">

</trim>
```

```xml
<trim prefix="SET" suffixOverrides=",">

</trim>
```

### SQL片段

> 使用 SQL 标签抽取公共部分

```xml
<!--  SQL 片段  -->
<sql id="if-title-author">
    <if test="title != null">
        title = #{title},
    </if>
    <if test="author != null">
        author = #{author},
    </if>
</sql>
<!--   通过 <include refid="if-title-author"/> 进行使用    -->
```

### foreach

此时已将id 有原来的UUID 改为了 1 2 3 4

```Java
 List<Blog> queryBlogForeach(Map map);
```

```xml
<select id="queryBlogForeach" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <foreach collection="ids" item="id" open="(" close=")" separator="or">
            id = #{id}
        </foreach>
    </where>
</select>
```

Test

```Java
@Test
public void queryBlogForeachTest() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

    HashMap map = new HashMap<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ids.add(1);
	// ids.add(2);
    
    map.put("ids", ids);

    List<Blog> blogList = blogMapper.queryBlogForeach(map);
    for (Blog blog : blogList) {
        System.out.println(blog);
    }
    sqlSession.commit();
    sqlSession.close();

}
```

# ==13 缓存==

- 什么是缓存[Cache]?
  - 存在内存中的临时数据。
  - 将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上（关系型数据库数据文件）查
    询，从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题。
- 为什么使用缓存？
  - 减少和数据库的交互次数，减少系统开销，提高系统效率。
- 什么样的数据能使用缓存？
  - 经常查询并且不经常改变的数据。【可以使用缓存】

Mybatis缓存

- MyBatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存。缓存可以极大的提升查询效率。

- MyBatis系统中默认定义了两级缓存：一级缓存和二级缓存
  - 默认情况下，只有一级缓存开启。（SqlSession级别的缓存，也称为本地缓存）
  - 二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
  - 为了提高扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存

```java
public interface Cache {
    String getId();
    void putObject(Object var1, Object var2);
    Object getObject(Object var1);
    Object removeObject(Object var1);
    void clear();
    int getSize();
    default ReadWriteLock getReadWriteLock() {
        return null;
    }
}
```

![image-20220423200457285](https://s2.loli.net/2022/04/23/jQtgOyIAVXcxNWw.png)

> - LRU-最近最少使用：移除最长时间不被使用的对象。
>
> - FIFO-先进先出：按对象进入缓存的顺序来移除它们。
>
> - SOFT-软引用：基于垃圾回收器状态和软引用规则移除对象。
>
> - WEAK-弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。
>
>   **默认的清除策略是LRU。**

==【mybatis-09】==

## 一级缓存

实体类 User

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
    
}
```

UserMapper.java

```java
public interface UserMapper {

    // 根据 id 查询用户
    User queryUserById(@Param("id") int id);

    // 修改用户
    int updateUser(User user);
}
```

UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.lee.dao.UserMapper">

    <select id="queryUserById" resultType="User">
        select * from mybatis.user where id = #{id}
    </select>

    <update id="updateUser" parameterType="User">
        update mybatis.user set name = #{name}, pwd = #{pwd} where id = #{id}
    </update>
</mapper>
```

Test

```java
@Test
public void test() {

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    // 连续两次查询同一目标
    User user = userMapper.queryUserById(1);
    System.out.println(user);
    // userMapper.updateUser(new User(2, "newName", "newPwd"));
    User user2 = userMapper.queryUserById(1);
    System.out.println(user2);

    System.out.println(user == user2);

    sqlSession.close();
}
```

只进行了一次查询操作（缓存了）

![image-20220423204226889](https://s2.loli.net/2022/04/23/9VM3ntNFQkPoliS.png)

在两次查询之间增加一次修改数据操作，执行两次查询

```Java
userMapper.updateUser(new User(2, "newName", "newPwd"));
```

![image-20220423204423085](https://s2.loli.net/2022/04/23/b7ErMzUDwmHFynN.png)

在两次查询之间手动清理缓存,执行两次查询

```java
// 手动清理缓存
sqlSession.clearCache();
```

![image-20220423205257318](https://s2.loli.net/2022/04/23/yd7oHsmn2J1BrNc.png)



> **缓存失效的情况：**
>
> - 查询不同的东西
> - 增删改操作，可能会改变原来的数据，所以必定会刷新缓存
> - 查询不同的Mapper.xml
> - 手动清理缓存（**一级缓存**默认开启，可手动清理（如上））

## 二级缓存



- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存；
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中；
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了；会话关闭了，一级缓存中的数据被保存到二级缓存中；
  - 新的会话查询信息，就可以从二级缓存中获取内容；
  - 不同的mapper查出的数据会放在自己对应的缓存（map）中；



显示的开启全局缓存   `mybatis-config.xml -->   <configuration>`

```xml
<setting name="cacheEnabled" value="true"/>
```

`UserMapper.xml`

```xml
<!--  开启缓存-->
<cache/>
<!--  也可以设置一些内容
  	eviction  缓存清理规则 按对象进入缓存的顺序来移除
	flushInterval 刷新缓存时间 60 s
	size  最多可以存储结果对象或列表的512个引用
  	readOnly 
-->
<cache eviction="FIFO"
       flushInterval="60000"
       size="512"
       readOnly="true"/>
<!--  也可以针对某一语句单独设置 开启/关闭 缓存-->
<select id="queryUserById" resultType="User" useCache="true">
```



当只使用了 `<cache/>`开启缓存后

```Java
    @Test
    public void test() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        User user = userMapper.queryUserById(1);
        System.out.println(user);
        sqlSession.close();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.queryUserById(1);
        System.out.println(user2);
        System.out.println(user == user2);
        sqlSession2.close();
    }
```

报错：==Cause: java.io.NotSerializableException: com.lee.pojo.User==

解决：将实体类序列化

```Java
public class User implements Serializable {...}
```

修复后只执行了一次查询

![image-20220423214937763](https://s2.loli.net/2022/04/23/OjyX9SblD56J3Ev.png)

当缓存设置为 `<cache  readOnly="true"/>` 时，也可解决问题，但是不同的线程对缓存进行修改可能会产生冲突

### 小结

- 只要开启了二级缓存，在同一个Mapper下就有效
- 所有的数据都会先放在一级缓存中
- 只有当会话提交，或者关闭的时候，才会提交到二级缓冲中

## ehcache

(了解即可，后期会学习**redis**)

#### 导入依赖

```xml
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.1</version>
</dependency>
```

`UserMapper.xml`

```xml
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

resources  >  `ehcache.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    
        <!--
        diskStore:为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。参数解释如下:
        user.home-用户主目录
        user.dir-用户当前工作目录
        java.io.tmpdir-默认临时文件路径
        -->

    <diskStore path="./tmpdir/Tmp_EhCache"/>

    <defaultCache
        eternal="false"
        maxElementsInMemory="10000"
        overflowToDisk="false"
        diskPersistent="false"
        timeToIdleSeconds="1800"
        timeToLiveSeconds="259200"
        memoryStoreEvictionPolicy="LRU"/>

    <cache
        name="cloud_user"
        eternal="false"
        maxElementsInMemory="5000"
        overflowToDisk="false"
        diskPersistent="false"
        timeToIdleSeconds="1800"
        timeToLiveSeconds="1800"
        memoryStoreEvictionPolicy="LRU"/>
</ehcache>

<!-- 

defaultCache:默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。

name：缓存名称。
maxElementsInMemory：缓存最大数目
maxElementsonDisk：硬盘最大缓存个数。
eternal：对象是否永久有效，一但设置了，timeout将不起作用。
overflowToDisk：是否保存到磁盘，当系统当机时
timeToIdleseconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
timeToLiveseconds：设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0，也就是对象存活时间无穷大。
diskpersistent:是否缓存虚拟机重启期数据 whether the disk store persists between restarts of the Virtual Machine. The default value is false.
diskSpoolBuffersizeMB：这个参数设置Diskstore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
diskExpiryThreadIntervalseconds:磁盘失效线程运行时间间隔,默认是120秒。
memoryStoreEvictionPolicy:当达到maxElementsInMemory限制时,Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
clearonFlush：内存数量最大时是否清除。
memoryStoreEvictionPolicy：可选策略有：LRU（最近最少使用，默认策略）、FIFo（先进先出）、LFU（最少访问次数）。
FIFo,first in first out,这个是大家最熟的,先进先出。
LFU，Less Frequent1y Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。
如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
-->
```



