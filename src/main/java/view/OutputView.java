package view;

import domain.chessboard.ChessBoard;
import domain.piece.Color;
import domain.chessboard.Square;
import domain.chessboard.Rank;

import java.util.List;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(ChessBoard chessBoard) {
        List<Rank> ranks = chessBoard.getChessBoard();

        for (Rank rank : ranks) {
            printRank(rank);
        }
    }

    private void printRank(final Rank rank) {
        for (Square square : rank.getRank()) {
            System.out.print(convertPieceToElement(square));
        }
        System.out.println();
    }

    private String convertPieceToElement(final Square square) {
//        final String elementName = ChessBoardElement.from(square.getType()).getElementName();
//
//        if (square.getColor() == Color.BLACK) {
//            return elementName.toUpperCase();
//        }
//        return elementName;
        return null;
    }

}
