package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.Test;

public class PieceTypeTest {
    @Test
    void 기물색에_따라_출력_포맷에_맞는_문자_반환() {
        PieceType pieceType = PieceType.BISHOP;
        Color color = Color.WHITE;
        assertThat(pieceType.formatName(color)).isEqualTo("b");
    }
}
