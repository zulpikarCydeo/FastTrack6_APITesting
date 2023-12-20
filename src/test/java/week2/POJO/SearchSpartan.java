package week2.POJO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;


@Data
@JsonIgnoreProperties(ignoreUnknown = true) //
public class SearchSpartan {

    private List<SingleSpartan> content;
    private int totalElement;

//    @JsonProperty("total element")
//    private int total_element;
}
