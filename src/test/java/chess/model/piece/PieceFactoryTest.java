package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.nonsliding.King;
import chess.model.piece.nonsliding.Knight;
import chess.model.piece.pawn.InitialBlackPawn;
import chess.model.piece.sliding.Bishop;
import chess.model.piece.sliding.Queen;
import chess.model.piece.sliding.Rook;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceFactoryTest {

    @ParameterizedTest(name = "create()는 {0}일 때, {1} 타입 클래스를 생성한다.")
    @MethodSource("initializePieceType")
    void create_givenPieceTypeAndColor_thenReturnPiece(final PieceType pieceType, final Class<Piece> result) {
        assertThat(PieceFactory.create(PieceColor.BLACK, pieceType)).isExactlyInstanceOf(result);
    }

    private static Stream<Arguments> initializePieceType() {
        return Stream.of(
                Arguments.of(PieceType.BLACK_PAWN, InitialBlackPawn.class),
                Arguments.of(PieceType.KING, King.class),
                Arguments.of(PieceType.QUEEN, Queen.class),
                Arguments.of(PieceType.ROOK, Rook.class),
                Arguments.of(PieceType.KNIGHT, Knight.class),
                Arguments.of(PieceType.BISHOP, Bishop.class)
        );
    }

}
