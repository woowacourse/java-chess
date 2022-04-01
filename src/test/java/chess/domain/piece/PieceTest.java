package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    @DisplayName("체스말은 생성될때 색, 이름, 상태을 가진다.")
    void pieceTest() {
        assertThatCode(() -> new Piece(Color.BLACK, "k", 0) {
            @Override
            public List<Position> getMovablePaths(Position source, ChessBoard board) {
                return null;
            }
        }).doesNotThrowAnyException();
    }
}
