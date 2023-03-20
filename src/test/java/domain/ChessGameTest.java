package domain;

import domain.chessboard.ChessBoard;
import domain.chessboard.Type;
import domain.coordinate.MovePosition;
import domain.coordinate.PositionFactory;
import domain.piece.Color;
import domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("본인 턴인 경우만 움직일 수 있다.")
    void validateTurnTest() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.generate());

        //when&then
        assertThatThrownBy(() -> chessGame.move(MovePosition.of(PositionFactory.createPosition("b7"), PositionFactory.createPosition("b6"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("BLACK의 턴이 아닙니다.");
    }

    @Test
    @DisplayName("이동경로 상에 장애물이 있다면 이동할 수 없다.")
    void routeTest() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.generate());

        //when&then
        assertThatThrownBy(() -> chessGame.move(MovePosition.of(PositionFactory.createPosition("a1"), PositionFactory.createPosition("a3"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("장애물이 있어 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("InitPawn이 움직이면 2칸을 이동할 수 있다.")
    void initPawnTest() {
        //given
        ChessBoard chessBoard = ChessBoard.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        //when
        chessGame.move(MovePosition.of(PositionFactory.createPosition("a2"), PositionFactory.createPosition("a4")));

        // then
        final Type a4 = chessBoard.findSquare(PositionFactory.createPosition("a4")).getType();

        assertThat(a4).isEqualTo(PieceType.PAWN);
    }

    @Test
    @DisplayName("상대편 말이 있을 경우 Pawn이 대각으로 움직일 수 있다.")
    void whenPieceMove_thenSuccess() {
        //given
        ChessBoard chessBoard = ChessBoard.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        //when
        chessGame.move(MovePosition.of(PositionFactory.createPosition("a2"), PositionFactory.createPosition("a4")));
        chessGame.move(MovePosition.of(PositionFactory.createPosition("b7"), PositionFactory.createPosition("b6")));
        chessGame.move(MovePosition.of(PositionFactory.createPosition("a4"), PositionFactory.createPosition("a5")));
        chessGame.move(MovePosition.of(PositionFactory.createPosition("b6"), PositionFactory.createPosition("a5")));

        //then
        final Color a5 = chessBoard.findSquare(PositionFactory.createPosition("a5")).getSquareStatus().getColor();
        assertThat(a5).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("initPawn이 움직이면 Pawn으로 상태가 변하고, pawn은 한 칸만 이동할 수 있다.")
    void whenInitPawnMove_thenFail() {
        //given
        ChessBoard chessBoard = ChessBoard.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        //when
        chessGame.move(MovePosition.of(PositionFactory.createPosition("a2"), PositionFactory.createPosition("a4")));
        chessGame.move(MovePosition.of(PositionFactory.createPosition("b7"), PositionFactory.createPosition("b6")));

        // then
        assertThatThrownBy(() -> chessGame.move(MovePosition.of(PositionFactory.createPosition("a4"), PositionFactory.createPosition("a6"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @Test
    @DisplayName("목적지에 우리팀 기물이 있을 때 이동할 수 없다.")
    void whenPieceMove_thenFail() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.generate());

        //when&then
        assertThatThrownBy(() -> chessGame.move(MovePosition.of(PositionFactory.createPosition("a1"), PositionFactory.createPosition("a2"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

}
