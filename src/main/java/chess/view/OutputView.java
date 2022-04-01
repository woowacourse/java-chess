package chess.view;

import chess.domain.Score;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
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

    public static void printStatus(Score score) {
        System.out.println("WHITE팀 점수는" + score.getTotalScoreWhiteTeam() + "\n"
                + "BLACK팀 점수는" + score.getTotalScoreBlackTeam());
        printWinningTeam(score.getWinningTeam());
    }

    private static void printWinningTeam(Team team) {
        if (team.matchTeam(Team.BLACK)) {
            System.out.println("Black팀이 이기고 있습니다.");
        }
        if (team.matchTeam(Team.WHITE)) {
            System.out.println("White팀이 이기고 있습니다.");
        }
        System.out.println("비기고 있습니다.");
    }

    public static void errorMessage(String message) {
        System.out.println(message);
    }
}
