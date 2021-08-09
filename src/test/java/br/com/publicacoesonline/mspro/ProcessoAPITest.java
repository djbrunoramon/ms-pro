package br.com.publicacoesonline.mspro;

import br.com.publicacoesonline.mspro.models.Processo;
import br.com.publicacoesonline.mspro.models.Reu;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MsProApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessoAPITest {

    private String urlApi = "/api/v1/processos";

    //Adicionar um numero de processo
    private String numeroProcesso = "6493257-19.4289.fja.5973";

    @Autowired
    private TestRestTemplate rest;

    //Apenas para tornar mais rapida a consulta na API
    private ResponseEntity<Processo> getProcesso(String url) {
        return rest.getForEntity(url, Processo.class);
    }

    //01 Salvar Processo POST
    @Test
    public void testeParaSalvarProcesso() {
        Processo processo = new Processo();
        processo.setNumero(numeroProcesso);

        //inserir processo
        ResponseEntity response = rest.postForEntity(urlApi + "/salvar", processo, null);
        System.out.println(response);

        //verificar resposta
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    //02 Retornar erro caso tenha um processo cadastrado.
    @Test
    public void testeParaVerificarSeJaTemProcessoCadastrado() {
        Processo processo = new Processo();
        processo.setNumero(numeroProcesso);

        //inserir processo
        ResponseEntity response = rest.postForEntity(urlApi + "/salvar", processo, null);
        System.out.println(response);

        //verificar resposta
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    //03 Consulta os processos
    @Test
    public void testeParaConsultarOsProcessos() {

        //realiza a consulta na API e retorna uma string
       ResponseEntity<String> response = rest.getForEntity(urlApi, String.class);
       System.out.println(response);

       //compara se dentro da string a lista nao esta vazia
       Assertions.assertNotEquals("<200,{\"content\":[]",response.toString().substring(0,18));
    }

    //04 Deleta e verifica se deletou
    @Test
    public void testeParaDeletar() {
        //deletar processo
        rest.delete(urlApi + "/numero/" + numeroProcesso);

        //verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getProcesso(urlApi + "/numero/" + numeroProcesso).getStatusCode());
    }

    //Adiciona um existente reu no processo
    @Test
    public void testeParaAdicionarUmReuNoProcesso(){

        //Adiciona um reu com string alertorio apenas para teste
        Reu reu = new Reu();
        reu.setNome("Mario N-"+ new Random().nextInt(100));
        ResponseEntity<Reu> responseReu = rest.postForEntity("/api/v1/reus/salvar", reu, null);
        System.out.println(responseReu);

        //Pesquisa ou insere o novo processo
        Processo processo = new Processo();
        processo.setNumero("1664276-91.6160.ShT.3605");

        if(getProcesso(urlApi + "/numero/" + processo.getNumero()).getStatusCode() == HttpStatus.OK){
            ResponseEntity<Processo> responsePro = getProcesso(urlApi + "/numero/" + processo.getNumero());
            processo = responsePro.getBody();
            System.out.println(responsePro);
        }else{
            ResponseEntity<Processo> responsePro = rest.postForEntity(urlApi + "/salvar", processo, null);
            processo = responsePro.getBody();
            System.out.println(responsePro);
        }
//        System.out.println(processo.getNumero());

        //inserir processo
       // ResponseEntity response = rest.postForEntity(urlApi + "/adicionar/reu?idProcesso=58&idReu=51", processo, null);
        //System.out.println(response);

        //verificar resposta
        //assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
