package ua.goit.goitnotes.group.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.goitnotes.error_handling.ObjectNotFoundException;
import ua.goit.goitnotes.group.model.Group;
import ua.goit.goitnotes.group.repository.GroupRepository;
import ua.goit.goitnotes.interfaces.CrudService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService implements CrudService<Group> {
    private final GroupRepository groupRepository;

    @Override
    public Group create(Group entity) {
        return null;
    }

    @Override
    public Group update(Group entity) {
        return groupRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(UUID id) {
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
}
