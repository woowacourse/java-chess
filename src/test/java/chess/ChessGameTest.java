package chess;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static chess.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
    }

    @Test
    void 사용자가_말을_움직였을때_말의_위치를_이동할_수_있다() {
        chessGame.movePiece(C_2, C_4);

        assertThat(chessGame.findPiece(C_2)).isInstanceOf(Empty.class);
        assertThat(chessGame.findPiece(C_4)).isInstanceOf(Pawn.class);
    }

    @Test
    void 사용자가_말을_움직였을때_다른_팀의_말을_움직이는_경우_예외가_발생한다() {
        assertThatThrownBy(() -> chessGame.movePiece(C_7, C_6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 말만 옮길 수 있습니다.");
    }

    @Test
    void 킹이_잡히지_않으면_게임이_끝나지_않는다() {
        assertThat(chessGame.isFinished()).isFalse();
    }

    @Test
    void 킹이_잡히면_게임이_끝난다() {
        chessGame.movePiece(E_2, E_4);
        chessGame.movePiece(F_7, F_5);
        chessGame.movePiece(E_4, F_5);
        chessGame.movePiece(G_7, G_5);
        chessGame.movePiece(D_1, H_5);
        chessGame.movePiece(C_7, C_6);
        chessGame.movePiece(H_5, E_8); // 화이트 팀이 킹을 잡는 상황

        assertThat(chessGame.isFinished()).isTrue();
    }

    @Test
    void 킹이_잡힌_뒤_체스_말을_움직일_수_없다() {
        chessGame.movePiece(E_2, E_4);
        chessGame.movePiece(F_7, F_5);
        chessGame.movePiece(E_4, F_5);
        chessGame.movePiece(G_7, G_5);
        chessGame.movePiece(D_1, H_5);
        chessGame.movePiece(C_7, C_6);
        chessGame.movePiece(H_5, E_8); // 화이트 팀이 킹을 잡는 상황

        assertThatThrownBy(() -> chessGame.movePiece(C_6, C_5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 끝나 더 이상 체스 말을 움직일 수 없습니다.");
    }
}
