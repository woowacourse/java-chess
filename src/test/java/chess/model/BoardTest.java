package chess.model;

import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Queen;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BoardTest {
    @Test
    void 생성자_확인() {
        Board board = new Board(new NormalCreateStrategy());
        assertThat(board).isEqualTo(new Board(new NormalCreateStrategy()));
    }

    @Test
    void 경로에_piece있는지_확인_piece가_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("56", Optional.ofNullable(new Pawn(true, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("56", testTile2);
            return testMap;
        });

        Vector vector = new Vector(Arrays.asList(
                Coordinate.valueOf(5),
                Coordinate.valueOf(5),
                Coordinate.valueOf(5),
                Coordinate.valueOf(7)));

        assertThat(board.checkPiecePresentInRoute(Arrays.asList("55", "57"), vector)).isTrue();
    }

    @Test
    void 경로에_piece있는지_확인_piece가_없을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });

        Vector vector = new Vector(Arrays.asList(
                Coordinate.valueOf(5),
                Coordinate.valueOf(5),
                Coordinate.valueOf(5),
                Coordinate.valueOf(7)));

        assertThat(board.checkPiecePresentInRoute(Arrays.asList("55", "57"), vector)).isFalse();
    }

    @Test
    void 타일에_piece_있는지_확인() {
        Board board = new Board(() -> {
            Tile testTile2 = new Tile("57", Optional.ofNullable(new Pawn(true, "white")));
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
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });

        assertThat(board.askTilePieceWhichTeam("55")).isEqualTo("white");
    }

    @Test
    void 타일에_있는_piece의_팀_확인_흑팀인_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });

        assertThat(board.askTilePieceWhichTeam("55")).isEqualTo("black");
    }

    @Test
    void piece이동_확인_pawn이_앞으로_이동_상대팀이_없을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("57", Optional.ofNullable(null));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("57", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("57", Optional.ofNullable(new Pawn(false, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("57", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "57"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }

    @Test
    void piece이동_확인_pawn이_앞으로_2칸_이동_상대팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("57", Optional.ofNullable(new Pawn(true, "black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("57", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "57")));
    }

    @Test
    void piece이동_확인_pawn이_앞으로_1칸_이동_상대팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("56", Optional.ofNullable(new Pawn(true, "black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("56", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "56")));
    }

    @Test
    void piece이동_확인_pawn이_대각선으로_이동_상대팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("66", Optional.ofNullable(new Pawn(true, "black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("66", testTile2);
            return testMap;
        });


        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("66", Optional.ofNullable(new Pawn(false, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("66", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "66"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }

    @Test
    void piece이동_확인_pawn이_대각선으로_이동_상대팀이_없을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("66", Optional.ofNullable(null));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("66", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "66")));
    }

    @Test
    void piece이동_확인_queen이_남쪽으로_이동_상대팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Queen("white")));
            Tile testTile2 = new Tile("53", Optional.ofNullable(new Pawn(true, "black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("53", Optional.ofNullable(new Queen("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "53"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }

    @Test
    void piece이동_확인_queen이_남동쪽으로_이동_상대팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Queen("white")));
            Tile testTile2 = new Tile("73", Optional.ofNullable(new Queen("black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("73", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("73", Optional.ofNullable(new Queen("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("73", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "73"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }

    @Test
    void piece이동_확인_queen이_남쪽으로_이동_같은팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Queen("white")));
            Tile testTile2 = new Tile("53", Optional.ofNullable(new Pawn(true, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "53")));
    }

    @Test
    void piece이동_확인_queen이_남동쪽으로_이동_같은팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Queen("white")));
            Tile testTile2 = new Tile("73", Optional.ofNullable(new Queen("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("73", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "73")));
    }

    @Test
    void piece이동_확인_knight가_남동쪽으로_이동_상대팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Knight("white")));
            Tile testTile2 = new Tile("63", Optional.ofNullable(new Queen("black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("63", Optional.ofNullable(new Knight("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "63"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }

    @Test
    void piece이동_확인_knight가_남동쪽으로_이동_상대팀이_없을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Knight("white")));
            Tile testTile2 = new Tile("63", Optional.ofNullable(null));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(null));
            Tile testTile2 = new Tile("63", Optional.ofNullable(new Knight("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        board.movePiece(Arrays.asList("55", "63"));

        assertThat(board).isEqualTo(boardAfterMoved);
    }

    @Test
    void piece이동_확인_knight가_남동쪽으로_이동_같은_팀이_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Knight("white")));
            Tile testTile2 = new Tile("63", Optional.ofNullable(new Queen("white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "63")));
    }

    @Test
    void 점수계산을_위한_ScoreResult_생성_테스트() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("63", Optional.ofNullable(new Pawn(true, "black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        ScoreResult result = board.makeScoreResult();
        assertThat(result).isEqualTo(new ScoreResult(1.0, 1.0));
    }

    @Test
    void 점수계산을_위한_ScoreResult_생성_테스트_같은_색이_세로선에_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("53", Optional.ofNullable(new Pawn(true, "white")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            return testMap;
        });

        ScoreResult result = board.makeScoreResult();
        assertThat(result).isEqualTo(new ScoreResult(1.0, 0.0));
    }

    @Test
    void 점수계산을_위한_ScoreResult_생성_테스트_pawn과_퀸이_공존할_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile2 = new Tile("53", Optional.ofNullable(new Pawn(true, "white")));
            Tile testTile3 = new Tile("11", Optional.ofNullable(new Queen("black")));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            testMap.put("11", testTile3);
            return testMap;
        });

        ScoreResult result = board.makeScoreResult();
        assertThat(result).isEqualTo(new ScoreResult(1.0, 9.0));
    }

}
