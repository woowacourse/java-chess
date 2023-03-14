package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PieceTest {
    @Test
    @DisplayName("기물들을 생성한다.")
    void createInitialPieces() {
        // given
        Side side = new Side(Color.BLACK);

        // when
        List<Piece> pieces = Piece.createInitialPieces(side);

        // expected
        Role[] values = Role.values();
        Arrays.stream(values).forEach(
                role -> {
                    long count = pieces.stream()
                            .filter(piece -> piece.getRole().equals(role))
                            .count();
                    Assertions.assertThat(count).isEqualTo(role.getCount());
                }
        );
    }
}
