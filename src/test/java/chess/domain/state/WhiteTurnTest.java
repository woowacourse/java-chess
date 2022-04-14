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

public class WhiteTurnTest {

    private static final Board board = new Board(new BoardInitializer());

    @Test
    @DisplayName("start 시 예외 발생")
    void startException() {
        State whiteTurn = new WhiteTurn(board);

        assertThatThrownBy(whiteTurn::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 이미 시작되었습니다.");
    }

    @Test
    @DisplayName("move 시 BlackTurn 상태로 변경")
    void move() {
        State whiteTurn = new WhiteTurn(board);

        whiteTurn = whiteTurn.move(Position.from("a2"), Position.from("a3"));

        assertThat(whiteTurn).isInstanceOf(BlackTurn.class);
    }

    @Test
    @DisplayName("move 시 black 킹을 잡으면 Ready 상태로 변경")
    void moveToKing() {
        State whiteTurn = new WhiteTurn(getBoard());

        whiteTurn = whiteTurn.move(Position.from("a2"), Position.from("b3"));

        assertThat(whiteTurn).isInstanceOf(Ready.class);
    }

    private Board getBoard() {
        Map<Position, Piece> catchKingBoard = new HashMap<>();
        catchKingBoard.put(Position.from("b3"), new King(Color.BLACK));
        catchKingBoard.put(Position.from("a2"), new Pawn(Color.WHITE));
        return new Board(() -> catchKingBoard);
    }

    @Test
    @DisplayName("출발 지점과 도착 지점이 같을 경우 예외 발생")
    void moveToEqualsPositionException() {
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
    @DisplayName("게임이 정상적으로 end 되는지 확인")
    void end() {
        State whiteTurn = new WhiteTurn(board);

        assertThatCode(whiteTurn::end)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("status 시 예외 발생")
    void statusException() {
        State whiteTurn = new WhiteTurn(board);

        assertThatThrownBy(() -> whiteTurn.status(Output::printScore))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료된 이후 점수 조회가 가능합니다.");
    }

    @Test
    @DisplayName("게임이 실행중인 것을 확인")
    void isRunning() {
        State whiteTurn = new WhiteTurn(board);

        assertThat(whiteTurn.isRunning()).isTrue();
    }

    @Test
    @DisplayName("게임이 끝나지 않은 것을 확인")
    void isEnded() {
        State whiteTurn = new WhiteTurn(board);

        assertThat(whiteTurn.isEnded()).isFalse();
    }
}
