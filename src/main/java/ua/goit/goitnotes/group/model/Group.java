package ua.goit.goitnotes.group.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ua.goit.goitnotes.groupnote.model.GroupNote;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<GroupNote> groupNotes;

    public Group(UUID id, String name, String description, Set<GroupNote> groupNotes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupNotes = groupNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(description, group.description) && Objects.equals(groupNotes, group.groupNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
