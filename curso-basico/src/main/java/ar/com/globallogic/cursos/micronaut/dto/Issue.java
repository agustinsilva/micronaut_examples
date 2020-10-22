package ar.com.globallogic.cursos.micronaut.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.convert.format.Format;
import lombok.*;

import javax.annotation.Nonnull;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
public class Issue implements Comparable{
    @NotNull(message = "id cannot be null")
    @Max( value=10 , message="Max value 10")
    @Min( value=1 , message="Min value 1")
    private Integer id;
    @NotBlank(message = "Name cannot be null")
    private String description;
    @NotNull(message = "Name cannot be null")
    private LocalDateTime createDate;

    private String status;

    @Override
    public int compareTo(Object obj) {
        Issue issue = (Issue) obj;
        return this.id - issue.getId();
    }
}
