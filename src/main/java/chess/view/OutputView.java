package chess.view;

import static chess.view.Expressions.COMMAND_END;
import static chess.view.Expressions.COMMAND_START;
import static chess.view.Expressions.PIECE_EXPRESSIONS;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_FORMAT = "> %s : %s%n";
    private static final String GAME_START_COMMAND_NAME = "게임 시작";
    private static final String GAME_MOVE_COMMAND_NAME = "게임 이동";
    private static final String GAME_END_COMMAND_NAME = "게임 종료";
    private static final String GAME_MOVE_COMMAND = "move source위치 target위치 - 예. move b2 b3";

    private static final int COUNT_PIECES_IN_A_ROW = 8;

    private static final String CAMP_BLACK = "흑색 진영";
    private static final String CAMP_WHITE = "백색 진영";
    private static final String FORMAT_WINNER = "%s의 승리입니다.%n";
    private static final String MESSAGE_DRAW = "무승부입니다.";

    private static final String MESSAGE_GAME_END = "해당 게임이 종료되었습니다.";
    private static final String FORMAT_SCORE = "%s 점수 : %.1f%n";

    private static final String ERROR_NO_SUCH_PIECE = "존재하지 않는 기물입니다.";

    public void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.printf(GAME_COMMAND_FORMAT, GAME_START_COMMAND_NAME, COMMAND_START);
        System.out.printf(GAME_COMMAND_FORMAT, GAME_END_COMMAND_NAME, COMMAND_END);
        System.out.printf(GAME_COMMAND_FORMAT, GAME_MOVE_COMMAND_NAME, GAME_MOVE_COMMAND);
    }

    public void printBoard(Map<Position, Piece> board) {
        String pieces = piecesToString(board);
        for (int i = 0; i < COUNT_PIECES_IN_A_ROW; i++) {
            int startIndex = i * COUNT_PIECES_IN_A_ROW;
            int endIndex = startIndex + COUNT_PIECES_IN_A_ROW;
            System.out.println(pieces.substring(startIndex, endIndex));
        }
    }

    private String piecesToString(Map<Position, Piece> board) {
        return board.values().stream()
                .map(this::pieceToString)
                .collect(Collectors.joining());
    }

    private String pieceToString(Piece piece) {
        String convertedString = convertPieceToString(piece);
        return decideCaseByCamp(piece, convertedString);
    }

    private String decideCaseByCamp(Piece piece, String convertedString) {
        if (piece.isCamp(Camp.BLACK)) {
            return convertedString.toUpperCase();
        }
        return convertedString;
    }

    private String convertPieceToString(Piece piece) {
        return PIECE_EXPRESSIONS.keySet().stream()
                .filter(piece::isType)
                .findFirst()
                .map(PIECE_EXPRESSIONS::get)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_SUCH_PIECE));
    }

    public void printFinishMessage() {
        System.out.println(MESSAGE_GAME_END);
    }

    public void printStatus(double statusOfWhite, double statusOfBlack) {
        System.out.printf(FORMAT_SCORE, CAMP_WHITE, statusOfWhite);
        System.out.printf(FORMAT_SCORE, CAMP_BLACK, statusOfBlack);
    }

    public void printWinner(Camp winner) {
        if (winner == Camp.BLACK) {
            printWinner(CAMP_BLACK);
            return;
        }
        if (winner == Camp.WHITE) {
            printWinner(CAMP_WHITE);
            return;
        }
        printDraw();
    }

    private void printWinner(String camp) {
        System.out.printf(FORMAT_WINNER, camp);
    }

    private void printDraw() {
        System.out.println(MESSAGE_DRAW);
    }
}
