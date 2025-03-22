package chess.piece;

import static chess.Fixtures.B2;
import static chess.Fixtures.B3;
import static chess.Fixtures.B4;
import static chess.Fixtures.B6;
import static chess.Fixtures.B7;
import static chess.Fixtures.D6;
import static chess.Fixtures.E5;
import static chess.Fixtures.H2;
import static chess.Fixtures.H4;
import static chess.Fixtures.H5;
import static chess.Fixtures.H6;
import static org.assertj.core.api.Assertions.assertThat;

import chess.ChessBoard;
import chess.Color;
import java.util.List;
import org.junit.jupiter.api.Test;

class WhitePawnTest {

    @Test
    void 화이트_폰은_아래로_한칸_이동할_수_있다() {
        //given
        WhitePawn whitePawn = new WhitePawn(H5);

        //when
        boolean movable = whitePawn.isMovable(new ChessBoard(List.of()), H6);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 화이트_폰은_초기에_아래로_두칸_이동할_수_있다() {
        //given
        WhitePawn whitePawn = new WhitePawn(H2);

        //when
        boolean movable = whitePawn.isMovable(new ChessBoard(List.of()), H4);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 화이트_폰은_아래_대각선에_상대_기물이_있다면_대각선으로_이동할_수_있다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Rook(Color.BLACK, D6)
        ));
        WhitePawn whitePawn = new WhitePawn(E5);

        //when
        boolean movable = whitePawn.isMovable(chessBoard, D6);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 화이트_폰은_공격이_아닌_이동일때_목적지까지_가는데_기물이_있다면_이동할_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Rook(Color.WHITE, B3)
        ));
        WhitePawn whitePawn = new WhitePawn(B2);

        //when
        boolean movable = whitePawn.isMovable(chessBoard, B4);

        //then
        assertThat(movable).isFalse();
    }

    @Test
    void 화이트_폰은_공격이_아닌_이동일때_목적지에_기물이_있다면_이동할_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Rook(Color.WHITE, B7)
        ));
        WhitePawn whitePawn = new WhitePawn(B6);

        //when
        boolean movable = whitePawn.isMovable(chessBoard, B7);

        //then
        assertThat(movable).isFalse();
    }
}
