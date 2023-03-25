package domain;

import domain.piece.Piece;
import domain.piece.PieceInfo;
import domain.piece.TeamColor;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RuleTest {

    @Test
    @DisplayName("백이 첫수를 두고 흑,백 순서로 진행한다.")
    void validateOrder() {
        Rule rule = new Rule();
        Piece white = new Piece(TeamColor.WHITE, null) {
            @Override
            public List<Square> findRoutes(Square source, Square destination) {
                return null;
            }
        };

        Piece black = new Piece(TeamColor.BLACK, null) {
            @Override
            public List<Square> findRoutes(Square source, Square destination) {
                return null;
            }
        };
        List<Piece> pieces = List.of(white, black, white, black);

        Assertions.assertDoesNotThrow(() -> {
            for (Piece piece : pieces) {
                rule.validateOrder(piece);
                rule.nextOrder();
            }
        });
    }
}
