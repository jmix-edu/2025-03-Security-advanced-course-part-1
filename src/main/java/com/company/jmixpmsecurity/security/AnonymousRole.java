package com.company.jmixpmsecurity.security;

import com.company.jmixpmsecurity.entity.Project;
import com.company.jmixpmsecurity.entity.Task;
import com.company.jmixpmsecurity.entity.dashboard.DashboardProject;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "Anonymous", code = AnonymousRole.CODE, scope = "UI")
public interface AnonymousRole {
    String CODE = "anonymous";

    @EntityAttributePolicy(entityClass = Task.class, attributes = {"id", "name", "assignee", "startDate", "project", "closed"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.READ)
    void task();

    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.READ)
    void project();

    @EntityAttributePolicy(entityClass = DashboardProject.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = DashboardProject.class, actions = EntityPolicyAction.READ)
    void dashboardProject();
}