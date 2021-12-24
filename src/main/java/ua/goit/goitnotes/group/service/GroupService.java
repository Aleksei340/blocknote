package ua.goit.goitnotes.group.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.goit.goitnotes.error_handling.ObjectNotFoundException;
import ua.goit.goitnotes.group.model.Group;
import ua.goit.goitnotes.group.repository.GroupRepository;
import ua.goit.goitnotes.interfaces.CrudService;
import ua.goit.goitnotes.user.model.User;
import ua.goit.goitnotes.user.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService implements CrudService<Group> {
    private final GroupRepository groupRepository;
    private final UserService userService;

    @Override
    public Group create(Group entity) {
        log.info("create .");
        return groupRepository.save(entity);
    }

    @Override
    public Group update(Group entity) {
        log.info("update group .");
        return groupRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        log.info("delete group .");
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> findAll() {
        log.info("findAll groups .");
        return groupRepository.findAll();
    }

    @Override
    public Group findById(UUID id) {
        log.info("findById group .");
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new ObjectNotFoundException(String.format("Object 'group' with ID %s not found", id));
        }
        return group.get();
    }

    @Override
    public Group findByName(String name) {
        // name of group isn't unique
        return null;
    }

    public List<Group> findByUserName(String userName) {
        log.info("findByUsrName groups .");
        User user = userService.findByName(userName);
        return groupRepository.findByOwnerId(user.getId());
    }
}
