package com.company.jmixpmsecurity.security.ldap;

import com.company.jmixpmsecurity.entity.User;
import io.jmix.ldap.userdetails.AbstractLdapUserDetailsSynchronizationStrategy;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.stereotype.Component;

@Component
public class PmUserSyncStrategy extends AbstractLdapUserDetailsSynchronizationStrategy<User> {

    @Override
    protected Class<User> getUserClass() {
        return User.class;
    }

    @Override
    protected void mapUserDetailsAttributes(User jmixJpaUser, DirContextOperations ctx) {
        jmixJpaUser.setFirstName(ctx.getStringAttribute("givenName"));
        jmixJpaUser.setLastName(ctx.getStringAttribute("sn"));
        jmixJpaUser.setEmail(ctx.getStringAttribute("mail"));

        //set anything else...
    }
}
