package chess.view;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class OutputView {

    private static final int UPPER_BOUND = 8;
    private static final int LOWER_BOUND = 1;
    private static final String NEW_LINE = "\n";
    private static final String NO_PIECE = ".";
    private static final String DELIMITER = " : ";

    public static void printGameStartInstruction() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printChessBoard(Board board) {
        Pieces pieces = board.getPieces();
        StringBuilder stringBuilder = new StringBuilder();
        for (int rank = UPPER_BOUND; rank >= LOWER_BOUND; rank--) {
            stringBuilder.append(parsePerRank(pieces, rank));
            stringBuilder.append(" ").append(rank).append(NEW_LINE);
        }
        stringBuilder.append("abcdefgh");
        System.out.println(stringBuilder.toString());
    }

    private static String parsePerRank(Pieces pieces, int rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = LOWER_BOUND; file <= UPPER_BOUND; file++) {
            Piece piece = pieces.findByPosition(new Position(file, rank));
            if (piece == null) {
                stringBuilder.append(NO_PIECE);
                continue;
            }
            stringBuilder.append(piece.toString());
        }
        return stringBuilder.toString();
    }

    public static void printScore(Board board) {
        for (Team team : Team.values()) {
            System.out.println(team.getName() + DELIMITER + board.calculateScoreByTeam(team));
        }
        System.out.println();
    }

    public static void printTeamWithHigherScore(Board board) {
        double teamBlackScore = board.calculateScoreByTeam(Team.BLACK);
        double teamWhiteScore = board.calculateScoreByTeam(Team.WHITE);
        if (teamBlackScore == teamWhiteScore) {
            System.out.println("무승부입니다.");
        }
        if (teamBlackScore > teamWhiteScore) {
            System.out.println(Team.BLACK.getName() + "이 승리했습니다.");
        }
        if (teamBlackScore < teamWhiteScore) {
            System.out.println(Team.WHITE.getName() + "이 승리했습니다.");
        }
        System.out.println();
    }

    public static void printWinner(Team team) {
        System.out.println(team.getName() + "이 승리했습니다.");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
