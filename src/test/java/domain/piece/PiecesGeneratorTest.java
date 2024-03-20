package domain.piece;

import domain.piece.type.Bishop;
import domain.piece.type.King;
import domain.piece.type.Knight;
import domain.piece.type.Pawn;
import domain.piece.type.Queen;
import domain.piece.type.Rook;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PiecesGeneratorTest {

    @DisplayName("컬러를 입력하면 해당 컬러의 피스들을 만들어준다")
    @Test
    void generatePiecesByColor() {
        // when
        PiecesGenerator piecesGenerator = PiecesGenerator.getInstance();
        Map<Position, Piece> pieces = piecesGenerator.generate();

        // then
        assertThat(pieces).hasSize(32);

        assertThat(countPiece(pieces, Pawn.class)).isEqualTo(16);
        assertThat(countPiece(pieces, Rook.class)).isEqualTo(4);
        assertThat(countPiece(pieces, Knight.class)).isEqualTo(4);
        assertThat(countPiece(pieces, Bishop.class)).isEqualTo(4);
        assertThat(countPiece(pieces, King.class)).isEqualTo(2);
        assertThat(countPiece(pieces, Queen.class)).isEqualTo(2);

        assertThat(findPiece(pieces, Pawn.class, PieceColor.WHITE).keySet()).containsAll(PiecesGenerator.WHITE_PAWN_POSITIONS);
        assertThat(findPiece(pieces, Rook.class, PieceColor.WHITE).keySet()).containsAll(PiecesGenerator.WHITE_ROOK_POSITION);
        assertThat(findPiece(pieces, Knight.class, PieceColor.WHITE).keySet()).containsAll(PiecesGenerator.WHITE_KNIGHT_POSITION);
        assertThat(findPiece(pieces, Bishop.class, PieceColor.WHITE).keySet()).containsAll(PiecesGenerator.WHITE_BISHOP_POSITION);
        assertThat(findPiece(pieces, King.class, PieceColor.WHITE).keySet()).containsExactly(PiecesGenerator.WHITE_KING_POSITION);
        assertThat(findPiece(pieces, Queen.class, PieceColor.WHITE).keySet()).containsExactly(PiecesGenerator.WHITE_QUEEN_POSITION);

        assertThat(findPiece(pieces, Pawn.class, PieceColor.BLACK).keySet()).containsAll(PiecesGenerator.BLACK_PAWN_POSITIONS);
        assertThat(findPiece(pieces, Rook.class, PieceColor.BLACK).keySet()).containsAll(PiecesGenerator.BLACK_ROOK_POSITION);
        assertThat(findPiece(pieces, Knight.class, PieceColor.BLACK).keySet()).containsAll(PiecesGenerator.BLACK_KNIGHT_POSITION);
        assertThat(findPiece(pieces, Bishop.class, PieceColor.BLACK).keySet()).containsAll(PiecesGenerator.BLACK_BISHOP_POSITION);
        assertThat(findPiece(pieces, King.class, PieceColor.BLACK).keySet()).containsExactly(PiecesGenerator.BLACK_KING_POSITION);
        assertThat(findPiece(pieces, Queen.class, PieceColor.BLACK).keySet()).containsExactly(PiecesGenerator.BLACK_QUEEN_POSITION);
    }

    private int countPiece(Map<Position, Piece> pieces, Class<? extends Piece> pieceClass) {
        return (int) pieces.values().stream()
                .filter(pieceClass::isInstance)
                .count();
    }

    private Map<Position, Piece> findPiece(Map<Position, Piece> pieces, Class<? extends Piece> pieceClass, PieceColor color) {
        return pieces.entrySet().stream()
                .filter(piece -> piece.getValue().getClass() == pieceClass)
                .filter(piece -> piece.getValue().isColor(color))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
