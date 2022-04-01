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

class PawnTest {

    @Test
    @DisplayName("폰은 처음 상태에서 1칸 또는 2칸 전진할 수 있으면 true 반환")
    void isCorrectMovable() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position source = Position.from("a2");
        Position target1Step = Position.from("a3");
        Position target2Step = Position.from("a4");

        assertAll(
                () -> assertThat(pawn.isMovable(source, target1Step, MoveType.MOVE)).isTrue(),
                () -> assertThat(pawn.isMovable(source, target2Step, MoveType.MOVE)).isTrue()
        );
    }

    @Test
    @DisplayName("폰은 처음 상태에서 3칸 전진하려면 false 반환")
    void isNotCorrectInitMovable() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position source = Position.from("a2");
        Position target = Position.from("a5");

        assertThat(pawn.isMovable(source, target, MoveType.MOVE)).isFalse();
    }

    @Test
    @DisplayName("폰은 한번 이동한 후, 2칸 움직이면 false 반환")
    void isNotCorrectMovable() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position source = Position.from("a3");
        Position target = Position.from("a5");

        assertThat(pawn.isMovable(source, target, MoveType.MOVE)).isFalse();
    }

    @Test
    @DisplayName("폰이 뒤로 움직이면 false 반환")
    void isNotCorrectBackMovable() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position source = Position.from("a3");
        Position target = Position.from("a2");

        assertThat(pawn.isMovable(source, target, MoveType.MOVE)).isFalse();
    }

    @Test
    @DisplayName("폰이 블랙팀이면 Rank가 감소하는 방향으로 알맞게 움직이면 true 반환")
    void isCorrectMovableWhenTeamIsBlack() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position source = Position.from("a7");
        Position target = Position.from("a6");

        assertThat(pawn.isMovable(source, target, MoveType.MOVE)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveKingTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("a7"), new Empty());
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 a7"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("화이트 폰의 target위치가 빈칸이면 움직임에 성공한다")
    void moveKingTest2() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a2"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("a3"), new Empty());
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a2 a3"), new Turn(Team.WHITE))
        );
    }

    @Test
    @DisplayName("폰의 target위치에 말이 있으면 예외처리")
    void moveFailureKingTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("a7"), new King(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a8 a7"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 source와 target사이에 말의 수 반환")
    void getIntervalPositionTest() {
        Piece pawn = new Pawn(Team.BLACK);
        List<Position> intervalPosition = pawn.getIntervalPosition(Position.from("a8"), Position.from("a6"));

        assertThat(intervalPosition.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("폰은 처음 위치가 아닌곳에서 움직일때는 한 칸만 움직일수 있다.")
    void movePawnOnlyOneStepTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a6"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("a5"), new Empty());
        boardMap.put(Position.from("a4"), new Empty());
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a6 a4"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 뒤로 움직이면 예외가 발생한다.")
    void movePawnBackOneStepTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a6"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("a7"), new Empty());
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a6 a7"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("블랙팀 폰이 대각선의 상대 말을 잡아먹는다.")
    void killBlackPawnTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("b7"), new Knight(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 b7"), new Turn(Team.BLACK))
        );
    }

    @Test
    @DisplayName("하얀팀 폰이 대각선의 상대 말을 잡아먹는다.")
    void killWhitePawnTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a2"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("b3"), new King(Team.BLACK));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a2 b3"), new Turn(Team.WHITE))
        );
    }

    @Test
    @DisplayName("폰이 대각선에 아군 말이 있으면 잡아먹지않는다.")
    void killFailureWhenOurTeamTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("b7"), new Knight(Team.BLACK));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a8 b7"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 대각선 뒤의 상대 말은 잡아먹을수 없다.")
    void killFailureWhenBackStepTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("b7"), new Pawn(Team.BLACK));
        boardMap.put(Position.from("a8"), new Knight(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move b7 a8"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
