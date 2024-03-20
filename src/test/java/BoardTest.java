import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @DisplayName("Board에서 위치와 Character를 알 수 있다.")
    @Test
    void mapPositionToCharacter() {
        Board board = new Board();

        Map<Position, Character> expected = Map.ofEntries(
                Map.entry(Position.of(1, 1), Character.WHITE_ROOK),
                Map.entry(Position.of(1, 2), Character.WHITE_KNIGHT),
                Map.entry(Position.of(1, 3), Character.WHITE_BISHOP),
                Map.entry(Position.of(1, 4), Character.WHITE_QUEEN),
                Map.entry(Position.of(1, 5), Character.WHITE_KING),
                Map.entry(Position.of(1, 6), Character.WHITE_BISHOP),
                Map.entry(Position.of(1, 7), Character.WHITE_KNIGHT),
                Map.entry(Position.of(1, 8), Character.WHITE_ROOK),

                Map.entry(Position.of(2, 1), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 2), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 3), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 4), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 5), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 6), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 7), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 8), Character.WHITE_PAWN),

                Map.entry(Position.of(7, 1), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 2), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 3), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 4), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 5), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 6), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 7), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 8), Character.BLACK_PAWN),

                Map.entry(Position.of(8, 1), Character.BLACK_ROOK),
                Map.entry(Position.of(8, 2), Character.BLACK_KNIGHT),
                Map.entry(Position.of(8, 3), Character.BLACK_BISHOP),
                Map.entry(Position.of(8, 4), Character.BLACK_QUEEN),
                Map.entry(Position.of(8, 5), Character.BLACK_KING),
                Map.entry(Position.of(8, 6), Character.BLACK_BISHOP),
                Map.entry(Position.of(8, 7), Character.BLACK_KNIGHT),
                Map.entry(Position.of(8, 8), Character.BLACK_ROOK)
        );

        assertThat(board.mapPositionToCharacter()).isEqualTo(expected);
    }
}
