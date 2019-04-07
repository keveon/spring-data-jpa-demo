# Spring Data Jpa 常用功能演示

配套说明请查看：[万字长文带你领略 Spring 全家桶带来的极速开发体验](https://keveon.me/spring/spring-data-jpa-1.html)

## 项目简介

- 本项目采用 [SpringBoot](http://projects.spring.io/spring-boot/) 当前最新的 `2.1.4.RELEASE` 做基础架构支撑, 参考本项目建议有一定的 [SpringBoot](http://projects.spring.io/spring-boot/) 基础及经验.

- 教程主要面向中文用户, 如果你英文良好, 建议直接阅读官网帮助文档.

  - Accessing Data with JPA：[https://spring.io/guides/gs/accessing-data-jpa/](https://spring.io/guides/gs/accessing-data-jpa/).
  
  - Accessing JPA Data with REST：[https://spring.io/guides/gs/accessing-data-rest/](https://spring.io/guides/gs/accessing-data-rest/).
  
- 项目中Bean全部采用 [lombok](https://projectlombok.org/) 进行精简, 需要配合IDE插件使用, 在此项目不进行讨论, 如需了解更多, 参考以下链接：

  - 官方文档地址：[https://projectlombok.org/features/index.html](https://projectlombok.org/features/index.html).
  
  - 官方下载地址：[https://projectlombok.org/download.html](https://projectlombok.org/download.html).
  
  - lombok-intellij-plugin：[https://github.com/mplushnikov/lombok-intellij-plugin](https://github.com/mplushnikov/lombok-intellij-plugin).
  
- 本项目所有配置文件均采用 `yaml` 方式, 如需了解更多, 请移步官网: <http://www.yaml.org/>.

- 教程目前在不定时进行调整和补充, 需要关注更新的请Watch、Star、Fork.

- 同时邀请您一起参与完善该教程, 帮助更多的人, 欢迎反馈错误和意见!

- 如果你想参与完善该教程, 请移步到 Github 上进行 Fork：[https://github.com/keveoneveon/spring-data-jpa-demo](https://github.com/keveon/spring-data-jpa-demo)

## 框架介绍

> **Spring Data JPA** 是 [Spring Data](http://projects.spring.io/spring-data) 系列的一部分, 使得轻松实现基于JPA的存储库变得更容易. 该模块处理对基于JPA的数据访问层的增强支持. 它使得更容易构建使用数据访问技术的Spring-powered应用程序.
Spring Data JPA 的目标是通过让一些必须的工作变得更简单, 来显著提高数据访问层的实现. 作为一个开发者, 你写你的仓库界面, 包括自定义查询方法, 而 Spring 给你提供自动实现. 

### 特点

- 对建立基于Spring 和 JPA 的库的支持.

- 支持 [Querydsl](http://www.querydsl.com/) predicates和安全类型的 JPA 查询.

- Transparent auditing of domain class.

- 分页支持, 动态查询执行, 可以集成自定义数据访问代码.

- 在 `@Query` 注明查询引导时验证.

- 支持基于XML的实体映射.

- 引入 `@EnableJpaRepositories` , 实现 `JavaConfig based` 仓库配置.

## 正式开始

#### 准备工作

>在开始提到, 本项目采用SpringBoot做基础架构支撑, 项目中使用 `spring-data-jpa` 的推荐方法是使用依赖关系管理系统,也就是我们平时所说的包管理器、构建工具.下面两种工具对应的代码段可以复制并粘贴到您的项目中.
如需帮助, 请参阅我们使用 [Maven](https://spring.io/guides/gs/maven/) 和 [Gradle](https://spring.io/guides/gs/gradle/) 构建的入门指南.

###### Maven
``` Maven
<dependencies>
    ...
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ...
</dependencies>
```

###### Gradle
``` Gradle
dependencies {
    ...
    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    ...
}
```

#### Jpa提供的接口

> 为了使用方便, spring-data-jpa已经提供了多个接口, 只需要继承其中一个, spring-data-jpa将会自动实现并提供相应的实现. 继承/实现关系如下：

     QueryDslJpaRepository
     ┣━ QueryDslPredicateExecutor
     ┗━ SimpleJpaRepository
         ┣━ JpaSpecificationExecutor
         ┗━ JpaRepository
             ┣━ QueryByExampleExecutor
             ┗━ PagingAndSortingRepository
                 ┗━ CrudRepository
                     ┗━Repository

> 这些 `Repository` 都是 `spring-data-commons` 提供给我们的核心接口, `spring-data-commons` 是 `Spring Data` 的核心包. 这个接口中为我们提供了数据的分页方法, 以及排序方法. `spring-data` 让我们省了很多心了, 一切都按照这个规范进行构造, 就连业务系统中常用到的一些操作都为我们考虑到了, 而我们只需更用心的去关注业务逻辑层. `spring-data` 将 `repository` 的颗粒度划得很细. 

- `Repository`：最基本的接口, 不提供任何功能, 仅作为 `SpringData` 的一个标记, 并提供实现.这个接口只是一个空的接口, 目的是为了统一所有 `Repository` 的类型, 其接口类型使用了泛型, 泛型参数中T代表实体类型, ID则是实体中id的类型. 任何直接或间接继承此接口的类或接口, 均会被Spring扫描到.

- `CrudRepository`：提供最基本的增删改查方法.此接口中的方法大多是我们在访问数据库中常用的一些方法, 如果我们要写自己的DAO的时候, 只需定义个接口来集成它便可使用了.

- `PagingAndSortingRepository`：提供基本的分页及排序功能, 并同时提供CrudRepository接口的功能.

- `QueryByExampleExecutor`：提供查询功能, 后期会具体讲解.

- `JpaRepository`：这个接口继承自 `PagingAndSortingRepository` , 里面的方法都是一些简单的操作, 并未涉及到复杂的逻辑. 当你在处理一些简单的数据逻辑时, 便可继承此接口. 

- `JpaSpecificationExecutor`：提供 `criteria` 查询, 排序、支持分页, 此接口没有父类(不包括 `Object` ), 即没有上级接口. 

- `SimpleJpaRepository`：实现 `JpaRepository` 和 `JpaSpecificationExecutor` 接口, 使用 [hibernate](http://hibernate.org/) 的 `EntityManager`做持久化相关处理. 您也可以更换为其他 `JPA` 实现, 如：`EclipseLink`、`TopLink` 等.

- `QueryDslPredicateExecutor`：提供 [Querydsl](http://www.querydsl.com/) 查询的接口.

- `QueryDslJpaRepository`：继承 `SimpleJpaRepository` 类, 实现 `QueryDslPredicateExecutor` 接口.

## 附录A：Github 常用按钮说明

- `Watch`：关注该项目, 作者有更新的时候, 会在你的 Github 主页有通知消息.

- `Star`：收藏该项目, 在你的头像上有一个 "Your stars" 链接, 可以看到你的收藏列表, 以方便下次进来.

- `Fork`：复制一份项目到自己的 Github 空间上, 你可以自己开发自己的这个地址项目, 然后 Pull Request 给项目原主人.

## 联系方式

- Github: <https://github.com/keveon>
- Email：<keveon@keveon.com>