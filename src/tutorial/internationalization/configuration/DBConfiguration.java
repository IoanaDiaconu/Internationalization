package tutorial.internationalization.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by Ioana on 2/28/2016.
 */
@Configuration
public class DBConfiguration {
    @Autowired
    DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setName("testdb")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("file:C:\\Users\\Ioana\\IdeaProjects\\Internationalization\\src\\main\\resources\\db\\sql\\create-db.sql")
                .addScript("file:C:\\Users\\Ioana\\IdeaProjects\\Internationalization\\src\\main\\resources\\db\\sql\\insert-data.sql").build();
        return db;
    }



}

