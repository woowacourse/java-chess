package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.AbstractTestFixture;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest extends AbstractTestFixture {

    @DisplayName("기물을 움직이게 한다")
    @Test
    void movePiece() {
        Game game = new Game();

        game.movePiece(createPosition("B,TWO"), createPosition("B,THREE"));

        Map<Position, Piece> pieces = game.getPieces();
        assertThat(pieces.get(createPosition("B,THREE")))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }

    @DisplayName("최초에 흑 기물을 움직일시 예외를 던진다")
    @Test
    void moveBlackFirst_throws() {
        Game game = new Game();

        assertThatThrownBy(() -> game.movePiece(createPosition("B,SEVEN"), createPosition("B,SIX")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자신의 기물만 움직일 수 있습니다");
    }

    @DisplayName("두번째 턴은 흑 기물을 움직일 수 있다")
    @Test
    void moveBlackSecond() {
        Game game = new Game();

        game.movePiece(createPosition("B,TWO"), createPosition("B,THREE"));
        game.movePiece(createPosition("B,SEVEN"), createPosition("B,SIX"));

        Map<Position, Piece> pieces = game.getPieces();
        assertThat(pieces.get(createPosition("B,SIX")))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }
}
