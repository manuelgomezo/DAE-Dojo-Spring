package co.edu.udea.dojospring.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity //Define que la clase es una entidad
@Table(name = "post") //Indeca que esta entidad va a ser mapeada en una tabla de la bd
@EntityListener(AuditingEntityListener.class) //Escucha eventos de modificacion
@JsonIgnoreProperties(value = {"createAt", "updatedAt"}, 
            allowGetters = true) //Inora unas propiedades
public class Post {
    @id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Colum(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP) //Especificar que va a ser de tipo TIMESTAMP
    @CreateDate //Al momento de crear guardar la fecha
    private Date createdAt;

    @Colum(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP) //Especificar que va a ser de tipo TIMESTAMP
    @CreateDate 
    @LastModifiedDate
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
