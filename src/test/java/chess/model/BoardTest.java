package chess.model;

import chess.model.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    void 생성자_확인() {
        Board board = new Board(new NormalCreateStrategy());
        assertThat(board).isEqualTo(new Board(new NormalCreateStrategy()));
    }

    @Test
    void 경로에_piece있는지_확인_piece가_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn("white")));
            Tile testTile2 = new Tile("56", Optional.ofNullable(new Pawn("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("56", testTile2);
            return testMap;
        });

        assertThat(board.checkPiecePresentInRoute(Arrays.asList("55", "57"))).isTrue();
    }

    @Test
    void 경로에_piece있는지_확인_piece가_없을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });

        assertThat(board.checkPiecePresentInRoute(Arrays.asList("55", "57"))).isFalse();
    }

    @Test
    void 타일에_piece_있는지_확인() {
        Board board = new Board(() -> {
            Tile testTile2 = new Tile("57", Optional.ofNullable(new Pawn("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("57", testTile2);
            return testMap;
        });

        assertThat(board.checkPiecePresentInTarget("57")).isTrue();
    }

    @Test
    void 타일에_piece_없는지_확인() {
        Board board = new Board(() -> {
            Tile testTile2 = new Tile("57", Optional.ofNullable(null));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("57", testTile2);
            return testMap;
        });

        assertThat(board.checkPiecePresentInTarget("57")).isFalse();
    }

    @Test
    void 타일에_있는_piece의_팀_확인_백팀인_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });

        assertThat(board.askTilePieceWhichTeam("55")).isEqualTo("white");
    }

    @Test
    void 타일에_있는_piece의_팀_확인_흑팀인_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn("black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });

        assertThat(board.askTilePieceWhichTeam("55")).isEqualTo("black");
    }

    @Test
    void piece이동_확인() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn("white")));
            Tile testTile2 = new Tile("57", Optional.ofNullable(null));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("57", testTile2);
            return testMap;
        });


        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("57", Optional.ofNullable(new Pawn("white")));
            testTile2.getPiece().get().signalMoved();
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("57", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "57"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }
}
