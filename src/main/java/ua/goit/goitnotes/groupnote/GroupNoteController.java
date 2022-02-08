package ua.goit.goitnotes.groupnote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.goitnotes.group.service.GroupService;
import ua.goit.goitnotes.groupnote.dto.GroupNoteDTO;
import ua.goit.goitnotes.groupnote.service.GroupNoteService;
import ua.goit.goitnotes.validation.ValidateGroupNoteRequest;
import ua.goit.goitnotes.validation.ValidateResponse;
import ua.goit.goitnotes.validation.ValidationService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/groupNote")
public class GroupNoteController {
    private final GroupService groupService;
    private final GroupNoteService groupNoteService;
    private final ValidationService validationService;

    @GetMapping("/list")
    public String showGroupNotes(@RequestParam(name = "id") UUID uuid, Model model) {
        log.info("GroupNoteController . showGroupNotes");
        List<GroupNoteDTO> notes = groupNoteService.findByGroupId(uuid).stream()
                .sorted(Comparator.comparing(GroupNoteDTO::getTitle))
                .collect(Collectors.toList());
        model.addAttribute("notes", notes);
        model.addAttribute("groupId", uuid);
        return "groupNotes";
    }

    @GetMapping("/create")
    public String showCreateGroupNotePage(@RequestParam(name = "id") UUID uuid, Model model) {
        model.addAttribute("groupId", uuid);
        return "newGroupNote";
    }

    @PostMapping("/create")
    public ValidateResponse createNoteOrShowException(@RequestBody ValidateGroupNoteRequest validateGroupNoteRequest,
                                                      @RequestParam(name = "id") UUID uuid) {
        log.info("GroupNoteController . createGroupNoteOrShowException");
        ValidateResponse response = validationService.validateGroupNote(validateGroupNoteRequest);
        if (response.isSuccess()) {
            GroupNoteDTO groupNoteDTO = new GroupNoteDTO();
            groupNoteDTO.setTitle(validateGroupNoteRequest.getTitle());
            groupNoteDTO.setContent(validateGroupNoteRequest.getContent());
            groupNoteDTO.setGroupID(uuid);
            groupNoteService.create(groupNoteDTO);
        }
        return response;
    }
}
