package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.state.Rook;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessBoardTest {

    @Test
    void 체스판은_64개의_스퀘어를_가진다() {
        assertThat(new ChessBoard())
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(Square.class))
                .hasSize(64);
    }

    @Test
    void 체스판은_각_기물을_규칙에_맞게_배치한다() {
        assertThat(new ChessBoard())
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(Square.class))
                .first()
                .extracting("pieceState")
                .isInstanceOf(Rook.class);
    }

    @Test
    void 체스판은_첫_시작_시_BLACK_이_선으로_두면_예외가_발생한다() {
        //given
        final ChessBoard chessBoard = new ChessBoard();
        final Coordinate BlackPawnFrom = Coordinate.of("a7");
        final Coordinate BlackPawnTo = Coordinate.of("a6");

        //when & then
        assertThatThrownBy(()-> chessBoard.move(BlackPawnFrom, BlackPawnTo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 턴은 WHITE입니다!");
    }

    @Test
    void 체스판은_한_팀이_연속으로_두면_예외가_발생한다() {
        //given
        final ChessBoard chessBoard = new ChessBoard();
        final Coordinate WhitePawnFrom = Coordinate.of("a2");
        final Coordinate WhitePawnTo = Coordinate.of("a3");
        final Coordinate WhitePawnFrom2 = Coordinate.of("b2");
        final Coordinate WhitePawnTo2 = Coordinate.of("b3");

        //when
        chessBoard.move(WhitePawnFrom, WhitePawnTo);

        //when & then
        assertThatThrownBy(()-> chessBoard.move(WhitePawnFrom2, WhitePawnTo2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 턴은 BLACK입니다!");
    }
}
