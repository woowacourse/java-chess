package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ChessBoardExpressionTest {

    @Test
    @DisplayName("현재 체스판의 상태를 문자열로 반환한다.")
    void toChessBoardExpression() {
        Piece piece = King.of(Color.WHITE);
        List<Piece> pieces = Collections.nCopies(64, piece);

        String actualExpression = ChessBoardExpression.toExpression(pieces);

        String expectedExpression = "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator()
                + "kkkkkkkk" + System.lineSeparator();
        Assertions.assertThat(actualExpression).isEqualTo(expectedExpression);
    }
}
