package ua.goit.goitnotes.group;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.goitnotes.group.model.Group;
import ua.goit.goitnotes.group.service.GroupService;
import ua.goit.goitnotes.user.service.UserService;
import ua.goit.goitnotes.validation.ValidateGroupRequest;
import ua.goit.goitnotes.validation.ValidateResponse;
import ua.goit.goitnotes.validation.ValidationService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/group")
public class GroupController {
    private final GroupService groupService;
    private final ValidationService validationService;
    private final UserService userService;

    @GetMapping(path = "/list")
    public String showGroups(Model model) {
        log.info("GroupController.showGroups");
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Group> groups = groupService.findByUserName(currentPrincipalName).stream()
                .sorted(Comparator.comparing(Group::getName))
                .collect(Collectors.toList());
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam(name = "id")UUID uuid) {
        log.info("NoteController.delete .");
        Group group = groupService.findById(uuid);
        if (group.getOwner().getName().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            groupService.delete(uuid);
        } else {
            return "redirect:/error";
        }
        return "redirect:/error";
    }

    @GetMapping("/create")
    public String showNewGroupPage() {
        return "newGroup";
    }

    @PostMapping("/create")
    @ResponseBody
    public ValidateResponse createGroupOrShowException(@RequestBody ValidateGroupRequest groupRequest) {
        log.info("GroupController . createNoteOrShowException");
        ValidateResponse response = validationService.validateGroup(groupRequest);
        if (response.isSuccess()) {
            Group group = new Group();
            group.setName(groupRequest.getName());
            group.setDescription(groupRequest.getDescription());
            group.setOwner(userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
            groupService.create(group);
        }
        return response;
    }


}
