package chess.view;

import chess.model.Team;
import chess.model.board.GameResult;
import chess.model.piece.Piece;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Map;

public class OutputView {

    private static final String SCORE_MESSAGE_FORMAT = "%s 점수: %.0f%n";
    private static final String WINNER_MESSAGE_FORMAT = "승자: %s%n";

    public void printGameRule() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            printFilesIn(board, rank);
            System.out.println();
        }
    }

    private void printFilesIn(final Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            Piece piece = board.get(Position.of(rank, file));
            PieceView.printSymbolOf(piece);
        }
    }

    public void printScores(GameResult scores) {
        Map<Team, Double> result = scores.getScores();
        for (Team team : result.keySet()) {
            System.out.printf(SCORE_MESSAGE_FORMAT, team.getName(), result.get(team));
        }
        System.out.printf(WINNER_MESSAGE_FORMAT, scores.pickWinner().getName());
    }
}
