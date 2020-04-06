package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @ParameterizedTest
    @MethodSource("pieceConstructorSourceAndExpected")
    void 팀에_해당하는_표현문자(Piece piece, String expected) {
        assertThat(piece.getAcronym()).isEqualTo(expected);
    }

    private static Stream<Arguments> pieceConstructorSourceAndExpected() {
        return Stream.of(
            Arguments.of(new Pawn(Team.BLACK), "P"),
            Arguments.of(new Bishop(Team.BLACK), "B"),
            Arguments.of(new Rook(Team.BLACK), "R"),
            Arguments.of(new Queen(Team.BLACK), "Q"),
            Arguments.of(new King(Team.BLACK), "K"),
            Arguments.of(new Knight(Team.BLACK), "N"),

            Arguments.of(new Pawn(Team.WHITE), "p"),
            Arguments.of(new Bishop(Team.WHITE), "b"),
            Arguments.of(new Rook(Team.WHITE), "r"),
            Arguments.of(new Queen(Team.WHITE), "q"),
            Arguments.of(new King(Team.WHITE), "k"),
            Arguments.of(new Knight(Team.WHITE), "n")
        );
    }

    @Test
    void isSameTeam() {
        assertThat(new Pawn(Team.WHITE).isSameTeam(new Bishop(Team.WHITE)))
            .isTrue();
        assertThat(new Pawn(Team.WHITE).isSameTeam(new Bishop(Team.BLACK)))
            .isFalse();
    }

    @Test
    void isKing() {
        assertThat(new King(Team.WHITE).isKing()).isTrue();
        assertThat(new Queen(Team.WHITE).isKing()).isFalse();
    }
}
