package domain;

import domain.chessboard.ChessBoard;
import domain.chessboard.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void givenChessBoard_thenSize64() {
        final int sum = ChessBoard.generate()
                .getChessBoard()
                .stream()
                .map(Row::getRow)
                .mapToInt(Collection::size)
                .sum();

        assertThat(sum).isEqualTo(64);
    }

}
