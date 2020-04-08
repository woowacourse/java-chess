package chess.domain.piece.state.move;

import chess.domain.piece.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MoveTypeTest {

    @ParameterizedTest
    @DisplayName("#update() : should return MoveType as to Piece from, to")
    @MethodSource({"getCasesForUpdate"})
    void update(Piece from, Piece to, MoveType expected) {
        MoveType moveType = MoveType.INITIALIZED;

        MoveType actual = moveType.update(from, to);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForUpdate() {
        return Stream.of(
                Arguments.of(
                        new InitializedPawn.InitializedPawnBuilder(
                                "p",
                                Position.of(1, 2),
                                Team.WHITE,
                                new ArrayList<>(),
                                new Score(1))
                                .build(),
                        new InitializedPawn.InitializedPawnBuilder(
                                "p",
                                Position.of(1, 2),
                                Team.WHITE,
                                new ArrayList<>(),
                                new Score(1))
                                .build(),
                        MoveType.STAYED),
                Arguments.of(
                        new InitializedPawn.InitializedPawnBuilder(
                                "p",
                                Position.of(1, 2),
                                Team.WHITE,
                                new ArrayList<>(),
                                new Score(1))
                                .build(),
                        Blank.of(),
                        MoveType.MOVED),
                Arguments.of(
                        new InitializedPawn.InitializedPawnBuilder(
                                "p",
                                Position.of(1, 2),
                                Team.WHITE,
                                new ArrayList<>(),
                                new Score(1))
                                .build(),
                        new King.KingBuilder(
                                "k",
                                Position.of(2, 3),
                                Team.BLACK,
                                new ArrayList<>(),
                                new Score(0))
                                .build(),
                        MoveType.ATTACKED_KING),
                Arguments.of(
                        new InitializedPawn.InitializedPawnBuilder(
                                "p",
                                Position.of(1, 2),
                                Team.WHITE,
                                new ArrayList<>(),
                                new Score(1))
                                .build(),
                        new MovedPawn.MovedPawnBuilder(
                                "p",
                                Position.of(2, 3),
                                Team.BLACK,
                                new ArrayList<>(),
                                new Score(1))
                                .build(),
                        MoveType.ATTACKED_SUBORDINATE)
        );
    }
}