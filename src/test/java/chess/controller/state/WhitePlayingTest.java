package chess.controller.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePlayingTest {
    private final Board board = BoardFactory.createBoard();
    private WhitePlaying whitePlaying;

    @BeforeEach
    void init() {
        whitePlaying = new WhitePlaying(board);
    }

    @Test
    @DisplayName("start시 본인의 클래스를 return 한다.")
    void whitePlayingStart() {
        //given & when
        ChessGameState stateAfterStart = whitePlaying.start();

        //then
        assertThat(stateAfterStart).isExactlyInstanceOf(WhitePlaying.class);
    }

    @Test
    @DisplayName("status시 본인의 클래스를 return 한다.")
    void whitePlayingStatus() {
        //given & when
        ChessGameState stateAfterStatus = whitePlaying.status();

        //then
        assertThat(stateAfterStatus).isExactlyInstanceOf(WhitePlaying.class);
    }

    @Test
    @DisplayName("move가 실패하였을 시 본인의 클래스를 return 한다.")
    void whitePlayingMoveFail() {
        //given & when
        ChessGameState stateAfterMove = whitePlaying.move("b1", "b2");

        //then
        assertThat(stateAfterMove).isExactlyInstanceOf(WhitePlaying.class);
    }


    @Test
    @DisplayName("move가 성공하였을 시 BlackPlaying을 return 한다.")
    void whitePlayingMoveSuccess() {
        //given & when
        ChessGameState stateAfterMove = whitePlaying.move("b2", "b4");

        //then
        assertThat(stateAfterMove).isExactlyInstanceOf(BlackPlaying.class);
    }

    @Test
    @DisplayName("end시 finished를 return한다.")
    void whitePlayingEnd() {
        //given & when
        ChessGameState stateAfterEnd = whitePlaying.end();

        //then
        assertThat(stateAfterEnd).isExactlyInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("isEnded는 false를 return한다.")
    void whitePlayingIsEnded() {
        //given & when
        boolean isEnded = whitePlaying.isEnded();

        //then
        assertThat(isEnded).isFalse();
    }
}