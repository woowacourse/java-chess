package chess;

import chess.piece.*;

import java.util.ArrayList;
import java.util.List;

public class BoardInitialization {

    public void initializeBoard(Chessboard chessboard) {
        setWhitePieces(chessboard);
        setBlackPieces(chessboard);
    }

    private void setBlackPieces(Chessboard chessboard) {
        chessboard.putPiece(Rank.EIGHT, initializeWithoutPawn(Camp.BLACK));
        chessboard.putPiece(Rank.SEVEN,initializePawn(Camp.BLACK));
    }

    private void setWhitePieces(Chessboard chessboard) {
        chessboard.putPiece(Rank.TWO,initializePawn(Camp.WHITE));
        chessboard.putPiece(Rank.ONE, initializeWithoutPawn(Camp.WHITE));
    }

    private List<Piece> initializeWithoutPawn(Camp camp){
        return List.of(
                new Rook(camp), new Knight(camp),new Bishop(camp),new Queen(camp),
                new King(camp),new Bishop(camp),new Knight(camp),new Rook(camp)
        );
    }

    private List<Piece> initializePawn(Camp camp){
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(camp));
        }

        return pieces;
    }
}
