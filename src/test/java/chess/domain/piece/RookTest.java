package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    @DisplayName("Rook 객체 생성 확인")
    @Test
    void 룩_객체_생성() {
        Rook rook = new Rook("P", Color.BLACK);

        assertThat(rook.getName()).isEqualTo("P");
        assertThat(rook.isSameColor(Color.BLACK)).isTrue();
    }

    @DisplayName("초기화된 룩 객체들 생성 확인")
    @Test
    void 룩_객체들_생성() {
        Map<Position, Rook> rooks = Rook.generate();

        assertThat(rooks.size()).isEqualTo(4);
    }

    @Test
    void 룩_이동() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Rook("R", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('d', '1');
        Piece rook = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(rook).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 룩_이동_규칙에_어긋나는_경우_예() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Rook("R", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('c', '3');

        assertThatThrownBy(() ->  chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Rook("R", Color.BLACK));
        current.put(Position.of('a', '4'), new Rook("r", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('a', '4');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }
}
