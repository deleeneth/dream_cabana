package lk.ijse.dreamcabana.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Payment {
    private String payment_id;
    private String booking_id;
    private String date;

}
