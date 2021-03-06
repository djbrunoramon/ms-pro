package br.com.publicacoesonline.mspro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TBL_REU")
@Data
public class Reu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "reus")
    private List<Processo> processos;
}
