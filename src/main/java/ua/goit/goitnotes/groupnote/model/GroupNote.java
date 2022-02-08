package ua.goit.goitnotes.groupnote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ua.goit.goitnotes.group.model.Group;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "groupnotes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupNote {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
