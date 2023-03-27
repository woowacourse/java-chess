package chess.dao;

import chess.domain.Position;
import chess.dto.Command;
import chess.dto.MovementDto;
import chess.view.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.mock.MockPosition.POSITION_0_4;
import static chess.mock.MockPosition.POSITION_0_6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MovementDaoTest {
    @BeforeEach
    void setUp() {
        new MovementDao().deleteAll();
        new GameDao().deleteAll();
    }

    @DisplayName("gameId를 통한 해당 게임의 모든 움직임 조회에 성공한다.")
    @Test
    void findAllBy() {
        // given
        final GameDao gameDao = new GameDao();
        final MovementDao movementDao = new MovementDao();
        final Command command = Command.from(GameState.MOVE, POSITION_0_6.toString(), POSITION_0_4.toString());

        // when
        final long savedGameId = gameDao.save(false, BLACK);
        movementDao.save(command, BLACK, savedGameId);

        final List<MovementDto> findMovements = movementDao.findAllBy(savedGameId);
        final MovementDto actual = findMovements.get(0);
        final Position source = actual.getSource();
        final Position target = actual.getTarget();

        // then
        assertAll(
                () -> assertThat(source).isEqualTo(command.getSource()),
                () -> assertThat(target).isEqualTo(command.getTarget())
        );
    }

    @DisplayName("움직임을 저장한다.")
    @Test
    void save() {
        // given
        final GameDao gameDao = new GameDao();
        final MovementDao movementDao = new MovementDao();
        final Command command = Command.from(GameState.MOVE, POSITION_0_6.toString(), POSITION_0_4.toString());

        // when
        final long savedGameId = gameDao.save(false, BLACK);
        final long savedMovementId = movementDao.save(command, BLACK, savedGameId);

        // then
        assertThat(savedMovementId).isGreaterThan(0);
    }
}
