package chess.domain.game;

import static chess.domain.board.piece.Color.BLACK;
import static chess.domain.board.piece.Color.WHITE;
import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.KNIGHT;
import static chess.domain.board.piece.PieceType.QUEEN;
import static chess.domain.board.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.NonPawn;
import chess.domain.board.piece.Pawn;
import chess.domain.board.position.Position;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class GameResultTest {

    private static final Position SAME_FILE_POSITION1 = Position.of("a1");
    private static final Position SAME_FILE_POSITION2 = Position.of("a2");
    private static final Position SAME_FILE_POSITION3 = Position.of("a3");
    private static final Position SAME_FILE_POSITION4 = Position.of("a4");

    @Test
    void 킹_생존_여부로만_승자_결정() {
        GameResult whiteKingKilled = new GameResult(new HashMap<>() {{
            put(SAME_FILE_POSITION1, new NonPawn(BLACK, KING));
            put(SAME_FILE_POSITION2, new NonPawn(WHITE, ROOK));
            put(SAME_FILE_POSITION3, new NonPawn(WHITE, KNIGHT));
            put(SAME_FILE_POSITION4, new NonPawn(WHITE, QUEEN));
        }});

        Color actual = whiteKingKilled.getWinner();

        assertThat(actual).isEqualTo(BLACK);
    }

    @Test
    void 점수_계산() {
        GameResult pawnsOfSameFile = new GameResult(new HashMap<>() {{
            put(SAME_FILE_POSITION1, new NonPawn(BLACK, KING));
            put(SAME_FILE_POSITION2, new NonPawn(BLACK, KNIGHT));
            put(SAME_FILE_POSITION3, new Pawn(BLACK));
        }});

        double actual = pawnsOfSameFile.getBlackScore();
        double expected = 2.5 + 1;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 같은_열에_존재하는_복수의_폰은_점수를_절반으로_계산() {
        GameResult pawnsOfSameFile = new GameResult(new HashMap<>() {{
            put(SAME_FILE_POSITION1, new NonPawn(BLACK, KING));
            put(SAME_FILE_POSITION2, new Pawn(BLACK));
            put(SAME_FILE_POSITION3, new Pawn(BLACK));
            put(SAME_FILE_POSITION4, new Pawn(BLACK));
        }});

        double actual = pawnsOfSameFile.getBlackScore();
        double expected = 0.5 * 3;

        assertThat(actual).isEqualTo(expected);
    }
}