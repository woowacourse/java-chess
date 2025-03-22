package chess.piece;

import static chess.Fixtures.B5;
import static chess.Fixtures.D4;
import static chess.Fixtures.H4;
import static chess.Fixtures.H5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.ChessBoard;
import chess.Color;
import chess.Fixtures;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackPawnTest {

    @Test
    void 블랙_폰은_아래로_한칸_이동할_수_있다() {
        //given
        BlackPawn blackPawn = new BlackPawn(H5);

        //when
        boolean movable = blackPawn.isMovable(new ChessBoard(List.of()), H4);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 블랙_폰은_초기에_아래로_두칸_이동할_수_있다() {
        //given
        BlackPawn blackPawn = new BlackPawn(Fixtures.H7);

        //when
        boolean movable = blackPawn.isMovable(new ChessBoard(List.of()), H5);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 블랙_폰은_아래_대각선에_상대_기물이_있다면_대각선으로_이동할_수_있다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Rook(Color.WHITE, Fixtures.D4)
        ));
        BlackPawn blackPawn = new BlackPawn(Fixtures.E5);

        //when
        boolean movable = blackPawn.isMovable(chessBoard, D4);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 블랙_폰은_공격이_아닌_이동일때_목적지까지_가는데_기물이_있다면_이동할_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Rook(Color.WHITE, Fixtures.B6)
        ));
        BlackPawn blackPawn = new BlackPawn(Fixtures.B7);

        //when
        boolean movable = blackPawn.isMovable(chessBoard, B5);

        //then
        assertThat(movable).isFalse();
    }

    @Test
    void 블랙_폰은_공격이_아닌_이동일때_목적지에_기물이_있다면_이동할_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Rook(Color.WHITE, Fixtures.B5)
        ));
        BlackPawn blackPawn = new BlackPawn(Fixtures.B6);

        //when
        boolean movable = blackPawn.isMovable(chessBoard, B5);

        //then
        assertThat(movable).isFalse();
    }
}
