package net.ljw.jpa2.service;

import lombok.RequiredArgsConstructor;
import net.ljw.jpa2.domain.Role;
import net.ljw.jpa2.domain.User;
import net.ljw.jpa2.repository.RoleRepository;
import net.ljw.jpa2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // 보통 서비스에서는 @Transactional 을 붙여서 하나의 트랜잭션으로 처리함
    // spring boot는 트랜잭션을 처리해주는 트랜잭션 관리자를 가지고 있다.
    @Transactional
    public User addUser(String username, String email, String password) {
        //트랜잭션이 시작된다.
        Optional<User> Optionaluser = userRepository.findByEmail(email);
        // option에서 isPresent() 데이터가 있다면
        if(Optionaluser.isPresent()){
            throw new RuntimeException("이미 가입된 이메일 입니다.");
        }

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow();
        User user = new User();
        user.setPassword(password);
        user.setRoles(Set.of(role));
        user.setEmail(email);
        user.setName(username);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<String> getRoles(int userId) {
        Set<Role> userRoles = userRepository.findById(userId).orElseThrow().getRoles();
        List<String> roles = new ArrayList<>();
        for(Role role : userRoles){
            roles.add(role.getName());
        }
        return roles;
    }
}
