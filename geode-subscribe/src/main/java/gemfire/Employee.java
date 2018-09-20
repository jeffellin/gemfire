package gemfire;


import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Employee implements Serializable {

	private String firstName;
	private String lastName;
	private int emplNumber;
	private String email;
	private int salary;
	private int hoursPerWeek;
}
