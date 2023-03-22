package chess;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    ChessBoard chessBoard = ChessBoard.GenerateChessBoard();

    @Test
    @DisplayName("체스 보드를 생성했을 때 64개의 piece가 생성된다.")
    void shouldSucceedGeneratingChessBoard() {
        int expectedNumberOfPieces = 64;

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(expectedNumberOfPieces);
    }

    @Test
    @DisplayName("체스 보드 생성 후 룩은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingRook() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a1"))).isInstanceOf(Rook.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h1"))).isInstanceOf(Rook.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a8"))).isInstanceOf(Rook.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a8"))).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 나이트는 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingKnight() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b1"))).isInstanceOf(Knight.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g1"))).isInstanceOf(Knight.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b8"))).isInstanceOf(Knight.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g8"))).isInstanceOf(Knight.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 비숍은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingBishop() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c1"))).isInstanceOf(Bishop.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f1"))).isInstanceOf(Bishop.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c8"))).isInstanceOf(Bishop.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f8"))).isInstanceOf(Bishop.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 퀸은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingQueen() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d1"))).isInstanceOf(Queen.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d8"))).isInstanceOf(Queen.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 킹은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingKing() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e1"))).isInstanceOf(King.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e8"))).isInstanceOf(King.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 폰은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingPawn() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h7"))).isInstanceOf(Pawn.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 공백은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingEmpty() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h6"))).isInstanceOf(Empty.class)
        );
    }

    @Test
    @DisplayName("체스보드에 Position 객체를 입력하면 해당 위치에 존재하는 기물이 반환된다")
    void ShouldSucceedFindChessPiece() {

        Position sourcePosition = Position.findPosition("a2");
        Color color = Color.WHITE;
        ChessPiece expectedChessPiece = new Pawn(Color.WHITE);

        ChessPiece chessPiece = chessBoard.findChessPiece(sourcePosition, color);

        assertThat(chessPiece).isEqualTo(expectedChessPiece);
    }

    @Test
    @DisplayName("체스보드에서 자신의 색깔이 아닌 기물의 Position을 입력하면 예외가 발생한다.")
    void shouldFailFindChessPiece() {

        Position sourcePosition = Position.findPosition("a2");
        Color color = Color.BLACK;

        assertThatThrownBy(() -> chessBoard.findChessPiece(sourcePosition, color))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 색상의 기물을 선택할 수 없습니다.");
    }

    @Test
    @DisplayName("체스 기물과 포지션이 주어졌을 때 체스 기물을 목표 지점을 이동시킨다.")
    void shouldSucceedMovePiece() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position targetPosition = Position.findPosition("a3");

        chessBoard.movePiece(pawn, targetPosition);

        assertThat(chessBoard.getChessBoard().get(Position.findPosition("a3"))).isEqualTo(new Pawn(Color.WHITE));
    }

    @Test
    @DisplayName("체스 기물을 이동 시킬 때, 기존 위치에 기물이 공백 객체로 대체된다.")
    void shouldSucceedRemovingChessPiece() {
        Position sourcePosition = Position.findPosition("a2");

        chessBoard.removePiece(sourcePosition);

        assertThat(chessBoard.getChessBoard().get(Position.findPosition("a2"))).isEqualTo(new Empty());
    }

    //    ChessBoard chessBoard = ChessBoard.generateChessBoard();
//
//    @Test
//    @DisplayName("체스판 생성 후 룩은 올바른 위치에 만들어진다.")
//    void shouldSuccessGenerateRook() {
//
//        Assertions.assertAll(
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(1, 1))).isInstanceOf(Rook.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(1, 8))).isInstanceOf(Rook.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(8, 1))).isInstanceOf(Rook.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(8, 8))).isInstanceOf(Rook.class)
//        );
//    }
//
//    @Test
//    @DisplayName("체스판 생성 후 킹은 올바른 위치에 만들어진다.")
//    void shouldSuccessGenerateKing() {
//
//        Assertions.assertAll(
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(5, 1))).isInstanceOf(King.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(5, 8))).isInstanceOf(King.class)
//        );
//    }
//
//    @Test
//    @DisplayName("체스판 생성 후 퀸은 올바른 위치에 만들어진다.")
//    void shouldSuccessGenerateQueen() {
//
//        Assertions.assertAll(
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(4, 1))).isInstanceOf(Queen.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(4, 8))).isInstanceOf(Queen.class)
//        );
//    }
//
//    @Test
//    @DisplayName("체스판 생성 후 나이트는 올바른 위치에 만들어진다.")
//    void shouldSuccessGenerateKnight() {
//
//        Assertions.assertAll(
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(2, 1))).isInstanceOf(Knight.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(7, 1))).isInstanceOf(Knight.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(2, 8))).isInstanceOf(Knight.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(7, 8))).isInstanceOf(Knight.class)
//        );
//    }
//
//    @Test
//    @DisplayName("체스판 생성 후 비숍은 올바른 위치에 만들어진다.")
//    void shouldSuccessGenerateBishop() {
//
//        Assertions.assertAll(
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(3, 1))).isInstanceOf(Bishop.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(6, 1))).isInstanceOf(Bishop.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(3, 8))).isInstanceOf(Bishop.class),
//                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(6, 8))).isInstanceOf(Bishop.class)
//        );
//    }
//
//    @Test
//    @DisplayName("체스판 생성 후 폰은 올바른 위치에 만들어진다.")
//    void shouldSuccessGeneratePawn() {
//        for (int i = 1; i <= 8; i++) {
//            assertThat(chessBoard.getChessBoard().get(Position.initPosition(i, 2))).isInstanceOf(Pawn.class);
//            assertThat(chessBoard.getChessBoard().get(Position.initPosition(i, 7))).isInstanceOf(Pawn.class);
//        }
//    }
//
//    @Test
//    @DisplayName("체스판 생성 후 기물이 없는 곳은 공백 클래스가 위치한다.")
//    void shouldSuccessSpaceIsEmpty() {
//        for (int i = 3; i <= 8; i++) {
//            for (int j = 3; j <= 6; j++) {
//                assertThat(chessBoard.getChessBoard().get(Position.initPosition(i, j))).isInstanceOf(Empty.class);
//            }
//        }
//    }
}
