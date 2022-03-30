package chess.view;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.piece.Piece;
import chess.domain.state.GameState;

import java.util.Map;

public class OutputView {
    public static void printChessBoard(Map<Position, Piece> board) {
        for (Row row : Row.values()) {
            printRank(board, row);
        }
    }

    private static void printRank(Map<Position, Piece> board, Row row) {
        for (Column col : Column.values()) {
            System.out.print(board.get(Position.of(col, row)).getSymbol());
        }
        System.out.println();
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printFinishedGame(GameState gameState) {
        printChessBoard(gameState.getBoard());
        System.out.println("이긴 팀은 " + gameState.getTeam());
    }

    public static void printStatus(double teamScore, double score) {
        System.out.printf("WHITE팀 점수는%f\n" +
                "BLACK팀 점수는%f\n", teamScore, score);
    }

    public static void errorMessage(String message) {
        System.out.println(message);
    }
}
