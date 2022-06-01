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
@Table(name = "capability_controls")
public class CapabilityControl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "capability_control_id")
    private UUID capabilityControlId;

    private UUID capabilityId;

    private UUID controlId;
}
