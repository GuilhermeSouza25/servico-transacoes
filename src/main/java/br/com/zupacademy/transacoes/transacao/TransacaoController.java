package br.com.zupacademy.transacoes.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired TransacaoRepository repository;

    @GetMapping("/cartao/{id}")
    public ResponseEntity<?> listarUltimasCompras(
            @NotBlank @PathVariable("id") String id,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "efetivadaEm") Pageable pageable) {

        Page<Transacao> transacoes = repository.findByCartaoId(id, pageable);

        if(transacoes.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TransacaoResponse.converter(transacoes));
    }
}
