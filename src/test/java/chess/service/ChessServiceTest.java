package chess.service;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A4;
import static chess.model.piece.DefaultType.EMPTY;
import static chess.model.piece.PieceType.PAWN;
import static chess.service.ChessServiceFixture.createService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.MoveDaoImpl;
import chess.dao.MoveSaveStrategy;
import chess.dao.MoveTruncator;
import chess.model.ChessGame;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChessServiceTest extends MoveTruncator {

    private ChessService service;

    @BeforeEach
    void init() {
        service = createService();
    }

    @Test
    @DisplayName("move() 테스트를 진행한다.")
    void move_whenCall_thenChangePosition() {
        // when
        service.move(A2, A4);

        // then
        final Map<Position, Piece> board = service.getBoard();
        assertAll(
                () -> assertThat(board.get(A2)).isSameAs(Empty.getInstance()),
                () -> assertThat(board.get(A4).getType()).isSameAs(PAWN)
        );
    }

    @Nested
    @DisplayName("loadGame()을 테스트한다.")
    class loadGameTest {

        @Test
        @DisplayName("게임이 저장되어 있으면 기존 값을 불러온다.")
        void loadGame_givenData_thenLoadGame() {
            // given
            final MoveDaoImpl moveDao = new MoveDaoImpl();
            moveDao.save(new MoveSaveStrategy(A2, A4));
            final ChessService service = new ChessService(new ChessGame(), moveDao);

            // when
            service.loadGame();

            // then
            final Map<Position, Piece> board = service.getBoard();
            assertAll(
                    () -> assertThat(board.get(A4).getType()).isEqualTo(PAWN),
                    () -> assertThat(board.get(A2).getType()).isEqualTo(EMPTY)
            );
        }

        @Test
        @DisplayName("게임이 없으면 기존 값을 불러온다.")
        void loadGame_givenNone_thenLoadGame() {
            // when
            service.loadGame();

            // then
            assertAll(
                    () -> assertThat(service.getBoard().get(A4).getType()).isSameAs(EMPTY),
                    () -> assertThat(service.getBoard().get(A2).getType()).isSameAs(PAWN)
            );
        }
    }

    @Nested
    @DisplayName("hasGame()을 테스트한다.")
    class hasGame {

        @Test
        @DisplayName("저장된 게임이 있으면 true가 반환된다.")
        void hasGame_giveData_thenReturnTrue() {
            // given
            service.move(A2, A4);

            // when, then
            assertThat(service.hasGame()).isTrue();
        }

        @Test
        @DisplayName("저장된 게임이 없면 false가 반환된다.")
        void hasGame_giveNone_thenReturnTrue() {
            // when, then
            assertThat(service.hasGame()).isFalse();
        }
    }
}
