package domain.piece.attribute.point;

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

    @Test
    @DisplayName("인덱스가 방향으로 이동한다.")
    void move_index_by_direction() {
        final var sut = new Index(4, 5);

        final var result = sut.move(Direction.UP);

        assertThat(result).isEqualTo(new Index(5, 5));
    }

    @Test
    @DisplayName("인덱스가 주어진 횟수만큼 방향으로 이동한다.")
    void move_index_by_direction_with_count() {
        final var sut = new Index(4, 5);

        final var result = sut.move(Direction.LEFT, 3);

        assertThat(result).isEqualTo(new Index(4, 2));
    }
}
