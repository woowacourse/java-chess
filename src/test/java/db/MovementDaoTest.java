package db;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementDaoTest {

    @Test
    void createMovement() {
        final MovementDao squareDao = new MovementDao();
        final Movement movement = new Movement("B","ONE", "A","THREE", "WHITE", "KING");
        squareDao.createMovement(movement);

        assertThat(squareDao.findByMovementId("1")).isEqualTo(movement);
    }
    @Test
    void findAll() {
        final MovementDao squareDao = new MovementDao();
//        final Movement movement = new Movement("B","ONE", "A","THREE", "WHITE", "KING");
//        squareDao.createSquare(movement);

        Assertions.assertThat(squareDao.findAll()).hasSize(4);
    }

}
