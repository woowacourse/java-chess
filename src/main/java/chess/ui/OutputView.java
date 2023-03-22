package chess.ui;

import chess.domain.dto.res.PiecesResponse;

import static chess.domain.Pieces.FIRST_FILE_OF_BLACK;
import static chess.domain.Pieces.FIRST_FILE_OF_WHITE;
import static chess.domain.Pieces.FIRST_RANK;
import static chess.domain.Pieces.LAST_RANK;

public final class OutputView {

    private static final String START_CHESS_GAME = "> 체스 게임을 시작합니다.";
    private static final String INPUT_GAME_START_COMMAND = "> 게임 시작 : start";
    private static final String INPUT_GAME_END_COMMAND = "> 게임 종료 : end";
    private static final String GAME_MOVE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String EMPTY_POSITION = ".";

    public static void printStartGame() {
        System.out.println(START_CHESS_GAME);
        System.out.println(INPUT_GAME_START_COMMAND);
        System.out.println(INPUT_GAME_END_COMMAND);
        System.out.println(GAME_MOVE_MESSAGE);
    }

    public static void printInitializedChessBoard(final PiecesResponse piecesResponse) {
        for (int rank = FIRST_FILE_OF_BLACK; rank >= FIRST_FILE_OF_WHITE; rank--) {
            printChessBoardByRankAndFile(piecesResponse, rank);
        }

        System.out.println(System.lineSeparator() + "abcdefgh");
        System.out.println(System.lineSeparator());
    }

    private static void printChessBoardByRankAndFile(final PiecesResponse piecesResponse, final int rank) {
        for (int file = FIRST_RANK; file <= LAST_RANK; file++) {
            printChessBoard(piecesResponse, rank, file);
        }
        System.out.print(" " + (rank + 1));
        System.out.println();
    }

    private static void printChessBoard(final PiecesResponse piecesResponse, final int rank, final int file) {
        if (piecesResponse.isExistPosition(rank, file)) {
            System.out.print(piecesResponse.findNameByRankAndFile(rank, file));
            return;
        }
        System.out.print(EMPTY_POSITION);
    }

    public static void printChessBoardStatus(final PiecesResponse piecesResponse) {
        printInitializedChessBoard(piecesResponse);
    }

    public static void printErrorMessage(final String message) {
        System.out.println("[ERROR]: " + message);
    }

    public static void printStatus(final double calculateScore) {
        System.out.println("score : " + calculateScore + System.lineSeparator());
    }

    public static void printWinner(final String color) {
        System.out.println(color + " 진영이 이겼습니다.");
    }

}
