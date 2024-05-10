package lk.ijse.dreamcabana.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Booking {
    private String booking_id;
    private String customer_id;
    private String Date;
}
