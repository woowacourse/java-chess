package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.InitialPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PieceDaoTest {

    private PieceDao pieceDao;
    private ChessGameDao gameDao;
    private Map<Position, Piece> piecesByPosition = Map.of(
        Position.from("A1"), InitialPiece.WHITE_ROOK.getPiece(),
        Position.from("A8"), InitialPiece.BLACK_ROOK.getPiece()
    );
    private long gameId;

    @BeforeEach
    void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(RollbackConnectionProvider.getConnection());
        gameDao = new ChessGameDao(jdbcTemplate);
        pieceDao = new PieceDao(jdbcTemplate);
        gameId = gameDao.save(new ChessGame(new ChessBoard(piecesByPosition)));
        pieceDao.save(piecesByPosition, gameId);
    }

    @AfterEach
    void clearUp() {
        pieceDao.rollback();
        gameDao.rollback();
    }


    @DisplayName("게임과 함께 체스말이 모두 조회된다.")
    @Test
    void 체스말_저장_조회() {
        assertThat(pieceDao.findAllByGameId(gameId).piecesByPosition())
            .containsExactlyInAnyOrderEntriesOf(piecesByPosition);
    }

    @DisplayName("체스말의 이동 위치가 저장된다.")
    @Test
    void 체스말_위치_변경() {
        pieceDao.updatePositionByPositionAndGameId(Position.from("A1"), gameId,
            Position.from("A4"));
        assertThat(pieceDao.findAllByGameId(gameId).piecesByPosition())
            .containsExactlyInAnyOrderEntriesOf(Map.of(
                Position.from("A4"), InitialPiece.WHITE_ROOK.getPiece(),
                Position.from("A8"), InitialPiece.BLACK_ROOK.getPiece()
            ));
    }

    @DisplayName("체스말의 정보가 제거된다.")
    @Test
    void 체스말_삭제() {
        pieceDao.deleteByPositionAndGameId(Position.from("A8"), gameId);
        assertThat(pieceDao.findAllByGameId(gameId).piecesByPosition())
            .doesNotContainEntry(Position.from("A8"), InitialPiece.BLACK_ROOK.getPiece());
    }

}