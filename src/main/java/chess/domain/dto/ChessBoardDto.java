package chess.domain.dto;

import chess.domain.db.ChessBoard;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardDto {
    private final List<ChessBoard> pieces;

    private ChessBoardDto(List<ChessBoard> pieces) {
        this.pieces = pieces;
    }

    public ChessBoardDto of(List<Piece> pieces) {
        List<ChessBoard> piece = makeChessBoard(pieces);
        return new ChessBoardDto(piece);
    }

    private List<ChessBoard> makeChessBoard(List<Piece> pieces) {
        List<ChessBoard> pieceSet = new ArrayList<>();
        pieces.forEach(piece -> pieceSet.add(
                new ChessBoard(piece.getPosition().getPosition(), piece.getTeam().name(), piece.getType().name())));
        return pieceSet;
    }

    public List<ChessBoard> getPieces() {
        return pieces;
    }
}
