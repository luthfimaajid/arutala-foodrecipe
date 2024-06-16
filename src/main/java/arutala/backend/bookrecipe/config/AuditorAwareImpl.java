package arutala.backend.bookrecipe.config;

import arutala.backend.bookrecipe.model.MyUserDetails;
import arutala.backend.bookrecipe.service.UserDetailsServiceImpl;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();
            return Optional.of(userDetails.getUsername());
        } catch (Exception e) {
            return Optional.of("admin");
        }
    }
}
