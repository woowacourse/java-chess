package chess.view;

import chess.model.Team;
import chess.model.piece.Piece;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Map;

public class OutputView {

    public static final String SCORE_MESSAGE_FORMAT = "%s: %.0f";

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

    public void printScores(final Map<Team, Double> scores) {
        for (Team team : scores.keySet()) {
            System.out.printf(SCORE_MESSAGE_FORMAT, team.getValue(), scores.get(team));
            System.out.println();
        }
    }
}
