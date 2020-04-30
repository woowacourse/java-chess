package chess.domain.piece.factory;

import chess.domain.piece.*;
import chess.domain.piece.position.InitialColumn;
import chess.domain.piece.team.Team;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    private static Map<InitialColumn, PieceCreator> pieceCreators = new HashMap<>();

    static {
        pieceCreators.put(InitialColumn.PAWN, Pawn::new);
        pieceCreators.put(InitialColumn.ROOK, Rook::new);
        pieceCreators.put(InitialColumn.KNIGHT, Knight::new);
        pieceCreators.put(InitialColumn.BISHOP, Bishop::new);
        pieceCreators.put(InitialColumn.QUEEN, Queen::new);
        pieceCreators.put(InitialColumn.KING, King::new);
    }

    public static Piece createPieceWithInitialColumn(int initialColumn, Team team) {
        PieceCreator pieceCreator = pieceCreators.get(InitialColumn.valueOf(initialColumn));
        return pieceCreator.create(team);
    }

    public static Piece createInitializedPawn(Team team) {
        PieceCreator pieceCreator = pieceCreators.get(InitialColumn.PAWN);
        return pieceCreator.create(team);
    }

    private interface PieceCreator {
        Piece create(Team team);
    }
}
