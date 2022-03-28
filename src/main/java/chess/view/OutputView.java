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
    private static final String EMPTY_PIECE_CHARACTER = ".";
    private static final String INITIAL_LETTER_BISHOP = "b";
    private static final String INITIAL_LETTER_KING = "k";
    private static final String INITIAL_LETTER_KNIGHT = "n";
    private static final String INITIAL_LETTER_PAWN = "p";
    private static final String INITIAL_LETTER_QUEEN = "q";
    private static final String INITIAL_LETTER_ROOK = "r";
    private static final String ERROR_NOT_EXISTING_PIECE_MESSAGE = "존재하지 않는 기물입니다.";
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
            String content = makeBoardContentString(board.get(position));
            System.out.print(content);
            count++;
            if (count % BOARD_ROW_AND_COLUMN_UNIT == UNIT_DIVISION_REMAINDER) {
                System.out.println();
            }
        }
    }

    private String makeBoardContentString(Piece piece) {
        if (piece.isNull()) {
            return EMPTY_PIECE_CHARACTER;
        }
        return decideCaseByCamp(piece);
    }

    private String decideCaseByCamp(Piece piece) {
        String convertedString = convertPieceToString(piece);
        if (piece.isBlack()) {
            return convertedString.toUpperCase();
        }
        return convertedString;
    }

    private String convertPieceToString(Piece piece) {
        if (piece.isBishop()) {
            return INITIAL_LETTER_BISHOP;
        }
        if (piece.isKing()) {
            return INITIAL_LETTER_KING;
        }
        if (piece.isKnight()) {
            return INITIAL_LETTER_KNIGHT;
        }
        if (piece.isPawn()) {
            return INITIAL_LETTER_PAWN;
        }
        if (piece.isQueen()) {
            return INITIAL_LETTER_QUEEN;
        }
        if (piece.isRook()) {
            return INITIAL_LETTER_ROOK;
        }
        throw new IllegalArgumentException(ERROR_NOT_EXISTING_PIECE_MESSAGE);
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
