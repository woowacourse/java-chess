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

    private final JdbcTemplate template = new JdbcTemplate();
    private final ChessGameDao chessGameDao = new ChessGameDao(template);

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
        final ChessGameEntity chessGameEntity = new ChessGameEntity(null, "MovePiece", Color.WHITE.name(), null);

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
        final ChessGameEntity chessGameEntity = new ChessGameEntity(null, "MovePiece", Color.WHITE.name(), null);
        chessGameDao.save(chessGameEntity);

        // when
        final ChessGameEntity update = new ChessGameEntity(chessGameEntity.id(), "MovePiece", Color.BLACK.name(), null);
        chessGameDao.update(update);

        // then
        final ChessGameEntity result = chessGameDao.findById(update.id()).get();
        assertAll(
                () -> assertThat(result.id()).isEqualTo(update.id()),
                () -> assertThat(result.turn()).isEqualTo("BLACK"),
                () -> assertThat(result.winner()).isNull()
        );
    }

    @Test
    void 업데이트_시_하나만_업데이트_되어야_한다() {
        // given
        final ChessGameEntity chessGameEntity1 = new ChessGameEntity(null, "MovePiece", Color.WHITE.name(), null);
        final ChessGameEntity chessGameEntity2 = new ChessGameEntity(null, "MovePiece", Color.WHITE.name(), null);
        chessGameDao.save(chessGameEntity1);
        chessGameDao.save(chessGameEntity2);

        // when
        final ChessGameEntity update = new ChessGameEntity(chessGameEntity1.id(), "MovePiece", Color.BLACK.name(), null);
        chessGameDao.update(update);

        // then
        final ChessGameEntity result = chessGameDao.findById(update.id()).get();
        final ChessGameEntity noUpdate = chessGameDao.findById(chessGameEntity2.id()).get();
        Assertions.assertAll(
                () -> assertThat(result.id()).isEqualTo(update.id()),
                () -> assertThat(result.turn()).isEqualTo("BLACK"),
                () -> assertThat(noUpdate.turn()).isEqualTo("WHITE"),
                () -> assertThat(result.winner()).isNull()
        );
    }
}
