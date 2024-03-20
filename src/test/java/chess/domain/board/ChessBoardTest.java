package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.lang.reflect.Field;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {
    @DisplayName("체스보드가 생성되면 32개의 말이 셋팅된다")
    @Test
    void initialBoard() {
        // given
        ChessBoard chessBoard = new ChessBoard();

        // when
        chessBoard.initialBoard();

        // then
        assertThat(chessBoard).extracting("chessBoard")
                .satisfies(map -> assertThat((Map<?, ?>) map).hasSize(32));
    }

    @Test
    public void testMove() throws NoSuchFieldException, IllegalAccessException {
        // given
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialBoard();

        Position source = new Position(Rank.ONE, File.B);
        Position target = new Position(Rank.THREE, File.C);

        // when
        chessBoard.move(source, target);

        // then
        Field field = ChessBoard.class.getDeclaredField("chessBoard");
        field.setAccessible(true);
        Map<Position, Piece> chessBoardMap = (Map<Position, Piece>) field.get(chessBoard);

        Piece piece = chessBoardMap.get(target);

        assertAll(
                () -> assertThat(chessBoardMap).containsKey(target),
                () -> assertThat(piece).isInstanceOf(Knight.class)
        );
    }
}
