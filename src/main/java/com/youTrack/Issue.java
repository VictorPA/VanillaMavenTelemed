package com.youTrack;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Représentation objet de tous les champs d'une issue YouTrack
 */
public class Issue {

    private String projectShortName;
    private int numberInProject;
    private String summary;
    private Date created;
    private Date updated;
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

    public int getNumberInProject() {
        return numberInProject;
    }

    public void setNumberInProject(String numberInProject) {
        this.numberInProject = Integer.parseInt(numberInProject);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(String created) {
        try {
            this.created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(created);
        } catch (ParseException e) {
            this.created = new Date();
        }
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {

        try {
            this.updated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(updated);
        } catch (ParseException e) {
            this.updated = new Date();
        }
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

    @Override public String toString() {
        return "Issue{" +
                "\n projectShortName = '" + projectShortName + '\'' +
                ",\n numberInProject = " + numberInProject +
                ",\n summary = '" + summary + '\'' +
                ",\n created = " + created +
                ",\n updated = " + updated +
                ",\n type = '" + type + '\'' +
                ",\n state = '" + state + '\'' +
                ",\n assignee = '" + assignee + '\'' +
                ",\n subsystem = '" + subsystem + '\'' +
                ",\n estimation = '" + estimation + '\'' +
                '}';
    }
}
