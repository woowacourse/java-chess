package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @ParameterizedTest
    @MethodSource("pieceConstructorSourceAndExpected")
    void 팀에_해당하는_표현문자(Team team, PieceType pieceType, String expected) {
        Piece piece = new Piece(team, pieceType);
        assertThat(piece.getAcronym()).isEqualTo(expected);
    }

    private static Stream<Arguments> pieceConstructorSourceAndExpected() {
        return Stream.of(
            Arguments.of(Team.BLACK, PieceType.PAWN, "P"),
            Arguments.of(Team.BLACK, PieceType.BISHOP, "B"),
            Arguments.of(Team.BLACK, PieceType.ROOK, "R"),
            Arguments.of(Team.BLACK, PieceType.QUEEN, "Q"),
            Arguments.of(Team.BLACK, PieceType.KING, "K"),
            Arguments.of(Team.BLACK, PieceType.KNIGHT, "N"),

            Arguments.of(Team.WHITE, PieceType.PAWN, "p"),
            Arguments.of(Team.WHITE, PieceType.BISHOP, "b"),
            Arguments.of(Team.WHITE, PieceType.ROOK, "r"),
            Arguments.of(Team.WHITE, PieceType.QUEEN, "q"),
            Arguments.of(Team.WHITE, PieceType.KING, "k"),
            Arguments.of(Team.WHITE, PieceType.KNIGHT, "n")
        );
    }
}