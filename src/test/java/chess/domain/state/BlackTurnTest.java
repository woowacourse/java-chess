package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlackTurnTest {

    private static final Board board = new Board(new BoardInitializer());

    @Test
    @DisplayName("start 시 예외 발생")
    void startException() {
        State blackTurn = new BlackTurn(board);

        assertThatThrownBy(blackTurn::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 이미 시작되었습니다.");
    }

    @Test
    @DisplayName("move 시 WhiteTurn 상태로 변경")
    void move() {
        State blackTurn = new BlackTurn(board);

        blackTurn = blackTurn.move(Position.from("a7"), Position.from("a6"));

        assertThat(blackTurn).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("move 시 white 킹을 잡으면 Ready 상태로 변경")
    void moveToKing() {
        State blackTurn = new BlackTurn(getBoard());

        blackTurn = blackTurn.move(Position.from("a7"), Position.from("b6"));

        assertThat(blackTurn).isInstanceOf(Ready.class);
    }

    private Board getBoard() {
        Map<Position, Piece> catchKingBoard = new HashMap<>();
        catchKingBoard.put(Position.from("b6"), new King(Color.WHITE));
        catchKingBoard.put(Position.from("a7"), new Pawn(Color.BLACK));
        return new Board(() -> catchKingBoard);
    }

    @Test
    @DisplayName("출발 지점과 도착 지점이 같을 경우 예외 발생")
    void moveToEqualsPositionException() {
        State blackTurn = new BlackTurn(board);

        assertThatThrownBy(() ->  blackTurn.move(Position.from("a8"), Position.from("a8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("출발 지점과 도착 지점 위치가 동일합니다.");
    }

    @Test
    @DisplayName("흰색 말을 선택할 경우 예외 발생")
    void moveFromWhitePieceException() {
        State blackTurn = new BlackTurn(board);

        assertThatThrownBy(() ->  blackTurn.move(Position.from("a2"), Position.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자신의 말을 선택하세요.");
    }

    @Test
    @DisplayName("도착 지점에 검은색 말이 있을 경우 예외 발생")
    void moveToBlackPieceException() {
        State blackTurn = new BlackTurn(board);

        assertThatThrownBy(() ->  blackTurn.move(Position.from("a8"), Position.from("b8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("도착 지점에 나의 말이 존재합니다.");
    }

    @Test
    @DisplayName("게임이 정상적으로 end 되는지 확인")
    void end() {
        State blackTurn = new BlackTurn(board);

        assertThatCode(blackTurn::end)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("status 시 예외 발생")
    void statusException() {
        State blackTurn = new BlackTurn(board);

        assertThatThrownBy(() -> blackTurn.status(Output::printScore))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료된 이후 점수 조회가 가능합니다.");
    }

    @Test
    @DisplayName("게임이 실행중인 것을 확인")
    void isRunning() {
        State blackTurn = new BlackTurn(board);

        assertThat(blackTurn.isRunning()).isTrue();
    }

    @Test
    @DisplayName("게임이 끝나지 않은 것을 확인")
    void isEnded() {
        State blackTurn = new BlackTurn(board);

        assertThat(blackTurn.isEnded()).isFalse();
    }
}
