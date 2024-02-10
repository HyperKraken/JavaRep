package com.company.demo.repository;

import com.company.demo.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUserIdAndDeletedAtIsNull(Integer userId);

    List<Users> findAllByDeletedAtIsNull();
    Boolean existsByEmailAndDeletedAtIsNull(String email);

}
