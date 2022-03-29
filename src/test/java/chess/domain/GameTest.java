package chess.domain;

import chess.domain.manager.BoardInitializer;
import chess.domain.manager.Game;
import chess.domain.pieces.King;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.pieces.Color.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameTest {

    @Test
    @DisplayName("출발지와 목적지가 같으면 예외를 발생한다")
    void thrown_sourceEqualsTarget() {
        Game game = new Game(new BoardInitializer());
        assertThatThrownBy(() -> game.move("a2", "a2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출발지와 목적지가 동일할 수 없습니다.");
    }

    @Test
    @DisplayName("수를 번갈아가면서 두어야 한다")
    void turnNotChanged_throwException() {
        Game game = new Game(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("a5"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("a4"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        game.move("a5", "a6");
        assertThatThrownBy(() -> game.move("a4", "a5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지금은 검은말의 턴입니다.");
    }
}
