package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private static List<Piece> INITIALIZED_POSITIONS;
    private GameResult gameResult = new GameResult();

    @BeforeEach
    void setUp() {
        INITIALIZED_POSITIONS = new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                INITIALIZED_POSITIONS.add(Blank.create(new Position(col, row)));
            }
        }
        INITIALIZED_POSITIONS.set(36, Bishop.createWhite(new Position(5, 5)));
        INITIALIZED_POSITIONS.set(54, Rook.createBlack(new Position(7, 7)));
        INITIALIZED_POSITIONS.set(0, Queen.createWhite(new Position(1, 1)));
        INITIALIZED_POSITIONS.set(60, King.createWhite(new Position(5, 8)));
        INITIALIZED_POSITIONS.set(27, Rook.createWhite(new Position(4, 4)));
        INITIALIZED_POSITIONS.set(37, Bishop.createBlack(new Position(6, 5)));
        INITIALIZED_POSITIONS.set(20, Pawn.createWhite(new Position(5, 3)));
        INITIALIZED_POSITIONS.set(33, Knight.createBlack(new Position(2, 5)));
        INITIALIZED_POSITIONS.set(53, Pawn.createBlack(new Position(6, 7)));
    }

    @DisplayName("체스판 위 남은 말들로 점수 계산")
    @Test
    void result() {
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(gameResult.calculateScore(board, Turn.WHITE)).isEqualTo(18);
        assertThat(gameResult.calculateScore(board, Turn.BLACK)).isEqualTo(11.5);
    }

    @DisplayName("체스판 위 남은 말들로 점수 계산 : 같은 열에 pawn 여러개 있을 경우 0.5로 계산")
    @Test
    void resultConsideringPawn() {
        INITIALIZED_POSITIONS.set(45, Pawn.createBlack(new Position(6, 6)));
        INITIALIZED_POSITIONS.set(61, Pawn.createBlack(new Position(6, 8)));

        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(gameResult.calculateScore(board, Turn.WHITE)).isEqualTo(18);
        assertThat(gameResult.calculateScore(board, Turn.BLACK)).isEqualTo(12);
    }
}
