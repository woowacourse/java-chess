package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WhiteTurnTest {

    private static final Board board = new Board(new BoardInitializer());

    @Test
    @DisplayName("출발 지점과 도착 지점이 같을 경우 예외 발생")
    void moveToEqualsPosition() {
        State whiteTurn = new WhiteTurn(board);

        assertThatThrownBy(() -> whiteTurn.move(Position.from("a1"), Position.from("a1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("출발 지점과 도착 지점 위치가 동일합니다.");
    }

    @Test
    @DisplayName("검은색 말을 선택할 경우 예외 발생")
    void moveBlackPieceException() {
        State whiteTurn = new WhiteTurn(board);

        assertThatThrownBy(() -> whiteTurn.move(Position.from("a7"), Position.from("a8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자신의 말을 선택하세요.");
    }

    @Test
    @DisplayName("도착 지점에 흰색 말이 있을 경우 예외 발생")
    void moveToWhitePieceException() {
        State whiteTurn = new WhiteTurn(board);

        assertThatThrownBy(() -> whiteTurn.move(Position.from("a1"), Position.from("b1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("도착 지점에 나의 말이 존재합니다.");
    }

    @Test
    @DisplayName("킹을 잡을 경우 게임 종료")
    void end() {
        Map<Position, Piece> catchKingBoard = new HashMap<>();
        catchKingBoard.put(Position.from("a1"), new Queen(Color.WHITE));
        catchKingBoard.put(Position.from("a2"), new King(Color.BLACK));
        Board board = new Board(() -> catchKingBoard);

        State whiteTurn = new WhiteTurn(board);
        whiteTurn = whiteTurn.move(Position.from("a1"), Position.from("a2"));

        assertThat(whiteTurn.isRunning()).isFalse();
    }
}
