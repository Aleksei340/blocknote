package ua.goit.goitnotes.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class ValidateGroupRequest {
    @NonNull
    private String name;
    @NonNull
    private String description;
    private String idString;
}
