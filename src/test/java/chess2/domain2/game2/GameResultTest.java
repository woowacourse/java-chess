package chess2.domain2.game2;


import static chess2.domain2.board2.piece2.Color.BLACK;
import static chess2.domain2.board2.piece2.Color.WHITE;
import static chess2.domain2.board2.piece2.PieceType.KING;
import static chess2.domain2.board2.piece2.PieceType.KNIGHT;
import static chess2.domain2.board2.piece2.PieceType.QUEEN;
import static chess2.domain2.board2.piece2.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.piece2.NonPawn;
import chess2.domain2.board2.piece2.Pawn;
import chess2.domain2.board2.position.Position;
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

        Color actual = whiteKingKilled.winnerColor();

        assertThat(actual).isEqualTo(BLACK);
    }

    @Test
    void 점수_계산() {
        GameResult pawnsOfSameFile = new GameResult(new HashMap<>() {{
            put(SAME_FILE_POSITION1, new NonPawn(BLACK, KING));
            put(SAME_FILE_POSITION2, new NonPawn(BLACK, KNIGHT));
            put(SAME_FILE_POSITION3, new Pawn(BLACK));
        }});

        double actual = pawnsOfSameFile.blackScore();
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

        double actual = pawnsOfSameFile.blackScore();
        double expected = 0.5 * 3;

        assertThat(actual).isEqualTo(expected);
    }
}