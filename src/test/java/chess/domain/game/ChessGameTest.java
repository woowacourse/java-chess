package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.fixture.StrategyFixture.CLEAR_PATH_STRATEGY;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.dto.MovePositionCommandDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    private static final String WHITE_ROOK_POSITION = "h1";
    private static final String BLACK_ROOK_POSITION = "h8";

    private ChessGame game;

    @BeforeEach
    void setUp() {
        List<Piece> pieces = new ArrayList<>(List.of(
                Rook.ofRight(WHITE), Rook.ofRight(BLACK), new King(BLACK)));
        game = new ChessGame(new ActivePieces(pieces));
    }

    @Test
    void 체스말_이동_가능() {
        game.moveChessmen(new MovePositionCommandDto(WHITE_ROOK_POSITION, "h3"));

        Piece whiteRook = Rook.ofRight(WHITE);
        whiteRook.move(Position.of("h3"), CLEAR_PATH_STRATEGY);

        List<Piece> actual = game.getChessmen();
        List<Piece> expected = List.of(whiteRook, Rook.ofRight(BLACK), new King(BLACK));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 체스말로_다른_체스말_공격_가능() {
        game.moveChessmen(new MovePositionCommandDto(WHITE_ROOK_POSITION, BLACK_ROOK_POSITION));

        Piece aliveRook = Rook.ofRight(WHITE);
        aliveRook.move(Position.of(BLACK_ROOK_POSITION), CLEAR_PATH_STRATEGY);

        List<Piece> actual = game.getChessmen();
        List<Piece> expected = List.of(aliveRook, new King(BLACK));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 킹이_2개_미만이면_게임종료() {
        boolean actual = game.isEnd();

        assertThat(actual).isTrue();
    }

    @Test
    void 킹이_2개_이상이면_게임진행중() {
        List<Piece> pieces = new ArrayList<>(List.of(new King(WHITE), new King(BLACK)));
        ChessGame game = new ChessGame(new ActivePieces(pieces));

        boolean actual = game.isEnd();

        assertThat(actual).isFalse();
    }
}