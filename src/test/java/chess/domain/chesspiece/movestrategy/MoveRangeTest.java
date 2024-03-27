package chess.domain.chesspiece.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoveRangeTest {
    private ChessBoard chessBoard;
    private MoveRange moveRange;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        moveRange = new MoveRange();
    }

    @Test
    void 앞_한_칸에_다른_체스_말이_없으면_앞_한_칸을_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addForward(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 앞_한_칸에_다른_체스_말이_있으면_앞_한_칸을_이동할_수_있는_범위에_추가하지_않는다() {
        Square startSquare = new Square(Lettering.A, Numbering.ONE);
        moveRange.addForward(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).isEmpty();
    }

    @Test
    void 앞으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addContinuousForward(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(4);
    }

    @Test
    void 뒤_한_칸에_다른_체스_말이_없으면_뒤_한_칸을_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.A, Numbering.SEVEN);
        moveRange.addBackward(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 뒤_한_칸에_다른_체스_말이_있으면_뒤_한_칸을_이동할_수_있는_범위에_추가하지_않는다() {
        Square startSquare = new Square(Lettering.A, Numbering.EIGHT);
        moveRange.addBackward(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).isEmpty();
    }

    @Test
    void 뒤로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        Square startSquare = new Square(Lettering.A, Numbering.SEVEN);
        moveRange.addContinuousBackward(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(4);
    }

    @Test
    void 왼쪽_한_칸에_다른_체스_말이_없으면_왼쪽_한_칸을_이동할_수_있는_범위에_추가한다() {
        chessBoard.movePiece(new Square(Lettering.H, Numbering.TWO), new Square(Lettering.H, Numbering.THREE));
        Square startSquare = new Square(Lettering.H, Numbering.THREE);
        moveRange.addLeft(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 왼쪽_한_칸에_다른_체스_말이_있으면_왼쪽_한_칸을_이동할_수_있는_범위에_추가하지_않는다() {
        Square startSquare = new Square(Lettering.H, Numbering.TWO);
        moveRange.addLeft(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).isEmpty();
    }

    @Test
    void 왼쪽으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        chessBoard.movePiece(new Square(Lettering.H, Numbering.TWO), new Square(Lettering.H, Numbering.THREE));
        Square startSquare = new Square(Lettering.H, Numbering.THREE);
        moveRange.addContinuousLeft(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(7);
    }

    @Test
    void 오른쪽_한_칸에_다른_체스_말이_없으면_오른쪽_한_칸을_이동할_수_있는_범위에_추가한다() {
        chessBoard.movePiece(new Square(Lettering.A, Numbering.TWO), new Square(Lettering.A, Numbering.THREE));
        Square startSquare = new Square(Lettering.A, Numbering.THREE);
        moveRange.addRight(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 오른쪽_한_칸에_다른_체스_말이_있으면_오른쪽_한_칸을_이동할_수_있는_범위에_추가하지_않는다() {
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addRight(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).isEmpty();
    }

    @Test
    void 오른쪽으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        chessBoard.movePiece(new Square(Lettering.A, Numbering.TWO), new Square(Lettering.A, Numbering.THREE));
        Square startSquare = new Square(Lettering.A, Numbering.THREE);
        moveRange.addContinuousRight(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(7);
    }

    @Test
    void 체스_말이_폰인_경우_왼쪽_대각선_한_칸에_다른_체스_말이_있으면_왼쪽_대각선_한_칸을_이동할_수_있는_범위에_추가한다() {
        chessBoard.movePiece(new Square(Lettering.G, Numbering.TWO), new Square(Lettering.G, Numbering.THREE));
        Square startSquare = new Square(Lettering.H, Numbering.TWO);
        moveRange.addLeftForwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 체스_말이_폰이_아닌_경우_왼쪽_대각선_한_칸에_다른_체스_말이_없으면_왼쪽_대각선_한_칸을_이동할_수_있는_범위에_추가한다() {
        chessBoard.movePiece(new Square(Lettering.G, Numbering.TWO), new Square(Lettering.G, Numbering.THREE));
        Square startSquare = new Square(Lettering.H, Numbering.ONE);
        moveRange.addLeftForwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 왼쪽_대각선으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        Square startSquare = new Square(Lettering.H, Numbering.TWO);
        moveRange.addContinuousLeftForwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(4);
    }

    @Test
    void 체스_말이_폰인_경우_오른쪽_대각선_한_칸에_다른_체스_말이_있으면_오른쪽_대각선_한_칸을_이동할_수_있는_범위에_추가한다() {
        chessBoard.movePiece(new Square(Lettering.B, Numbering.TWO), new Square(Lettering.B, Numbering.THREE));
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addRightForwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 체스_말이_폰이_아닌_경우_오른쪽_대각선_한_칸에_다른_체스_말이_없으면_오른쪽_대각선_한_칸을_이동할_수_있는_범위에_추가한다() {
        chessBoard.movePiece(new Square(Lettering.B, Numbering.TWO), new Square(Lettering.B, Numbering.THREE));
        Square startSquare = new Square(Lettering.A, Numbering.ONE);
        moveRange.addRightForwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 오른쪽_대각선으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addContinuousRightForwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(4);
    }

    @Test
    void 왼쪽_대각선_뒤_한_칸에_다른_체스_말이_없으면_왼쪽_대각선_뒤_한_칸을_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.H, Numbering.SEVEN);
        moveRange.addLeftBackwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 왼쪽_대각선_뒤_한_칸에_다른_체스_말이_있으면_왼쪽_대각선_뒤_한_칸을_이동할_수_있는_범위에_추가하지_않는다() {
        Square startSquare = new Square(Lettering.H, Numbering.EIGHT);
        moveRange.addLeftBackwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).isEmpty();
    }

    @Test
    void 왼쪽_대각선_뒤쪽으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        Square startSquare = new Square(Lettering.H, Numbering.SEVEN);
        moveRange.addContinuousLeftBackwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(4);
    }

    @Test
    void 오른쪽_대각선_뒤_한_칸에_다른_체스_말이_없으면_오른쪽_대각선_뒤_한_칸을_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.G, Numbering.SEVEN);
        moveRange.addRightBackwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 오른쪽_대각선_뒤_한_칸에_다른_체스_말이_있으면_오른쪽_대각선_뒤_한_칸을_이동할_수_있는_범위에_추가하지_않는다() {
        Square startSquare = new Square(Lettering.G, Numbering.EIGHT);
        moveRange.addRightBackwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).isEmpty();
    }

    @Test
    void 오른쪽_대각선_뒤쪽으로_두_칸_이상_움직일때_이동_할_수_있는_범위는_이동_경로에서_비어있는_체스_칸의_개수이다() {
        Square startSquare = new Square(Lettering.A, Numbering.SEVEN);
        moveRange.addContinuousRightBackwardDiagonal(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(4);
    }

    @Test
    void 뒤_오른쪽_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.A, Numbering.SEVEN);
        moveRange.addBackwardRightLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 뒤_왼쪽_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.H, Numbering.SEVEN);
        moveRange.addBackwardLeftLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 앞_오른쪽_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addForwardRightLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 앞_왼쪽_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.H, Numbering.TWO);
        moveRange.addForwardLeftLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 왼쪽_앞_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.H, Numbering.TWO);
        moveRange.addLeftForwardLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 왼쪽_뒤_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.H, Numbering.SEVEN);
        moveRange.addLeftBackwardLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 오른쪽_앞_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.A, Numbering.TWO);
        moveRange.addRightForwardLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }

    @Test
    void 오른쪽_뒤_L_모양으로_움직일때_목적지에_다른_체스_말이_없으면_이동할_수_있는_범위에_추가한다() {
        Square startSquare = new Square(Lettering.A, Numbering.SEVEN);
        moveRange.addRightBackwardLShape(chessBoard, startSquare);

        assertThat(moveRange.getMoveRange()).hasSize(1);
    }
}
