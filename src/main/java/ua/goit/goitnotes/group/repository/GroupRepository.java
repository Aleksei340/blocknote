package ua.goit.goitnotes.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ua.goit.goitnotes.group.model.Group;

import java.util.List;
import java.util.UUID;

@Component
public interface GroupRepository extends JpaRepository<Group, UUID> {
    @Query("""
            select g from Group g where (g.owner.id = ?1)""")
    List<Group> findByOwnerId(UUID id);
}
