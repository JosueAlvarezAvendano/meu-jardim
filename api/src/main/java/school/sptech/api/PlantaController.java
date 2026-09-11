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

        if (TipoPlanta.fromDescricao(novaPlanta.getTipo()) == null) {
            return ResponseEntity.status(400).build();
        }
        if (FrequenciaRega.fromDescricao(novaPlanta.getFrequenciaRega()) == null) {
            return ResponseEntity.status(400).build();
        }
        if (NivelLuz.fromDescricao(novaPlanta.getNivelLuz()) == null) {
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

        String sqlCount = "SELECT COUNT(*) FROM planta WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCount, Integer.class, id);

        if (count == 0) {
            return ResponseEntity.status(404).build();
        }

        String sqlDelete = "DELETE FROM planta WHERE id = ?";
        jdbcTemplate.update(sqlDelete, id);

        return ResponseEntity.status(204).build();
    }

    // GET /plantas/buscar?nome=nome — busca plantas pelo nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Planta>> buscarPorNome(@RequestParam String nome) {

        String sql = "SELECT * FROM planta WHERE nome LIKE ?";

        List<Planta> plantas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Planta.class), "%" + nome + "%");

        if (plantas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(plantas);
    }

    // PUT /plantas/{id} — atualiza uma planta pelo id
    @PutMapping("/{id}")
    public ResponseEntity<Planta> atualizar(@PathVariable Integer id, @RequestBody Planta plantaAtualizada) {

        String sqlCount = "SELECT COUNT(*) FROM planta WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCount, Integer.class, id);

        if (count == 0) {
            return ResponseEntity.status(404).build();
        }

        if (plantaAtualizada.getNome() == null || plantaAtualizada.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (plantaAtualizada.getEspecie() == null || plantaAtualizada.getEspecie().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (plantaAtualizada.getTipo() == null || plantaAtualizada.getTipo().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (plantaAtualizada.getFrequenciaRega() == null || plantaAtualizada.getFrequenciaRega().isBlank()) {
            return ResponseEntity.status(400).build();
        }
        if (plantaAtualizada.getNivelLuz() == null || plantaAtualizada.getNivelLuz().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        if (TipoPlanta.fromDescricao(plantaAtualizada.getTipo()) == null) {
            return ResponseEntity.status(400).build();
        }
        if (FrequenciaRega.fromDescricao(plantaAtualizada.getFrequenciaRega()) == null) {
            return ResponseEntity.status(400).build();
        }
        if (NivelLuz.fromDescricao(plantaAtualizada.getNivelLuz()) == null) {
            return ResponseEntity.status(400).build();
        }

        String sql = "UPDATE planta SET nome = ?, especie = ?, tipo = ?, frequenciaRega = ?, nivelLuz = ?, descricao = ? WHERE id = ?";

        jdbcTemplate.update(sql, plantaAtualizada.getNome(), plantaAtualizada.getEspecie(), plantaAtualizada.getTipo(), plantaAtualizada.getFrequenciaRega(), plantaAtualizada.getNivelLuz(), plantaAtualizada.getDescricao(), id);

        plantaAtualizada.setId(id);

        return ResponseEntity.status(200).body(plantaAtualizada);
    }

}
