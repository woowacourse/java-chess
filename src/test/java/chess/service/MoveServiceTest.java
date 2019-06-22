package chess.service;

import chess.domain.board.Board;
import chess.domain.direction.core.Square;
import chess.domain.piece.*;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveServiceTest {

    @Test
    void 말이_잘_이동하는지_테스트() {
        Map<Square, Piece> testBoard = new HashMap<>();
        testBoard.put(Square.of(0, 0), new Rook(Team.BLACK));
        testBoard.put(Square.of(1, 0), new Knight(Team.BLACK));
        testBoard.put(Square.of(2, 0), new Bishop(Team.BLACK));
        testBoard.put(Square.of(3, 0), new Queen(Team.BLACK));
        testBoard.put(Square.of(4, 0), new King(Team.BLACK));
        testBoard.put(Square.of(5, 0), new Bishop(Team.BLACK));
        testBoard.put(Square.of(6, 0), new Knight(Team.BLACK));
        testBoard.put(Square.of(7, 0), new Rook(Team.BLACK));

        testBoard.put(Square.of(0, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(1, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(2, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(3, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(4, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(5, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(6, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(7, 1), new Pawn(Team.BLACK));

        testBoard.put(Square.of(0, 7), new Rook(Team.WHITE));
        testBoard.put(Square.of(1, 7), new Knight(Team.WHITE));
        testBoard.put(Square.of(2, 7), new Bishop(Team.WHITE));
        testBoard.put(Square.of(3, 7), new Queen(Team.WHITE));
        testBoard.put(Square.of(4, 7), new King(Team.WHITE));
        testBoard.put(Square.of(5, 7), new Bishop(Team.WHITE));
        testBoard.put(Square.of(6, 7), new Knight(Team.WHITE));
        testBoard.put(Square.of(7, 7), new Rook(Team.WHITE));

        testBoard.put(Square.of(0, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(1, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(2, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(3, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(4, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(5, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(6, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(7, 6), new Pawn(Team.WHITE));

        MoveService moveService = MoveService.getInstance();
        assertThat(moveService.request(Square.of(0,6), Square.of(0,5))).isEqualTo(new Board(testBoard));
    }
}
