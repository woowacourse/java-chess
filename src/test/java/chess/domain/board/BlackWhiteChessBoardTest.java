package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.sliding.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.fixture.CoordinateFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackWhiteChessBoardTest {
    private BlackWhiteChessBoard blackWhiteChessBoard;

    @BeforeEach
    void setUp() {
        blackWhiteChessBoard = BlackWhiteChessBoard.create();
    }

    @Test
    void 체스보드를_생성하면_32개의_기물이_생성된다() {
        assertThat(blackWhiteChessBoard.pieces()).hasSize(32);
    }

    @Test
    void 경로에_기물이_있으면_이동_불가능하다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackWhiteChessBoard.move("a1", "a3", Team.WHITE))
                .withMessage("해당 도착 좌표는 이동할 수 없는 좌표입니다.");
    }

    @Test
    void 경로에_기물이_없으면_이동_가능하다() {
        blackWhiteChessBoard.move("a2", "a4", Team.WHITE);
        Piece piece = blackWhiteChessBoard.pieces().get(A_FOUR);
        assertThat(piece).isEqualTo(new WhitePawn(Team.WHITE));
    }

    @Test
    void 도착지에_상대팀_기물이_있으면_잡아먹는다() {
        blackWhiteChessBoard.move("a2", "a4", Team.WHITE);
        blackWhiteChessBoard.move("a1", "a3", Team.WHITE);
        blackWhiteChessBoard.move("a3", "b3", Team.WHITE);
        blackWhiteChessBoard.move("b3", "b7", Team.WHITE);
        Piece piece = blackWhiteChessBoard.pieces().get(B_SEVEN);
        assertThat(piece).isEqualTo(new Rook(Team.WHITE));
    }
    
    @Test
    void 해당_좌표의_기물이_아군인지_확인() {
        assertAll(
                () -> assertThat(blackWhiteChessBoard.isAllyAtCoordinate(new Knight(Team.WHITE), B_TWO)).isTrue(),
                () -> assertThat(blackWhiteChessBoard.isAllyAtCoordinate(new Knight(Team.WHITE), B_EIGHT)).isFalse()
        );
    }
    
    @Test
    void 해당_좌표의_기물이_적군인지_확인() {
        assertAll(
                () -> assertThat(blackWhiteChessBoard.isEnemyAtCoordinate(new Knight(Team.WHITE), B_TWO)).isFalse(),
                () -> assertThat(blackWhiteChessBoard.isEnemyAtCoordinate(new Knight(Team.WHITE), B_EIGHT)).isTrue()
        );
    }
    
    @Test
    void 해당_좌표가_비었는지_확인() {
        assertAll(
                () -> assertThat(blackWhiteChessBoard.isEmpty(B_THREE)).isTrue(),
                () -> assertThat(blackWhiteChessBoard.isEmpty(B_TWO)).isFalse()
        );
    }
    
    @Test
    void 지정한_팀의_현재_점수_구하기() {
        double score = blackWhiteChessBoard.calculateScore(Team.WHITE);
        assertThat(score).isEqualTo(38.0);
    }
    
    @Test
    void 킹이_죽었는지_확인() {
        assertThat(blackWhiteChessBoard.isKingDead()).isFalse();
    }
}
