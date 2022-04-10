package chess.domain.game;

import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.QUEEN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.Position;
import chess.domain.event.Event;
import chess.domain.event.MoveEvent;
import chess.util.BoardMapGeneratorUtil;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RunningTest {

    private static final Position BLACK_KING_POSITION = Position.of("d8");
    private static final Position BLACK_QUEEN_POSITION = Position.of("a1");
    private static final Position WHITE_QUEEN_POSITION = Position.of("d1");
    private static final Position WHITE_KING_POSITION = Position.of("e1");
    private static final Position NON_KILL_MOVED_POSITION = Position.of("d3");

    private static final Event NON_KILL_MOVE = new MoveEvent("d1 d3");
    private static final Event KILL_BLACK_KING_MOVE = new MoveEvent("d1 d8");
    private static final Event KILL_BLACK_QUEEN_MOVE = new MoveEvent("d1 a1");

    private Game game;

    @BeforeEach
    void setUp() {
        game = new WhiteTurn(new Board(new HashMap<>() {{
            put(BLACK_KING_POSITION, Piece.of(Color.BLACK, KING));
            put(BLACK_QUEEN_POSITION, Piece.of(Color.BLACK, QUEEN));
            put(WHITE_QUEEN_POSITION, Piece.of(Color.WHITE, QUEEN));
            put(WHITE_KING_POSITION, Piece.of(Color.WHITE, KING));
        }}));
    }

    @Test
    void 체스말_이동() {
        Game actual = game.play(NON_KILL_MOVE);

        Game expected = new BlackTurn(new Board(new HashMap<>() {{
            put(BLACK_KING_POSITION, Piece.of(Color.BLACK, KING));
            put(BLACK_QUEEN_POSITION, Piece.of(Color.BLACK, QUEEN));
            put(NON_KILL_MOVED_POSITION, Piece.of(Color.WHITE, QUEEN));
            put(WHITE_KING_POSITION, Piece.of(Color.WHITE, KING));
        }}));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 체스말로_다른_체스말_공격() {
        Game actual = game.play(KILL_BLACK_QUEEN_MOVE);

        Game expected = new BlackTurn(new Board(new HashMap<>() {{
            put(BLACK_KING_POSITION, Piece.of(Color.BLACK, KING));
            put(BLACK_QUEEN_POSITION, Piece.of(Color.WHITE, QUEEN));
            put(WHITE_KING_POSITION, Piece.of(Color.WHITE, KING));
        }}));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 아직_게임_종료_전() {
        boolean actual = game.isEnd();

        assertThat(actual).isFalse();
    }

    @Test
    void 체스말_이동_후_킹이_여전히_2개면_게임진행() {
        Game afterMove = game.play(KILL_BLACK_QUEEN_MOVE);

        boolean actual = afterMove.isEnd();

        assertThat(actual).isFalse();
        assertThat(afterMove).isNotInstanceOf(GameOver.class);
    }

    @Test
    void 체스말_이동_후_킹이_2개_미만이면_게임종료() {
        Game killedKingGame = game.play(KILL_BLACK_KING_MOVE);

        boolean actual = killedKingGame.isEnd();

        assertThat(actual).isTrue();
        assertThat(killedKingGame).isInstanceOf(GameOver.class);
    }

    @Test
    void 보드_정보가_동일하더라도_턴이_다르면_별개의_인스턴스() {
        Board board = new Board(BoardMapGeneratorUtil.initFullChessBoard());
        Game whiteTurn = new WhiteTurn(board);
        Game blackTurn = new BlackTurn(board);

        assertThat(whiteTurn).isNotEqualTo(blackTurn);
    }
}