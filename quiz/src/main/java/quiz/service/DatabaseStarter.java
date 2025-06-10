package quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import quiz.entity.User;
//import quiz.entity.Authority;
//import quiz.repository.AuthorityRepository;
import quiz.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DatabaseStarter implements CommandLineRunner {
//    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
//        for (Authority.Role role : Authority.Role.values()) {
//            if (!authorityRepository.existsByRole(role)) {
//                Authority authority = new Authority();
//                authority.setRole(role);
//                authorityRepository.save(authority);
//            }
//        }
        if (!userRepository.existsByUsername(adminUsername)) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(adminPassword);

//            Set<Authority> authorities = new HashSet<>();
//            authorities.add(authorityRepository.findByRole(Authority.Role.ADMIN));
//            admin.setAuthorities(authorities);
            userRepository.save(admin);
        }
    }
}
