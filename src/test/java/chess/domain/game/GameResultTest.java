package chess.domain.game;

import static chess.domain.piece.Side.BLACK;
import static chess.domain.piece.Side.NEUTRALITY;
import static chess.domain.piece.Side.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.immediate.King;
import chess.domain.piece.immediate.Knight;
import chess.domain.piece.linear.Bishop;
import chess.domain.piece.linear.Queen;
import chess.domain.piece.linear.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameResultTest {

    private Map<Position, Piece> boardMap;

    @BeforeEach
    void init() {
        boardMap = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                boardMap.put(Position.of(file, rank), new Empty());
            }
        }
    }

    @Test
    void 같은_진영의_기물_가치_합을_구할_수_있다() {
        /*

        .KR.....
        P.PB....
        .P..Q...
        ........
        .....nq.
        .....p.p
        .....pp.
        ....rk..

         */

        // given
        boardMap.put(Position.of(E, ONE), new Rook(WHITE));
        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(F, TWO), new Pawn(WHITE));
        boardMap.put(Position.of(G, TWO), new Pawn(WHITE));
        boardMap.put(Position.of(F, THREE), new Pawn(WHITE));
        boardMap.put(Position.of(H, THREE), new Pawn(WHITE));
        boardMap.put(Position.of(F, FOUR), new Knight(WHITE));
        boardMap.put(Position.of(G, FOUR), new Queen(WHITE));

        boardMap.put(Position.of(A, SEVEN), new Pawn(BLACK));
        boardMap.put(Position.of(B, SIX), new Pawn(BLACK));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        boardMap.put(Position.of(C, SEVEN), new Pawn(BLACK));
        boardMap.put(Position.of(C, EIGHT), new Rook(BLACK));
        boardMap.put(Position.of(D, SEVEN), new Bishop(BLACK));
        boardMap.put(Position.of(E, SIX), new Queen(BLACK));

        final Board board = new Board(boardMap);
        final GameResult gameResult = GameResult.from(board);

        // when
        final double priceByWhite = gameResult.calculatePriceBySide(WHITE);
        final double priceByBlack = gameResult.calculatePriceBySide(BLACK);

        // then
        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(priceByWhite).isEqualTo(19.5);
        softAssertions.assertThat(priceByBlack).isEqualTo(20.0);

        softAssertions.assertAll();
    }

    @Test
    void 흰_진영의_기물_점수가_더_높다면_흰_진영이_더_유리하다() {
        /*

        .K......
        ........
        ........
        ........
        ........
        ........
        ........
        ....rk..

        */

        boardMap.put(Position.of(E, ONE), new Rook(WHITE));
        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        final Board board = new Board(boardMap);
        final GameResult gameResult = GameResult.from(board);

        assertThat(gameResult.calculateAdvantageSide()).isEqualTo(WHITE);
    }

    @Test
    void 검은_진영의_기물_점수가_더_높다면_검은_진영이_더_유리하다() {
        /*

        .KR.....
        ........
        ........
        ........
        ........
        ........
        ........
        .....k..

        */

        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(C, EIGHT), new Rook(BLACK));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        final Board board = new Board(boardMap);
        final GameResult gameResult = GameResult.from(board);

        assertThat(gameResult.calculateAdvantageSide()).isEqualTo(BLACK);
    }

    @Test
    void 두_진영의_기물_점수가_같다면_어떤_진영도_유리하지_않으므로_중립을_반환한다() {
        /*

        .KR.....
        ........
        ........
        ........
        ........
        ........
        ........
        ....rk..

        */

        boardMap.put(Position.of(E, ONE), new Rook(WHITE));
        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(C, EIGHT), new Rook(BLACK));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        final Board board = new Board(boardMap);
        final GameResult gameResult = GameResult.from(board);

        assertThat(gameResult.calculateAdvantageSide()).isEqualTo(NEUTRALITY);
    }
}
