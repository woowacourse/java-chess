package chess.domain.piece.factory;

import chess.domain.piece.*;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceFactory {
    private static Map<PieceType, PieceBuilderCreator> builderGetters = new HashMap<>();

    static {
        builderGetters.put(PieceType.INITIALIZED_PAWN, InitializedPawn.InitializedPawnBuilder::new);
        builderGetters.put(PieceType.MOVED_PAWN, MovedPawn.MovedPawnBuilder::new);
        builderGetters.put(PieceType.ROOK, Rook.RookBuilder::new);
        builderGetters.put(PieceType.KNIGHT, King.KingBuilder::new);
        builderGetters.put(PieceType.BISHOP, Bishop.BishopBuilder::new);
        builderGetters.put(PieceType.QUEEN, Queen.QueenBuilder::new);
        builderGetters.put(PieceType.KING, King.KingBuilder::new);
    }

    public static Piece createInitializedPiece(PieceType pieceType, Position position, Team team) {
        Initialized.InitializedBuilder builder = getBuilder(pieceType, position, team);
        return builder.build();
    }


    public static Piece createInitializedPieceWithInitialColumn(int initialColumn, Position position, Team team) {
        PieceType pieceType = PieceType.findByInitialColumn(initialColumn);
        return createInitializedPiece(pieceType, position, team);
    }

    public static Piece createMovedPiece(PieceType pieceType, Position position, Team team, MoveType moveType) {
        Initialized.InitializedBuilder builder = getBuilder(pieceType, position, team);
        builder.moveType(moveType);
        return builder.build();
    }

    private static Initialized.InitializedBuilder getBuilder(PieceType pieceType, Position position, Team team) {
        PieceBuilderCreator builderGetter = builderGetters.get(pieceType);
        return builderGetter.create(pieceType.getName(team), position, team, pieceType.getCanNotMoveStrategies(), pieceType.getScore());
    }

    private interface PieceBuilderCreator {
        Initialized.InitializedBuilder create(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score);
    }
}
