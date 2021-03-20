package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Result;
import chess.domain.piece.Piece;
import chess.domain.player.TeamType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printBoard(Board board) {
        List<Rank> ranks = Arrays.asList(Rank.values());
        List<Rank> reversedRanks = ranks.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        reversedRanks.forEach(rank -> printFiles(rank, board));
        System.out.println();
    }

    private static void printFiles(Rank rank, Board board) {
        for (File file : File.values()) {
            Piece piece = board.find(new Coordinate(file, rank)).getPiece();
            printPiece(piece);
        }
        System.out.println();
    }

    private static void printPiece(Piece piece) {
        if (piece != null) {
            System.out.print(piece.getName());
            return;
        }
        System.out.print(".");
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
