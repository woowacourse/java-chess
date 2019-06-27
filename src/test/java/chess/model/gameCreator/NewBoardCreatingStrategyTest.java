package chess.model.gameCreator;

import chess.model.board.Tile;
import chess.model.piece.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NewBoardCreatingStrategyTest {
    @Test
    void 체스판_초기화_확인() {
        BoardCreatingStrategy strategy = new NewBoardCreatingStrategy();
        Map<String, Tile> tiles = new HashMap<>();
        tiles.put("11", new Tile("11", new Rook("white")));
        tiles.put("21", new Tile("21", new Knight("white")));
        tiles.put("31", new Tile("31", new Bishop("white")));
        tiles.put("41", new Tile("41", new Queen("white")));
        tiles.put("51", new Tile("51", new King("white")));
        tiles.put("61", new Tile("61", new Bishop("white")));
        tiles.put("71", new Tile("71", new Knight("white")));
        tiles.put("81", new Tile("81", new Rook("white")));

        tiles.put("18", new Tile("18", new Rook("black")));
        tiles.put("28", new Tile("28", new Knight("black")));
        tiles.put("38", new Tile("38", new Bishop("black")));
        tiles.put("48", new Tile("48", new Queen("black")));
        tiles.put("58", new Tile("58", new King("black")));
        tiles.put("68", new Tile("68", new Bishop("black")));
        tiles.put("78", new Tile("78", new Knight("black")));
        tiles.put("88", new Tile("88", new Rook("black")));

        for (int column = 1; column <= 8; column++) {
            String coordinate = String.valueOf(column).concat("2");
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, "white")));

            coordinate = String.valueOf(column).concat("7");
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, "black")));

            coordinate = String.valueOf(column).concat("3");
            tiles.put(coordinate, new Tile(coordinate, null));

            coordinate = String.valueOf(column).concat("4");
            tiles.put(coordinate, new Tile(coordinate, null));

            coordinate = String.valueOf(column).concat("5");
            tiles.put(coordinate, new Tile(coordinate, null));

            coordinate = String.valueOf(column).concat("6");
            tiles.put(coordinate, new Tile(coordinate, null));
        }

        assertThat(strategy.create()).isEqualTo(tiles);
    }
}
