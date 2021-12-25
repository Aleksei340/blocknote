package ua.goit.goitnotes.validation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.goitnotes.groupnote.service.GroupNoteService;
import ua.goit.goitnotes.note.model.AccessType;
import ua.goit.goitnotes.note.service.NoteService;
import ua.goit.goitnotes.user.model.User;
import ua.goit.goitnotes.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor

public class ValidationService implements Validate {
    @Autowired
    UserService userService;
    @Autowired
    GroupNoteService groupNoteService;
    @Autowired
    NoteService noteService;

    @Override
    public ValidateResponse validateNote(@NonNull ValidateNoteRequest noteRequest, User currentUser) {
        log.info("validateNote .");
        List<ValidationError> errors = new ArrayList<>();
        String title = noteRequest.getTitle();
        String content = noteRequest.getContent();
        String noteIdString = noteRequest.getIdString();
       String accessType = noteRequest.getAccessType();
        if(noteService.isTitlePresetForTheUser(title, currentUser)){
            if(Objects.isNull(noteIdString) || !noteService.findByName(title).getId().equals(UUID.fromString(noteIdString))){
                errors.add(ValidationError.NOTE_TITLE_NOT_UNIQUE_FOR_CURRENT_USER);
                log.error("validateNote . note title is not unique for this user:'{}'" , title);
            }
        }
        if (title.length() < 5 || title.length() > 100) {
            errors.add(ValidationError.WRONG_NOTE_TITLE_LENGTH);
            log.error("validateNote . note title:'{}' length should be between 5 and 100 included", title);
        }
        if (content.length() < 5 || content.length() > 10000) {
            errors.add(ValidationError.WRONG_NOTE_CONTENT_LENGTH);
            log.error("validateNote . note content length:'{}', but should be between 5 and 10000 included", content.length());
        }
        if(!AccessType.isAccessType(accessType)){
            errors.add(ValidationError.WRONG_ACCESS_TYPE);
            log.error("validateNote . validation type is wrong:'{}'", accessType);
        }
        return new ValidateResponse(errors.isEmpty(), errors);
    }

    @Override
    public ValidateResponse validateUser(@NonNull ValidateUserRequest userRequest) {
        log.info("validateUser .");
        List<ValidationError> errors = new ArrayList<>();
        String name = userRequest.getName();
        String password = userRequest.getPassword();
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(name);
        if (userService.isUserNamePresent(name)) {
            errors.add(ValidationError.USER_NAME_NOT_UNIQUE);
            log.error("validateUser . user name:'{}' is already present in database", name);
        }
        if (name.length() < 5 || name.length() > 50) {
            errors.add(ValidationError.WRONG_USER_NAME_LENGTH);
            log.error("validateUser . user name:'{}' length should be between 5 and 50 included", name);
        }
        if (!matcher.matches()) {
            errors.add(ValidationError.WRONG_USER_NAME_SYMBOLS);
            log.error("validateUser . user name:'{}' should be only Latin symbols and digits only ", name);
        }
        if (password.length() < 8 || password.length() > 100) {
            errors.add(ValidationError.WRONG_USER_PASSWORD_LENGTH);
            log.error("validateUser . user password:'{}' length should be between 8 and 100 included", password);
        }
        return new ValidateResponse(errors.isEmpty(), errors);
    }

    @Override
    public ValidateResponse validateGroup(@NonNull ValidateGroupRequest groupRequest) {
        log.info("validateGroup .");
        List<ValidationError> errors = new ArrayList<>();
        String name = groupRequest.getName();
        String description = groupRequest.getDescription();
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(name);
        if (name.length() < 5 || name.length() > 60) {
            errors.add(ValidationError.WRONG_GROUP_NAME);
            log.error("validateGroup . group name: '{}' should be only Latin symbols and digits only", name);
        }
        if (description.length() < 5 || description.length() > 10000) {
            errors.add(ValidationError.WRONG_GROUP_DESCRIPTION_LENGTH);
            log.error("validateGroup . group description length:'{}', but should be between 5 and 10000 included", description.length());
        }
        return new ValidateResponse(errors.isEmpty(), errors);
    }

    @Override
    public ValidateResponse validateGroupNote(ValidateGroupNoteRequest groupNoteRequest) {
        log.info("validateGroupNote .");
        List<ValidationError> errors = new ArrayList<>();
        String title = groupNoteRequest.getTitle();
        String content = groupNoteRequest.getContent();
        if (title.length() < 5 || title.length() > 100) {
            errors.add(ValidationError.WRONG_NOTE_TITLE_LENGTH);
            log.error("validateNote . note title:'{}' length should be between 5 and 100 included", title);
        }
        if (content.length() < 5 || content.length() > 10000) {
            errors.add(ValidationError.WRONG_NOTE_CONTENT_LENGTH);
            log.error("validateNote . note content length:'{}', but should be between 5 and 10000 included", content.length());
        }
        return new ValidateResponse(errors.isEmpty(), errors);
    }
}
