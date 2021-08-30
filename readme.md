### 本项目是验证spring-session的



项目部署的配置文件在deploy目录中



1. 先将deploy目录中的user.sql导入到user数据库中，地址指向localhost，root/root

2. 启动redis，指向127.0.0.1

3. 复制两个tomcat，将配置文件分别放入两个tomcat中，一个端口是8080，一个是8081

4. 将war包分别放入两个tomcat中然后分别启动

5. 将nginx的配置文件复制到nginx项目中，然后启动

6. nginx中配置了负载均衡，指向了本地的8080和8081端口

7. - 访问http://localhost/session/getUser，会提示“非法访问，请重新登录”

     - 然后访问 https://localhost/session/login?username=zhangsan&password=123456，会提示“登录成功”

   - 此时再访问getUser即可获取到信息：{"context":"xxxxxx\apache-tomcat-9.0.52-8080\\webapps\\session\\:80","sessionId":"6fb4b051-a04c-4b9f-be27-76cf6dbd818e","user":{"id":"1111","username":"zhangsan","password":"123456"}}

   -  此时再访问getUser即可获取到信息：{"context":"xxxxxx\apache-tomcat-9.0.52-8081\\webapps\\session\\:80","sessionId":"6fb4b051-a04c-4b9f-be27-76cf6dbd818e","user":{"id":"1111","username":"zhangsan","password":"123456"}}

 因为配置了负载均衡，所以打印出来的绝对路径会变化，但是获取的用户信息依然相同

 退出时候访问http://localhost/session/logout，会显示“退出成功”