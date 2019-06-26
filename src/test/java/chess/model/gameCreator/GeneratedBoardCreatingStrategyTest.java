package chess.model.gameCreator;

import chess.model.board.BoardDTO;
import chess.model.board.Tile;
import chess.model.piece.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneratedBoardCreatingStrategyTest {
    @Test
    void 체스판_초기화_확인() {
        BoardDTO dto = new BoardDTO(Arrays.asList(
                "RNBKQBNR",
                "PPPPPPPP",
                "########",
                "########",
                "########",
                "########",
                "pppppppp",
                "rnbqkbnr"));
        GeneratedBoardCreatingStrategy strategy = new GeneratedBoardCreatingStrategy(dto);
        Map<String, Tile> tiles = new HashMap<>();

        tiles.put("11", new Tile("11", Optional.of(new Rook("white"))));
        tiles.put("21", new Tile("21", Optional.of(new Knight("white"))));
        tiles.put("31", new Tile("31", Optional.of(new Bishop("white"))));
        tiles.put("41", new Tile("41", Optional.of(new Queen("white"))));
        tiles.put("51", new Tile("51", Optional.of(new King("white"))));
        tiles.put("61", new Tile("61", Optional.of(new Bishop("white"))));
        tiles.put("71", new Tile("71", Optional.of(new Knight("white"))));
        tiles.put("81", new Tile("81", Optional.of(new Rook("white"))));

        tiles.put("18", new Tile("18", Optional.of(new Rook("black"))));
        tiles.put("28", new Tile("28", Optional.of(new Knight("black"))));
        tiles.put("38", new Tile("38", Optional.of(new Bishop("black"))));
        tiles.put("48", new Tile("48", Optional.of(new King("black"))));
        tiles.put("58", new Tile("58", Optional.of(new Queen("black"))));
        tiles.put("68", new Tile("68", Optional.of(new Bishop("black"))));
        tiles.put("78", new Tile("78", Optional.of(new Knight("black"))));
        tiles.put("88", new Tile("88", Optional.of(new Rook("black"))));

        for (int column = 1; column <= 8; column++) {
            String coordinate = String.valueOf(column).concat("2");
            tiles.put(coordinate, new Tile(coordinate, Optional.of(new Pawn(true, "white"))));

            coordinate = String.valueOf(column).concat("7");
            tiles.put(coordinate, new Tile(coordinate, Optional.of(new Pawn(true, "black"))));

            coordinate = String.valueOf(column).concat("3");
            tiles.put(coordinate, new Tile(coordinate, Optional.empty()));

            coordinate = String.valueOf(column).concat("4");
            tiles.put(coordinate, new Tile(coordinate, Optional.empty()));

            coordinate = String.valueOf(column).concat("5");
            tiles.put(coordinate, new Tile(coordinate, Optional.empty()));

            coordinate = String.valueOf(column).concat("6");
            tiles.put(coordinate, new Tile(coordinate, Optional.empty()));
        }

        assertThat(strategy.create()).isEqualTo(tiles);
    }
}
