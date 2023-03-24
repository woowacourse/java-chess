package chess.infrastructure.persistence.repository;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameRepository;
import chess.domain.game.GameState;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.infrastructure.persistence.dao.ChessGameDao;
import chess.infrastructure.persistence.dao.JdbcTemplate;
import chess.infrastructure.persistence.dao.PieceDao;
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
@DisplayName("JdbcChessGameRepository 은")
class JdbcChessGameRepositoryTest {

    private final JdbcTemplate template = new JdbcTemplate();
    private final ChessGameDao chessGameDao = new ChessGameDao(template);
    private final PieceDao pieceDao = new PieceDao(template);
    private final ChessGameRepository repository = new JdbcChessGameRepository(pieceDao, chessGameDao);

    @BeforeEach
    void setUp() {
        pieceDao.deleteAll();
        chessGameDao.deleteAll();
    }

    @AfterEach
    void clean() {
        pieceDao.deleteAll();
        chessGameDao.deleteAll();
    }

    @Test
    void ChessGame_을_저장할_수_있다() {
        // given
        final ChessGame chessGame = ChessGame.start(new ChessBoardFactory().create());

        // when
        final ChessGame save = repository.save(chessGame);

        // then
        assertThat(save.id()).isNotNull();
    }

    @Test
    void id_로_체스_게임을_가져올_수_있다() {
        // given
        final ChessGame chessGame = ChessGame.start(new ChessBoardFactory().create());
        final ChessGame save = repository.save(chessGame);

        // when
        final ChessGame find = repository.findById(save.id()).get();

        // then
        assertThat(find.pieces().size()).isEqualTo(32);
    }

    @Test
    void 체스_게임의_상태를_업데이트_할_수_있다() {
        // given
        final ChessGame chessGame = ChessGame.start(new ChessBoardFactory().create());
        final ChessGame save = repository.save(chessGame);
        final ChessGame after = 흰색_왕을_죽인다(save);

        // when
        repository.update(after);

        // then
        final ChessGame result = repository.findById(after.id()).get();
        assertAll(
                () -> assertThat(result.state()).isEqualTo(GameState.END),
                () -> assertThat(result.winColor()).isEqualTo(Color.BLACK),
                () -> assertThat(result.pieces().size()).isEqualTo(31)
        );
    }

    private ChessGame 흰색_왕을_죽인다(final ChessGame chessGame) {
        return chessGame.movePiece(PiecePosition.of("e2"), PiecePosition.of("e4"))
                .movePiece(PiecePosition.of("d7"), PiecePosition.of("d5"))
                .movePiece(PiecePosition.of("e1"), PiecePosition.of("e2"))
                .movePiece(PiecePosition.of("d5"), PiecePosition.of("d4"))
                .movePiece(PiecePosition.of("e2"), PiecePosition.of("e3"))
                .movePiece(PiecePosition.of("d4"), PiecePosition.of("e3"));
    }
}
