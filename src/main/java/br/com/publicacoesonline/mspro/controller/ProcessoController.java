package br.com.publicacoesonline.mspro.controller;

import br.com.publicacoesonline.mspro.models.Processo;
import br.com.publicacoesonline.mspro.models.Reu;
import br.com.publicacoesonline.mspro.services.ProcessoService;
import br.com.publicacoesonline.mspro.services.ReuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ReuService reuService;

    @PostMapping("/salvar")
    public ResponseEntity<Processo> salvar(@RequestBody @Valid Processo processo){
        processo.setCriadoEm(LocalDateTime.now());
        processoService.salvar(processo);
        return new ResponseEntity<>(processo, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Processo>> getProcessos(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(processoService.buscarTodos(pageable), HttpStatus.OK);
    }

    @GetMapping({"/numero/{numero}"})
    public ResponseEntity<?> getProcessoPorNumero(@PathVariable("numero") String numero){
        Optional<Processo> processo = processoService.buscarPorNumero(numero);

        if(processo.isPresent()){
            return new ResponseEntity<>(processo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/numero/{numero}")
    public ResponseEntity<?> deletarProcessoPorNumero(@PathVariable("numero") String numero){
        Optional<Processo> processo = processoService.buscarPorNumero(numero);
        if(processo.isPresent()){
            processoService.deletar(processo.get());
            return ResponseEntity.ok().build();
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/adicionar/reu")
    public ResponseEntity<?> addReuNoProcesso(@RequestParam("idReu") Long idReu,
                                              @RequestParam("idProcesso") Long idProcesso){
        Optional<Reu> reu = reuService.buscarPorId(idReu);
        Optional<Processo> processo = processoService.buscarPorId(idProcesso);
        if(!reu.isPresent() || !processo.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        processoService.adicionarReu(processo, reu);
        return ResponseEntity.ok().build();
    }
}
