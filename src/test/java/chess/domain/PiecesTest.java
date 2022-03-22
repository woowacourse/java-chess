package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    @DisplayName("게임에 사용될 말들을 생성한다.")
    void construct() {
        assertThatCode(() -> new Pieces(
                List.of(new King(new Position(Column.A, Row.ONE)),
                        new Pawn(new Position(Column.A, Row.TWO)))))
                .doesNotThrowAnyException();
    }
}
