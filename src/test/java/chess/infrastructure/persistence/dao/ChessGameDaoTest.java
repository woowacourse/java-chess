package chess.infrastructure.persistence.dao;

import chess.domain.game.state.MovePiece;
import chess.domain.piece.Color;
import chess.infrastructure.persistence.entity.ChessGameEntity;
import org.junit.jupiter.api.AfterEach;
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
        final ChessGameEntity chessGameEntity = new ChessGameEntity(1L, MovePiece.class.getSimpleName(), Color.WHITE.name(), null);

        // when
        chessGameDao.save(chessGameEntity);

        // then
        final ChessGameEntity byId = chessGameDao.findById(chessGameEntity.id());
        assertAll(
                () -> assertThat(byId.id()).isEqualTo(chessGameEntity.id()),
                () -> assertThat(byId.state()).isEqualTo(chessGameEntity.state()),
                () -> assertThat(byId.turn()).isEqualTo(chessGameEntity.turn()),
                () -> assertThat(byId.winner()).isEqualTo(chessGameEntity.winner())
        );
    }
}
