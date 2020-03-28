package chess.view;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printStartInfo() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 현재 점수 및 고득점자 확인 : status");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        Map<BoardSquare, Piece> gameBoard = chessBoard.getChessBoard();
        for (int rank = 8; rank >= 1; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                if (gameBoard.containsKey(BoardSquare.of(String.valueOf(file) + rank))) {
                    System.out.print(
                        gameBoard.get(BoardSquare.of(String.valueOf(file) + rank)).getLetter());
                    continue;
                }
                System.out.print(".");
            }
            System.out.println();
        }
    }

    public static void printCanNotMove() {
        System.out.println("해당 좌표로는 이동할 수 없습니다.");
    }

    public static void printWinner(Color color) {
        System.out.println(color.getName() + "(이)가 승리했습니다");
    }

    public static void printScore(Map<Color, Double> teamScore) {
        for (Color color : teamScore.keySet()) {
            System.out.println(color.getName() + "의 점수는 " + teamScore.get(color) + "입니다");
        }
    }

    public static void printWinners(List<Color> winners) {
        System.out.print("고득점자는 ");
        for (Color color : winners) {
            System.out.print(color.getName() + " ");
        }
        System.out.println("입니다.");
    }

    public static void printCanNotStart() {
        System.out.println("현재 게임 진행중이므로 START 할 수 없습니다.");
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }
}

