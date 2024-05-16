package lk.ijse.dreamcabana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddBooking {
    private Booking booking;
    private Room room;
}
