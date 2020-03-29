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
                INITIALIZED_POSITIONS.add(new Blank('.', Team.BLANK, new Position(col, row)));
            }
        }
        INITIALIZED_POSITIONS.set(36, new Bishop('b', Team.WHITE, new Position(5, 5)));
        INITIALIZED_POSITIONS.set(54, new Rook('R', Team.BLACK, new Position(7, 7)));
        INITIALIZED_POSITIONS.set(0, new Queen('q', Team.WHITE, new Position(1, 1)));
        INITIALIZED_POSITIONS.set(60, new King('k', Team.WHITE, new Position(5, 8)));
        INITIALIZED_POSITIONS.set(27, new Rook('r', Team.WHITE, new Position(4, 4)));
        INITIALIZED_POSITIONS.set(37, new Bishop('B', Team.BLACK, new Position(6, 5)));
        INITIALIZED_POSITIONS.set(20, new WhitePawn('p', Team.WHITE, new Position(5, 3)));
        INITIALIZED_POSITIONS.set(33, new Knight('N', Team.BLACK, new Position(2, 5)));
        INITIALIZED_POSITIONS.set(53, new BlackPawn('P', Team.BLACK, new Position(6, 7)));
    }

    @DisplayName("체스판 위 남은 말들로 점수 계산")
    @Test
    void result() {
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(gameResult.calculateScore(board, Team.WHITE)).isEqualTo(18);
        assertThat(gameResult.calculateScore(board, Team.BLACK)).isEqualTo(11.5);
    }

    @DisplayName("체스판 위 남은 말들로 점수 계산 : 같은 열에 pawn 여러개 있을 경우 0.5로 계산")
    @Test
    void resultConsideringPawn() {
        INITIALIZED_POSITIONS.set(45, new BlackPawn('P', Team.BLACK, new Position(6, 6)));
        INITIALIZED_POSITIONS.set(61, new BlackPawn('P', Team.BLACK, new Position(6, 8)));

        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(gameResult.calculateScore(board, Team.WHITE)).isEqualTo(18);
        assertThat(gameResult.calculateScore(board, Team.BLACK)).isEqualTo(12);
    }
}
