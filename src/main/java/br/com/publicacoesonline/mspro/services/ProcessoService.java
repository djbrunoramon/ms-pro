package br.com.publicacoesonline.mspro.services;

import br.com.publicacoesonline.mspro.models.Processo;
import br.com.publicacoesonline.mspro.models.Reu;
import br.com.publicacoesonline.mspro.repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository repository;

    @Autowired
    private ReuService reuService;

    public Processo salvar(Processo processo){
        return repository.save(processo);
    }

    public Page<Processo> buscarTodos(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Processo> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Optional<Processo> buscarPorNumero(String numero){
        return repository.findByNumero(numero);
    }

    public void deletar(Processo processo){
        repository.delete(processo);
    }

    public void adicionarReu(Optional<Processo> processo, Optional<Reu> reu) {
        Processo proc = buscarPorId(processo.get().getId()).get();
        Reu reuAdd = reuService.buscarPorId(reu.get().getId()).get();

        if(!proc.getReus().contains(reuAdd)){
            proc.getReus().add(reuAdd);
        }

        repository.save(proc);
    }
}
