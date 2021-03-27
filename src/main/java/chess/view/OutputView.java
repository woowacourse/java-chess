package chess.view;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Locale;

public final class OutputView {
    public static final int BOARD_MAX_SIZE = 8;

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 말 이동 : move source위치 target위치 - ex) move b2 b3");
        System.out.println("> 현재 점수 : status");
    }

    public static void printBoard(final Board board) {
        String[][] boardStatus = initBoard();
        putPieceToBoard(board, boardStatus);

        for (int boardSize = 0; boardSize < BOARD_MAX_SIZE; ++boardSize) {
            printOneLine(boardStatus[boardSize]);
            System.out.printf(" | %d", BOARD_MAX_SIZE - boardSize);
            System.out.println();
        }
        printColUI();
    }

    private static void printColUI() {
        System.out.println("----------------");
        for (char alpha = 'a'; alpha <= 'h'; ++alpha) {
            System.out.print(alpha + " ");
        }
        System.out.println();
    }

    private static void putPieceToBoard(final Board board, final String[][] outputBoard) {
        board.toMap().forEach((team, value) -> {
            for (Piece piece : value.toList()) {
                putPieceByTeam(outputBoard, team, piece);
            }
        });
    }

    private static void putPieceByTeam(String[][] board, Team team, Piece piece) {
        Position position = piece.position();
        if (Team.BLACK.equals(team)) {
            board[position.row()][position.col()] = piece.initial();
        }
        if (Team.WHITE.equals(team)) {
            board[position.row()][position.col()] = piece.initial().toLowerCase(Locale.ROOT);
        }
    }

    private static String[][] initBoard() {
        String[][] board = new String[8][8];

        for (int i = 0; i < 8; ++i) {
            Arrays.fill(board[i], ".");
        }
        return board;
    }

    private static void printOneLine(final String[] line) {
        for (String val : line) {
            System.out.print(val + " ");
        }
    }

    public static void printEachTeamScore(final Double blackTeamScore, final Double whiteTeamScore) {
        System.out.println("Black 팀 점수 : " + blackTeamScore);
        System.out.println("White 팀 점수 : " + whiteTeamScore);
    }

    public static void printWinner(final Team winner, final Double blackTeamScore, final Double whiteTeamScore) {
        printEachTeamScore(blackTeamScore, whiteTeamScore);
        System.out.println("승자는 " + winner.name() + "팀 입니다.");
    }

    public static void printError(final String message) {
        System.err.println(message);
    }
}
