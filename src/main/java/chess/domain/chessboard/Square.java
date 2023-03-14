package chess.domain.chessboard;

import chess.domain.chessboard.state.Empty;
import chess.domain.chessboard.state.PieceState;

public class Square {
    private final PieceState pieceState;

    public Square(){
        pieceState = new Empty();
    }

    public boolean isEmpty(){
        return pieceState.isEmpty();
    }



}
