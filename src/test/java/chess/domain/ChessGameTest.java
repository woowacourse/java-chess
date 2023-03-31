package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    public ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = ChessGame.create();
        System.out.println("setup");
    }

    @Test
    @DisplayName("시작 테스트")
    void start() {
        chessGame.start();
        assertThat(chessGame.getStatus()).isEqualTo(GameStatus.MOVE);
        assertThat(chessGame.getTurn()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("이동 테스트 - 이동 가능 경우")
    void success_move() {
        chessGame.start();
        chessGame.move(List.of("a2", "a4"));
        assertThat(chessGame.getStatus()).isEqualTo(GameStatus.MOVE);
        assertThat(chessGame.getTurn()).isEqualTo(Color.BLACK);
        Piece empty = chessGame.getBoard().getPieceAtPosition(Position.from("a2"));
        Piece pawn = chessGame.getBoard().getPieceAtPosition(Position.from("a4"));
        assertThat(empty).isEqualTo(Empty.create(Color.NONE));
        assertThat(pawn).isEqualTo(Pawn.create(Color.WHITE));
    }

    @Test
    @DisplayName("이동 테스트 - 이동 불가 경우")
    void error_move() {
        chessGame.start();
        assertThatThrownBy(() -> chessGame.move(List.of("a1", "a2")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("종료 테스트")
    void end() {
        chessGame.start();
        chessGame.end();
        assertThat(chessGame.getStatus()).isEqualTo(GameStatus.END);
    }
}
