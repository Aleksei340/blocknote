package ua.goit.goitnotes.groupnote.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.goit.goitnotes.error_handling.ObjectNotFoundException;
import ua.goit.goitnotes.groupnote.dto.GroupNoteDTO;
import ua.goit.goitnotes.groupnote.model.GroupNote;
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
        log.info("create groupNote .");
        GroupNote groupNote = noteConvertor.fromDTO(entity);
        return noteConvertor.toDTO(noteRepository.save(groupNote));
    }

    @Override
    public GroupNoteDTO update(GroupNoteDTO entity) {
        log.info("update groupNote .");
        GroupNote groupNote = noteConvertor.fromDTO(entity);
        return  noteConvertor.toDTO(noteRepository.save(groupNote));
    }

    @Override
    public void delete(UUID id) {
        log.info("delete groupNote .");
        try {
            noteRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            log.error("delete groupNote . There are no notes with such id in database");
            throw new ObjectNotFoundException("There are no notes with such id in database");
        }
    }

    @Override
    public List<GroupNoteDTO> findAll() {
        log.info("findAll groupNotes .");
        return noteConvertor.listToDTO(noteRepository.findAll());
    }

    @Override
    public GroupNoteDTO findById(UUID id) {
        log.info("fingById groupNote .");
        return noteRepository.findById(id).map(noteConvertor::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException("object 'note' with specified ID not found"));
    }

    @Override
    public GroupNoteDTO findByName(String name) {
        // group name isn't unique
        return null;
    }

    public  List<GroupNoteDTO> findByGroupId (UUID groupUUID) {
        log.info("findGroupNotesByGroupId .");
        return noteConvertor.listToDTO(noteRepository.findByGroup_Id(groupUUID));
    }
}
