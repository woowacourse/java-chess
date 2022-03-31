package chess.controller.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackPlayingTest {
    private final Board board = BoardFactory.createBoard();
    private BlackPlaying blackPlaying;

    @BeforeEach
    void init() {
        blackPlaying = new BlackPlaying(board);
    }

    @Test
    @DisplayName("start시 본인의 클래스를 return 한다.")
    void blackPlayingStart() {
        //given & when
        ChessGameState stateAfterStart = blackPlaying.start();

        //then
        assertThat(stateAfterStart).isExactlyInstanceOf(BlackPlaying.class);
    }

    @Test
    @DisplayName("status시 본인의 클래스를 return 한다.")
    void blackPlayingStatus() {
        //given & when
        ChessGameState stateAfterStatus = blackPlaying.status();

        //then
        assertThat(stateAfterStatus).isExactlyInstanceOf(BlackPlaying.class);
    }

    @Test
    @DisplayName("move가 실패하였을 시 본인의 클래스를 return 한다.")
    void blackPlayingMoveFail() {
        //given & when
        ChessGameState stateAfterMove = blackPlaying.move("b1", "b2");

        //then
        assertThat(stateAfterMove).isExactlyInstanceOf(BlackPlaying.class);
    }


    @Test
    @DisplayName("move가 성공하였을 시 WhitePlaying을 return 한다.")
    void blackPlayingMoveSuccess() {
        //given & when
        ChessGameState stateAfterMove = blackPlaying.move("b7", "b6");

        //then
        assertThat(stateAfterMove).isExactlyInstanceOf(WhitePlaying.class);
    }

    @Test
    @DisplayName("end시 finished를 return한다.")
    void blackPlayingEnd() {
        //given & when
        ChessGameState stateAfterEnd = blackPlaying.end();

        //then
        assertThat(stateAfterEnd).isExactlyInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("isEnded는 false를 return한다.")
    void blackPlayingIsEnded() {
        //given & when
        boolean isEnded = blackPlaying.isEnded();

        //then
        assertThat(isEnded).isFalse();
    }
}