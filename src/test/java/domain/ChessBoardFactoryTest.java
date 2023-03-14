package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardFactoryTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void givenChessBoard_thenSize64() {
        final int sum = ChessBoardFactory.generate()
                .getChessBoard()
                .stream()
                .map(Rank::getRank)
                .mapToInt(Collection::size)
                .sum();

        assertThat(sum).isEqualTo(64);
    }
}
