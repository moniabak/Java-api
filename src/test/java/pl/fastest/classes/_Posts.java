package pl.fastest.classes;

import org.hamcrest.Condition;

public class _Posts {
    private String id;
    private String title;
    private Info info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Info getInfo(){
        return info;
    }

    public void setInfo(Info info){
        this.info = info;
    }
}
