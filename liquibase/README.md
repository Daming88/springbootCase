# springboot+多数据源(dynamic-datasource-spring-boot-starter)+liquibase(liquibase-core)
实现多数据源配置，并且使用liquibase进行版本管理
# 1、在pom.xml中引入主要的相关依赖、
# 2、在resources目录下创建liquibase文件夹，在liquibase文件夹下创建dbChangeLog.xml文件，
用于存放数据库版本管理的脚本（这里的文件需要在pom.xml中配置扫描，否则打包的时候会丢失）
# 3、在application.yml中配置数据源信息，并且给每个数据源配置相关的数据库版本管理信息（liquibase）
    这里需要注意，因为多数据源的配置，需要手动配置，所以需要手动配置liquibase的配置信息，否则无法使用liquibase进行版本管理
# 4、创建DataSourceConfig.java配置，分别给每个数据源配置对应的SpringLiquibase配置，并且注入到spring容器中，
这样项目启动时就会自动的注入，并且执行数据库版本管理的脚本
# 5、通过接口触发的方式，实现数据库版本回滚接口，这里的例子是所有数据源统一回滚（TestController）
