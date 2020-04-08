package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;
import chess.domain.ui.UserInterface;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InitializedPawnTest {

    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        InitializedPawn initializedPawn = (InitializedPawn) PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, from, team);
        Board board = RunningBoard.initiaize(userInterface);
        boolean hasHindrance = initializedPawn.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should return Piece as to team and Position 'to'")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Team team, Position to) {
        Piece initializedPawn = PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, Position.of(1,2), team);

        Board board = RunningBoard.initiaize(userInterface);

        Piece moved = initializedPawn.move(to, board);
        assertThat(moved).isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to team and Position 'to'")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Team team, Position from, Position to) {
        Piece initializedPawn = PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, from, team);

        Board board = RunningBoard.initiaize(userInterface);

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