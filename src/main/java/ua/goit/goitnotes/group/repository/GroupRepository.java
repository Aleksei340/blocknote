package ua.goit.goitnotes.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ua.goit.goitnotes.group.model.Group;

import java.util.UUID;

@Component
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
