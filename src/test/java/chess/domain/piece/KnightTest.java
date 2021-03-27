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

public class KnightTest {

    @DisplayName("Knight 객체 생성 확인")
    @Test
    void 나이트_객체_생성() {
        Knight knight = new Knight("N", Color.BLACK);

        assertThat(knight.getName()).isEqualTo("N");
        assertThat(knight.isSameColor(Color.BLACK)).isTrue();
    }

    @DisplayName("초기화된 Knight 객체들 생성 확인")
    @Test
    void 나이트_객체들_생성() {
        Map<Position, Knight> nights = Knight.generate();

        assertThat(nights.size()).isEqualTo(4);
    }

    @Test
    void 나이트_이동() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Knight("N", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('f', '3');
        Piece knight = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(knight).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 나이트_이동_규칙에_어긋나는_경우_예() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Knight("N", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('c', '3');
        Piece knight = chessBoard.findByPosition(source);

        assertThatThrownBy(() ->  chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Knight("N", Color.BLACK));
        current.put(Position.of('f', '3'), new Knight("n", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('f', '3');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }
}
