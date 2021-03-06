package ua.goit.goitnotes.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class ValidateGroupNoteRequest {
    @NonNull
    private String title;
    @NonNull
    private String content;

    private String idString;
}
