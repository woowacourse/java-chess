package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Row;
import chess.domain.square.Bishop;
import chess.domain.square.Color;
import chess.domain.square.Empty;
import chess.domain.square.King;
import chess.domain.square.Knight;
import chess.domain.square.Pawn;
import chess.domain.square.Queen;
import chess.domain.square.Rook;
import chess.domain.square.Square;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @DisplayName("ofPawn 메서드는 BLACK를 넘기면 8개의 흑색 폰 인스턴스의 행을 생성한다.")
    @Test
    void ofPawn_black() {
        Row blackPawnRow = Row.ofPawn(Color.BLACK);

        List<Square> squares = blackPawnRow.getSquares();

        assertThat(squares).containsOnly(new Pawn(Color.BLACK));
        assertThat(squares.size()).isEqualTo(8);
    }

    @DisplayName("ofPawn 메서드에 WHITE를 넘기면 8개의 백색 폰 인스턴스의 행을 생성한다.")
    @Test
    void ofPawn_white() {
        Row whitePawnRow = Row.ofPawn(Color.WHITE);

        List<Square> squares = whitePawnRow.getSquares();

        assertThat(squares).containsOnly(new Pawn(Color.WHITE));
        assertThat(squares.size()).isEqualTo(8);
    }

    @DisplayName("ofMainPieces 메서드 테스트")
    @Nested
    class OfMainPiecesTest {

        List<Square> squares;

        @BeforeEach
        void setUp() {
            Row mainPiecesRow = Row.ofMainPieces(Color.WHITE);
            squares = mainPiecesRow.getSquares();
        }

        @DisplayName("ofMainPieces 메서드는 오직 1개의 킹과 퀸 인스턴스를 생성한다.")
        @Test
        void ofMainPieces_OnlyOneKingAndQueen() {
            assertThat(squares).containsOnlyOnce(
                    new King(Color.WHITE), new Queen(Color.WHITE));
        }

        @DisplayName("ofMainPieces 메서드는 2개의 나이트 인스턴스를 생성한다.")
        @Test
        void ofMainPieces_TwoKnights() {
            long knightCount = squares.stream()
                    .filter(value -> value instanceof Knight)
                    .count();

            assertThat(knightCount).isEqualTo(2);
        }

        @DisplayName("ofMainPieces 메서드는 2개의 비숍 인스턴스를 생성한다.")
        @Test
        void ofMainPieces_TwoBishops() {
            long bishopCount = squares.stream()
                    .filter(value -> value instanceof Bishop)
                    .count();

            assertThat(bishopCount).isEqualTo(2);
        }

        @DisplayName("ofMainPieces 메서드는 2개의 룩 인스턴스를 생성한다.")
        @Test
        void ofMainPieces_TwoRooks() {
            long rookCount = squares.stream()
                    .filter(value -> value instanceof Rook)
                    .count();

            assertThat(rookCount).isEqualTo(2);
        }
    }
}
