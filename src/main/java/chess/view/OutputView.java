package chess.view;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;

import java.util.Map;

public class OutputView {

    private static final String START_CHESS_GAME_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String MOVE_COMMAND_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String STATUS_COMMAND_MESSAGE = "> 점수 확인 : status";
    private static final String END_COMMAND_MESSAGE = "> 게임 종료 : end";
    private static final String STATUS_TITLE_MESSAGE = "[현재 점수]";
    private static final String STATUS_WHITE_SCORE_MESSAGE = "White 팀: %.1f점";
    private static final String STATUS_BLACK_SCORE_MESSAGE = "White 팀: %.1f점";
    private static final String STATUS_WHITE_WIN_MESSAGE = "결과: White 팀 승리";
    private static final String STATUS_BLACK_WIN_MESSAGE = "결과: Black 팀 승리";
    private static final String STATUS_DRAW_MESSAGE = "결과: 무승부";
    private static final String NEW_LINE = "\n";
    private static final String RESULT_WHITE_WIN_MESSAGE = "White 팀 승리!";
    private static final String RESULT_BLACK_WIN_MESSAGE = "Black 팀 승리!";
    private static final String ERROR = "[ERROR] ";

    public static void printStart() {
        System.out.println(START_CHESS_GAME_MESSAGE);
        System.out.println(START_COMMAND_MESSAGE);
        System.out.println(MOVE_COMMAND_MESSAGE);
        System.out.println(STATUS_COMMAND_MESSAGE);
        System.out.println(END_COMMAND_MESSAGE);
    }

    public static void printBoard(final Board board) {
        final Map<Position, Piece> chessBoard = board.getBoard();

        for (final Rank rank : Rank.values()) {
            printEachRank(chessBoard, rank);
        }
        System.out.println();
    }

    private static void printEachRank(final Map<Position, Piece> chessBoard, final Rank rank) {
        for (final File file : File.values()) {
            final Position position = Position.of(file, rank);
            final Piece piece = chessBoard.get(position);
            System.out.printf(PieceName.findByPiece(piece));
        }
        System.out.println();
    }

    public static void printStatus(final Score white, final Score black) {
        System.out.println(STATUS_TITLE_MESSAGE);
        System.out.println(String.format(STATUS_WHITE_SCORE_MESSAGE, white.value()));
        System.out.println(String.format(STATUS_BLACK_SCORE_MESSAGE, black.value()));
    }

    public static void printScoreWinning(final Score white, final Score black) {
        if (white.value() > black.value()) {
            System.out.println(STATUS_WHITE_WIN_MESSAGE + NEW_LINE);
            return;
        }
        if (white.value() < black.value()) {
            System.out.println(STATUS_BLACK_WIN_MESSAGE + NEW_LINE);
            return;
        }
        System.out.println(STATUS_DRAW_MESSAGE + NEW_LINE);
    }

    public static void printResultWinning(final Team team) {
        if (team == Team.WHITE) {
            System.out.println(RESULT_WHITE_WIN_MESSAGE);
        }
        if (team == Team.BLACK) {
            System.out.println(RESULT_BLACK_WIN_MESSAGE);
        }
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR + e.getMessage());
    }
}
