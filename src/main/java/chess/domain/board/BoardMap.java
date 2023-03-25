package chess.domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.PositionPiece;
import chess.domain.position.File;
import chess.domain.position.Position;

public class BoardMap {

    private static final PositionPiece EMPTY_PIECE_2 = new PositionPiece(new Position(null, null), EmptyPiece.create());
    // TODO: 2023/03/25 null 제거

    private final List<PositionPiece> pieces;

    public BoardMap(List<PositionPiece> pieces) {
        this.pieces = pieces;
    }

    public static BoardMap from(Map<Position, Piece> pieces) {
        List<PositionPiece> newPieces = new ArrayList<>();
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            newPieces.add(new PositionPiece(position, piece));
        }
        return new BoardMap(newPieces);
    }

    public boolean isTeamSame(Position position, Position other) {
        return getPieceAt(position).isSameTeamWith(getPieceAt(other));
    }

    public boolean hasPieceAt(Position position) {
        return !getPieceAt(position).isEmpty();
    }

    private PositionPiece getPieceAt(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isAt(position))
                .findFirst()
                .orElse(EMPTY_PIECE_2);
    }

    public long countPawnsIn(File file) {
        return pieces.stream()
                .filter(it -> it.isAt(file))
                .filter(it -> it.hasType(PieceType.PAWN))
                .count();
    }
}
