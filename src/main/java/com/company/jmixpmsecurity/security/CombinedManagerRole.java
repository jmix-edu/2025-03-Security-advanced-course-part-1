package com.company.jmixpmsecurity.security;

import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;

@ResourceRole(name = "CombinedManager", code = CombinedManagerRole.CODE)
public interface CombinedManagerRole extends ProjectManagementRole, UiMinimalRole {
    String CODE = "combined-manager";

    @SpecificPolicy(resources = "jmix.pm.project.archive")
    void specific();
}