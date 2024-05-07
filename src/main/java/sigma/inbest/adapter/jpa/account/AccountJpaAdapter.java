package sigma.inbest.adapter.jpa.account;

import sigma.inbest.application.account.outport.AccountDBPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountJpaAdapter implements AccountDBPort {

    private final AccountJpaRepository accountJpaRepository;



}
