package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessOutputView {
    private ChessOutputView() {
    }

    public static void chessStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 점수 확인 : status - 예. status");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        RenderChessBoardView renderChessBoardView = new RenderChessBoardView(chessBoard);

        renderChessBoardView.getChessBoardView().forEach(ChessOutputView::printOneChessRank);
        System.out.println();
    }

    private static void printOneChessRank(RenderOneChessRank oneChessRank) {
        oneChessRank.getOneChessRank().forEach(System.out::print);
        System.out.println();
    }

    public static void scoreOf(ChessBoard chessBoard, double score) {
        PieceColor playerTurn = chessBoard.getPlayerColor();
        System.out.println(String.format("%s점수: %.1f", playerTurn.getColor(), score));
    }

    public static void printCaughtKing(ChessBoard chessBoard) {
        PieceColor playerTurn = chessBoard.getPlayerColor();
        System.out.println(String.format("왕이 잡혔습니다 %s승", playerTurn.getColor()));
    }

    public static void printError(String error) {
        System.out.println(error);
    }
}
