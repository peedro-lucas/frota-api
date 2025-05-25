package com.xwz.frota.frota_api.controller;

import com.xwz.frota.frota_api.model.Veiculo;
import com.xwz.frota.frota_api.repository.VeiculoRepository;
import com.xwz.frota.frota_api.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*") // ← Isso permite requisições de qualquer origem
public class VeiculoController {

    private final VeiculoService veiculoService;
    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable int id) {
        return veiculoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        System.out.println("Classe do objeto recebido: " + veiculo.getClass().getName());
        System.out.println("veiculo: "+ veiculo.getTipo() );

        Veiculo salvo = veiculoService.salvar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirVeiculo(@PathVariable Integer id) {
        veiculoService.excluirPorId(id);
        return ResponseEntity
                .ok("Veículo com ID " + id + " foi excluído com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(
            @PathVariable int id,
            @RequestBody Veiculo veiculoAtualizado) {
        System.out.println("veocuilo atualizado: "+ veiculoAtualizado);
        System.out.println("Tipo do veiculo: " + veiculoAtualizado.getTipo());
        Veiculo veiculo = veiculoService.atualizarVeiculo(id, veiculoAtualizado);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Veiculo>> buscarPorFiltros(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer ano) {

        List<Veiculo> veiculos = veiculoService.buscarPorFiltros(tipo, modelo, cor, ano);

        return ResponseEntity.ok(veiculos);
    }

}
