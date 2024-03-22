package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Empty;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathPiecesTest {

    @DisplayName("모든 기물이 EMPTY는 아니다.")
    @Test
    void notAllEmpty() {
        PathPieces pathPieces = new PathPieces(List.of(new Empty(), new Rook(Side.BLACK)));

        boolean actual = pathPieces.notAllEmpty();

        assertThat(actual).isTrue();
    }

    @DisplayName("모든 기물이 EMPTY이다.")
    @Test
    void allEmpty() {
        PathPieces pathPieces = new PathPieces(List.of(new Empty(), new Empty()));

        boolean actual = pathPieces.notAllEmpty();

        assertThat(actual).isFalse();
    }
}
