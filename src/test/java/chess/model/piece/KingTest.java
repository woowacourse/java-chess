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

class KingTest {

    @Test
    @DisplayName("킹의 진행 방향과 거리가 맞는다면 true 반환")
    void correctDirectionMove() {
        King king = new King(Team.WHITE);
        Position source = Position.from("a1");
        Position targetDiagonal = Position.from("b2");
        Position targetVertical = Position.from("a2");
        Position targetHorizontal = Position.from("b1");

        assertAll(
                () -> assertThat(king.isMovable(source, targetDiagonal, MoveType.MOVE)).isTrue(),
                () -> assertThat(king.isMovable(source, targetVertical, MoveType.MOVE)).isTrue(),
                () -> assertThat(king.isMovable(source, targetHorizontal, MoveType.MOVE)).isTrue()
        );
    }

    @Test
    @DisplayName("킹이 이동할수 없는 거리이면 false 반환")
    void noCorrectDistanceMove() {
        King king = new King(Team.WHITE);
        Position source = Position.from("a1");
        Position target = Position.from("c3");

        assertThat(king.isMovable(source, target, MoveType.MOVE)).isFalse();
    }


    @Test
    @DisplayName("킹의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKingTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new King(Team.BLACK));
        boardMap.put(Position.from("a7"), new Pawn(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 a7"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("킹의 target위치가 빈칸이면 움직임에 성공한다")
    void moveKingTest2() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new King(Team.BLACK));
        boardMap.put(Position.from("b7"), new Empty());
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 b7"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("킹의 target위치에 아군 말이 있으면 예외처리")
    void moveFailureKingTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new King(Team.BLACK));
        boardMap.put(Position.from("a7"), new Pawn(Team.BLACK));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a8 a7"), new Turn(Team.WHITE))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("킹은 source와 target사이에 말들이 없다.")
    void getIntervalPositionTest() {
        Piece king = new King(Team.BLACK);
        List<Position> intervalPosition = king.getIntervalPosition(Position.from("e8"), Position.from("f8"));

        assertThat(intervalPosition.isEmpty()).isTrue();
    }
}
