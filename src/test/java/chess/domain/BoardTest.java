package chess.domain;

import chess.domain.board.Board;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import chess.domain.position.PositionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void 하얀_폰을_움직여_보자() {
        Position source = PositionManager.getMatchPosition(2, 2);
        Position target = PositionManager.getMatchPosition(2, 4);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Pawn(target, Team.WHITE));
    }

    @Test
    public void 검은_폰을_움직여_보자() {
        Position source = PositionManager.getMatchPosition(1, 7);
        Position target = PositionManager.getMatchPosition(1, 5);
        if(board.movable(source, target, Team.BLACK)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Pawn(target, Team.BLACK));
    }

    @Test
    public void 나이트를_움직여_보자() {
        Position source = PositionManager.getMatchPosition(2, 1);
        Position target = PositionManager.getMatchPosition(1, 3);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Knight(target, Team.WHITE));
    }

    @Test
    public void 퀸을_움직여_보자() {
        Position source = PositionManager.getMatchPosition(4, 2);
        Position target = PositionManager.getMatchPosition(4, 4);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }

        source = PositionManager.getMatchPosition(4, 1);
        target = PositionManager.getMatchPosition(4, 3);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Queen(target, Team.WHITE));
    }

    @Test
    public void 킹을_움직여_보자() {
        Position source = PositionManager.getMatchPosition(5, 2);
        Position target = PositionManager.getMatchPosition(5, 4);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }

        source = PositionManager.getMatchPosition(5, 1);
        target = PositionManager.getMatchPosition(5, 2);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new King(target, Team.WHITE));
    }

    @Test
    public void 비숍을_움직여_보자() {
        Position source = PositionManager.getMatchPosition(4, 2);
        Position target = PositionManager.getMatchPosition(4, 4);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }

        source = PositionManager.getMatchPosition(3, 1);
        target = PositionManager.getMatchPosition(8, 6);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Bishop(target, Team.WHITE));
    }

    @Test
    public void 룩을_움직여_보자() {
        Position source = PositionManager.getMatchPosition(1, 2);
        Position target = PositionManager.getMatchPosition(1, 4);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }

        source = PositionManager.getMatchPosition(1, 1);
        target = PositionManager.getMatchPosition(1, 3);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Rook(target, Team.WHITE));
    }

    @Test
    void 폰으로_잡아보자() {
        Position source = PositionManager.getMatchPosition(2, 2);
        Position target = PositionManager.getMatchPosition(2, 4);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        source = PositionManager.getMatchPosition(2, 4);
        target = PositionManager.getMatchPosition(2, 5);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }

        source = PositionManager.getMatchPosition(2, 5);
        target = PositionManager.getMatchPosition(2, 6);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }

        source = PositionManager.getMatchPosition(2, 6);
        target = PositionManager.getMatchPosition(3, 7);
        if(board.movable(source, target, Team.WHITE)){
            board.move(source, target);
        }
        assertThat(board.findPiece(target)).isEqualTo(new Pawn(target, Team.WHITE));
    }
}