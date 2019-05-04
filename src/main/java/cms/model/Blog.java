package cms.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String content;
    private String date = new Date().toString();
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Blog(){}
    public Blog(String author,String content,String date){
        this.author = author;
        this.content = content;
        this.date = date;
    }
    @Override
    public String toString() {
        return String.format("Blog[id=%d, author='%s', content='%s',date='%s']", id, author, content,date);
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
