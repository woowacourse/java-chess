package chess.domain.piece;

import chess.domain.dto.BoardDto;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.position.Position;

public abstract class Piece {
    private final String notation;
    private final MoveStrategy moveStrategy;

    public Piece(String notation, MoveStrategy moveStrategy) {
        this.notation = notation;
        this.moveStrategy = moveStrategy;
    }

    public String getNotation() {
        return notation;
    }

    public boolean canMove(BoardDto boardDto, Position from, Position to) {
        return this.moveStrategy.canMove(boardDto, from, to);
    }

    public boolean isNotBlank(){
        return !this.equals(new Blank());
    }
}
