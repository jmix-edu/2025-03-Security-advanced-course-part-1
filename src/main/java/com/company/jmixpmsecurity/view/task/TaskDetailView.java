package com.company.jmixpmsecurity.view.task;

import com.company.jmixpmsecurity.app.TaskService;
import com.company.jmixpmsecurity.entity.Task;
import com.company.jmixpmsecurity.view.main.MainView;
import com.company.jmixpmsecurity.view.project.ProjectListView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("Task_.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {
    @Autowired
    private TaskService taskService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Task> event) {
        event.getEntity().setAssignee(taskService.findLeastBusyUser());
    }

    @Install(to = "projectField.entityLookup", subject = "viewConfigurer")
    private void projectFieldEntityLookupViewConfigurer(final ProjectListView view) {
        view.setHideArchived(true);
    }
}