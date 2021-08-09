package br.com.publicacoesonline.mspro.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_PROCESSO")
@Data
public class Processo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String numero;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime criadoEm;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "TBL_PROCESSO_REUS",
            joinColumns = {@JoinColumn(name = "processo_id")},
            inverseJoinColumns = {@JoinColumn(name = "reu_id")}
    )
    private List<Reu> reus;
}
