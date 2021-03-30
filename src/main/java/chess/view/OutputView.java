package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    public static final String GAME_START = "> 체스 게임을 시작합니다.";
    public static final String START_COMMAND = "> 게임 시작 : start";
    public static final String END_COMMAND = "> 게임 종료: end";
    public static final String MOVE_COMMAND = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String GAME_END = "> 체스 게임이 종료되었습니다.";
    public static final String STATUS_COMMAND = "> 결과 확인: status";
    public static final String RESULT_FORMAT = "%s: %.1f점";
    public static final int BOARD_MAXIMUM_INDEX = 7;

    private OutputView() {
    }

    public static void gameStart() {
        System.out.println(GAME_START);
        System.out.println(START_COMMAND);
        System.out.println(END_COMMAND);
        System.out.println(MOVE_COMMAND);
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        for (Map.Entry<Position, Piece> board: chessBoard.getChessBoard().entrySet()) {
            System.out.print(board.getValue().getName());
            newlineByIndex(board);
        }
    }

    private static void newlineByIndex(Map.Entry<Position, Piece> board) {
        if (board.getKey().getColumnAsIndex() == BOARD_MAXIMUM_INDEX) {
            System.out.println();
        }
    }

    public static void printResult(Result result) {
        System.out.printf(RESULT_FORMAT, Color.BLACK.name(), result.score(Color.BLACK));
        System.out.printf(RESULT_FORMAT, Color.WHITE.name(), result.score(Color.WHITE));
    }

    public static void gameEnd() {
        System.out.println(GAME_END);
        System.out.println(STATUS_COMMAND);
    }
}
