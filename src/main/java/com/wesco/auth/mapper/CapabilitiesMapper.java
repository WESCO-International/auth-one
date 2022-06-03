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
public class CapabilitiesMapper {
    private UUID capabilityId;
    private String name;
    private String capabilityGroup;
    private String application;

}
