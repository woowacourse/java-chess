package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = ChessBoard.generate();
    }

    @DisplayName("Pawn 객체 생성 확인")
    @Test
    void 폰_객체_생성() {
        Pawn pawn = new Pawn("P", Color.BLACK);

        assertThat(pawn.getName()).isEqualTo("P");
        assertThat(pawn.isSameColor(Color.BLACK)).isTrue();
    }

    @DisplayName("초기화된 Pawn 객체들 생성 확인")
    @Test
    void 폰_객체들_생성() {
        Map<Position, Pawn> pawns = Pawn.generate();

        assertThat(pawns.size()).isEqualTo(16);
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 - 2칸 이동")
    @Test
    void pawn_처음으로_이동_2칸() {
        Position source = Position.of('a', '7');
        Position target = Position.of('a', '5');
        Piece pawn = chessBoard.findByPosition(source);

        chessBoard.movePiece(source, target);

        assertThat(pawn).isEqualTo(chessBoard.findByPosition(target));
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 예외 - 3칸 이동 ")
    @Test
    void pawn_처음으로_이동_3칸_예외() {
        Position source = Position.of('a', '7');
        Position target = Position.of('a', '4');

        assertThatThrownBy(() -> {
            chessBoard.movePiece(source, target);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("Pawn 규칙에 따라 이미 움직인 Pawn 을 움직이는 경우 - 1칸 이동 ")
    @Test
    void pawn_이미_이동했을때_1칸_이동() {
        Position source = Position.of('a', '4');
        Position target = Position.of('a', '3');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("P", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Piece pawn = chessBoard.findByPosition(source);

        chessBoard.movePiece(source, target);

        assertThat(pawn).isEqualTo(chessBoard.findByPosition(target));
    }

    @DisplayName("Pawn 규칙에 따라 이미 움직인 Pawn 을 움직이는 경우 예외 - 2칸 이동 ")
    @Test
    void pawn_이미_이동_2칸_예외() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('a', '4'), new Pawn("P", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);
        Position source = Position.of('a', '4');
        Position target = Position.of('a', '2');

        assertThatThrownBy(() -> chessBoard.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("검은색 폰이 뒤로 이동할 경우 예외")
    @Test
    void 검은색_pawn_뒤로_이동() {
        Position source = Position.of('a', '7');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("P", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);

        assertThatThrownBy(() -> chessBoard.movePiece(source, Position.of('a', '8')))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하얀색 폰이 뒤로 이동할 경우 예외")
    @Test
    void 하얀색_pawn_뒤로_이동() {
        Position source = Position.of('a', '2');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("p", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);

        assertThatThrownBy(() -> chessBoard.movePiece(source, Position.of('a', '1')))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이동하는데_위치_경로에_장애물이_있는_경우() {
        Position source = Position.of('a', '7');
        Position target = Position.of('a', '5');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("P", Color.BLACK));
        current.put(Position.of('a', '6'), new Pawn("P", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);

        assertThatThrownBy(() -> chessBoard.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이동하려는_위치에_체스말이_있을경우_예외() {
        Position source = Position.of('a', '7');
        Position target = Position.of('a', '5');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("P", Color.BLACK));
        current.put(target, new Pawn("P", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);

        assertThatThrownBy(() -> chessBoard.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 검은말이_상대편_말을_공격한다() {
        Position source = Position.of('a', '6');
        Position target = Position.of('b', '5');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("P", Color.BLACK));
        current.put(target, new Pawn("p", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);

        chessBoard.movePiece(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }

    @Test
    void 하얀말이_상대편_말을_공격한다() {
        Position source = Position.of('a', '2');
        Position target = Position.of('b', '3');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("p", Color.WHITE));
        current.put(target, new Pawn("P", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);

        chessBoard.movePiece(source, target);

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(1);
    }

    @Test
    void 검은말이_같은편_말을_공격할때_예외() {
        Position source = Position.of('a', '6');
        Position target = Position.of('b', '5');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("P", Color.BLACK));
        current.put(target, new Pawn("P", Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(current);

        assertThatThrownBy(() -> {
            chessBoard.movePiece(source, target);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 하얀말이_같은편_말을_공격할때_예외() {
        Position source = Position.of('a', '2');
        Position target = Position.of('b', '3');
        Map<Position, Piece> current = new HashMap<>();
        current.put(source, new Pawn("p", Color.WHITE));
        current.put(target, new Pawn("p", Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(current);

        assertThatThrownBy(() -> {
            chessBoard.movePiece(source, target);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
