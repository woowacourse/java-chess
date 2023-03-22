package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    @DisplayName("기물을 움직이게 한다")
    @Test
    void movePiece() {
        Game game = new Game();

        game.movePiece("b2", "b3");

        Map<Position, Piece> pieces = game.getPieces();
        assertThat(pieces.get(new Position("b3")))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }

    @DisplayName("최초에 흑 기물을 움직일시 예외를 던진다")
    @Test
    void moveBlackFirst_throws() {
        Game game = new Game();

        assertThatThrownBy(() -> game.movePiece("b7", "b6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자신의 기물만 움직일 수 있습니다");
    }

    @DisplayName("두번째 턴은 흑 기물을 움직일 수 있다")
    @Test
    void moveBlackSecond() {
        Game game = new Game();

        game.movePiece("b2", "b3");
        game.movePiece("b7", "b6");

        Map<Position, Piece> pieces = game.getPieces();
        assertThat(pieces.get(new Position("b6")))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }
}
