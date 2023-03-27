package chess.domain;

import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ResultTest {

    private ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        chessGame.movePiece(E_2, E_4);
        chessGame.movePiece(F_7, F_5);
        chessGame.movePiece(E_4, F_5);
        chessGame.movePiece(G_7, G_5);
        chessGame.movePiece(D_1, H_5);
        chessGame.movePiece(C_7, C_6);
        chessGame.movePiece(H_5, E_8); // 화이트 팀이 킹을 잡는 상황
    }

    @Test
    void 체스게임_후_결과를_구할_수_있다() {
        Result result = Result.from(chessGame);
        assertAll(
                () -> assertThat(result.getWinner()).isEqualTo(Team.WHITE),
                () -> assertThat(result.getScore().get(Team.BLACK)).isEqualTo(37.0),
                () -> assertThat(result.getScore().get(Team.WHITE)).isEqualTo(37.0)
        );

    }
}
