package ua.goit.goitnotes.groupnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.goitnotes.groupnote.model.GroupNote;

import java.util.UUID;

public interface GroupNoteRepository extends JpaRepository<GroupNote, UUID> {}
