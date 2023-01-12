package com.example.demo.model.criteria;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SearchCriteria {
    private String key;
    private String value;
    private SearchOperation operation;
}
