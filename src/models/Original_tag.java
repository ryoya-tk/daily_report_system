package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name="getAllOriginalTag",
            query="select r from Original_tag as r order by r.title asc"
            ),
    @NamedQuery(
            name = "checkRegisteredTitle",
            query = "SELECT COUNT(e) FROM Original_tag AS e WHERE e.title = :title"
            )
})

@Table(name="originalTag")
public class Original_tag {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title",length=225,nullable=false)
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
