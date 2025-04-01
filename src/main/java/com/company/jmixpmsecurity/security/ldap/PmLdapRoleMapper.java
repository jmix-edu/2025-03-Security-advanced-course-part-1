package com.company.jmixpmsecurity.security.ldap;

import com.company.jmixpmsecurity.security.CombinedManagerRole;
import com.company.jmixpmsecurity.security.DeveloperRole;
import com.company.jmixpmsecurity.security.FullAccessRole;
import com.google.common.collect.ImmutableMap;
import io.jmix.ldap.userdetails.LdapAuthorityToJmixRoleCodesMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Component
public class PmLdapRoleMapper implements LdapAuthorityToJmixRoleCodesMapper {

    private final static Map<String, String> roleCodes = ImmutableMap.of(
            "admin", FullAccessRole.CODE,
            "developers", DeveloperRole.CODE,
            "managers", CombinedManagerRole.CODE
    );

    @Override
    public Collection<String> mapAuthorityToJmixRoleCodes(String authority) {
        return Collections.singleton(roleCodes.getOrDefault(authority, authority));
    }
}
