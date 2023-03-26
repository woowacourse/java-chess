package chess.service.mapper;

import chess.domain.chess.CampType;
import chess.domain.piece.Movable;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.move.Position;
import chess.entity.PieceEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessBoardMapper {

    public static Map<Position, Piece> from(final List<PieceEntity> pieceEntities) {
        final Map<Position, Piece> chessBoard = new HashMap<>();
        for (PieceEntity pieceEntity : pieceEntities) {
            final Position position = new Position(pieceEntity.getRank(), pieceEntity.getFile());
            final PieceType pieceType = PieceType.from(pieceEntity.getPieceType());
            final CampType campType = CampType.from(pieceEntity.getCampType());
            final Movable movable = MovableMapper.from(pieceType);
            chessBoard.put(position, new Piece(pieceType, campType, movable));
        }
        return chessBoard;
    }
}
