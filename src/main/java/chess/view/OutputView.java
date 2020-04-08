package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Line;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.domain.result.ChessResult;
import chess.domain.result.Score;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printStart() {
        System.out.println(String.join("\n",
                "> 체스 게임을 시작합니다.",
                "> 게임 시작 : start",
                "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b1 b3")
        );
    }

    public static void printBoard(Board board) {
        for (Line row : board.getRows()) {
            System.out.println(getPiecesName(row));
        }
    }

    private static String getPiecesName(Line row) {
        return row.getGamePieces()
                .stream()
                .map(GamePiece::getName)
                .collect(Collectors.joining(""));
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printScore(ChessResult chessResult) {
        Map<PlayerColor, Score> scores = chessResult.getResult();
        for (PlayerColor playerColor : scores.keySet()) {
            System.out.printf("%s: %.1f점", playerColor.getName(), scores.get(playerColor).getScore());
            System.out.println();
        }
    }
}
