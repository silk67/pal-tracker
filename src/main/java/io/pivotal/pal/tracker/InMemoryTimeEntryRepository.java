package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private List<TimeEntry> entries = new ArrayList<TimeEntry>();
    private long currentId;


    @Override
    public TimeEntry create(TimeEntry timeEntry) {
//        if (this.find(timeEntry.getId()) != null) this.delete(timeEntry.getId());
        long newId = ++currentId;
        timeEntry.setId(newId);
        entries.add(timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        for (TimeEntry e : entries)
            if (e.getId() == id) return e;
        return null;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry oldTimeEntry = this.find(id);
        if (oldTimeEntry == null) return null;
        this.delete(id);
        timeEntry.setId(id);
        entries.add(timeEntry);
        return find(id);
    }

    @Override
    public List<TimeEntry> list() {
        return entries;
    }

    @Override
    public void delete(long id) {
        TimeEntry toRemove = entries.stream().filter(entry -> id == entry.getId()).findFirst().get();
        if (toRemove != null) {
            entries.remove(toRemove);
        }
    }
}
