package chess.model.gameCreator;

import chess.model.board.BoardDTO;
import chess.model.board.Tile;
import chess.model.piece.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static chess.model.board.Board.*;


public class NormalBoardCreatingStrategyTest {
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
        NormalBoardCreatingStrategy strategy = new NormalBoardCreatingStrategy(dto);
        Map<String, Tile> tiles = new HashMap<>();

        tiles.put("11", new Tile("11", new Rook(WHITE_TEAM)));
        tiles.put("21", new Tile("21", new Knight(WHITE_TEAM)));
        tiles.put("31", new Tile("31", new Bishop(WHITE_TEAM)));
        tiles.put("41", new Tile("41", new Queen(WHITE_TEAM)));
        tiles.put("51", new Tile("51", new King(WHITE_TEAM)));
        tiles.put("61", new Tile("61", new Bishop(WHITE_TEAM)));
        tiles.put("71", new Tile("71", new Knight(WHITE_TEAM)));
        tiles.put("81", new Tile("81", new Rook(WHITE_TEAM)));

        tiles.put("18", new Tile("18", new Rook(BLACK_TEAM)));
        tiles.put("28", new Tile("28", new Knight(BLACK_TEAM)));
        tiles.put("38", new Tile("38", new Bishop(BLACK_TEAM)));
        tiles.put("48", new Tile("48", new King(BLACK_TEAM)));
        tiles.put("58", new Tile("58", new Queen(BLACK_TEAM)));
        tiles.put("68", new Tile("68", new Bishop(BLACK_TEAM)));
        tiles.put("78", new Tile("78", new Knight(BLACK_TEAM)));
        tiles.put("88", new Tile("88", new Rook(BLACK_TEAM)));

        for (int column = 1; column <= 8; column++) {
            String coordinate = String.valueOf(column).concat("2");
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, WHITE_TEAM)));

            coordinate = String.valueOf(column).concat("7");
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, BLACK_TEAM)));

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
