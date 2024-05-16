package lk.ijse.dreamcabana.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingTm {
    private String booking_id;
    private String customer_id;
    private String payment;
    private String Date;
    private String room_id;
}
