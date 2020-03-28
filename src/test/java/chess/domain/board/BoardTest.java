package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    @DisplayName("보드 생성 실패")
    @Test
    void constructFail() {
        List<Piece> initializedPieces = new ArrayList<>();
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 7; col++) {
                initializedPieces.add(new Blank('.', new Position(col, row)));
            }
        }
        assertThatThrownBy(() -> {
            Board board = new Board(initializedPieces);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보드가 제대로 생성되지 못했습니다.");
    }
}
