package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {

    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(long id, long projectId, long userId, LocalDate localDate, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = localDate;
        this.hours = hours;
    }

    public TimeEntry(long projectId, long userId, LocalDate localDate, int hours) {
       this(0, projectId, userId, localDate, hours);
    }

    public TimeEntry() {
        this(0, 0, 0, null, 0);
    }

    public long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }
    public long getProjectId() {        return projectId;    }
    public void setProjectId(long projectId) {        this.projectId = projectId;    }
    public long getUserId() {        return userId;    }
    public void setUserId(long userId) {        this.userId = userId;    }
    public LocalDate getDate() {        return date;    }
    public void setDate(LocalDate date) {        this.date = date;    }
    public int getHours() {       return hours;    }
    public void setHours(int hours) {        this.hours = hours;    }


    @Override
    public boolean equals(Object obj) {
        boolean match = true;
        TimeEntry tobj = (TimeEntry) obj;
        match = match && (id == tobj.getId());
        match = match && (projectId == tobj.getProjectId());
        match = match && (userId == tobj.getUserId());
        match = match && (date.compareTo(tobj.getDate()) == 0);
        match = match && (hours == tobj.getHours());

        return match;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }
}
