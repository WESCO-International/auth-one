package com.wesco.auth.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMapper {
    UUID userId;
    UUID roleId;
    UUID capabilityId;
    UUID controlId;

    public UserMapper(UUID userId, UUID roleId, UUID capabilityId) {
        this.userId = userId;
        this.roleId = roleId;
        this.capabilityId = capabilityId;
    }
}
