package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.piece.type.Pawn;
import domain.piece.type.restricted.King;
import domain.piece.type.restricted.Knight;
import domain.piece.type.unrestricted.Bishop;
import domain.piece.type.unrestricted.Queen;
import domain.piece.type.unrestricted.Rook;

class PieceTest {

    @ParameterizedTest(name = "기물마다 해당되는 점수를 반환한다. {0} == {1}")
    @MethodSource("provideScoreList")
    void getScore(Piece piece, double expected) {
        double result = piece.getScore().getScore();

        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScoreList() {
        return Stream.of(
                Arguments.of(new King(Camp.WHITE), 0),
                Arguments.of(new Pawn(Camp.WHITE), 1),
                Arguments.of(new Knight(Camp.WHITE), 2.5),
                Arguments.of(new Bishop(Camp.WHITE), 3),
                Arguments.of(new Rook(Camp.WHITE),  5),
                Arguments.of(new Queen(Camp.WHITE), 9)
        );
    }

}
