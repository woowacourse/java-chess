package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @ParameterizedTest
    @EnumSource(PieceType.class)
    @DisplayName("말은 기물 종류를 가질 수 있다")
    void getPieceType(PieceType pieceType) {
        Piece piece = new Piece(pieceType, Camp.WHITE);

        assertThat(piece.getPieceType()).isEqualTo(pieceType);
    }

    @ParameterizedTest
    @EnumSource(Camp.class)
    @DisplayName("말을 화이트 팀을 수 있다")
    void getCamp(Camp camp) {
        Piece piece = new Piece(PieceType.QUEEN, camp);

        assertThat(piece.getCamp()).isEqualTo(camp);
    }
}
