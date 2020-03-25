package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceColorTest {
    @Test
    void convertName_PieceTypeName_ConvertByPieceColor() {
        assertThat(PieceColor.BLACK.convertName(King.NAME)).isEqualTo("K");
        assertThat(PieceColor.WHITE.convertName(King.NAME)).isEqualTo("k");
    }
}

