package chess.view;

import chess.domain.Position;
import chess.domain.Result;
import chess.domain.piece.ChessPiece;

import java.util.Map;

public class OutputView {
    public static void printChessBoard(Map<Position, ChessPiece> chessBoard) {
        for (int rank = 8; rank >= 1; rank--) {
            StringBuilder stringBuilder = new StringBuilder();
            printColumn(chessBoard, rank, stringBuilder);
            System.out.println(stringBuilder);
        }
    }

    private static void printColumn(Map<Position, ChessPiece> chessBoard, int rank, StringBuilder stringBuilder) {
        for (char column = 'a'; column <= 'h'; column++) {
            String rawPosition = String.valueOf(column) + rank;
            ChessPiece chessPiece = chessBoard.get(Position.findPosition(rawPosition));
            stringBuilder.append(chessPiece.getShape());
        }
    }

    public static void printWhiteResult(Result whiteResult) {
        System.out.printf("White Pieces Score : %f\n", whiteResult.getScore());
    }

    public static void printBlackResult(Result blackResult) {
        System.out.printf("Black Pieces Score : %f\n", blackResult.getScore());
    }

    public static void printGameEnd() {
        System.out.println("King이 제거되어 게임이 종료됩니다.");
    }
}
