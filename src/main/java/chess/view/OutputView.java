package chess.view;

import chess.domain.board.*;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;
import chess.domain.result.Scores;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String EMPTY_CELL = ".";

    private OutputView() {
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        Map<Coordinate, Cell> cells = chessBoard.getCells();
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        ranks.forEach(rank -> printFilesByRank(rank, cells));
        System.out.println();
    }

    private static void printFilesByRank(Rank rank, Map<Coordinate, Cell> cells) {
        Arrays.stream(File.values())
                .map(file -> new Coordinate(file, rank))
                .forEach(coordinate -> printCell(coordinate, cells));
        System.out.println();
    }

    private static void printCell(Coordinate coordinate, Map<Coordinate, Cell> cells) {
        Cell cell = cells.get(coordinate);
        if (cell.isEmpty()) {
            System.out.print(EMPTY_CELL);
            return;
        }
        Piece piece = cell.getPiece();
        System.out.print(piece.getName());
    }

    public static void printScoreStatus(Scores scores) {
        double blackTeamScore = scores.getBlackTeamScore();
        double whiteTeamScore = scores.getWhiteTeamScore();
        System.out.printf("흑팀 점수 : %.1f, 백팀 점수 : %.1f\n", blackTeamScore, whiteTeamScore);
    }

    public static void printWinnerTeam(TeamType winnerTeam) {
        if (winnerTeam == TeamType.BLACK) {
            System.out.println("흑팀이 이겼습니다.");
            return;
        }
        System.out.println("백팀이 이겼습니다.");
    }
}
