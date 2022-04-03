package chess.console.view;

import chess.Controller.dto.PieceDto;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import java.util.List;

public class OutputView {

    private static final double KING_CAUGHT_AND_LOST = -1.0;
    private static final int BOARD_ROW_MAX_POSITION = 8;
    private static final int BOARD_ROW_MIN_POSITION = 1;

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 현재 스코어 확인: status");
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printBoard(final PiecesDto piecesDto) {
        final List<PieceDto> pieces = piecesDto.getPieces();
        for (int i = BOARD_ROW_MAX_POSITION; i >= BOARD_ROW_MIN_POSITION; i--) {
            printRow(pieces, i);
            System.out.print(System.lineSeparator());
        }
    }

    private static void printRow(final List<PieceDto> pieces, final int rawRow) {
        for (int i = BOARD_ROW_MIN_POSITION; i <= BOARD_ROW_MAX_POSITION; i++) {
            final String position = "" + (char) (97 + i - 1) + rawRow;
            final String pieceSymbol = findPieceSymbol(pieces, position);
            System.out.print(pieceSymbol);
        }
    }

    private static String findPieceSymbol(final List<PieceDto> pieces, final String position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .map(PieceDto::getSymbol)
                .findFirst()
                .orElse(".");
    }

    public static void printStatus(final ScoreDto score) {
        final double blackScore = score.getBlackScore();
        final double whiteScore = score.getWhiteScore();
        if (blackScore == KING_CAUGHT_AND_LOST || whiteScore == KING_CAUGHT_AND_LOST) {
            printWinner(blackScore);
            return;
        }
        printCurrentScore(blackScore, whiteScore);
    }

    private static void printCurrentScore(final double blackScore, final double whiteScore) {
        System.out.println("현재 까지의 스코어:");
        System.out.println("=======================");
        System.out.println("블랙 스코어: " + blackScore);
        System.out.println("화이트 스코어: " + whiteScore);
    }

    private static void printWinner(final double blackScore) {
        if (blackScore == KING_CAUGHT_AND_LOST) {
            System.out.println("화이트의 승리입니다");
        }
        System.out.println("블랙의 승리입니다");
    }
}
