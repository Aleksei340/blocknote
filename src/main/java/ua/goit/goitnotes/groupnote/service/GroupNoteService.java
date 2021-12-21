package ua.goit.goitnotes.groupnote.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.goit.goitnotes.groupnote.dto.GroupNoteDTO;
import ua.goit.goitnotes.groupnote.repository.GroupNoteRepository;
import ua.goit.goitnotes.groupnote.service.convertors.GroupNoteConvertor;
import ua.goit.goitnotes.interfaces.CrudService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class GroupNoteService implements CrudService<GroupNoteDTO> {
    private final GroupNoteRepository noteRepository;
    private final GroupNoteConvertor noteConvertor;

    @Override
    public GroupNoteDTO create(GroupNoteDTO entity) {
        return null;
    }

    @Override
    public GroupNoteDTO update(GroupNoteDTO entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<GroupNoteDTO> findAll() {
        return null;
    }

    @Override
    public GroupNoteDTO findById(UUID id) {
        return null;
    }

    @Override
    public GroupNoteDTO findByName(String name) {
        return null;
    }
}
