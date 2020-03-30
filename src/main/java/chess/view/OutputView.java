package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Square;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printGameInformation() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
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

    public static void printCanNotMove() {
        System.out.println("못움직여요");
    }

    public static void printWinner(Color color) {
        System.out.println(color.getName() + "가 이겼습니다");
    }

    public static void printScore(Map<Color, Double> teamScore) {
        for (Color color : teamScore.keySet()) {
            System.out.println(color.getName() + "의 점수는 " + teamScore.get(color) + "입니다");
        }
    }

    public static void printWinners(List<Color> winners) {
        System.out.print("최고점은 ");
        for (Color color : winners) {
            System.out.print(color.getName() + " ");
        }
        System.out.println("입니다.");
    }

    public static void printStartedErrorMessage() {
        System.out.println("이미 체스게임이 진행되고 있습니다.");
    }
}

