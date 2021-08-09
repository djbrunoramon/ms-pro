package br.com.publicacoesonline.mspro.controller;

import br.com.publicacoesonline.mspro.models.Reu;
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

@RestController
@RequestMapping("/api/v1/reus")
public class ReuController {

    @Autowired
    private ReuService reuService;

    @PostMapping("/salvar")
    public ResponseEntity<Reu> salvar(@RequestBody @Valid Reu reu){
        reuService.salvar(reu);
        return new ResponseEntity<>(reu, HttpStatus.CREATED);
    }

    @GetMapping( "/")
    public ResponseEntity<Page<Reu>> getReus(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(reuService.buscarTodos(pageable), HttpStatus.OK);
    }

}
