package br.com.publicacoesonline.mspro.services;

import br.com.publicacoesonline.mspro.models.Reu;
import br.com.publicacoesonline.mspro.repositories.ReuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReuService {

    @Autowired
    private ReuRepository repository;

    public Reu salvar(Reu reu){
        return repository.save(reu);
    }

    public Page<Reu> buscarTodos(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Reu> buscarPorId(Long id){
        return repository.findById(id);
    }
}
