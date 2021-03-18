package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.RealPiece;

import java.util.NoSuchElementException;

public class Square {
    private Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }

    public RealPiece getPiece() {
        if(hasPiece()){
            return (RealPiece) this.piece;
        }
        throw new NoSuchElementException("해당 칸에는 말이 없습니다.");
    }

    public boolean hasPiece() {
        return piece.isNotBlank();
    }

    public String getNotation(){
        return piece.getNotation();
    }
}
