package chess.view;

import chess.Board;
import chess.piece.Piece;
import chess.piece.Rook;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printBoard(Board board) {
        Map<Position, Piece> pieces = board.getPieces();

        StringBuilder builder = new StringBuilder();

        for (Rank rank : Rank.valuesReverseOrder()) {
            for (File file : File.values()) {
                if (pieces.containsKey(Position.of(file, rank))) {
                    Piece piece = pieces.get(Position.of(file, rank));
                    builder.append(findSymbol(piece));
                } else {
                    builder.append(".");
                }
            }
        }
        System.out.println(builder);
    }

    private static String findSymbol(Piece piece) {
        return piece.getSymbolBy();
    }
}
