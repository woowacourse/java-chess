package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.model.piece.type.Bishop;
import chess.model.piece.type.InitialPawn;
import chess.model.piece.type.King;
import chess.model.piece.type.Knight;
import chess.model.piece.type.Pawn;
import chess.model.piece.type.Queen;
import chess.model.piece.type.Rook;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceScoreConverterTest {

    @ParameterizedTest(name = "클래스가 {0}일 때 그에 맞는 PieceScore를 반환한다")
    @DisplayName("convert() 테스트")
    @MethodSource("providePieceClass")
    void convert_givenClass_thenReturnPieceScore(final Class<? extends Piece> pieceType, final PieceScore expected) {
        final PieceScore actual = PieceScoreConverter.convert(pieceType);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePieceClass() {
        return Stream.of(
                Arguments.of(Bishop.class, PieceScore.BISHOP), Arguments.of(Pawn.class, PieceScore.PAWN),
                Arguments.of(King.class, PieceScore.ZERO), Arguments.of(Knight.class, PieceScore.KNIGHT),
                Arguments.of(Queen.class, PieceScore.QUEEN), Arguments.of(Rook.class, PieceScore.ROOK)
        );
    }
}
