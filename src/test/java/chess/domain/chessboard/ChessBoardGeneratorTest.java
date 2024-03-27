package chess.domain.chessboard;

import static chess.domain.chesspiece.Role.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardGeneratorTest {

    @Test
    @DisplayName("초기화된 체스 보드를 생성한다.")
    void ChessBoardGenerator_Create_initial_chess_board() {
        var result = ChessBoardGenerator.initializeBoard();

        assertAll(
                () -> assertThat(result.get(new Position("a", "8")).getRole()).isEqualTo(BLACK_ROOK),
                () -> assertThat(result.get(new Position("b", "8")).getRole()).isEqualTo(BLACK_KNIGHT),
                () -> assertThat(result.get(new Position("c", "8")).getRole()).isEqualTo(BLACK_BISHOP),
                () -> assertThat(result.get(new Position("d", "8")).getRole()).isEqualTo(BLACK_QUEEN),
                () -> assertThat(result.get(new Position("e", "8")).getRole()).isEqualTo(BLACK_KING),
                () -> assertThat(result.get(new Position("f", "8")).getRole()).isEqualTo(BLACK_BISHOP),
                () -> assertThat(result.get(new Position("g", "8")).getRole()).isEqualTo(BLACK_KNIGHT),
                () -> assertThat(result.get(new Position("h", "8")).getRole()).isEqualTo(BLACK_ROOK),
                () -> assertThat(result.get(new Position("a", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("b", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("c", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("d", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("e", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("f", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("g", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("h", "7")).getRole()).isEqualTo(BLACK_PAWN),
                () -> assertThat(result.get(new Position("a", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("b", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("c", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("d", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("e", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("f", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("g", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("h", "6")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("a", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("b", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("c", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("d", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("e", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("f", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("g", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("h", "5")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("a", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("b", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("c", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("d", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("e", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("f", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("g", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("h", "4")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("a", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("b", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("c", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("d", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("e", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("f", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("g", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("h", "3")).getRole()).isEqualTo(EMPTY),
                () -> assertThat(result.get(new Position("a", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("b", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("c", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("d", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("e", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("f", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("g", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("h", "2")).getRole()).isEqualTo(WHITE_PAWN),
                () -> assertThat(result.get(new Position("a", "1")).getRole()).isEqualTo(WHITE_ROOK),
                () -> assertThat(result.get(new Position("b", "1")).getRole()).isEqualTo(WHITE_KNIGHT),
                () -> assertThat(result.get(new Position("c", "1")).getRole()).isEqualTo(WHITE_BISHOP),
                () -> assertThat(result.get(new Position("d", "1")).getRole()).isEqualTo(WHITE_QUEEN),
                () -> assertThat(result.get(new Position("e", "1")).getRole()).isEqualTo(WHITE_KING),
                () -> assertThat(result.get(new Position("f", "1")).getRole()).isEqualTo(WHITE_BISHOP),
                () -> assertThat(result.get(new Position("g", "1")).getRole()).isEqualTo(WHITE_KNIGHT),
                () -> assertThat(result.get(new Position("h", "1")).getRole()).isEqualTo(WHITE_ROOK)
        );
    }
}
