package com.company.jmixpmsecurity.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "CombinedManager", code = CombinedManagerRole.CODE)
public interface CombinedManagerRole extends ProjectManagementRole, UiMinimalRole {
    String CODE = "combined-manager";
}