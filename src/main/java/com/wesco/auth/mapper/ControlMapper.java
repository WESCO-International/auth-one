package com.wesco.auth.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControlMapper {
    private UUID controlId;
    private String name;
    private boolean isEnable;
    private boolean isVisible;

}
