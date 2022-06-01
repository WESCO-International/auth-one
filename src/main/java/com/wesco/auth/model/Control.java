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
@Table(name = "controls")
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "control_id")
    private UUID controlId;

    private String name;

    private boolean isEnable;

    private boolean isVisible;

    private UUID parentId;
    
}
