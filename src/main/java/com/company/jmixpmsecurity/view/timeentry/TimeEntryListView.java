package com.company.jmixpmsecurity.view.timeentry;

import com.company.jmixpmsecurity.entity.TimeEntry;
import com.company.jmixpmsecurity.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "timeEntries", layout = MainView.class)
@ViewController("TimeEntry.list")
@ViewDescriptor("time-entry-list-view.xml")
@LookupComponent("timeEntriesDataGrid")
@DialogMode(width = "64em")
public class TimeEntryListView extends StandardListView<TimeEntry> {
}