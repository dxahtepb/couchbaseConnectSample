package com.example.taco.data;

import com.example.taco.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Ingredient> findAll() {
        return jdbc.query("select id, name, type from springApp where _type='ingredient'",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject(
                "select id, name, type from springApp where _type='ingredient' and id=?",
                this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into springApp (key, value) " +
                        "values (uuid(), {'_type': 'ingredient', 'id': ?, 'name': ?, 'type': ?})",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    @SuppressWarnings("unchecked")
    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> result = (Map<String, Object>) rs.getObject(1);
        return new Ingredient(
                (String) result.get("id"),
                (String) result.get("name"),
                Ingredient.Type.valueOf((String) result.get("type")));
    }
}
