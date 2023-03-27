package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        Map<Position, Piece> boardForTest = Map.of(
            Position.from("A2"), InitialPiece.WHITE_QUEEN.getPiece(),
            Position.from("E3"), InitialPiece.WHITE_KING.getPiece(),
            Position.from("C4"), InitialPiece.BLACK_KING.getPiece(),
            Position.from("B2"), InitialPiece.BLACK_PAWN.getPiece());

        ChessBoard chessBoard = new ChessBoard(new HashMap<>(boardForTest));
        chessGame = new ChessGame(chessBoard);
    }

    @DisplayName("King 이 잡히면 게임이 종료된다.")
    @Test
    void 킹_잡히면_게임_종료() {
        chessGame.move(Position.from("A2"), Position.from("C4"));

        assertThat(chessGame.isEnd()).isTrue();
    }

    @DisplayName("King 이 잡혔을 때 우승팀을 반환한다.")
    @Test
    void 우승팀_안내() {
        chessGame.move(Position.from("A2"), Position.from("C4"));

        assertThat(chessGame.findWinningTeam()).isEqualTo(TeamColor.WHITE);
    }

    @DisplayName("King 을 잡지 않았다면 우승팀 반환 요청 시, 예외가 발생한다.")
    @Test
    void 종료_안됨_우승팀_조회_예외() {
        assertThatThrownBy(() -> chessGame.findWinningTeam())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("아직 게임이 종료되지 않아서 승자를 찾을 수 없습니다.");
    }

    @DisplayName("게임이 종료되었을 때 이동 요청 시, 예외가 발생한다.")
    @Test
    void 게임_종료_이동_요청_예외() {
        chessGame.move(Position.from("A2"), Position.from("C4"));

        assertThatThrownBy(() -> chessGame.move(Position.from("B2"), Position.from("B3")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("게임이 종료되어 이동할 수 없습니다.");
    }

}