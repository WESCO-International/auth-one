package com.wesco.auth.mapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccess {

    public boolean manageGroupMembership;
    public boolean view;
    public boolean mapRoles;
    public boolean impersonate;
    public boolean manage;

}
