package com.example.gemfirehack.model;


import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Track implements Serializable {

	private Long id;

	private String name;

	private Long position;
}
