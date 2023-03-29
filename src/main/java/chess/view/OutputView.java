package chess.view;

import chess.domain.Position;
import chess.domain.Result;
import chess.domain.piece.ChessPiece;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final int WHITE_RESULT_INDEX = 0;
    private static final int BLACK_RESULT_INDEX = 1;
    private static final char FIRST_COLUMN = 'a';
    private static final char LAST_COLUMN = 'h';
    private static final int FIRST_RANK = 1;
    private static final int LAST_RANK = 8;

    public static void gameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public static void printChessBoard(Map<Position, ChessPiece> chessBoard) {
        for (int rank = LAST_RANK; rank >= FIRST_RANK; rank--) {
            StringBuilder stringBuilder = new StringBuilder();
            printColumn(chessBoard, rank, stringBuilder);
            System.out.println(stringBuilder);
        }
    }

    private static void printColumn(Map<Position, ChessPiece> chessBoard, int rank, StringBuilder stringBuilder) {
        for (char column = FIRST_COLUMN; column <= LAST_COLUMN; column++) {
            String rawPosition = String.valueOf(column) + rank;
            ChessPiece chessPiece = chessBoard.get(Position.findPosition(rawPosition));
            stringBuilder.append(chessPiece.getShape());
        }
    }

    public static void printResults(List<Result> results) {
        System.out.printf("White Pieces Score : %f\n", results.get(WHITE_RESULT_INDEX).getScore());
        System.out.printf("Black Pieces Score : %f\n", results.get(BLACK_RESULT_INDEX).getScore());
    }
}
