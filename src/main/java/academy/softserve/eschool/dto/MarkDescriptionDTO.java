package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkDescriptionDTO {
    @ApiModelProperty(notes = "id of lesson")
    private int idLesson;
    
    @ApiModelProperty(notes = "mark value")
    private Byte mark;
    
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd")
    @ApiModelProperty(notes = "date of mark")
    private Date dateMark;
    
    @ApiModelProperty(notes = "type of mark")
    private String typeMark;
    
    @ApiModelProperty(notes = "note for mark")
    private String note;

}
