package lk.ijse.dreamcabana.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Customer {
    private String customer_id;
    private String name;
    private String address;
    private String contact;
}
