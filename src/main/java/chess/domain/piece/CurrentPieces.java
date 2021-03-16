package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class CurrentPieces {

    private List<Piece> currentPieces;

    private CurrentPieces(List<Piece> currentPieces) {
        this.currentPieces = currentPieces;
    }

//    public static CurrentPieces generate() {
//        List<Piece> pieces = new ArrayList<>();
//
//        return new CurrentPieces();
//    }
}
