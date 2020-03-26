package chess.board;

import chess.board.piece.Piece;
import chess.board.piece.Pieces;

import java.util.HashMap;
import java.util.Map;

public class BoardGenerator {
    private final String template;

    public BoardGenerator(String template) {
        this.template = template;
    }

    public Map<Coordinate, Tile> generate() {
        Map<Coordinate, Tile> board = new HashMap<>();

        String[] lines = template.split(",");
        for (int row = 1; row <= 8; row++) {
            String[] tokens = lines[row - 1].split("");
            for (int col = 1; col <= 8; col++) {
                Piece piece = Pieces.findByToken(tokens[col - 1]);
                File file = File.findByValue(col);
                Rank rank = Rank.findByValue(9 - row);

                Coordinate coordinate = Coordinate.of(file, rank);
                board.put(coordinate, new Tile(coordinate, piece));
            }
        }
        return board;
    }

    public enum Template {
        BASIC_BOARD(
                "RNBQKBNR" + "," +
                        "PPPPPPPP" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "pppppppp" + "," +
                        "rnbqkbnr"),
        EMPTY_BOARD(
                "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........" + "," +
                        "........");

        private final String template;

        Template(final String template) {
            this.template = template;
        }

        public String getTemplate() {
            return template;
        }
    }
}
