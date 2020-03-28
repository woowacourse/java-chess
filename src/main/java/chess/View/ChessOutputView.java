package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

import java.util.List;

public class ChessOutputView {
    private ChessOutputView() {
    }

    public static void chessStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        RenderChessBoardView renderChessBoardView = new RenderChessBoardView(chessBoard);

        renderChessBoardView.getChessBoardView().forEach(ChessOutputView::printOneChessRank);
    }

    private static void printOneChessRank(List<String> oneChessRank) {
        oneChessRank.forEach(System.out::print);
        System.out.println();
    }


    public static void scoreOf(PieceColor pieceColor, ChessBoard chessBoard) {
        System.out.println(String.format("%s점수: %f", pieceColor.getColor(), chessBoard.calculateScoreOf(pieceColor)));
    }
}
