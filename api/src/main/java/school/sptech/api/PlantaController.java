package school.sptech.api;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/plantas")
public class PlantaController {

    private final JdbcTemplate jdbcTemplate;

    public PlantaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GET /plantas — lista todas as plantas
    @GetMapping
    public ResponseEntity<List<Planta>> listar() {

        String sql = "SELECT * FROM planta";

        List<Planta> plantas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Planta.class));

        if (plantas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(plantas);
    }

    // GET /plantas/{id} — busca uma planta pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Planta> buscarPorId(@PathVariable Integer id) {

        String sql = "SELECT * FROM planta WHERE id = ?";

        List<Planta> plantas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Planta.class), id);

        if (plantas.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(plantas.get(0));
    }

    // POST /plantas — cadastra uma nova planta
    @PostMapping
    public ResponseEntity<Planta> cadastrar(@RequestBody Planta novaPlanta) {

        if (novaPlanta.getNome() == null || novaPlanta.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (novaPlanta.getEspecie() == null || novaPlanta.getEspecie().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (novaPlanta.getTipo() == null || novaPlanta.getTipo().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (novaPlanta.getFrequenciaRega() == null || novaPlanta.getFrequenciaRega().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (novaPlanta.getNivelLuz() == null || novaPlanta.getNivelLuz().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        String sql = "INSERT INTO planta (nome, especie, tipo, frequenciaRega, nivelLuz, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, novaPlanta.getNome());
            ps.setString(2, novaPlanta.getEspecie());
            ps.setString(3, novaPlanta.getTipo());
            ps.setString(4, novaPlanta.getFrequenciaRega());
            ps.setString(5, novaPlanta.getNivelLuz());
            ps.setString(6, novaPlanta.getDescricao());

            return ps;
        }, keyHolder);

        novaPlanta.setId(keyHolder.getKey().intValue());

        return ResponseEntity.status(201).body(novaPlanta);
    }

    // DELETE /plantas/{id} — deleta uma planta pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {

        String sqlBusca = "SELECT * FROM planta WHERE id = ?";
        List<Planta> plantas = jdbcTemplate.query(sqlBusca, new BeanPropertyRowMapper<>(Planta.class), id);

        if (plantas.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        String sqlDelete = "DELETE FROM planta WHERE id = ?";
        jdbcTemplate.update(sqlDelete, id);

        return ResponseEntity.status(204).build();
    }

}
