package chess.domain;

import chess.domain.board.Board;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private Board initializedBoard;

    @BeforeEach
    public void setUp() {
        initializedBoard = new Board();
    }

    @Test
    public void 첫_위치의_하얀_폰을_움직여_보자() {
        Position source = Positions.matchWith(2, 2);
        Position target = Positions.matchWith(2, 4);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new Pawn(target, Team.WHITE));
    }

    @Test
    public void 첫_위치의_검은_폰을_움직여_보자() {
        Position source = Positions.matchWith(1, 7);
        Position target = Positions.matchWith(1, 5);
        if (initializedBoard.movable(source, target, Team.BLACK)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new Pawn(target, Team.BLACK));
    }

    @Test
    public void 나이트를_움직여_보자() {
        Position source = Positions.matchWith(2, 1);
        Position target = Positions.matchWith(1, 3);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new Knight(target, Team.WHITE));
    }

    @Test
    public void 퀸을_움직여_보자() {
        Position source = Positions.matchWith(4, 2);
        Position target = Positions.matchWith(4, 4);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        source = Positions.matchWith(4, 1);
        target = Positions.matchWith(4, 3);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new Queen(target, Team.WHITE));
    }

    @Test
    public void 킹을_움직여_보자() {
        Position source = Positions.matchWith(5, 2);
        Position target = Positions.matchWith(5, 4);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        source = Positions.matchWith(5, 1);
        target = Positions.matchWith(5, 2);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new King(target, Team.WHITE));
    }

    @Test
    public void 비숍을_움직여_보자() {
        Position source = Positions.matchWith(4, 2);
        Position target = Positions.matchWith(4, 4);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        source = Positions.matchWith(3, 1);
        target = Positions.matchWith(8, 6);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new Bishop(target, Team.WHITE));
    }

    @Test
    public void 룩을_움직여_보자() {
        Position source = Positions.matchWith(1, 2);
        Position target = Positions.matchWith(1, 4);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        source = Positions.matchWith(1, 1);
        target = Positions.matchWith(1, 3);
        if (initializedBoard.movable(source, target, Team.WHITE)) {
            initializedBoard.move(source, target);
        }

        assertThat(initializedBoard.findPiece(target)).isEqualTo(new Rook(target, Team.WHITE));
    }

    @Test
    public void 폰으로_잡아_보자() {
        Map<Position, Piece> boardState = new HashMap<>();
        boardState.put(Positions.matchWith(5, 4), new Pawn(Positions.matchWith(5, 4), Team.WHITE));
        boardState.put(Positions.matchWith(6, 5), new Pawn(Positions.matchWith(6, 5), Team.BLACK));
        Board controlledBoard = new Board(boardState);

        Position source = Positions.matchWith(5, 4);
        Position target = Positions.matchWith(6, 5);
        if (controlledBoard.movable(source, target, Team.WHITE)) {
            controlledBoard.move(source, target);
        }

        assertThat(controlledBoard.findPiece(target)).isEqualTo(new Pawn(target, Team.WHITE));
    }

    @Test
    public void 점수_계산_확인하기() {
        assertThat(initializedBoard.getScore(Team.WHITE)).isEqualTo(38);
        assertThat(initializedBoard.getScore(Team.BLACK)).isEqualTo(38);
    }
}