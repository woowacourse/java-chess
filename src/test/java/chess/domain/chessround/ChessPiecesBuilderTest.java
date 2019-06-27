package chess.domain.chessround;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessPiecesBuilderTest {
    @Test
    void getInstance_싱글톤_체크() {
        ChessPiecesBuilder builder = ChessPiecesBuilder.getInstance();
        assertThat(builder == ChessPiecesBuilder.getInstance()).isTrue();
    }
}