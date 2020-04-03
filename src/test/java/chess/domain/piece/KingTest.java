package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.move.MoveType;
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

class KingTest {
    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#move() : should return King as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        //todo: check convention
        King king = (King) PieceFactory.createInitializedPiece(King.class, from, team);

        Board board = RunningBoard.initiaize(userInterface);
        Piece moved = king.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        //todo: check convention, 타입캐스팅 해도 될 지
        King kin = (King) PieceFactory.createInitializedPiece(King.class, from, team);

        Board board = RunningBoard.initiaize(userInterface);

        assertThatThrownBy(() -> kin.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(5, 4),
                        Position.of(5, 5),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(5, 5), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 5),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(6, 5), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 4),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(6, 4), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 3),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(6, 3), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(5, 3),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(5, 3), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(4, 3),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(4, 3), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(4, 4),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(4, 4), team, MoveType.MOVED)),

                Arguments.of(Position.of(5, 4),
                        Position.of(4, 5),
                        team,
                        PieceFactory.createMovedPiece(King.class, Position.of(4, 5), team, MoveType.MOVED))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(5, 1), Position.of(5, 1), team),
                Arguments.of(Position.of(5, 2), Position.of(5, 4), team),
                Arguments.of(Position.of(5, 1), Position.of(5, 2), team)

        );
    }
}