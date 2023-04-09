package chess.domain.chessboard;

import chess.PieceFactory;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import study.PieceParameterResolver;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(PieceParameterResolver.class)
class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void beforeEach() {
        chessBoard = new ChessBoardFactory().createInitialBoard();
    }

    @DisplayName("moveWithCapture 메서드는")
    @Nested
    class moveWithCapture {

        @ParameterizedTest(name = "빈 칸으로 이동하면 Empty 객체를 반환한다")
        @CsvSource(value = {
                "b2, b3"
                , "b2, b4"
                , "b7, b6"
                , "b7, b5"
        })
        void test1(Position from, Position to) {
            assertThat(chessBoard.moveWithCapture(from, to)).isInstanceOf(Empty.class);
        }

        @ParameterizedTest(name = "적이 있는 칸으로 이동하면 적 객체를 반환한다")
        @CsvSource(value = {
                "WHITE~pawn~b2, BLACK~pawn~c3"
                , "BLACK~pawn~c3, WHITE~pawn~b2"
                , "BLACK~king~c3, WHITE~pawn~b2"
        })
        void test2(String toMove, String target) {
            final Piece from = PieceFactory.from(toMove);
            final Piece to = PieceFactory.from(target);
            chessBoard = ChessBoard.of(from, to);

            assertThat(chessBoard.moveWithCapture(from.getPosition(), to.getPosition())).isEqualTo(to);
        }
    }
}
