package db;

import domain.board.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementDaoTest {

    private final MovementDao movementDao = new MovementDao();
    @BeforeEach
    void setUp(){
        movementDao.deleteAll();
    }
    @Test
    @DisplayName("움직임이 잘 만들어지는지 확인한다")
    void createMovement() {
        final Movement movement = new Movement(Position.from("b2"), Position.from("b3"), "WHITE", "KING");

        Assertions.assertThatCode(() -> movementDao.createMovement(movement)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("저장된 모든 움직임이 잘 반환되는지 확인한다")
    void findAll() {
        final Movement movement = new Movement(Position.from("b2"), Position.from("b3"), "WHITE", "KING");
        movementDao.createMovement(movement);

        Assertions.assertThat(movementDao.findAll()).hasSize(1);
    }

    @Test
    @DisplayName("저장된 움직임이 잘 지워졌는지 확인한다")
    void deleteAll() {
        final Movement movement = new Movement(Position.from("b2"), Position.from("b3"), "WHITE", "KING");
        movementDao.createMovement(movement);

        movementDao.deleteAll();

        Assertions.assertThat(movementDao.findAll()).isEmpty();
    }


}
