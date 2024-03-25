package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessBoardGeneratorTest {

    @DisplayName("컬러를 입력하면 해당 컬러의 피스들을 만들어준다")
    @Test
    void generatePiecesByColor() {
        // when
        ChessBoardGenerator chessBoardGenerator = ChessBoardGenerator.getInstance();
        Map<Position, Piece> pieces = chessBoardGenerator.generate();

        // then
        assertThat(pieces).hasSize(32);

        assertAll(
                () -> assertThat(countPiece(pieces, PieceType.WHITE_PAWN)).isEqualTo(8),
                () -> assertThat(countPiece(pieces, PieceType.BLACK_PAWN)).isEqualTo(8),
                () -> assertThat(countPiece(pieces, PieceType.WHITE_ROOK)).isEqualTo(2),
                () -> assertThat(countPiece(pieces, PieceType.BLACK_ROOK)).isEqualTo(2),
                () -> assertThat(countPiece(pieces, PieceType.WHITE_KNIGHT)).isEqualTo(2),
                () -> assertThat(countPiece(pieces, PieceType.BLACK_KNIGHT)).isEqualTo(2),
                () -> assertThat(countPiece(pieces, PieceType.WHITE_BISHOP)).isEqualTo(2),
                () -> assertThat(countPiece(pieces, PieceType.BLACK_BISHOP)).isEqualTo(2),
                () -> assertThat(countPiece(pieces, PieceType.WHITE_KING)).isEqualTo(1),
                () -> assertThat(countPiece(pieces, PieceType.BLACK_KING)).isEqualTo(1),
                () -> assertThat(countPiece(pieces, PieceType.WHITE_QUEEN)).isEqualTo(1),
                () -> assertThat(countPiece(pieces, PieceType.BLACK_QUEEN)).isEqualTo(1)
        );
    }

    private int countPiece(Map<Position, Piece> pieces, PieceType type) {
        return (int) pieces.values().stream()
                .filter(piece -> piece.isType(type))
                .count();
    }
}
