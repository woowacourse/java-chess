package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private static final XAxis X_AXIS = XAxis.A;
    private static final YAxis Y_AXIS = YAxis.ONE;
    private static final PieceType PIECE_TYPE = PieceType.PAWN;
    private static final PieceColor PIECE_COLOR = PieceColor.WHITE;

    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDao();
    }

    @AfterEach
    void tearDown() throws SQLException {
        pieceDao.delete(X_AXIS, Y_AXIS, PIECE_TYPE, PIECE_COLOR);
    }

    @DisplayName("데이터베이스 연결을 할 수 있다.")
    @Test
    void connection() {
        // when
        Connection actual = pieceDao.getConnection();

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("데이터베이스에서 기물 목록을 받아와 Board로 반환한다.")
    @Test
    void findAll() throws SQLException {
        // when
        Board actual = pieceDao.findAll();

        // then
        assertThat(actual).isInstanceOf(Board.class);
    }

    @DisplayName("데이터베이스에 기물을 하나 추가한다.")
    @Test
    void addPiece() throws SQLException {
        // when & then
        pieceDao.create(X_AXIS, Y_AXIS, PIECE_TYPE, PIECE_COLOR);
    }

    @DisplayName("데이터베이스에서 기물을 하나 제거한다.")
    @Test
    void removePiece() throws SQLException {
        // given
        pieceDao.create(X_AXIS, Y_AXIS, PIECE_TYPE, PIECE_COLOR);

        // when & then
        pieceDao.delete(X_AXIS, Y_AXIS, PIECE_TYPE, PIECE_COLOR);
    }
}
