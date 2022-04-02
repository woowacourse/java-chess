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
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RookTest {

    @Test
    @DisplayName("룩의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Rook rook = new Rook(Team.WHITE);
        Position source = Position.from("a1");
        Position target = Position.from("f1");

        assertThat(rook.isMovable(source, target, MoveType.MOVE)).isTrue();
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.(가로)")
    void getIntervalPositionTest() {
        Piece rook = new Rook(Team.BLACK);
        List<Position> intervalPosition = rook.getIntervalPosition(Position.from("h8"), Position.from("e8"));

        assertThat(intervalPosition.contains(Position.from("f8"))).isTrue();
        assertThat(intervalPosition.contains(Position.from("g8"))).isTrue();
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.(세로)")
    void getIntervalPositionVerticalTest() {
        Piece rook = new Rook(Team.BLACK);
        List<Position> intervalPosition = rook.getIntervalPosition(Position.from("h8"), Position.from("h5"));

        assertThat(intervalPosition.contains(Position.from("h7"))).isTrue();
        assertThat(intervalPosition.contains(Position.from("h6"))).isTrue();
    }

    @Test
    @DisplayName("룩의 진행방향에 말이 있으면 예외 처리")
    void moveFailureWhenExistPieceTest() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a8 a5"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 있으면 예외 처리")
    void moveFailureTest() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(
                () -> chessGame.progress(new Move("move a8 a7"), new Turn(Team.BLACK))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a8"), new Rook(Team.BLACK));
        boardMap.put(Position.from("a7"), new Empty());
        boardMap.put(Position.from("a6"), new Empty());
        boardMap.put(Position.from("a5"), new Pawn(Team.WHITE));
        Board board = new Board(boardMap);
        ChessGame chessGame = new ChessGame(board);

        assertDoesNotThrow(
                () -> chessGame.progress(new Move("move a8 a5"), new Turn(Team.BLACK))
        );
    }
}
