package chess.domain.piece;

import chess.domain.ui.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.piece.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookTest {

    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#move() : should return Piece as to Position 'from' and 'to'")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        //todo: check convention
        Rook rook = (Rook) PieceFactory.createPiece(Rook.class, from, team);

        Board board = RunningBoard.initiaize(userInterface);
        Piece moved = rook.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from' and 'to'")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        //todo: check convention, 타입캐스팅 해도 될 지
        Rook rook = (Rook) PieceFactory.createPiece(Rook.class, from, team);

        Board board = RunningBoard.initiaize(userInterface);


        assertThatThrownBy(() -> rook.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        Rook rook = (Rook) PieceFactory.createPiece(Rook.class, from, team);
        Board board = RunningBoard.initiaize(userInterface);
        boolean hasHindrance = rook.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(1, 2),
                        Position.of(1, 6),
                        team,
                        PieceFactory.createPiece(Rook.class, Position.of(1, 6), team)),
                Arguments.of(Position.of(1, 3),
                        Position.of(8, 3),
                        team,
                        PieceFactory.createPiece(Rook.class, Position.of(8, 3), team))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(1, 1), Position.of(1, 1), team),
                Arguments.of(Position.of(1, 1), Position.of(1, 3), team),
                Arguments.of(Position.of(1, 1), Position.of(3, 1), team),
                Arguments.of(Position.of(1, 1), Position.of(1, 2), team),
                Arguments.of(Position.of(1, 2), Position.of(2, 3), team)

        );
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 1), Position.of(1, 8), Team.WHITE, true),
                Arguments.of(Position.of(1, 2), Position.of(1, 8), Team.WHITE, true),
                Arguments.of(Position.of(1, 1), Position.of(1, 2), Team.WHITE, false),
                Arguments.of(Position.of(1, 1), Position.of(2, 1), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(1, 7), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(1, 6), Team.WHITE, false)
        );
    }
}