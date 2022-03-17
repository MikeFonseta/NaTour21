package com.example.NaTour21.User.Repository;

import com.example.NaTour21.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM public.user WHERE email=?1", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT * FROM public.user WHERE facebook_id=?1", nativeQuery = true)
    User findByFacebookId(String id);

    @Query(value="SELECT * FROM public.user WHERE username=?1", nativeQuery = true)
    User findByUsername(String username);

}
