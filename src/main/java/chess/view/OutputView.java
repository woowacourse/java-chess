package chess.view;

import chess.domain.ChessPiece;
import chess.domain.Column;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printChessBoard(Map<Column, List<ChessPiece>> chessBoard) {
        chessBoard.values()
                .forEach(OutputView::printOneRow);
    }

    private static void printOneRow(List<ChessPiece> row) {
        row.forEach(chessPiece -> System.out.print(chessPiece.getName()));
        System.out.println();
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }
}
