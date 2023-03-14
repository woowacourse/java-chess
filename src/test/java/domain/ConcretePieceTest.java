package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConcretePieceTest {

    @ParameterizedTest
    @EnumSource(PieceType.class)
    @DisplayName("말은 기물 종류를 가질 수 있다")
    void getPieceType(PieceType pieceType) {
        ConcretePiece concretePiece = new ConcretePiece(pieceType, Camp.WHITE);

        assertThat(concretePiece.getPieceType()).isEqualTo(pieceType);
    }

    @ParameterizedTest
    @EnumSource(Camp.class)
    @DisplayName("말을 화이트 팀을 수 있다")
    void getCamp(Camp camp) {
        ConcretePiece concretePiece = new ConcretePiece(PieceType.QUEEN, camp);

        assertThat(concretePiece.getCamp()).isEqualTo(camp);
    }
}
