package chess.domain;

import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

public class ChessBoard {

    private final List<Piece> pieces;

    private ChessBoard(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard createNewChessBoard() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(PieceFactory.createNewBlackPieces());
        pieces.addAll(PieceFactory.createNewWhitePieces());
        return new ChessBoard(pieces);
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
