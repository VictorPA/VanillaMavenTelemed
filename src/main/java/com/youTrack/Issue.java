package com.youTrack;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */

/**
 * Représentation objet de tous les champs d'une issue YouTrack
 */
public class Issue {

    private String projectShortName;
    private String numberInProject;
    private String summary;
    private String description;
    private String created;
    private String updated;
    private String updaterName;
    private String updaterFullName;
    private String reporterName;
    private String reporterFullName;
    private String commentsCount;
    private String votes;
    private String links;
    private String priority;
    private String type;
    private String state;
    private String assignee;
    private String subsystem;
    private String estimation;


    public String getProjectShortName() {
        return projectShortName;
    }

    public void setProjectShortName(String projectShortName) {
        this.projectShortName = projectShortName;
    }

    public String getNumberInProject() {
        return numberInProject;
    }

    public void setNumberInProject(String numberInProject) {
        this.numberInProject = numberInProject;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public String getUpdaterFullName() {
        return updaterFullName;
    }

    public void setUpdaterFullName(String updaterFullName) {
        this.updaterFullName = updaterFullName;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterFullName() {
        return reporterFullName;
    }

    public void setReporterFullName(String reporterFullName) {
        this.reporterFullName = reporterFullName;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }
}
