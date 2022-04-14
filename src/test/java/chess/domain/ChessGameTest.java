package chess.domain;

import static chess.domain.TestPieces.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.SavedBoardGenerator;
import chess.domain.position.Square;

public class ChessGameTest {

    @Test
    @DisplayName("흰 말로 시작하지 않으면 에러를 반환한다")
    void errorTurn_Start() {
        ChessGame chessGame = new ChessGame(
                new SavedBoardGenerator(Map.of(new Square("c3"), WHITE_QUEEN, new Square("d4"), BLACK_QUEEN)),
                GameTurn.WHITE);
        assertThatThrownBy(() -> chessGame.move(new Square("d4"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("순서 지키시지?!");
    }

    @Test
    @DisplayName("흰 말 다음 검은 말 순서로 진행하지 않으면 에러를 반환한다")
    void errorTurn() {
        ChessGame chessGame = new ChessGame(
                new SavedBoardGenerator(Map.of(new Square("c3"), WHITE_QUEEN, new Square("d4"), BLACK_QUEEN)),
                GameTurn.WHITE);
        chessGame.move(new Square("c3"), new Square("d4"));
        assertThatThrownBy(() -> chessGame.move(new Square("d4"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("순서 지키시지?!");
    }
}
