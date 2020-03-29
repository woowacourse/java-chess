package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Placeable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
public class PlaceableTest {
    @ParameterizedTest
    @MethodSource("pieceConstructorSourceAndExpected")
    void 팀에_해당하는_표현문자(Team team, PieceType pieceType, String expected) {
        Placeable placeable = new Piece(team, pieceType);
        assertThat(placeable.getAcronym()).isEqualTo(expected);
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

    @Test
    void findRoute() {
    }

    @ParameterizedTest
    @MethodSource("createPieceAndTeam")
    void isTeam(Placeable placeable, Team team, boolean expected) {
        assertThat(placeable.isTeam(team)).isEqualTo(expected);
    }

    private static Stream<Arguments> createPieceAndTeam() {
        return Stream.of(
                Arguments.of(new Piece(Team.BLACK, PieceType.BISHOP), Team.BLACK, true),
                Arguments.of(new Piece(Team.BLACK, PieceType.BISHOP), Team.WHITE, false),

                Arguments.of(new Piece(Team.WHITE, PieceType.BISHOP), Team.WHITE, true),
                Arguments.of(new Piece(Team.WHITE, PieceType.BISHOP), Team.BLACK, false)
        );
    }

    @ParameterizedTest
    @MethodSource("createPieceAndScore")
    void getScore(Placeable placeable, double score) {
        assertThat(placeable.getScore()).isEqualTo(score);
    }

    private static Stream<Arguments> createPieceAndScore() {
        return Stream.of(
                Arguments.of(new Piece(Team.BLACK, PieceType.PAWN), 1.0),
                Arguments.of(new Piece(Team.BLACK, PieceType.KNIGHT), 2.5),
                Arguments.of(new Piece(Team.BLACK, PieceType.BISHOP), 3.0),
                Arguments.of(new Piece(Team.BLACK, PieceType.ROOK), 5.0),
                Arguments.of(new Piece(Team.BLACK, PieceType.QUEEN), 9.0),
                Arguments.of(new Piece(Team.BLACK, PieceType.KING), 0.0)
        );
    }
}
