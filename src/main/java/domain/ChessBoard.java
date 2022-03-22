package domain;

import domain.piece.Piece;
import domain.piece.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.Locale;
import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String getSymbol(Position position) {
        Piece piece = board.get(position);
        if(piece == null){
            return ".";
        }
        return piece.getSymbol();

//        if(piece.getPlayer().equals(Player.BLACK)){
//            return piece.getInitial().toUpperCase(Locale.ROOT);
//        }
        // private String initial;  getInitial() {return initial;}
    }
}
