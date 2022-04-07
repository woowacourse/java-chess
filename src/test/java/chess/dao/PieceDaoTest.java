package chess.dao;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.single.King;
import chess.testutil.H2Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private static PieceDao pieceDao;
    private static Connection connection;
    private Position position;
    private Piece pawn;

    @BeforeAll
    static void beforeAll() throws SQLException {
        H2Connection.setUpTable();
        connection = H2Connection.getConnection();
        connection.setAutoCommit(false);
        pieceDao = new PieceDao(connection);
    }

    @AfterEach
    void afterEach() throws SQLException {
        connection.rollback();
    }

    @BeforeEach
    void setUp() {
        H2Connection.setUpTable();
        pieceDao = new PieceDao(H2Connection.getConnection());
        position = Position.of('a', '1');
        pawn = new Piece(WHITE, new Pawn(WHITE));
    }

    @Test
    @DisplayName("piece를 저장하고 저장 확인")
    void saveAndFindPiece() {
        // when
        int[] result = pieceDao.savePieces(Map.of(position, pawn,
                Position.of('a', '2'), new Piece(WHITE, new King())));
        Piece piece = pieceDao.findAllPieces().get(position);

        // then
        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(piece.color()).isEqualTo(WHITE),
                () -> assertThat(piece.name()).isEqualTo("pawn")
        );
    }

    @Test
    @DisplayName("저장한 piece를 삭제")
    void saveAndDelecePiece() {
        // given
        pieceDao.savePieces(Map.of(position, pawn,
                Position.of('a', '2'), new Piece(WHITE, new King())));

        // when
        int result = pieceDao.deletePiece(position);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("저장한 piece의 위치를 업데이트")
    void saveAndUpdatePiecePosition() {
        // given
        pieceDao.savePieces(Map.of(position, pawn));
        Position movePosition = Position.of('a', '2');

        // when
        int result = pieceDao.updatePiecePosition(position, movePosition);
        Piece piece = pieceDao.findAllPieces().get(movePosition);

        // then
        assertAll(
                () -> assertThat(result).isEqualTo(1),
                () -> assertThat(piece.color()).isEqualTo(WHITE),
                () -> assertThat(piece.name()).isEqualTo("pawn")
        );
    }

    @Test
    @DisplayName("저장한 piece 정보를 업데이트")
    void saveAndUpdatePiece() {
        // given
        pieceDao.savePieces(Map.of(position, pawn));
        Piece changePiece = new Piece(WHITE, new Queen());

        // when
        int result = pieceDao.updatePiece(position, changePiece);
        Piece piece = pieceDao.findAllPieces().get(position);

        // then
        assertAll(
                () -> assertThat(result).isEqualTo(1),
                () -> assertThat(piece.color()).isEqualTo(WHITE),
                () -> assertThat(piece.name()).isEqualTo("queen")
        );
    }

    @Test
    @DisplayName("저장한 모든 piece를 삭제")
    void saveAndDeleceAllPiece() {
        // given
        pieceDao.savePieces(PieceFactory.createNewChessBoard());

        // when
        int result = pieceDao.deleteAllPiece();

        // then
        assertThat(result).isEqualTo(32);
    }
}
