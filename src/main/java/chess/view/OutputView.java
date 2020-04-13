package chess.view;

import java.util.List;
import java.util.Map;

import chess.domain.player.PlayerColor;
import chess.domain.result.ChessResult;
import chess.domain.result.Score;
import chess.dto.LineDto;

public class OutputView {

    public static void printStart() {
        System.out.println(String.join("\n",
                "> 체스 게임을 시작합니다.",
                "> 게임 시작 : start",
                "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b1 b3")
        );
    }

    public static void printBoard(List<LineDto> rows) {
        rows.stream()
                .map(LineDto::getGamePieces)
                .forEach(pieces -> {
                    pieces.forEach(piece -> System.out.print(piece.getName()));
                    System.out.println();
                });
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
