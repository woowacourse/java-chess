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

public class KingTest {
    @DisplayName("King 객체 생성 확인")
    @Test
    void 킹_객체_생성() {
        King king = new King("K", Color.BLACK);

        assertThat(king.getName()).isEqualTo("K");
        assertThat(king.isSameColor(Color.BLACK)).isTrue();
    }

    @DisplayName("초기화된 King 객체들 생성 확인")
    @Test
    void 킹_객체들_생성() {
        Map<Position, King> kings = King.generate();

        assertThat(kings.size()).isEqualTo(2);
    }

    @Test
    void 킹_이동_십자() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new King("K", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('d', '3');
        Piece king = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(king).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 킹_이동_대각선() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new King("K", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('c', '3');
        Piece king = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(king).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 킹_이동_규칙에_어긋나는_경우_이동_규칙_예외() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new King("K", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('c', '1');

        assertThatThrownBy(() ->  chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다_십자() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new King("K", Color.BLACK));
        current.put(Position.of('d', '3'), new King("k", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('d', '3');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }

    @Test
    void 상대편_말을_공격한다_대각선() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new King("K", Color.BLACK));
        current.put(Position.of('c', '3'), new King("k", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('c', '3');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }
}
