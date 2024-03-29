package chess.domain.dto;

import chess.domain.db.ChessBoardEntity;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardEntityDto {
    private final List<ChessBoardEntity> pieces;

    private ChessBoardEntityDto(List<ChessBoardEntity> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoardEntityDto of(List<Piece> pieces) {
        List<ChessBoardEntity> piece = makeChessBoard(pieces);
        return new ChessBoardEntityDto(piece);
    }

    private static List<ChessBoardEntity> makeChessBoard(List<Piece> pieces) {
        List<ChessBoardEntity> pieceSet = new ArrayList<>();
        pieces.forEach(piece -> pieceSet.add(
                new ChessBoardEntity(piece.getPosition().getPosition(), piece.getTeam().name(), piece.getType().name())));
        return pieceSet;
    }

    public List<ChessBoardEntity> getPieces() {
        return pieces;
    }
}
