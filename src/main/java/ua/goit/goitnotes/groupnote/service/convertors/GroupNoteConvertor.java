package ua.goit.goitnotes.groupnote.service.convertors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.goit.goitnotes.group.service.GroupService;
import ua.goit.goitnotes.groupnote.dto.GroupNoteDTO;
import ua.goit.goitnotes.groupnote.model.GroupNote;
import ua.goit.goitnotes.interfaces.Convertor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupNoteConvertor implements Convertor<GroupNote, GroupNoteDTO> {
    private final GroupService groupService;

    @Override
    public GroupNote fromDTO(GroupNoteDTO groupNoteDTO) {
        log.info("from groupNoteDTO .");
        return new GroupNote(groupNoteDTO.getId(), groupNoteDTO.getTitle(), groupNoteDTO.getContent(),
                groupService.findById(groupNoteDTO.getId()));
    }

    @Override
    public GroupNoteDTO toDTO(GroupNote groupNote) {
        log.info("toGroupNotedDTO .");
        return new GroupNoteDTO(groupNote.getId(), groupNote.getTitle(), groupNote.getContent(),
                groupNote.getGroup().getId());
    }

    public List<GroupNoteDTO> listToDTO(List<GroupNote> notes) {
        log.info("listToDTO .");
        return notes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
