package chess.domain.board;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BoardMakerTest {

    private final List<Rank> ranks = new BoardMaker().createBoard();

    @Test
    @DisplayName("생성된 board의 사이즈는 8x8이다.")
    void 생성된_board의_사이즈는_8x8이다() {
        Assertions.assertAll(
                () -> assertThat(ranks.size()).isEqualTo(8),
                () -> assertThat(ranks.get(0).getRank().size()).isEqualTo(8)
        );
    }

    @Test
    @DisplayName("첫 번째 인덱스에 블랙팀 기물들이 들어간다.")
    void 첫_번째_인덱스에_블랙팀_기물들이_들어간다() {
        var rank = ranks.get(0);

        assertAll(
                () -> assertThat(rank.findPieceAt(0).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(0)).isInstanceOf(Rook.class),

                () -> assertThat(rank.findPieceAt(1).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(1)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPieceAt(2).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(2)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPieceAt(3).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(3)).isInstanceOf(Queen.class),

                () -> assertThat(rank.findPieceAt(4).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(4)).isInstanceOf(King.class),

                () -> assertThat(rank.findPieceAt(5).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(5)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPieceAt(6).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(6)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPieceAt(7).getTeam()).isEqualTo(BLACK),
                () -> assertThat(rank.findPieceAt(7)).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("두번 째 인덱스에 블랙팀 폰들이 들어간다")
    void 두번_째_인덱스에_블랙팀_폰들이_들어간다() {
        var rank = ranks.get(1);

        for (int index = 0; index < 8; index++) {
            assertThat(rank.findPieceAt(index).getTeam()).isEqualTo(BLACK);
            assertThat(rank.findPieceAt(index)).isInstanceOf(Pawn.class);
        }
    }

    @Test
    @DisplayName("여섯 번 째 인덱스에 흰색팀 폰들이 들어간다")
    void 여섯_번_째_인덱스에_흰색팀_폰들이_들어간다() {
        var rank = ranks.get(6);

        for (int index = 0; index < 8; index++) {
            assertThat(rank.findPieceAt(index).getTeam()).isEqualTo(WHITE);
            assertThat(rank.findPieceAt(index)).isInstanceOf(Pawn.class);
        }
    }

    @Test
    @DisplayName("마지막 인덱스에 흰색팀 기물들이 들어간다")
    void 마지막_인덱스에_흰색팀_기물들이_들어간다() {
        var rank = ranks.get(7);

        assertAll(
                () -> assertThat(rank.findPieceAt(0).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(0)).isInstanceOf(Rook.class),

                () -> assertThat(rank.findPieceAt(1).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(1)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPieceAt(2).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(2)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPieceAt(3).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(3)).isInstanceOf(Queen.class),

                () -> assertThat(rank.findPieceAt(4).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(4)).isInstanceOf(King.class),

                () -> assertThat(rank.findPieceAt(5).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(5)).isInstanceOf(Bishop.class),

                () -> assertThat(rank.findPieceAt(6).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(6)).isInstanceOf(Knight.class),

                () -> assertThat(rank.findPieceAt(7).getTeam()).isEqualTo(WHITE),
                () -> assertThat(rank.findPieceAt(7)).isInstanceOf(Rook.class)
        );
    }
}
