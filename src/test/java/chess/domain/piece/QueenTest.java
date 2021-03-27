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

public class QueenTest {

    @DisplayName("Queen 객체 생성 확인")
    @Test
    void 퀸_객체_생성() {
        Queen queen = new Queen("Q", Color.BLACK);

        assertThat(queen.getName()).isEqualTo("Q");
        assertThat(queen.isSameColor(Color.BLACK)).isTrue();
    }

    @DisplayName("초기화된 퀸 객체들 생성 확인")
    @Test
    void 퀸_객체들_생성() {
        Map<Position, Queen> queens = Queen.generate();

        assertThat(queens.size()).isEqualTo(2);
    }

    @Test
    void 퀸_이동_십자() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Queen("Q", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('d', '1');
        Piece queen = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(queen).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 퀸_이동_대각선() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Queen("Q", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('a', '1');
        Piece queen = chessBoard.findByPosition(source);

        chessBoard.move(source, target);

        assertThat(queen).isEqualTo(chessBoard.findByPosition(target));
    }

    @Test
    void 퀸_이동_규칙에_어긋나는_경우_이동_규칙_예외() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Queen("Q", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('b', '1');

        assertThatThrownBy(() ->  chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다_십자() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Queen("Q", Color.BLACK));
        current.put(Position.of('a', '4'), new Queen("q", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('a', '4');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }

    @Test
    void 상대편_말을_공격한다_대각선() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('d', '4'), new Queen("Q", Color.BLACK));
        current.put(Position.of('g', '7'), new Queen("q", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('d', '4');
        Position target = Position.of('g', '7');

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }
}
