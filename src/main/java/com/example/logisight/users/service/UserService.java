package com.example.logisight.users.service;

import com.example.logisight.users.model.Role;
import com.example.logisight.users.model.User;
import com.example.logisight.users.repo.RoleRepository;
import com.example.logisight.users.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.management.relation.RoleNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.logisight.users.model.RoleName.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Пользователь %s не найден".formatted(username)
                ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    public void createNewUser(User user) throws RoleNotFoundException {
        String role = ROLE_USER.toString();
        Set<Role> roles = Set.of(roleRepository.findByName(role).orElseThrow(
                () -> new RoleNotFoundException("Role %s not found".formatted(role))
        ));
        user.setRoles(roles);
        userRepository.save(user);
    }
    public String user(Model model, Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauth2User = token.getPrincipal();
            Map<String, Object> attributes = oauth2User.getAttributes();

            model.addAttribute("username", attributes.get("username"));
            model.addAttribute("email", attributes.get("email"));
            model.addAttribute("sub", attributes.get("sub"));
        }

        return "user";
    }

    private List<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
