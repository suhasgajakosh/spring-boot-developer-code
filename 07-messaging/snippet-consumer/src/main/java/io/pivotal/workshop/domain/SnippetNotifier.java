package io.pivotal.workshop.domain;

import java.util.Date;

public class SnippetNotifier {

    private String title;
    private Date created;

    public SnippetNotifier(){}

    public SnippetNotifier(String title, Date date) {
        this.title = title;
        this.created = date;
    }


    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }

}

