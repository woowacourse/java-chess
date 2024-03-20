package domain.piece;

import domain.piece.attribute.point.Index;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IndexTest {
    @Test
    @DisplayName("인덱스를 생성한다.")
    void create_index() {
        int x = 2;
        int y = 4;
        assertThat(new Index(x, y)).isEqualTo(new Index(x, y));
    }

    @Test
    @DisplayName("인덱스가 File 과 Rank 범위 안에 있으면 참을 반환한다.")
    void is_in_range() {
        int x = 4;
        int y = 5;
        assertThat(new Index(x, y).isInBoundary()).isTrue();
    }

    @Test
    @DisplayName("인덱스가 랭크 범위 밖에 있으면 거짓을 반환한다.")
    void is_rank_out_range() {
        int x = 9;
        int y = 4;
        assertThat(new Index(x, y).isInBoundary()).isFalse();
    }

    @Test
    @DisplayName("인덱스가 파일 범위 밖에 있으면 거짓을 반환한다.")
    void is_file_out_range() {
        int x = 7;
        int y = 7;
        assertThat(new Index(x, y).isInBoundary()).isTrue();
    }
}
