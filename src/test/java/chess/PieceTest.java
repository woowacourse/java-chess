package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    @DisplayName("File이 E이고, Rank가 8이면 블랙킹을 반환한다")
    void getBlackKing(){
        assertThat(Piece.of(File.E,Rank.EIGHT)).isEqualTo(Piece.BLACK_KING);
    }
    
    @Test
    @DisplayName("File이 D이고, Rank가 1이면 화이트퀸을 반환한다")
    void getWhiteQueen(){
        assertThat(Piece.of(File.D,Rank.ONE)).isEqualTo(Piece.WHITE_QUEEN);
    }
}
