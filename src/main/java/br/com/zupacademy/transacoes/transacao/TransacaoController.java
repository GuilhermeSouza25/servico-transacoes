package br.com.zupacademy.transacoes.transacao;

import br.com.zupacademy.transacoes.TokenService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired TransacaoRepository repository;
    @Autowired TokenService tokenService;

    @GetMapping("/cartao/{id}")
    public ResponseEntity<?> listarUltimasCompras(
            @NotBlank @PathVariable("id") String id,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "efetivadaEm") Pageable pageable) {

        String email = tokenService.getEmail();

        Page<Transacao> transacoes = repository.findByCartaoIdAndCartaoEmail(id, email, pageable);

        if(transacoes.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TransacaoResponse.converter(transacoes));
    }
}
