package chess;

import chess.piece.King;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGame {

    private final List<Piece> pieces;

    public ChessGame(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public boolean isSomeBodyKingDoesntExist(){
        Map<Color,Boolean> colorKingDead = getKingSurviveStatus();
        for(Color color : colorKingDead.keySet()){
            if(colorKingDead.get(color)){
                return true;
            }
        }
        return false;
    }

    private Map<Color, Boolean> getKingSurviveStatus() {
        Map<Color,Boolean> colorKingDead = new HashMap<>(Map.of(Color.BLACK,true,Color.WHITE,true));
        for(Piece piece : pieces){
            if(piece instanceof King){
                colorKingDead.put(piece.getColor(),false);
            }
        }
        return colorKingDead;
    }

    public Color getLoseColor(){
        Map<Color,Boolean> colorKingDead = getKingSurviveStatus();
        for(Color color : colorKingDead.keySet()){
            if(colorKingDead.get(color)){
                return color;
            }
        }
        return Color.EMPTY;
    }

    public Optional<Piece> killPieceWhenExistSamePositionPiece(Piece movePiece) {
        Optional<Piece> samePositionOtherPiece = findSamePositionPiece(movePiece);
        if(samePositionOtherPiece.isEmpty()){
            return samePositionOtherPiece;
        }

        Piece otherPiece = samePositionOtherPiece.get();
        if(movePiece.isSamePosition(otherPiece) && movePiece.isOpposite(otherPiece)){
            killPiece(otherPiece);
            return Optional.of(otherPiece);
        }
        return Optional.empty();
    }

    private Optional<Piece> findSamePositionPiece(Piece movePiece) {
        for(Piece piece : pieces){
            if(piece.isSamePosition(movePiece) && movePiece.isOpposite(piece)){
                return Optional.of(piece);
            }
        }
        return Optional.empty();
    }

    private void killPiece(Piece deadPiece) {
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if(piece.equals(deadPiece)){
                pieces.remove(piece);
            }
        }
    }
}
