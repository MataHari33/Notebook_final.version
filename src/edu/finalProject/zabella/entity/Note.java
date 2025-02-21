package edu.finalProject.zabella.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import edu.finalProject.zabella.util.GenerateId;

public class Note implements Serializable {

    private int id;
    private String title;
    private String content;
    private Date d;

    public Note() {
    }


    public Note(int id, String title, String content, Date d) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.d = d;
    }

    public Note(String title, String content, Date d) {
        this.id = GenerateId.nextId();
        this.title = title;
        this.content = content;
        this.d = d;
    }

    public Note(String title, String content) {
        this.id = GenerateId.nextId();
        this.title = title;
        this.content = content;
        this.d = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, d, id, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        return Objects.equals(content, other.content) && Objects.equals(d, other.d) && id == other.id
                && Objects.equals(title, other.title);
    }

    @Override
    public String toString() {
        return "Note [id=" + id + ", title=" + title + ", content=" + content + ", d=" + d + "]";
    }

    public void updateNote(Note _note) {
        _note.setTitle(_note.title);
    }

}