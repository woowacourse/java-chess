package chess;

import static org.assertj.core.api.Assertions.*;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BoardTest {
    private Board board;
    private Pawn pawn;

    @BeforeEach
    void setup() {
        Map<Position, Piece> pieces = new HashMap<>();
        pawn = new Pawn(Team.BLACK);
        pieces.put(Position.of(File.A, Rank.ONE), pawn);
        board = new Board(pieces);
    }

    @DisplayName("Board객체 생성 테스트")
    @Test
    void constructTest() {
        assertThat(board).isInstanceOf(Board.class);
    }

    @DisplayName("move메서드를 실행하면 Map이 수정되는지 테스트")
    @Test
    void moveTest() {
        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.TWO);
        board.move(from, to);
        assertThat(board.getPiece(to)).isEqualTo(pawn);
    }
}
