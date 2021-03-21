package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {
    @Test
    @DisplayName("movable 테스트")
    void movableTest() {
//        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
//        assertThat(chessGame.movable(Position.of("a1"))).containsExactly();
    }
}
