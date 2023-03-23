package chess.domain.piece.maker;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyPieceGeneratorTest {

    @Test
    @DisplayName("빈 체스 말 리스트 생성 테스트")
    void empty_piece_generate_test() {
        final EmptyPieceGenerator emptyPieceGenerator = new EmptyPieceGenerator();

        final Set<Piece> pieces = emptyPieceGenerator.generate();

        assertThat(pieces).isEmpty();
    }

}
