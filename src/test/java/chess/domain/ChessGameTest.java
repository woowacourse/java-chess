package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.TestOtherTeamBoard;
import chess.domain.position.Square;

public class ChessGameTest {

    @Test
    @DisplayName("흰 말로 시작하지 않으면 에러를 반환한다")
    void errorTurn_Start() {
        ChessGame chessGame = new ChessGame(new TestOtherTeamBoard());
        assertThatThrownBy(() -> chessGame.move(new Square("d4"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 순서 지키시지?!");
    }

    @Test
    @DisplayName("흰 말 다음 검은 말 순서로 진행하지 않으면 에러를 반환한다")
    void errorTurn() {
        ChessGame chessGame = new ChessGame(new TestOtherTeamBoard());
        chessGame.startGame();
        chessGame.move(new Square("c3"), new Square("d4"));
        assertThatThrownBy(() -> chessGame.move(new Square("d4"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 순서 지키시지?!");
    }
}
