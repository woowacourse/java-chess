package chess.model;

import chess.model.gameCreator.NewBoardCreatingStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChessGameTest {
    @Test
    void 생성자_오류확인_strategy가_null인_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new ChessGame(null, 1));
    }

    @Test
    void 생성자_오류확인_turn이_음수인_경우() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new ChessGame(new NewBoardCreatingStrategy(), -1));
    }

    @Test
    void 말을_움직일_때_확인_입력된_좌표가_null인_경우() {
        ChessGame game = new ChessGame(new NewBoardCreatingStrategy(), 1);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> game.movePiece(null, "13"));
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> game.movePiece("12", null));
    }

    @Test
    void 말이_움직이면_다음턴으로_넘어가는지_확인() {
        ChessGame game = new ChessGame(new NewBoardCreatingStrategy(), 1);
        assertThat(game.getCurrentTeam()).isEqualTo("white");
        game.movePiece("12", "13");
        assertThat(game.getCurrentTeam()).isEqualTo("black");
    }

    @Test
    void white팀_턴이_맞는지_확인() {
        ChessGame game = new ChessGame(new NewBoardCreatingStrategy(), 3);
        assertThat(game.getCurrentTeam()).isEqualTo("white");
    }

    @Test
    void black팀_턴이_맞는지_확인() {
        ChessGame game = new ChessGame(new NewBoardCreatingStrategy(), 4);
        assertThat(game.getCurrentTeam()).isEqualTo("black");
    }
}
