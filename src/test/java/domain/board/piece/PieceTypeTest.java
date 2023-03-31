package domain.board.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTypeTest {

    @Test
    void getPossibleDirections() {
    }

    @DisplayName("이름으로 PieceType을 검색하여 반환받는다.")
    @CsvSource({"KING,KING", "BISHOP,BISHOP", "KNIGHT,KNIGHT", "ROOK,ROOK", "QUEEN,QUEEN", "PAWN,PAWN"})
    @ParameterizedTest()
    void findByName(String input, PieceType pieceType) {
        Assertions.assertThat(PieceType.findByName(input)).isEqualTo(pieceType);
    }

    @DisplayName("PieceType의 점수를 반환받는다.")
    @CsvSource({"KING,0.0", "BISHOP,3.0", "KNIGHT,2.5", "ROOK,5", "QUEEN,9", "PAWN,1"})
    @ParameterizedTest()
    void findByName(PieceType pieceType, double score) {
        Assertions.assertThat(pieceType.getScore()).isEqualTo(score);
    }
}
