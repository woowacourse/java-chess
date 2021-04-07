package chess.view;

import static chess.domain.board.ChessBoardInfo.CHESS_BOARD_COLUMN_FIRST;
import static chess.domain.board.ChessBoardInfo.CHESS_BOARD_COLUMN_LAST;
import static chess.domain.board.ChessBoardInfo.CHESS_BOARD_ROW_FIRST;
import static chess.domain.board.ChessBoardInfo.CHESS_BOARD_ROW_LAST;
import static chess.domain.board.ChessBoardInfo.COLUMN_FIRST;
import static chess.domain.board.ChessBoardInfo.COLUMN_LAST;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NOTICE_CHESS_GAME = "> 체스 게임을 시작합니다.";
    private static final String NOTICE_CHESS_GAME_START = "> 게임 시작 : start";
    private static final String NOTICE_CHESS_GAME_END = "> 게임 종료 : end";
    private static final String NOTICE_CHESS_GAME_HOW_MOVE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String SKELETON_RESULT_WHITE_SCORE = "WHITE POINT: %s";
    private static final String SKELETON_RESULT_BLACK_SCORE = "BLACK POINT: %s";
    private static final String WINNER = "승자: %s";

    public static final int ONE_ROW_LINE_SIZE = 8;
    public static final int PRINT_NUMBER_CORRECTION = 1;

    private OutputView() {
    }

    public static void printBoard(Map<Position, Piece> chessBoard) {
        List<String> chessBoardNames = chessBoard.values().stream()
            .map(Piece::getPieceName)
            .collect(Collectors.toList());

        printAllChessBoard(chessBoardNames);
        printRowLineNames();
    }

    private static void printAllChessBoard(List<String> chessBoardNames) {
        for (int row = CHESS_BOARD_ROW_FIRST.getBoardInfo();
            row >= CHESS_BOARD_ROW_LAST.getBoardInfo(); row--) {
            for (int column = CHESS_BOARD_COLUMN_FIRST.getBoardInfo();
                column < CHESS_BOARD_COLUMN_LAST
                    .getBoardInfo();
                column++) {
                System.out.print(chessBoardNames.get(row * ONE_ROW_LINE_SIZE + column));
            }
            System.out.println("  " + (row + PRINT_NUMBER_CORRECTION));
        }
        System.out.println();
    }

    private static void printRowLineNames() {
        for (char row = (char) COLUMN_FIRST.getBoardInfo();
            row <= (char) COLUMN_LAST.getBoardInfo(); row++) {
            System.out.print(row);
        }
        System.out.println();
    }

    public static void printInitMessage() {
        System.out.println(NOTICE_CHESS_GAME);
        System.out.println(NOTICE_CHESS_GAME_START);
        System.out.println(NOTICE_CHESS_GAME_END);
        System.out.println(NOTICE_CHESS_GAME_HOW_MOVE);
    }

    public static void printScore(Result result) {
        System.out.println(String.format(SKELETON_RESULT_WHITE_SCORE, result.whiteScore()));
        System.out.println(String.format(SKELETON_RESULT_BLACK_SCORE, result.blackScore()));
        System.out.println(String.format(WINNER, result.getWinner()));
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
