package chess.domain.piece;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PieceScoreTest {

    @Test
    void 기물의_점수를_계산할_수_있다() {
        Map<Class, Integer> numberOfPieces = new HashMap<>(Map.of(
                Pawn.class, 0,
                Rook.class, 2,
                Knight.class, 1,
                Bishop.class, 0,
                Queen.class, 1,
                King.class, 1
        ));

        assertThat(PieceScore.calculateWithoutPawn(numberOfPieces)).isEqualTo(21.5);
    }
}