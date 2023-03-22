package chess.domain.piece.linear;

import static chess.domain.piece.Side.BLACK;
import static chess.domain.piece.Side.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RookTest {

    private Map<Position, Piece> boardMap;
    private Rook rook;

    @BeforeEach
    void init() {
        rook = new Rook(WHITE);
        boardMap = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                boardMap.put(Position.of(file, rank), new Empty());
            }
        }
    }

    @Test
    void 룩은_자신이_갈_수_있는_위치들을_반환한다() {
        /*

        ........
        ........
        ........
        ........
        ........
        P.......
        ........
        r..p....

        */

        boardMap.put(Position.of(A, ONE), rook);
        boardMap.put(Position.of(A, THREE), new Pawn(BLACK));
        boardMap.put(Position.of(D, ONE), new Pawn(WHITE));
        final Board board = new Board(boardMap);

        final List<Position> movablePosition = rook.findMovablePosition(Position.of(A, ONE), board);

        assertThat(movablePosition)
                .containsOnly(
                        Position.of(A, TWO),
                        Position.of(A, THREE),
                        Position.of(B, ONE),
                        Position.of(C, ONE)
                );
    }
}
