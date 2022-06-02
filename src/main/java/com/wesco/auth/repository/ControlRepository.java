package com.wesco.auth.repository;

import com.wesco.auth.mapper.ControlMapper;
import com.wesco.auth.mapper.UserMapper;
import com.wesco.auth.model.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ControlRepository extends JpaRepository<Control, UUID> {

    @Query("Select new com.wesco.auth.mapper.ControlMapper(cntrl.controlId, cntrl.name, cntrl.isEnable, cntrl.isVisible) FROM User user " +
            "INNER JOIN UserRole ur ON user.userId = ur.userId " +
            "INNER JOIN RoleCapability rc ON rc.roleId = ur.roleId " +
            "INNER JOIN CapabilityControl cc ON cc.capabilityId = rc.capabilityId " +
            "INNER JOIN Control cntrl ON cntrl.controlId = cc.controlId WHERE user.email = :emailId")
    List<ControlMapper> findControlsByEmail(@Param("emailId") String emailId);

}
