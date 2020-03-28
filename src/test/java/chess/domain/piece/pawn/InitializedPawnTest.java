package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.move.*;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InitializedPawnTest {

    private List<CanNotMoveStrategy> initializedPawnCanNotMoveStrategies = Arrays.asList(
            new IsStayed(),
            new IsNotForward(),
            new InitializedPawnCanNotReach(InitializedPawn.MAX_DISTANCE),
            new InitializedPawnHasHindrance(),
            new IsAttackingSameTeam(),
            new IsDiagonalWithoutAttack()
    );

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        InitializedPawn initializedPawn = new InitializedPawn("testInitializedPawn", from, team, new ArrayList<>());
        Board board = ChessBoard.initiaize();
        boolean hasHindrance = initializedPawn.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should return Piece as to team and Position 'to'")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Team team, Position to) {

        //todo: check convention
        InitializedPawn initializedPawn = new InitializedPawn("testInitializedPawn",
                Position.of(1, 2),
                team,
                initializedPawnCanNotMoveStrategies);

        Board board = ChessBoard.initiaize();

        Piece moved = initializedPawn.move(to, board);
        assertThat(moved).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to team and Position 'to'")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Team team, Position from, Position to) {

        //todo: check convention
        InitializedPawn initializedPawn = new InitializedPawn("testInitializedPawn",
                from,
                team,
                initializedPawnCanNotMoveStrategies);

        Board board = ChessBoard.initiaize();


        assertThatThrownBy(() -> initializedPawn.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 2), Position.of(1, 3), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(1, 4), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(2, 3), Team.WHITE, false),
                Arguments.of(Position.of(1, 5), Position.of(1, 7), Team.WHITE, true),
                Arguments.of(Position.of(1, 6), Position.of(1, 7), Team.WHITE, true)
        );
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        return Stream.of(
                Arguments.of(Team.WHITE, Position.of(1, 3)),
                Arguments.of(Team.WHITE, Position.of(1, 4))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        return Stream.of(
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(1, 5)),
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(2, 3)),
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(1, 2)),
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(2, 2)),
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(1, 1)),
                Arguments.of(Team.WHITE, Position.of(1, 1), Position.of(1, 2))
        );
    }


}