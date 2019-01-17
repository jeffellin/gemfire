package com.example.gfsh.model;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutsideObject {


    private String name;

    private InsideObject insideObject;


}
