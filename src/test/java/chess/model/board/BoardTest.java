package chess.model.board;

import chess.model.ScoreResult;
import chess.model.gameCreator.NormalBoardCreatingStrategy;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Queen;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BoardTest {
    @Test
    void 생성자_확인() {
        BoardDTO dto1 = new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "########",
                "########",
                "pppppppp",
                "rnbqkbnr"));
        BoardDTO dto2 = new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "########",
                "########",
                "pppppppp",
                "rnbqkbnr"));
        Board board = new Board(new NormalBoardCreatingStrategy(dto1));
        assertThat(board).isEqualTo(new Board(new NormalBoardCreatingStrategy(dto2)));
    }

    @Test
    void 생성자_오류확인_null이_입력된_경우() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Board(null));
    }

    @Test
    void 경로에_piece있는지_확인_piece가_있을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("56", new Pawn(true, "white"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("56", testTile2);
            return testMap;
        });

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> board.movePiece(Arrays.asList("55", "57")))
                .withMessage("경로에 piece가 있어서 움직일 수 없습니다.");
    }

    @Test
    void 타일에_piece_있는지_확인() {
        Board board = new Board(() -> {
            Tile testTile2 = new Tile("57", new Pawn(true, "white"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("57", testTile2);
            return testMap;
        });
        assertThat(board.getTile("57").isPiecePresent()).isTrue();
    }

    @Test
    void 타일에_piece_없는지_확인() {
        Board board = new Board(() -> {
            Tile testTile2 = new Tile("57", null);
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("57", testTile2);
            return testMap;
        });
        assertThat(board.getTile("57").isPiecePresent()).isFalse();
    }

    @Test
    void 타일에_있는_piece의_팀_확인_백팀인_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });
        assertThat(board.isRightTurn("55", 1)).isTrue();
    }

    @Test
    void 타일에_있는_piece의_팀_확인_흑팀인_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", new Pawn(true, "black"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            return testMap;
        });
        assertThat(board.isRightTurn("55", 2)).isTrue();
    }

    @Test
    void piece이동_확인_pawn이_앞으로_이동_상대팀이_없을_경우() {
        Board board = new Board(() -> {
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("57", null);
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("57", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", null);
            Tile testTile2 = new Tile("57", new Pawn(false, "white"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("57", new Pawn(true, "black"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("56", new Pawn(true, "black"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("66", new Pawn(true, "black"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("66", testTile2);
            return testMap;
        });


        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", null);
            Tile testTile2 = new Tile("66", new Pawn(false, "white"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("66", null);
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
            Tile testTile1 = new Tile("55", new Queen("white"));
            Tile testTile2 = new Tile("53", new Pawn(true, "black"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", null);
            Tile testTile2 = new Tile("53", new Queen("white"));
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
            Tile testTile1 = new Tile("55", new Queen("white"));
            Tile testTile2 = new Tile("73", new Queen("black"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("73", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", null);
            Tile testTile2 = new Tile("73", new Queen("white"));
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
            Tile testTile1 = new Tile("55", new Queen("white"));
            Tile testTile2 = new Tile("53", new Pawn(true, "white"));
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
            Tile testTile1 = new Tile("55", new Queen("white"));
            Tile testTile2 = new Tile("73", new Queen("white"));
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
            Tile testTile1 = new Tile("55", new Knight("white"));
            Tile testTile2 = new Tile("63", new Queen("black"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", null);
            Tile testTile2 = new Tile("63", new Knight("white"));
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
            Tile testTile1 = new Tile("55", new Knight("white"));
            Tile testTile2 = new Tile("63", null);
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("63", testTile2);
            return testMap;
        });

        Board boardAfterMoved = new Board(() -> {
            Tile testTile1 = new Tile("55", null);
            Tile testTile2 = new Tile("63", new Knight("white"));
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
            Tile testTile1 = new Tile("55", new Knight("white"));
            Tile testTile2 = new Tile("63", new Queen("white"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("63", new Pawn(true, "black"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("53", new Pawn(true, "white"));
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
            Tile testTile1 = new Tile("55", new Pawn(true, "white"));
            Tile testTile2 = new Tile("53", new Pawn(true, "white"));
            Tile testTile3 = new Tile("11", new Queen("black"));
            Map<String, Tile> testMap = new HashMap<>();
            testMap.put("55", testTile1);
            testMap.put("53", testTile2);
            testMap.put("11", testTile3);
            return testMap;
        });

        ScoreResult result = board.makeScoreResult();
        assertThat(result).isEqualTo(new ScoreResult(1.0, 9.0));
    }

    @Test
    void 말_초기화_확인() {
//        Board board = new Board(new NewBoardCreatingStrategy());
        BoardDTO dto = new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "########",
                "########",
                "pppppppp",
                "rnbqkbnr"));
        Board board = new Board(new NormalBoardCreatingStrategy(dto));
        assertThat(board.getTile("12"))
                .isEqualTo(new Tile("12", new Pawn(true, "white")));
        assertThat(board.getTile("55")).isEqualTo(new Tile("55", null));
        assertThat(board.getTile("58")).isEqualTo(new Tile("58", new King("black")));
    }

    @Test
    void 턴이_맞는지_확인() {
//        Board board = new Board(new NewBoardCreatingStrategy());
        BoardDTO dto = new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "########",
                "########",
                "pppppppp",
                "rnbqkbnr"));
        Board board = new Board(new NormalBoardCreatingStrategy(dto));
        assertThat(board.isRightTurn("11", 3)).isTrue();
        assertThat(board.isRightTurn("18", 3)).isFalse();
        assertThat(board.isRightTurn("11", 2)).isFalse();
        assertThat(board.isRightTurn("18", 2)).isTrue();
    }
}
