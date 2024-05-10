package lk.ijse.dreamcabana.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentTm {
    private String payment_id;
    private String booking_id;
    private String date;
}
