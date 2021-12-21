package ua.goit.goitnotes.groupnote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupNoteDTO {
    private UUID id;
    private String title;
    private String content;
    private UUID groupID;
}
