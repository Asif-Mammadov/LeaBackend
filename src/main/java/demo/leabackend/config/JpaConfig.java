//import org.hibernate.cfg.Environment;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//class DatasourceConfig {
//    @Value("${SPRING_DATASOURCE_URL}:None")
//    String datasource;
//    @Value("${SPRING_DATASOURCE_USERNAME:None")
//    String username;
//    @Value("${SPRING_DATASOURCE_PASSWORD:None")
//    String password;
//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url(datasource)
//                .username(username)
//                .password(password)
//                .build();
//    }
//}
