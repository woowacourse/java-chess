package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyBoardTest {
    
    @Test
    @DisplayName("빈 보드 초기화 테스트")
    void initializedBlankTest() {
        
        // when
        final Map<Position, Piece> chessBord = EmptyBoard.create()
                                                         .getBoard();
        
        // then
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = Position.of(i, j);
                assertThat(chessBord.get(position)).isInstanceOf(Blank.class);
            }
        }
    }
}