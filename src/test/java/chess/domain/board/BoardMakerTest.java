package chess.domain.board;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.pawn.WhitePawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;

class BoardMakerTest {

    private static final int MAX_COLUMN = 8;
    private static final int MIN_COLUMN = 0;

    private final BoardMaker boardMaker = new BoardMaker();
    private final Map<Position, Piece> board = boardMaker.createBoard();

    @Test
    @DisplayName("만들어진 Board는 사이즈가 64이다.")
    void 만들어진_Board는_사이즈가_64이다() {
        Assertions.assertThat(board.size()).isEqualTo(64);
    }

    @Test
    @DisplayName("첫 번째 행에 블랙팀 기물들이 들어간다.")
    void 첫_번째_행에_블랙팀_기물들이_들어간다() {
        int row = 0;

        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            Position targetPosition = new Position(row, column);
            Piece foundPiece = board.get(targetPosition);

            assertAll(
                    () -> assertThat(foundPiece.getTeam()).isEqualTo(BLACK),
                    () -> assertThat(foundPiece).isNotInstanceOf(EmptyPiece.class)
            );
        }
    }

    @Test
    @DisplayName("두번 째 행에 블랙팀 폰들이 들어간다")
    void 두번_째_행에_블랙팀_폰들이_들어간다() {
        int row = 1;

        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            Position targetPosition = new Position(row, column);
            Piece foundPiece = board.get(targetPosition);

            assertThat(foundPiece).isInstanceOf(BlackPawn.class);
        }
    }

    @Test
    @DisplayName("여섯 번째 행에 흰색팀 폰들이 들어간다")
    void 여섯_번째_행에_흰색팀_폰들이_들어간다() {
        int row = 6;

        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            Position targetPosition = new Position(row, column);
            Piece foundPiece = board.get(targetPosition);

            assertAll(
                    () -> assertThat(foundPiece.getTeam()).isEqualTo(WHITE),
                    () -> assertThat(foundPiece).isNotInstanceOf(EmptyPiece.class)
            );
        }
    }

    @Test
    @DisplayName("마지막 행에 흰색팀 기물들이 들어간다")
    void 마지막_행에_흰색팀_기물들이_들어간다() {
        int row = 7;

        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            Position targetPosition = new Position(row, column);
            Piece foundPiece = board.get(targetPosition);

            assertThat(foundPiece).isNotInstanceOf(WhitePawn.class);
        }
    }

    @ParameterizedTest(name = "두 번째에서 다섯 번째 행에는 EmptyPiece가 들어간다.")
    @ValueSource(ints = {2, 3, 4, 5})
    void 두_번째에서_다섯_번째_행에는_EmptyPiece가_들어간다(final int row) {
        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            Position targetPosition = new Position(row, column);
            Piece foundPiece = board.get(targetPosition);

            assertThat(foundPiece).isInstanceOf(EmptyPiece.class);
        }
    }
}
