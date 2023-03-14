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
        Piece piece = new Piece(pieceType);

        assertThat(piece.getPieceType()).isEqualTo(pieceType);
    }
}
