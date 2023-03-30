package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.dto.GameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("GameDao 는")
class GameDaoTest {

    private final GameDao gameDao = new GameDao();

    @Test
    void 생성된_게임_id를_조회할_수_있다() {
        final int id = gameDao.create(GameDto.create());
        final GameDto gameDto = GameDto.from(true);

        assertThat(gameDao.findAllIdsByIsRunning(gameDto).size()).isGreaterThan(0);
        gameDao.delete(id);
    }

    @Test
    void 생성된_게임_id로_turn을_조회할_수_있다() {
        final int id = gameDao.create(GameDto.create());
        final GameDto gameDto = GameDto.from(id);

        assertThat(gameDao.findTurnById(gameDto)).isEqualTo(new Turn(Color.WHITE));
        gameDao.delete(id);
    }

    @Test
    void 새로운_turn으로_업데이트할_수_있다() {
        final int id = gameDao.create(GameDto.create());
        GameDto gameDto = GameDto.of(id, "Black");

        gameDao.updateTurn(gameDto);
        gameDto = GameDto.from(id);

        assertThat(gameDao.findTurnById(gameDto)).isEqualTo(new Turn(Color.BLACK));
        gameDao.delete(id);
    }

    @Test
    void 종료_상태로_업데이트할_수_있다() {
        final int id = gameDao.create(GameDto.create());
        GameDto updateDto = GameDto.of(id, false);

        GameDto retrieveDto = GameDto.from(id);
        final int expected = gameDao.findAllIdsByIsRunning(retrieveDto).size() - 1;

        gameDao.updateIsRunning(updateDto);
        assertThat(gameDao.findAllIdsByIsRunning(retrieveDto).size()).isEqualTo(expected);
        gameDao.delete(id);
    }
}
