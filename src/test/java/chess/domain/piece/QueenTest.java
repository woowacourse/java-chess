package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {
    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        Piece queen = PieceFactory.createInitializedPiece(PieceType.QUEEN, from, team);

        Board board = RunningBoard.initiaize();
        Piece moved = queen.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        Piece queen = PieceFactory.createInitializedPiece(PieceType.QUEEN, from, team);

        Board board = RunningBoard.initiaize();

        assertThatThrownBy(() -> queen.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("#calculateScore() : should return score of Queen")
    void calculateScore() {
        //given
        Piece queen = PieceFactory.createInitializedPiece(PieceType.QUEEN, Position.of(5, 5), Team.WHITE);
        Board board = RunningBoard.initiaize();
        //when
        Score score = queen.calculateScore(board);
        //then
        assertThat(score).isEqualTo(PieceType.QUEEN.getScore());
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 2),
                        Position.of(4, 6),
                        team,
                        PieceFactory.createMovedPiece(PieceType.QUEEN, Position.of(4, 6), team, MoveType.MOVED)),
                Arguments.of(Position.of(4, 2),
                        Position.of(1, 5),
                        team,
                        PieceFactory.createMovedPiece(PieceType.QUEEN, Position.of(1, 5), team, MoveType.MOVED)),
                Arguments.of(Position.of(4, 2),
                        Position.of(8, 6),
                        team,
                        PieceFactory.createMovedPiece(PieceType.QUEEN, Position.of(8, 6), team, MoveType.MOVED)),
                Arguments.of(Position.of(4, 6),
                        Position.of(4, 3),
                        team,
                        PieceFactory.createMovedPiece(PieceType.QUEEN, Position.of(4, 3), team, MoveType.MOVED))
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