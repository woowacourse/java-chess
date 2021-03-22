package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.RealPiece;

import java.util.NoSuchElementException;

public class Square {
    private Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }

    public RealPiece getPiece() {
        if (hasPiece()) {
            return (RealPiece) this.piece;
        }
        throw new NoSuchElementException("해당 칸에는 말이 없습니다.");
    }

    public boolean hasPiece() {
        return piece.isNotBlank();
    }

    public String getNotation() {
        return piece.getNotationText();
    }

    public MoveResult move(MoveOrder moveOrder) {
        if (!hasPiece()) {
            throw new NoSuchElementException("해당 위치엔 말이 없습니다.");
        }

        if (piece.canMove(moveOrder)) {
            Piece removedPiece = moveOrder.getFrom().getPiece();
            moveOrder.getTo().piece = this.piece;
            this.piece = Blank.getInstance();
            return new MoveResult(removedPiece);
        }

        throw new IllegalArgumentException("기물이 움직일 수 없는 상황입니다.");
    }
}
