package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.fixture.StrategyFixture.CLEAR_PATH_STRATEGY;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import chess.dto.MoveCommandDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class RunningTest {

    private static final String WHITE_QUEEN_POSITION = "d1";
    private static final String BLACK_QUEEN_POSITION = "d8";
    private static final String BLACK_KING_POSITION = "e8";

    @Nested
    class MoveOrAttackTest {

        private Game game;

        @BeforeEach
        void setUp() {
            List<Piece> pieces = new ArrayList<>(List.of(new Queen(WHITE), new Queen(BLACK)));
            game = new WhiteTurn(new ActivePieces(pieces));
        }

        @Test
        void 체스말_이동() {
            Game moveQueenGame = game.moveChessmen(new MoveCommandDto(WHITE_QUEEN_POSITION, "d4"));

            Piece whiteQueen = new Queen(WHITE);
            whiteQueen.move(Position.of("d4"), CLEAR_PATH_STRATEGY);

            ActivePieces actual = moveQueenGame.getChessmen();
            ActivePieces expected = new ActivePieces(List.of(whiteQueen, new Queen(BLACK)));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 체스말로_다른_체스말_공격() {
            Game killQueenGame = game.moveChessmen(new MoveCommandDto(WHITE_QUEEN_POSITION, BLACK_QUEEN_POSITION));

            Piece aliveWhiteQueen = new Queen(WHITE);
            aliveWhiteQueen.move(Position.of(BLACK_QUEEN_POSITION), CLEAR_PATH_STRATEGY);

            ActivePieces actual = killQueenGame.getChessmen();
            ActivePieces expected = new ActivePieces(List.of(aliveWhiteQueen));

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class GameOverTest {

        private Game game;

        @BeforeEach
        void setUp() {
            List<Piece> pieces = new ArrayList<>(List.of(
                    new Queen(WHITE), new King(WHITE), new King(BLACK)));
            game = new BlackTurn(new ActivePieces(pieces));
        }

        @Test
        void 게임은_진행중() {
            boolean actual = game.isEnd();

            assertThat(actual).isFalse();
        }

        @Test
        void 체스말_이동_후_킹이_2개면_게임진행() {
            Game aliveKingsGame = game.moveChessmen(new MoveCommandDto(BLACK_KING_POSITION, "d8"));

            boolean actual = aliveKingsGame.isEnd();

            assertThat(actual).isFalse();
        }

        @Test
        void 체스말_이동_후_킹이_2개_미만이면_게임종료() {
            Game killedKingGame = game.moveChessmen(new MoveCommandDto(BLACK_KING_POSITION, "d8"))
                    .moveChessmen(new MoveCommandDto(WHITE_QUEEN_POSITION, "d8"));

            boolean actual = killedKingGame.isEnd();

            assertThat(actual).isTrue();
        }
    }
}
