package com.wesco.auth.model;

import com.sun.security.auth.UnixNumericUserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "capability_groups")
@Builder
public class CapabilityGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "capability_group_id")
    private UUID capabilityGroupId;

    private String name;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "capability_group_id", foreignKey = @ForeignKey(name = "fk_capability_capability_gp"))
    List<Capability> capabilities;

}
