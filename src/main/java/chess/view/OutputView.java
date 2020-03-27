package chess.view;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printStartGame() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printStartEndOption() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        Map<BoardSquare, Piece> gameBoard = chessBoard.getChessBoard();
        for (int rank = 8; rank >= 1; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                if (gameBoard.containsKey(BoardSquare.of(String.valueOf(file) + rank))) {
                    System.out.print(gameBoard.get(BoardSquare.of(String.valueOf(file) + rank)).getLetter());
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
}

