package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Piece;

import java.util.List;
import java.util.Objects;

public class OutputView {

    public static void printStartGame() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printStartEndOption() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        List<List<Piece>> gameBoard = chessBoard.getChessBoard();
        for (List<Piece> pieces : gameBoard) {
            printChessBoardRow(pieces);
            System.out.println();
        }
    }

    public static void printChessBoard2(ChessBoard chessBoard) {

    }

    private static void printChessBoardRow(List<Piece> pieces) {
        for (Piece piece : pieces) {
            if (Objects.isNull(piece)) {
                System.out.print(".");
                continue;
            }
            System.out.print(piece.getLetter());
        }
    }
}

