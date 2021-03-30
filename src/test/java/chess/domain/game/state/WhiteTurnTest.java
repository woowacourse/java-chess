package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    private WhiteTurn whiteTurn;

    @BeforeEach
    void setUp() {
        this.whiteTurn = new WhiteTurn(new Board());
    }

    @Test
    @DisplayName("백색 차례에서 start 명령시 에러 반환")
    void testStartException() {
        assertThatThrownBy(() -> this.whiteTurn.start()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("백색 차례에서 백색 말 move 명령 진행")
    void testMove() {
        Board board = this.whiteTurn.afterStartBoard();

        assertThat(board.pieceByPosition(Position.of("a2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("a4"))).isEqualTo(Empty.create());

        this.whiteTurn.moveIfValidColor(Position.of("a2"), Position.of("a4"));

        assertThat(board.pieceByPosition(Position.of("a2"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("a4"))).isEqualTo(Pawn.createWhite());
    }

    @Test
    @DisplayName("백색 차례에서 흑색 말 move 명령시 예외반환")
    void testMoveException() {
        Board board = this.whiteTurn.afterStartBoard();

        assertThat(board.pieceByPosition(Position.of("a7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("a5"))).isEqualTo(Empty.create());

        assertThatThrownBy(
            () -> this.whiteTurn.moveIfValidColor(Position.of("a7"), Position.of("a5")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("백색 차례에서 passTurn 명령시 흑색 차례 반환")
    void testPassTurn() {
        assertThat(this.whiteTurn.passTurn()).isInstanceOf(BlackTurn.class);
    }

    @Test
    @DisplayName("백색 차례에서 board 명령시 board 반환")
    void testBoard() {
        assertThat(this.whiteTurn.board()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("백색 차례에서 winner 명령시 에러 반환")
    void testWinnerException() {
        assertThatThrownBy(() -> this.whiteTurn.winner()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("백색 차례에서 end 명령시 End 상태 반환")
    void testEnd() {
        assertThat(this.whiteTurn.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("백색 차례에서 isInit 명령시 false 반환")
    void testIsInitTure() {
        assertThat(this.whiteTurn.isInit()).isFalse();
    }

    @Test
    @DisplayName("백색 차례에서 isRunning 명령시 true 반환")
    void testIsRunningTrue() {
        assertThat(this.whiteTurn.isRunning()).isTrue();
    }

    @Test
    @DisplayName("백색 차례에서 isFinished 명령시 false 반환")
    void testIsFinishedFalse() {
        assertThat(this.whiteTurn.isFinished()).isFalse();
    }

    @Test
    @DisplayName("백색 차례에서 isNotEnd 명령시 true 반환")
    void testIsNotEndTrue() {
        assertThat(this.whiteTurn.isNotEnd()).isTrue();
    }

    @Test
    @DisplayName("백색 차례에서 백색말의 movablePath 명령시 결과 반환")
    void testMovablePathOfSameColor() {
        assertThat(this.whiteTurn.movablePath(Position.of("a2")))
            .containsExactly(Position.of("a4"), Position.of("a3"));
    }

    @Test
    @DisplayName("흑색 차례에서 백색말의 movablePath 명령시 예외 반환")
    void testMovablePathOfDifferentColorException() {
        assertThatThrownBy(() -> this.whiteTurn.movablePath(Position.of("a7")))
            .isInstanceOf(IllegalStateException.class);
    }
}
