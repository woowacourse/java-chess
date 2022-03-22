package chess2.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess2.domain.board.Row;
import chess2.domain.square.Color;
import chess2.domain.square.Empty;
import chess2.domain.square.Pawn;
import chess2.domain.square.Square;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RowTest {

    @DisplayName("ofEmpty 메서드는 8개의 Empty 인스턴스의 행을 생성한다.")
    @Test
    void ofEmpty() {
        Row emptyRow = Row.ofEmpty();

        List<Square> squares = emptyRow.getSquares();

        assertThat(squares).containsOnly(new Empty());
        assertThat(squares.size()).isEqualTo(8);
    }

    @DisplayName("ofPawn 메서드는 8개의 흑색 폰 인스턴스의 행을 생성한다.")
    @Test
    void ofPawn_black() {
        Row blackPawnRow = Row.ofPawn(Color.BLACK);

        List<Square> squares = blackPawnRow.getSquares();

        assertThat(squares).containsOnly(new Pawn(Color.BLACK));
        assertThat(squares.size()).isEqualTo(8);
    }

    @DisplayName("ofPawn 메서드는 8개의 백색 폰 인스턴스의 행을 생성한다.")
    @Test
    void ofPawn_white() {
        Row whitePawnRow = Row.ofPawn(Color.WHITE);

        List<Square> squares = whitePawnRow.getSquares();

        assertThat(squares).containsOnly(new Pawn(Color.WHITE));
        assertThat(squares.size()).isEqualTo(8);
    }
}
