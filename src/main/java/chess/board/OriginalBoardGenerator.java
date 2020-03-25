package chess.board;

import chess.board.piece.Piece;
import chess.board.piece.Pieces;

import java.util.HashMap;
import java.util.Map;

public class OriginalBoardGenerator implements BoardGenerator {
    private static final String originalBoard =
            "RNBQKBNR" + "," +
                    "PPPPPPPP" + "," +
                    "........" + "," +
                    "........" + "," +
                    "........" + "," +
                    "........" + "," +
                    "pppppppp" + "," +
                    "rnbqkbnr";

    @Override
    public Map<Coordinate, Tile> generate() {
        Map<Coordinate, Tile> board = new HashMap<>();

        String[] lines = originalBoard.split(",");
        for (int row = 8; row >= 1; row--) {
            String[] tokens = lines[8 - row].split("");
            for (int col = 1; col <= 8; col++) {
                Piece piece = Pieces.findByToken(tokens[col - 1]);
                File file = File.findByValue(col);
                Rank rank = Rank.findByValue(row);

                Coordinate coordinate = Coordinate.of(file, rank);
                board.put(coordinate, new Tile(coordinate, piece));
            }
        }
        System.out.println(board);
        return board;
    }
}
