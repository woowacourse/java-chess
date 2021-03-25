package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.ResultDto;
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
    public static final int CHESS_BOARD_ROW_UP = 7;
    public static final int CHESS_BOARD_ROW_DOWN = 0;
    public static final int CHESS_BOARD_COLUMN_LEFT = 0;
    public static final int CHESS_BOARD_COLUMN_RIGHT = 8;
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
        for (int row = CHESS_BOARD_ROW_UP; row >= CHESS_BOARD_ROW_DOWN; row--) {
            for (int column = CHESS_BOARD_COLUMN_LEFT; column < CHESS_BOARD_COLUMN_RIGHT;
                column++) {
                System.out.print(chessBoardNames.get(row * ONE_ROW_LINE_SIZE + column));
            }
            System.out.println("  " + (row + PRINT_NUMBER_CORRECTION));
        }
        System.out.println();
    }

    private static void printRowLineNames() {
        for (char row = ChessBoard.COLUMN_FIRST; row <= ChessBoard.COLUMN_LAST; row++) {
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

    public static void printScore(ResultDto resultDto) {
        System.out.println(String.format(SKELETON_RESULT_WHITE_SCORE, resultDto.whiteScore()));
        System.out.println(String.format(SKELETON_RESULT_BLACK_SCORE + resultDto.blackScore()));
        System.out.println(String.format(WINNER + resultDto.getWinner()));
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
