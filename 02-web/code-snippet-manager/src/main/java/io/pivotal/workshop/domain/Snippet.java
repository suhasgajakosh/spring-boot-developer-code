package io.pivotal.workshop.domain;

import java.util.Date;

//Option 1. Add annotation to get XML output in Responses.
//@XmlRootElement
public class Snippet {

    private String id;
    private String title;
    private String code;

    //Option 2. You can uncomment this as an alternative of the application.properties
    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date created;

    //Option 2. You can uncomment this as an alternative of the application.properties
    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date modified;

    public Snippet() {
    }

    public Snippet(String title, String code) {
        this.id = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.code = code;
        this.created = new Date();
        this.modified = new Date();
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

}
