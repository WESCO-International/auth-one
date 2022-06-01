package com.wesco.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_capabilities")
public class RoleCapability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_capability_id")
    private UUID roleCapabilityId;

    private UUID roleId;

    private UUID capabilityId;
    
}
