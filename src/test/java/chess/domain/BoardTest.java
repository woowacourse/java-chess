package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.board.position.Position;
import chess.domain.board.position.PositionTest;
import chess.domain.piece.team.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    @DisplayName("체스보드 생성 테스트")
    void createTest() {
        assertThat(new Board()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("체스보드 좌표 초기화 테스트")
    void getMapTest() {
        Board board = new Board();
        List<Position> positions = new ArrayList<>();
        for (Map<Position, Piece> square : board.getSquares()) {
            positions.addAll(square.keySet());
        }
        assertThat(positions).containsAll(PositionTest.testPositions());
    }

    @Test
    @DisplayName("체스판 초기 상태 세팅")
    void chessInitStateTest() {
        Board board = new Board();
        assertThat(board.getSquares().get(0).get(Position.valueOf("a8"))).isEqualTo(new Rook(new Black(Symbol.ROOK)));
        assertThat(board.getSquares().get(7).get(Position.valueOf("a1"))).isEqualTo(new Rook(new White(Symbol.ROOK)));
        assertThat(board.getSquares().get(7).get(Position.valueOf("h1"))).isEqualTo(new Rook(new White(Symbol.ROOK)));
        assertThat(board.getSquares().get(0).get(Position.valueOf("h8"))).isEqualTo(new Rook(new Black(Symbol.ROOK)));
        assertThat(board.getSquares().get(3).get(Position.valueOf("c5"))).isEqualTo(new Empty(new Neutral(Symbol.EMPTY)));
        assertThat(board.getSquares().get(7).get(Position.valueOf("d1"))).isEqualTo(new Queen(new White(Symbol.QUEEN)));
        assertThat(board.getSquares().get(7).get(Position.valueOf("e1"))).isEqualTo(new King(new White(Symbol.KING)));
        assertThat(board.getSquares().get(0).get(Position.valueOf("d8"))).isEqualTo(new Queen(new Black(Symbol.QUEEN)));
        assertThat(board.getSquares().get(0).get(Position.valueOf("e8"))).isEqualTo(new King(new Black(Symbol.KING)));
    }
}
