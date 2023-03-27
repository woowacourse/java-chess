package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Team;
import chess.domain.piece.state.Rook;
import java.util.List;
import org.assertj.core.api.Assertions;
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
        final Coordinate BlackPawnFrom = Coordinate.from("a7");
        final Coordinate BlackPawnTo = Coordinate.from("a6");

        //when & then
        assertThatThrownBy(() -> chessBoard.move(BlackPawnFrom, BlackPawnTo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 턴은 WHITE입니다!");
    }

    @Test
    void 체스판은_한_팀이_연속으로_두면_예외가_발생한다() {
        //given
        final ChessBoard chessBoard = new ChessBoard();
        final Coordinate WhitePawnFrom = Coordinate.from("a2");
        final Coordinate WhitePawnTo = Coordinate.from("a3");
        final Coordinate WhitePawnFrom2 = Coordinate.from("b2");
        final Coordinate WhitePawnTo2 = Coordinate.from("b3");

        //when
        chessBoard.move(WhitePawnFrom, WhitePawnTo);

        //when & then
        assertThatThrownBy(() -> chessBoard.move(WhitePawnFrom2, WhitePawnTo2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 턴은 BLACK입니다!");
    }

    @Test
    void 체스판은_같은_팀_기물만_가져올_수_있다() {
        //given
        final Team team = Team.WHITE;
        final ChessBoard chessBoard = new ChessBoard();

        //when
        final List<Square> squareList = chessBoard.squaresByTeam(Team.WHITE);

        //then
        assertThat(squareList).allMatch(square -> square.isMyTeam(team));
    }

    @Test
    void 체스판은_한_파일에_연속된_폰이_있는_파일의_개수를_가져올_수_있다() {
        //given
        final Team team = Team.BLACK;
        final ChessBoard chessBoard = new ChessBoard();

        //when
        chessBoard.move(Coordinate.from("a2"), Coordinate.from("a4"));
        chessBoard.move(Coordinate.from("a7"), Coordinate.from("a5"));
        chessBoard.move(Coordinate.from("b2"), Coordinate.from("b4"));
        chessBoard.move(Coordinate.from("a5"), Coordinate.from("b4"));
        final long count = chessBoard.countFileOfContinuousPawnByTeam(team);

        //then
        assertThat(count).isEqualTo(1);
    }
}
