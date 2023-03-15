package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Map;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;

public final class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final char BLANK = '.';
    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : %s source위치 target위치 - 예. move b2 b3";

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        print(String.format(START_MESSAGE, START.name().toLowerCase(),
                END.name().toLowerCase(), MOVE.name().toLowerCase()));
    }

    public void printBoard(final Map<Position, Piece> board) {
        final StringBuilder boardMessage = new StringBuilder();
        for (int rank = BOARD_SIZE - 1; rank >= 0; rank--) {
            boardMessage.append(makeFileMessage(board, rank));
            boardMessage.append(System.lineSeparator());
        }
        print(boardMessage.toString());
    }

    private String makeFileMessage(final Map<Position, Piece> board, final int rank) {
        final StringBuilder fileMessage = new StringBuilder();
        for (int file = 0; file < BOARD_SIZE; file++) {
            final Position position = new Position(rank, file);
            if (board.containsKey(position)) {
                fileMessage.append(PieceName.findMessage(board.get(position)));
                continue;
            }
            fileMessage.append(BLANK);
        }
        return fileMessage.toString();
    }
}
