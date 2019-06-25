package chess.model;

import chess.model.piece.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NewGameCreateStrategy implements CreateStrategy {
    @Override
    public Map<String, Tile> create() {
        Map<String, Tile> tiles = new HashMap<>();

        for (int row = 1; row <= 8; row++) {
            if (row == 1) {
                // white팀 말 넣기
                String coordinateY = String.valueOf(row);

                tiles.put("1".concat(coordinateY), new Tile("1".concat(coordinateY), Optional.ofNullable(new Rook("white"))));
                tiles.put("2".concat(coordinateY), new Tile("2".concat(coordinateY), Optional.ofNullable(new Knight("white"))));
                tiles.put("3".concat(coordinateY), new Tile("3".concat(coordinateY), Optional.ofNullable(new Bishop("white"))));
                tiles.put("4".concat(coordinateY), new Tile("4".concat(coordinateY), Optional.ofNullable(new Queen("white"))));
                tiles.put("5".concat(coordinateY), new Tile("5".concat(coordinateY), Optional.ofNullable(new King("white"))));
                tiles.put("6".concat(coordinateY), new Tile("6".concat(coordinateY), Optional.ofNullable(new Bishop("white"))));
                tiles.put("7".concat(coordinateY), new Tile("7".concat(coordinateY), Optional.ofNullable(new Knight("white"))));
                tiles.put("8".concat(coordinateY), new Tile("8".concat(coordinateY), Optional.ofNullable(new Rook("white"))));
            }

            if (row == 8) {
                // black팀 말 넣기
                String coordinateY = String.valueOf(row);

                tiles.put("1".concat(coordinateY), new Tile("1".concat(coordinateY), Optional.ofNullable(new Rook("black"))));
                tiles.put("2".concat(coordinateY), new Tile("2".concat(coordinateY), Optional.ofNullable(new Knight("black"))));
                tiles.put("3".concat(coordinateY), new Tile("3".concat(coordinateY), Optional.ofNullable(new Bishop("black"))));
                tiles.put("4".concat(coordinateY), new Tile("4".concat(coordinateY), Optional.ofNullable(new Queen("black"))));
                tiles.put("5".concat(coordinateY), new Tile("5".concat(coordinateY), Optional.ofNullable(new King("black"))));
                tiles.put("6".concat(coordinateY), new Tile("6".concat(coordinateY), Optional.ofNullable(new Bishop("black"))));
                tiles.put("7".concat(coordinateY), new Tile("7".concat(coordinateY), Optional.ofNullable(new Knight("black"))));
                tiles.put("8".concat(coordinateY), new Tile("8".concat(coordinateY), Optional.ofNullable(new Rook("black"))));
            }

            if (row == 2) {
                // white팀 pawn 넣기
                for (int column = 1; column <= 8; column++) {
                    String coordinate = String.valueOf(column).concat(String.valueOf(row));
                    tiles.put(coordinate, new Tile(coordinate, Optional.ofNullable(new Pawn(true, "white"))));
                }
            }

            if (row == 7) {
                // black팀 pawn 넣기
                for (int column = 1; column <= 8; column++) {
                    String coordinate = String.valueOf(column).concat(String.valueOf(row));
                    tiles.put(coordinate, new Tile(coordinate, Optional.ofNullable(new Pawn(true, "black"))));
                }
            }

            if (row == 3 || row == 4 || row == 5 || row == 6) {
                // null 넣기
                for (int column = 1; column <= 8; column++) {
                    String coordinate = String.valueOf(column).concat(String.valueOf(row));
                    tiles.put(coordinate, new Tile(coordinate, Optional.ofNullable(null)));
                }
            }
        }

        return tiles;
    }
}
