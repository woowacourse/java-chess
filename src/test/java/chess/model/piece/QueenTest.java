package chess.model.piece;

import chess.model.ChessGame;
import chess.model.MoveType;
import chess.model.Team;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.command.Move;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class QueenTest {

    @Test
    @DisplayName("퀸의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Queen queen = new Queen(Team.WHITE);
        Position source = Position.from("a1");
        Position targetDiagonal = Position.from("f6");
        Position targetVertical = Position.from("a5");
        Position targetHorizontal = Position.from("f1");

        assertAll(
                () -> assertThat(queen.isMovable(source, targetDiagonal, MoveType.MOVE)).isTrue(),
                () -> assertThat(queen.isMovable(source, targetVertical, MoveType.MOVE)).isTrue(),
                () -> assertThat(queen.isMovable(source, targetHorizontal, MoveType.MOVE)).isTrue()
        );
    }

    @Test
    @DisplayName("퀸의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKingTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Queen(Team.BLACK));
        boardMap.put(Position.from("a7"), new Knight(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 a7"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("퀸의 target위치가 빈칸이면 움직임에 성공한다")
    void moveKingTest2() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Queen(Team.BLACK));
        boardMap.put(Position.from("b7"), new Empty());
        boardMap.put(Position.from("c6"), new Empty());
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 c6"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("퀸의 target위치에 아군 말이 있으면 예외처리")
    void moveFailureKingTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("c6"), new Queen(Team.WHITE));
        boardMap.put(Position.from("d7"), new Empty());
        boardMap.put(Position.from("e8"), new Pawn(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move c6 e8"), new Turn(Team.WHITE))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.")
    void getIntervalPositionTest() {
        Piece queen = new Queen(Team.BLACK);
        List<Position> intervalPosition = queen.getIntervalPosition(Position.from("h8"), Position.from("e5"));

        assertThat(intervalPosition.contains(Position.from("f6"))).isTrue();
        assertThat(intervalPosition.contains(Position.from("g7"))).isTrue();
        assertThat(intervalPosition.size()).isEqualTo(2);
    }
}
