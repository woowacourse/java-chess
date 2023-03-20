package chess.domain.pieces;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.BoardMaker;
import chess.domain.board.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BoardMakerTest {

    private final List<Rank> ranks = new BoardMaker().createBoard();

    @Test
    @DisplayName("생성된 board의 사이즈는 8x8이다.")
    void createTest_size() {
        Assertions.assertAll(
                () -> assertThat(ranks.size()).isEqualTo(8),
                () -> assertThat(ranks.get(0).getRank().size()).isEqualTo(8)
        );
    }

    @Test
    @DisplayName("0번 째 인덱스를 테스트한다.")
    void createTest_index_0() {
        var rank = ranks.get(0);

        assertAll(
                () -> assertThat(rank.findPiece(0).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(0)).isInstanceOf(Rook.class),

                () -> assertThat(rank.findPiece(1).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(1)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPiece(2).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(2)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPiece(3).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(3)).isInstanceOf(Queen.class),

                () -> assertThat(rank.findPiece(4).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(4)).isInstanceOf(King.class),

                () -> assertThat(rank.findPiece(5).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(5)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPiece(6).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(6)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPiece(7).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPiece(7)).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("1번 째 인덱스를 테스트한다")
    void createTest_index_1() {
        var rank = ranks.get(1);

        for (int index = 0; index < 8; index++) {
            assertThat(rank.findPiece(index).getTeam()).isEqualTo(BLACK);
            assertThat(rank.findPiece(index)).isInstanceOf(Pawn.class);
        }
    }

    @Test
    @DisplayName("6번 째 인덱스를 테스트한다.")
    void createTest_index_6() {
        var rank = ranks.get(6);

        for (int index = 0; index < 8; index++) {
            assertThat(rank.findPiece(index).getTeam()).isEqualTo(WHITE);
            assertThat(rank.findPiece(index)).isInstanceOf(Pawn.class);
        }
    }

    @Test
    @DisplayName("7번 째 인덱스를 테스트한다.")
    void createTest_index_7() {
        var rank = ranks.get(7);

        assertAll(
                () -> assertThat(rank.findPiece(0).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(0)).isInstanceOf(Rook.class),

                () -> assertThat(rank.findPiece(1).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(1)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPiece(2).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(2)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPiece(3).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(3)).isInstanceOf(Queen.class),

                () -> assertThat(rank.findPiece(4).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(4)).isInstanceOf(King.class),

                () -> assertThat(rank.findPiece(5).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(5)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPiece(6).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(6)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPiece(7).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPiece(7)).isInstanceOf(Rook.class)
        );
    }
}
