package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.Square;

import java.util.Map;

public class OutputView {

    public static void printStartGame() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printStartEndOption() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        Map<Square, Piece> gameBoard = chessBoard.getChessBoard();
        for (int rank = 8; rank >= 1; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                if (gameBoard.containsKey(Square.of(String.valueOf(file) + rank))) {
                    System.out.print(gameBoard.get(Square.of(String.valueOf(file) + rank)).getLetter());
                    continue;
                }
                System.out.print(".");
            }
            System.out.println();
        }
    }
}

