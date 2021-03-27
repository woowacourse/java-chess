package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class BishopTest {

    @DisplayName("Bishop 객체 생성 확인")
    @Test
    void 비숍_객체_생성() {
        Bishop bishop = new Bishop("B", Color.BLACK);

        assertThat(bishop.getName()).isEqualTo("B");
        assertThat(bishop.isSameColor(Color.BLACK)).isTrue();
    }

    @DisplayName("초기화된 Bishop 객체들 생성 확인")
    @Test
    void 비숍_객체들_생성() {
        Map<Position, Bishop> bishops = Bishop.generate();

        assertThat(bishops.size()).isEqualTo(4);
    }

    @DisplayName("Bishop 규칙에 따른 이동")
    @Test
    void 비숍_이동_확인() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Bishop("B", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('a', '1');
        Piece bishop = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(bishop).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 비숍_이동에_장애물() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Bishop("B", Color.BLACK));
        current.put(Position.of('b', '2'), new Bishop("B", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('a', '1');

        assertThatThrownBy(() ->  chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비숍_이동_규칙에_어긋나는_경우_예() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Bishop("B", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('a', '4');

        assertThatThrownBy(() ->  chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Bishop("B", Color.BLACK));
        current.put(Position.of('g', '7'), new Bishop("b", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('g', '7');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }
}
