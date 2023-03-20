package domain.chessgame;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardFactory;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import domain.piece.Color;
import domain.type.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("본인 턴인 경우만 움직일 수 있다.")
    void validateTurnTest() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoardFactory.generate());

        //when&then
        assertThatThrownBy(() -> chessGame.move(PositionFactory.createPosition("b7"), PositionFactory.createPosition("b6")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("지금은 WHITE의 턴입니다.");
    }

    @Test
    @DisplayName("이동경로 상에 장애물이 있다면 이동할 수 없다.")
    void routeTest() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoardFactory.generate());

        //when&then
        assertThatThrownBy(() -> chessGame.move(PositionFactory.createPosition("a1"), PositionFactory.createPosition("a3")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("장애물이 있어 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("InitPawn이 움직이면 2칸을 이동할 수 있다.")
    void initPawnTest() {
        //given
        ChessBoard chessBoard = ChessBoardFactory.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        //when
        final Position source = PositionFactory.createPosition("a2");
        final Position target = PositionFactory.createPosition("a4");

        chessGame.move(source, target);

        // then
        final boolean isPawn = chessBoard.isSameType(target, PieceType.PAWN);
        assertThat(isPawn).isTrue();
    }

    @Test
    @DisplayName("상대편 말이 있을 경우 Pawn이 대각으로 움직일 수 있다.")
    void whenPieceMove_thenSuccess() {
        //given
        ChessBoard chessBoard = ChessBoardFactory.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        //when
        chessGame.move(PositionFactory.createPosition("a2"), PositionFactory.createPosition("a4"));
        chessGame.move(PositionFactory.createPosition("b7"), PositionFactory.createPosition("b6"));
        chessGame.move(PositionFactory.createPosition("a4"), PositionFactory.createPosition("a5"));
        chessGame.move(PositionFactory.createPosition("b6"), PositionFactory.createPosition("a5"));

        //then

        final boolean isSameColor = chessBoard.isSameColor(PositionFactory.createPosition("a5"), Color.BLACK);
        assertThat(isSameColor).isTrue();
    }

    @Test
    @DisplayName("initPawn이 움직이면 Pawn으로 상태가 변하고, pawn은 한 칸만 이동할 수 있다.")
    void whenInitPawnMove_thenFail() {
        //given
        ChessBoard chessBoard = ChessBoardFactory.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        //when
        chessGame.move(PositionFactory.createPosition("a2"), PositionFactory.createPosition("a4"));
        chessGame.move(PositionFactory.createPosition("b7"), PositionFactory.createPosition("b6"));

        // then
        assertThatThrownBy(() -> chessGame.move(PositionFactory.createPosition("a4"), PositionFactory.createPosition("a6")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

    @Test
    @DisplayName("목적지에 우리팀 기물이 있을 때 이동할 수 없다.")
    void whenPieceMove_thenFail() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoardFactory.generate());

        //when&then
        assertThatThrownBy(() -> chessGame.move(PositionFactory.createPosition("a1"), PositionFactory.createPosition("a2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("잘못된 도착 지점입니다.");
    }

}
