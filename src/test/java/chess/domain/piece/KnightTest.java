package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        Piece knight = PieceFactory.createInitializedPiece(PieceType.KNIGHT, from, team);

        Board board = RunningBoard.initiaize();
        Piece moved = knight.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        Piece knight = PieceFactory.createInitializedPiece(PieceType.KNIGHT, from, team);

        Board board = RunningBoard.initiaize();

        assertThatThrownBy(() -> knight.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        return Stream.of(
                Arguments.of(Position.of(4, 4),
                        Position.of(5, 6),
                        Team.WHITE,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(5, 6), Team.WHITE, MoveType.MOVED)),
                Arguments.of(Position.of(4, 4),
                        Position.of(6, 5),
                        Team.WHITE,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(6, 5), Team.WHITE, MoveType.MOVED)),
                Arguments.of(Position.of(4, 4),
                        Position.of(6, 3),
                        Team.WHITE,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(6, 3), Team.WHITE, MoveType.MOVED)),
                Arguments.of(Position.of(4, 4),
                        Position.of(5, 2),
                        Team.BLACK,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(5, 2), Team.BLACK, MoveType.ATTACKED_SUBORDINATE)),
                Arguments.of(Position.of(4, 4),
                        Position.of(3, 2),
                        Team.BLACK,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(3, 2), Team.BLACK, MoveType.ATTACKED_SUBORDINATE)),
                Arguments.of(Position.of(4, 4),
                        Position.of(2, 3),
                        Team.WHITE,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(2, 3), Team.WHITE, MoveType.MOVED)),
                Arguments.of(Position.of(4, 4),
                        Position.of(2, 5),
                        Team.WHITE,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(2, 5), Team.WHITE, MoveType.MOVED)),
                Arguments.of(Position.of(4, 4),
                        Position.of(3, 6),
                        Team.WHITE,
                        PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(3, 6), Team.WHITE, MoveType.MOVED))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 4), Position.of(4, 4), team),
                Arguments.of(Position.of(4, 4), Position.of(5, 7), team),
                Arguments.of(Position.of(4, 4), Position.of(4, 5), team),
                Arguments.of(Position.of(4, 4), Position.of(5, 4), team),
                Arguments.of(Position.of(4, 4), Position.of(5, 5), team)

        );
    }
}