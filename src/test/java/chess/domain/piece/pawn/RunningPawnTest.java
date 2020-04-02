package chess.domain.piece.pawn;

import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunningPawnTest {
    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#move() : should return Piece as to team and Position 'to'")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Team team, Position from, Position to, Piece expected) {
        //todo: check convention
        MovedPawn runningPawn = (MovedPawn) PieceFactory.createPiece(MovedPawn.class, from, team);

        Board board = InitializedBoard.initiaize(userInterface);

        Piece moved = runningPawn.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to team and Position 'to'")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Team team, Position from, Position to) {
        //todo: check convention
        MovedPawn initializedPawn = (MovedPawn) PieceFactory.createPiece(MovedPawn.class, from, team);

        Board board = InitializedBoard.initiaize(userInterface);


        assertThatThrownBy(() -> initializedPawn.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        MovedPawn runningPawn = (MovedPawn) PieceFactory.createPiece(MovedPawn.class, from, team);
        Board board = InitializedBoard.initiaize(userInterface);
        boolean hasHindrance = runningPawn.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        return Stream.of(
                Arguments.of(Team.WHITE,
                        Position.of(1, 2),
                        Position.of(1, 3),
                        PieceFactory.createPiece(MovedPawn.class, Position.of(1, 3), Team.WHITE))
        );
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 3), Position.of(1, 4), Team.WHITE, false),
                Arguments.of(Position.of(1, 7), Position.of(1, 8), Team.WHITE, true),
                Arguments.of(Position.of(1, 1), Position.of(1, 2), Team.WHITE, true)
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        return Stream.of(
                Arguments.of(Team.WHITE, Position.of(1, 1), Position.of(1, 1)),
                Arguments.of(Team.WHITE, Position.of(1, 3), Position.of(1, 2)),
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(1, 4)),
                Arguments.of(Team.WHITE, Position.of(1, 7), Position.of(1, 8)),
                Arguments.of(Team.WHITE, Position.of(1, 1), Position.of(1, 2)),
                Arguments.of(Team.WHITE, Position.of(1, 2), Position.of(2, 3))
        );
    }
}