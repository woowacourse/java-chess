package chess.view;

import chess.dto.ChessBoardDto;
import java.util.List;

public class OutputView {

    private static final String GAME_START = "> 체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_MOVE_DESCRIPTION = String.format(
        "%s source위치 target위치 - 예. %s b2 b3", Command.MOVE.getAnswer(), Command.MOVE.getAnswer());
    private static final String GAME_COMMAND_REQUEST = String.format(
        "> 게임 시작 : %s\n> 게임 종료 : %s\n> 게임 이동 : %s\n",
        Command.START.getAnswer(), Command.END.getAnswer(), GAME_COMMAND_MOVE_DESCRIPTION);

    public void printStartMessage() {
        System.out.println(GAME_START);
        System.out.println(GAME_COMMAND_REQUEST);
    }

    public void printBoard(final ChessBoardDto chessBoard) {
        System.out.println();
        List<List<String>> board = chessBoard.getBoard();
        for (List<String> rank : board) {
            printRank(rank);
        }
        System.out.println();
    }

    private void printRank(final List<String> rank) {
        for (String value : rank) {
            System.out.print(value);
        }
        System.out.println();
    }

    public void printErrorMessage(final IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

}
