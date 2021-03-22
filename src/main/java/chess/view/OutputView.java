package chess.view;

import static chess.controller.Application.WHITE_TEAM_COLOR;

import chess.controller.dto.response.ScoresResponseDTO;
import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.board.type.File;
import chess.domain.board.type.Rank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String EMPTY_CELL = ".";
    private static final String WHITE_TEAM_COLOR_KOREAN = "백";
    private static final String BLACK_TEAM_COLOR_KOREAN = "흑";

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
            Cell cell = board.findCell(Position.of(file, rank));
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

    public static void printScores(ScoresResponseDTO scoresResponseDTO) {
        double blackTeamScore = scoresResponseDTO.getBlackTeamScore();
        double whiteTeamScore = scoresResponseDTO.getWhiteTeamScore();
        System.out.printf("흑 팀 점수 : %.1f, 백 팀 점수 : %.1f\n", blackTeamScore, whiteTeamScore);
    }

    public static void printWinnerTeamColor(String winnerTeamColor) {
        System.out.println(getKoreanTeamColorName(winnerTeamColor) + " 팀이 이겼습니다.");
    }

    private static String getKoreanTeamColorName(String teamColor) {
        if (teamColor.equals(WHITE_TEAM_COLOR)) {
            return WHITE_TEAM_COLOR_KOREAN;
        }
        return BLACK_TEAM_COLOR_KOREAN;
    }
}
