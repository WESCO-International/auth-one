package com.wesco.auth.repository;

import com.wesco.auth.mapper.UserMapper;
import com.wesco.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("Select new com.wesco.auth.mapper.UserMapper(user.userId, ur.roleId, rc.capabilityId) FROM User user INNER JOIN UserRole ur ON user.userId = ur.userId " +
            "INNER JOIN RoleCapability rc ON rc.roleId = ur.roleId WHERE user.email = :emailId")
    List<UserMapper> findUserRoleAndCapability(@Param("emailId") String emailId);

}
