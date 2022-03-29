package chess.view;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_FORMAT = "> %s : %s%n";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String GAME_START_COMMAND_NAME = "게임 시작";
    private static final String GAME_MOVE_COMMAND_NAME = "게임 이동";
    private static final String GAME_END_COMMAND_NAME = "게임 종료";
    private static final String GAME_MOVE_COMMAND = "move source위치 target위치 - 예. move b2 b3";
    private static final String RESULT_MESSAGE_SUFFIX = "입니다.";
    private static final int BOARD_ROW_AND_COLUMN_UNIT = 8;
    private static final int UNIT_DIVISION_REMAINDER = 0;
    private static final String END_GAME_MESSAGE = "해당 게임이 종료되었습니다.";
    private static final String RESULT_SCORE_FORMAT = "백 진영 점수 : %.1f%n흑 진영 점수 : %.1f%n";

    public void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.printf(GAME_COMMAND_FORMAT, GAME_START_COMMAND_NAME, START_COMMAND);
        System.out.printf(GAME_COMMAND_FORMAT, GAME_END_COMMAND_NAME, END_COMMAND);
        System.out.printf(GAME_COMMAND_FORMAT, GAME_MOVE_COMMAND_NAME, GAME_MOVE_COMMAND);
    }

    public void printBoard(Map<Position, Piece> board) {
        int count = 0;
        for (Position position : board.keySet()) {
            final Piece piece = board.get(position);
            String content = makeBoardContentString(piece);
            System.out.print(content);
            count++;
            if (count % BOARD_ROW_AND_COLUMN_UNIT == UNIT_DIVISION_REMAINDER) {
                System.out.println();
            }
        }
    }

    private String makeBoardContentString(Piece piece) {
        if (piece.isNullPiece()) {
            return piece.getPieceNameCharacter();
        }
        return decideCaseByCamp(piece);
    }

    private String decideCaseByCamp(Piece piece) {
        if (piece.isBlack()) {
            return piece.getPieceNameCharacter().toUpperCase();
        }
        return piece.getPieceNameCharacter();
    }

    public void printFinishMessage() {
        System.out.println(END_GAME_MESSAGE);
    }

    public void printStatus(double statusOfWhite, double statusOfBlack) {
        System.out.printf(RESULT_SCORE_FORMAT, statusOfWhite, statusOfBlack);
    }

    public void printResultMessage(final String resultMessage) {
        System.out.println(resultMessage + RESULT_MESSAGE_SUFFIX);
    }
}
