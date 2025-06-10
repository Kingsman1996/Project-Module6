package quiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Authority> authorities = new HashSet<>();

    @Column(nullable = false)
    private boolean active = true;

    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;
}
