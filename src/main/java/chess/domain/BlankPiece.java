package chess.domain;

public class BlankPiece implements ChessPiece {
    public BlankPiece() {
    }
    @Override
    public String toString(){
        return ".";
    }
}
