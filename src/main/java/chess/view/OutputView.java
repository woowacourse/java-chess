package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Result;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String EMPTY_CELL = ".";

    private OutputView() {
    }

    public static void printBoard(Board board) {
        List<Rank> ranks = Arrays.asList(Rank.values());
        List<Rank> reversedRanks = ranks.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        reversedRanks.forEach(rank -> printFilesByRank(rank, board));
        System.out.println();
    }

    private static void printFilesByRank(Rank rank, Board board) {
        for (File file : File.values()) {
            Cell cell = board.find(new Coordinate(file, rank));
            printCell(cell);
        }
        System.out.println();
    }

    private static void printCell(Cell cell) {
        if (cell.isEmpty()) {
            System.out.print(EMPTY_CELL);
            return;
        }
        Piece piece = cell.getPiece();
        System.out.print(piece.getName());
    }

    public static void printScoreResult(Result result) {
        double blackTeamScore = result.getBlackTeamScore();
        double whiteTeamScore = result.getWhiteTeamScore();
        System.out.printf("흑팀 점수 : %.1f, 백팀 점수 : %.1f\n", blackTeamScore, whiteTeamScore);
    }

    public static void printWinner(TeamType winnerTeam) {
        if (winnerTeam == TeamType.BLACK) {
            System.out.println("흑팀이 이겼습니다.");
            return;
        }
        System.out.println("백팀이 이겼습니다.");
    }
}
