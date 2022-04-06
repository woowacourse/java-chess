package chess.domain.board;

import static chess.domain.board.piece.Color.BLACK;
import static chess.domain.board.piece.Color.WHITE;
import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.PAWN;
import static chess.domain.board.piece.PieceType.QUEEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.piece.Piece;
import chess.domain.board.position.Position;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class BoardTest {

    private static final Position BLACK_KING_POSITION = Position.of("d5");
    private static final Position BLACK_PAWN_POSITION = Position.of("d2");
    private static final Position WHITE_KING_POSITION = Position.of("e1");
    private static final Position WHITE_QUEEN_POSITION = Position.of("d1");

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new HashMap<>() {{
            put(BLACK_KING_POSITION, Piece.of(BLACK, KING));
            put(WHITE_QUEEN_POSITION, Piece.of(WHITE, QUEEN));
        }});
    }

    @Test
    void 현재_턴과_같은_색의_체스말_이동_가능() {
        Position movablePosition = Position.of("d3");

        board.movePiece(WHITE_QUEEN_POSITION, movablePosition, WHITE);

        Board expected = new Board(new HashMap<>() {{
            put(BLACK_KING_POSITION, Piece.of(BLACK, KING));
            put(movablePosition, Piece.of(WHITE, QUEEN));
        }});

        assertThat(board).isEqualTo(expected);
    }

    @Test
    void 다른_색의_체스말_공격_가능() {
        board.movePiece(WHITE_QUEEN_POSITION, BLACK_KING_POSITION, WHITE);

        Board expected = new Board(new HashMap<>() {{
            put(BLACK_KING_POSITION, Piece.of(WHITE, QUEEN));
        }});

        assertThat(board).isEqualTo(expected);
    }

    @Test
    void 선택된_위치에_체스말이_존재하지_않으면_예외발생() {
        Position wrongPosition1 = Position.of("a1");
        Position wrongPosition2 = Position.of("a2");

        assertThatThrownBy(()->  board.movePiece(wrongPosition1, wrongPosition2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 체스 말은 존재하지 않습니다.");
    }

    @Test
    void 현재_턴과_다른_색의_체스말_이동시도시_예외발생() {
        Position movablePosition = Position.of("d6");

        assertThatThrownBy(()->  board.movePiece(BLACK_KING_POSITION, movablePosition, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE 진영이 움직일 차례입니다!");
    }

    @Test
    void 선택된_체스말이_이동할_수_없는_위치로_이동시도시_예외발생() {
        Position nonMovablePosition = Position.of("a2");

        assertThatThrownBy(()->  board.movePiece(WHITE_QUEEN_POSITION, nonMovablePosition, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    void 이동가능한_경로에_다른_체스말이_존재하는_경우_예외발생(){
        Position movablePosition = Position.of("d8");

        assertThatThrownBy(()->  board.movePiece(WHITE_QUEEN_POSITION, movablePosition, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 말이 가로막고 있습니다.");
    }

    @Test
    void 아군_공격시도시_예외발생(){
        Board board = new Board(new HashMap<>() {{
            put(WHITE_KING_POSITION, Piece.of(WHITE, KING));
            put(WHITE_QUEEN_POSITION, Piece.of(WHITE, QUEEN));
        }});

        assertThatThrownBy(()->  board.movePiece(WHITE_QUEEN_POSITION, WHITE_KING_POSITION, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공격할 수 없는 대상입니다.");
    }

    @Test
    void 이동하려는_곳에_적이_있더라도_공격_불가능한_방향이면_예외발생(){
        Board board = new Board(new HashMap<>() {{
            put(BLACK_PAWN_POSITION, Piece.of(BLACK, PAWN));
            put(WHITE_QUEEN_POSITION, Piece.of(WHITE, QUEEN));
        }});

        assertThatThrownBy(()->  board.movePiece(BLACK_PAWN_POSITION, WHITE_QUEEN_POSITION, BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공격할 수 없는 위치입니다.");
    }

    @Test
    void 특정_종류의_체스말의_개수_합산() {
        int actual = board.countByType(KING);

        assertThat(actual).isEqualTo(1);
    }
}