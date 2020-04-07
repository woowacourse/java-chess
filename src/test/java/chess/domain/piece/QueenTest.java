package chess.domain.piece;

import chess.domain.piece.state.move.MoveType;
import chess.domain.ui.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
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

class QueenTest {

    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        Piece queen = PieceFactory.createInitializedPiece(Queen.class, from, team);

        Board board = RunningBoard.initiaize(userInterface);
        Piece moved = queen.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        Piece queen = PieceFactory.createInitializedPiece(Queen.class, from, team);

        Board board = RunningBoard.initiaize(userInterface);

        assertThatThrownBy(() -> queen.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 2),
                        Position.of(4, 6),
                        team,
                        PieceFactory.createMovedPiece(Queen.class, Position.of(4, 6), team, MoveType.MOVED)),
                Arguments.of(Position.of(4, 2),
                        Position.of(1, 5),
                        team,
                        PieceFactory.createMovedPiece(Queen.class, Position.of(1, 5), team, MoveType.MOVED)),
                Arguments.of(Position.of(4, 2),
                        Position.of(8, 6),
                        team,
                        PieceFactory.createMovedPiece(Queen.class, Position.of(8, 6), team, MoveType.MOVED)),
                Arguments.of(Position.of(4, 6),
                        Position.of(4, 3),
                        team,
                        PieceFactory.createMovedPiece(Queen.class, Position.of(4, 3), team, MoveType.MOVED))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 1), Position.of(4, 1), team),
                Arguments.of(Position.of(4, 1), Position.of(4, 3), team),
                Arguments.of(Position.of(4, 1), Position.of(4, 2), team),
                Arguments.of(Position.of(4, 2), Position.of(6, 3), team)

        );
    }
}