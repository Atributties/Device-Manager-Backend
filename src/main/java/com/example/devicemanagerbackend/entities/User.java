package com.example.devicemanagerbackend.entities;

import com.example.devicemanagerbackend.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private int id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Device> devices;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Simcard simcard;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Datacard> datacards;

    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> combinedAuthorities = new HashSet<>(this.authorities);
        combinedAuthorities.addAll(getAuthoritiesBasedOnUserType());
        return combinedAuthorities;
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesBasedOnUserType() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (this.userType) {
            case DEVICE_ADMIN:
                authorities.add(new SimpleGrantedAuthority("DEVICE_ADMIN"));
                break;
            case USER:
                authorities.add(new SimpleGrantedAuthority("USER"));
                break;
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

}

