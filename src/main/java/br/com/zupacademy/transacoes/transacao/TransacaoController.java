package br.com.zupacademy.transacoes.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired TransacaoRepository repository;

    @GetMapping("/cartao/{id}")
    public ResponseEntity<?> listarUltimasCompras(
            @AuthenticationPrincipal Jwt jwt,
            @NotBlank @PathVariable("id") String id,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "efetivadaEm") Pageable pageable) {

        String email = jwt.getClaimAsString("email");

        Page<Transacao> transacoes = repository.findByCartaoIdAndCartaoEmail(id, email, pageable);

        if(transacoes.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TransacaoResponse.converter(transacoes));
    }
}
