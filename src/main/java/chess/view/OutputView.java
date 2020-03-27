package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Line;
import chess.domain.piece.GamePiece;
import chess.domain.player.Player;
import chess.domain.score.Score;

import java.util.Map;

public class OutputView {

    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b1 b3");
    }

    public static void printBoard(Board board) {
        for (Line row : board.getRows()) {
            for (GamePiece piece : row.getGamePieces()) {
                System.out.print(piece.getName());
            }
            System.out.println();
        }
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printScore(Map<Player, Score> scores) {
        for (Player player : scores.keySet()) {
            System.out.printf("%s: %.1f점", player.getName(), scores.get(player).getScore());
            System.out.println();
        }
    }
}
