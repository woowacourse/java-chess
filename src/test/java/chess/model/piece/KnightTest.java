package chess.model.piece;

import chess.model.ChessGame;
import chess.model.MoveType;
import chess.model.Team;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.command.Move;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {

    @Test
    @DisplayName("나이트의 진행 방향이 맞는다면 true 반환")
    void isCorrectMovable() {
        Knight knight = new Knight(Team.WHITE);
        Position source = Position.from("a1");
        Position target = Position.from("b3");

        assertThat(knight.isMovable(source, target, MoveType.MOVE)).isTrue();
    }

    @Test
    @DisplayName("나이트의 진행 방향이 틀리다면 false 반환")
    void isNotCorrectMovable() {
        Knight knight = new Knight(Team.WHITE);
        Position source = Position.from("a1");
        Position target = Position.from("b2");

        assertThat(knight.isMovable(source, target, MoveType.MOVE)).isFalse();
    }

    @Test
    @DisplayName("나이트의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKnightTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Knight(Team.BLACK));
        boardMap.put(Position.from("b6"), new Pawn(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 b6"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("나이트가 target위치로 진행할때 방해물이 있으면 넘어서 진행한다.")
    void moveKnightTest2() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move g1 h3"), new Turn(Team.WHITE))
        );
    }

    @Test
    @DisplayName("나이트의 target위치에 아군 말이 있으면 예외처리")
    void moveFailureKnightTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Knight(Team.WHITE));
        boardMap.put(Position.from("c7"), new Pawn(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a8 c7"), new Turn(Team.WHITE))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
