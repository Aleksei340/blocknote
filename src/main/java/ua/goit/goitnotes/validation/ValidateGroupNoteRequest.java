package ua.goit.goitnotes.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ValidateGroupNoteRequest {
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private UUID groupUUID;
}
