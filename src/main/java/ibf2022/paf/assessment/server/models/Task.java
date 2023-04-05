package ibf2022.paf.assessment.server.models;

public class Task {

    private String description;

    private Integer priority;

    private String dueDate;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task [description=" + description + ", priority=" + priority + ", dueDate=" + dueDate + "]";
    }



}
