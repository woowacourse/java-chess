package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Empty;
import domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @DisplayName("모든 기물이 EMPTY는 아니다.")
    @Test
    void notAllEmpty() {
        Pieces pieces = new Pieces(List.of(new Empty(), new Rook(Side.BLACK)));

        boolean actual = pieces.notAllEmpty();

        assertThat(actual).isTrue();
    }

    @DisplayName("모든 기물이 EMPTY이다.")
    @Test
    void allEmpty() {
        Pieces pieces = new Pieces(List.of(new Empty(), new Empty()));

        boolean actual = pieces.notAllEmpty();

        assertThat(actual).isFalse();
    }
}
