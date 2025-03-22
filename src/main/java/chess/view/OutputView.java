package chess.view;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printBoard(ChessBoard board) {
        Map<Position, Piece> pieceByPosition = board.getPieceByPosition();
        List<List<String>> boardString = new ArrayList<>();

        for (int row = Position.MIN_ROW_INDEX; row <= Position.MAX_ROW_INDEX; row++) {
            List<String> rowString = new ArrayList<>();
            for (int column = Position.MIN_COLUMN_INDEX; column <= Position.MAX_COLUMN_INDEX; column++) {
                Position position = new Position(row, column);
                if (!board.existsPiece(position)) {
                    rowString.add(".");
                    continue;
                }
                Piece piece = pieceByPosition.get(position);
                rowString.add(piece.type().getTitleByTeam(piece.team()));
            }
            boardString.add(rowString);
        }

        for (List<String> rowString : boardString) {
            System.out.println(String.join("", rowString));
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
