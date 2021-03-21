package chess.view;

import chess.controller.dto.ScoresDTO;
import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.player.type.TeamColor;
import chess.domain.position.Position;
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
            Cell cell = board.find(Position.of(file, rank));
            printCell(cell);
        }
        System.out.println();
    }

    private static void printCell(Cell cell) {
        if (cell.isEmpty()) {
            System.out.print(EMPTY_CELL);
            return;
        }
        Piece piece = cell.piece();
        System.out.print(piece.getName());
    }

    public static void printScores(ScoresDTO scoresDTO) {
        double blackTeamScore = scoresDTO.blackPlayerScore();
        double whiteTeamScore = scoresDTO.whitePlayerScore();
        System.out.printf("흑팀 점수 : %.1f, 백팀 점수 : %.1f\n", blackTeamScore, whiteTeamScore);
    }

    public static void printWinnerTeamColor(TeamColor winnerTeamColor) {
        System.out.println(winnerTeamColor.koreanColorName() + "팀이 이겼습니다.");
    }
}
