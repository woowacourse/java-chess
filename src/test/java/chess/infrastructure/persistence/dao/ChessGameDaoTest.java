package chess.infrastructure.persistence.dao;

import chess.domain.piece.Color;
import chess.infrastructure.persistence.entity.ChessGameEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChessGameDao 은")
class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @BeforeEach
    void setUp() {
        chessGameDao.deleteAll();
    }

    @AfterEach
    void clean() {
        chessGameDao.deleteAll();
    }

    @Test
    void ChessGameEntity_를_저장한다() {
        // given
        final ChessGameEntity chessGameEntity = new ChessGameEntity(null, Color.WHITE.name(), null);

        // when
        chessGameDao.save(chessGameEntity);

        // then
        final ChessGameEntity byId = chessGameDao.findById(chessGameEntity.id()).get();
        assertAll(
                () -> assertThat(byId.id()).isNotNull(),
                () -> assertThat(byId.turn()).isEqualTo(chessGameEntity.turn()),
                () -> assertThat(byId.winner()).isEqualTo(chessGameEntity.winner())
        );
    }

    @Test
    void 업데이트_할_수_있다() {
        // given
        final ChessGameEntity chessGameEntity = new ChessGameEntity(null, Color.WHITE.name(), null);
        chessGameDao.save(chessGameEntity);

        // when
        final ChessGameEntity update = new ChessGameEntity(chessGameEntity.id(), Color.BLACK.name(), null);
        chessGameDao.update(update);

        // then
        final ChessGameEntity result = chessGameDao.findById(update.id()).get();
        Assertions.assertAll(
                () -> assertThat(result.id()).isEqualTo(update.id()),
                () -> assertThat(result.turn()).isEqualTo("BLACK"),
                () -> assertThat(result.winner()).isNull()
        );
    }
}
