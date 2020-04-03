package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name="getAllReport",
            query="select r from Report as r order by r.id desc"
            ),
    @NamedQuery(
            name="getReportByTag",
            query="select r from Report as r where r.tag_01= :tag_01"
            )
})
@Table(name="report_messages")
public class Report {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="employee",nullable=false)
    private Employee employee;

    @Column(name="title",length=225,nullable=false)
    private String title;

    @Lob
    @Column(name="content",length=1000,nullable=false)
    private String content;

    @ManyToOne
    @JoinColumn(name="tag_01",nullable=true)
    private Original_tag tag_01;

    @Column(name="tag_02",length=225,nullable=true)
    private String tag_02;

    @Column(name="created_at",length=225,nullable=false)
    private Timestamp created_at;

    @Column(name="updated_at",length=225,nullable=false)
    private Timestamp updated_at;

    @Column(name="date_at",length=225,nullable=false)
    private Date date_at;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Original_tag getTag_01() {
        return tag_01;
    }

    public void setTag_01(Original_tag tag_01) {
        this.tag_01 = tag_01;
    }

    public String getTag_02() {
        return tag_02;
    }

    public void setTag_02(String tag_02) {
        this.tag_02 = tag_02;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDate_at() {
        return date_at;
    }

    public void setDate_at(Date date_at){
        this.date_at=date_at;
    }
}
