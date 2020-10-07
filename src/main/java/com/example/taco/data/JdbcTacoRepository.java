package com.example.taco.data;

import com.example.taco.domain.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbc;

    public JdbcTacoRepository(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Taco save(Taco taco) {
        taco.setCreatedAt(new Date());
        taco.setId(generateTacoId());
        saveTacoInfo(taco);
        return taco;
    }

    private void saveTacoInfo(Taco taco) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = new ObjectMapper().convertValue(taco, Map.class);
        map.put("_type", "taco");
        jdbc.update("insert into springApp (key, value) values (?, ?)", taco.getId(), map);
    }

    private String generateTacoId() {
        return UUID.randomUUID().toString();
    }
}
