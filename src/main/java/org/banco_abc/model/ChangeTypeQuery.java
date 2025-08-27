package org.banco_abc.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "change_type_query")
public class ChangeTypeQuery extends PanacheEntity {

    @Column(nullable = false)
    public String dni;

    @Column(nullable = false)
    public LocalDateTime queryDate;
}
