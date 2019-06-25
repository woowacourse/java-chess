package chess.model;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessEngineTest {
    ChessEngine chessEngine;

    @BeforeEach
    void setUp() {
        chessEngine = new ChessEngine();
    }

    @Test
    void 동일한_source_target_move_테스트() {
        assertThrows(SameTwoPointsException.class, () -> {
            chessEngine.move(new Point(1, 1), new Point(1, 1));
        });
    }

    @Test
    void 체스말이_존재하지_않는_source_move_테스트() {
        assertThrows(NoPieceAtSourceException.class, () -> {
            chessEngine.move(new Point(1, 3), new Point(1, 4));
        });
    }

    @Test
    void 팀_순서에_맞는_move_테스트() {
        assertThrows(InvalidTurnException.class, () -> {
            chessEngine.move(new Point(1, 7), new Point(1, 6));
        });
    }

    @Test
    void 같은_팀_공격_move_테스트() {
        assertThrows(AttackMyTeamException.class, () -> {
            chessEngine.move(new Point(1, 2), new Point(1, 3));
            chessEngine.move(new Point(1, 7), new Point(1, 6));
            chessEngine.move(new Point(2, 2), new Point(1, 3));
        });
    }

    @Test
    void 적절하지_않은_move_테스트() {
        assertThrows(InvalidMovePointException.class, () -> {
            chessEngine.move(new Point(1, 2), new Point(1, 5));
        });
    }

    @Test
    void 왕_사망_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1,1), new WhitePawn());
        board.put(new Point(2, 2), new King(ChessPieceColor.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);

        chessEngine = new ChessEngine(chessBoard, ChessPieceColor.WHITE);
        Point source = new Point(1, 1);
        Point target = new Point(2, 2);
        assertThat(chessEngine.move(source, target)).isEqualTo(GameFlow.WHITE_WIN);
    }

    @Test
    void 점수_계산_승자_판별() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 2), new WhitePawn());
        board.put(new Point(2, 2), new WhitePawn());
        board.put(new Point(1, 7), new BlackPawn());
        ChessBoard chessBoard = new ChessBoard(board);
        chessEngine = new ChessEngine(chessBoard, ChessPieceColor.BLACK);
        GameResult gameResult = chessEngine.getGameStatus();
        assertThat(gameResult.getResult()).isEqualTo(GameFlow.WHITE_WIN);
        assertThat(gameResult.getBlackScore()).isEqualTo(1.0, Offset.offset(0.01));
        assertThat(gameResult.getWhiteScore()).isEqualTo(2.0, Offset.offset(0.01));
    }
}