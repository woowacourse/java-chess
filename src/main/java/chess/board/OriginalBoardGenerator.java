package chess.board;

import chess.board.piece.Pieces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        List<String> lines = Arrays.asList(originalBoard.split(","));
        for (String line : lines) {
            List<String> tokens = Arrays.asList(line.split(""));
            for (String token : tokens) {
                Pieces.findByToken(token);
            }
        }
    }
}
