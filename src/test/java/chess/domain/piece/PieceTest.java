package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @ParameterizedTest
    @MethodSource("pieceConstructorSourceAndExpected")
    void 팀에_해당하는_표현문자(Piece piece, String expected) {
        assertThat(piece.getAcronym()).isEqualTo(expected);
    }

    @Test
    void isSameTeam() {
        assertThat(new Pawn(Team.WHITE).isSameTeam(new Bishop(Team.WHITE)))
            .isTrue();
        assertThat(new Pawn(Team.WHITE).isSameTeam(new Bishop(Team.BLACK)))
            .isFalse();
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
    void isKing() {
        assertThat(new King(Team.WHITE).isKing()).isTrue();
        assertThat(new Queen(Team.WHITE).isKing()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"true:0.5", "false:1.0"}, delimiter = ':')
    void 인자있는_getScore_폰인_경우(boolean mustPawnScoreDowngraded, double expected) {
        assertThat(new Pawn(Team.WHITE).getScore(mustPawnScoreDowngraded)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("pieceTypesExceptPawn")
    void 인자있는_score_폰이_아닌_경우_예외처리(Piece piece) {
        assertThatThrownBy(() -> {
            piece.getScore(true);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    private static Stream<Arguments> pieceTypesExceptPawn() {
        return Stream.of(
            Arguments.of(new Bishop(Team.BLACK)),
            Arguments.of(new King(Team.WHITE)),
            Arguments.of(new Knight(Team.BLACK)),
            Arguments.of(new Queen(Team.WHITE)),
            Arguments.of(new Rook(Team.BLACK))
        );
    }

    @ParameterizedTest
    @MethodSource("pieceTypesExceptPawnWithScore")
    void 인자없는_score_폰이_아닌_경우(Piece piece, double expected) {
        assertThat(piece.getScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> pieceTypesExceptPawnWithScore() {
        return Stream.of(
            Arguments.of(new Bishop(Team.WHITE), 3.0),
            Arguments.of(new King(Team.BLACK), 0.0),
            Arguments.of(new Knight(Team.WHITE), 2.5),
            Arguments.of(new Queen(Team.BLACK), 9.0),
            Arguments.of(new Rook(Team.WHITE), 5.0)
        );
    }

    @Test
    void 인자없는_score_폰인_경우_예외처리() {
        assertThatThrownBy(() -> {
            new Pawn(Team.WHITE).getScore();
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
