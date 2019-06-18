package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceTypesTest {

    @Test
    void 체스_피스_유무_확인() {
        assertThat(ChessPieceTypes.KING).isNotNull();
    }
}
