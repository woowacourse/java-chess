package chess.domain;

import chess.domain.pieces.Color;
import chess.domain.pieces.PointFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void etUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new ChessBoard());
    }

    @Test
    void source_위치에_체스말이_없는_경우_Test() {
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("d6");
        assertThrows(IllegalArgumentException.class,
                () -> chessBoard.checkSourceAndTarget(source, target, Color.BLACK));
    }

    @Test
    void source_위치에_체스말이_현재턴_색깔과_다른_경우_Test() {
        Point source = PointFactory.of("d2");
        Point target = PointFactory.of("d3");
        assertThrows(IllegalArgumentException.class,
                () -> chessBoard.checkSourceAndTarget(source, target, Color.BLACK));
    }

    @Test
    void source_체스말과_target_체스말의_색깔이_같은_경우_Test() {
        Point source = PointFactory.of("b8");
        Point target = PointFactory.of("d7");
        assertThrows(IllegalArgumentException.class,
                () -> chessBoard.checkSourceAndTarget(source, target, Color.BLACK));
    }

    @Test
    void 중간_경로에_다른_체스말이_존재하는_경우_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a5");
        List<Point> path = chessBoard.makePath(source, target);
        assertThrows(IllegalArgumentException.class, () -> chessBoard.checkPath(path));
    }

    @Test
    void 흰색_체스말_점수_계산_Test() {
        assertThat(chessBoard.calculateScore(Color.WHITE)).isEqualTo(38.0);
    }

    @Test
    void 흰색_폰이_세로줄에_두개_있을_때_점수_계산_Test() {
        Point source = PointFactory.of("d2");
        Point target = PointFactory.of("c3");
        chessBoard.updatePieceLocation(source, target);
        assertThat(chessBoard.calculateScore(Color.WHITE)).isEqualTo(37.0);
    }
}
