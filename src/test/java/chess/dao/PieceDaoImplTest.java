package chess.dao;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.pawn.Pawn;
import chess.testutil.H2Connection;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoImplTest {

    private PieceDao pieceDao;
    private Position position;
    private Piece pawn;

    @BeforeEach
    void setUp() {
        H2Connection.setUpTable();
        pieceDao = new PieceDaoImpl(H2Connection.getConnection());
        position = Position.of('a', '1');
        pawn = new Piece(WHITE, new Pawn(WHITE));
    }

    @Test
    @DisplayName("piece를 저장하고 저장 확인")
    void test() {
        // when
        pieceDao.savePieces(Map.of(position, pawn));
        Piece result = pieceDao.findAllPieces().get(position);

        // then
        assertAll(
                () -> assertThat(result.color()).isEqualTo(WHITE),
                () -> assertThat(result.name()).isEqualTo("pawn")
        );
    }

    @Test
    @DisplayName("저장한 piece를 삭제")
    void saveAndDelecePiece() {
        // given
        pieceDao.savePieces(Map.of(position, pawn));

        // when
        pieceDao.deletePiece(position);

        // then
        assertThat(pieceDao.findAllPieces()).isEmpty();
    }

    @Test
    @DisplayName("저장한 piece의 위치를 업데이트")
    void saveAndUpdatePiecePosition() {
        // given
        pieceDao.savePieces(Map.of(position, pawn));
        Position movePosition = Position.of('a', '2');

        // when
        pieceDao.updatePiecePosition(position, movePosition);
        Piece result = pieceDao.findAllPieces().get(movePosition);

        // then
        assertAll(
                () -> assertThat(result.color()).isEqualTo(WHITE),
                () -> assertThat(result.name()).isEqualTo("pawn")
        );
    }

    @Test
    @DisplayName("저장한 piece 정보를 업데이트")
    void saveAndUpdatePiece() {
        // given
        pieceDao.savePieces(Map.of(position, pawn));
        Piece changePiece = new Piece(WHITE, new Queen());

        // when
        pieceDao.updatePiece(position, changePiece);
        Piece result = pieceDao.findAllPieces().get(position);

        // then
        assertAll(
                () -> assertThat(result.color()).isEqualTo(WHITE),
                () -> assertThat(result.name()).isEqualTo("queen")
        );
    }
}
