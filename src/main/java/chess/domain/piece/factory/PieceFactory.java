package chess.domain.piece.factory;

import chess.config.PieceConfig;
import chess.domain.piece.*;
import chess.domain.piece.column.InitialColumn;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceFactory {
    private static Map<InitialColumn, PieceBuilderCreator> builderGetters = new HashMap<>();

    static {
        builderGetters.put(InitialColumn.PAWN, InitializedPawn.InitializedPawnBuilder::new);
        builderGetters.put(InitialColumn.ROOK, Rook.RookBuilder::new);
        builderGetters.put(InitialColumn.KNIGHT, Knight.KnightBuilder::new);
        builderGetters.put(InitialColumn.BISHOP, Bishop.BishopBuilder::new);
        builderGetters.put(InitialColumn.QUEEN, Queen.QueenBuilder::new);
        builderGetters.put(InitialColumn.KING, King.KingBuilder::new);
    }

    public static Piece createInitializedPiece(int initialColumn, Team team) {
        Initialized.InitializedBuilder builder = getBuilder(initialColumn, team);
        return builder.build();
    }


    @FunctionalInterface
    private interface PieceBuilderCreator {
        Initialized.InitializedBuilder create(String name, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score scores);
    }

    private static Initialized.InitializedBuilder getBuilder(int initialColumn, Team team) {
        PieceBuilderCreator builderGetter = builderGetters.get(InitialColumn.valueOf(initialColumn));
        return builderGetter.create(
                PieceConfig.getName(InitialColumn.valueOf(initialColumn), team),
                team,
                new ArrayList<>(),
                //todo: refac
                new Score(0));
    }

    //    public static Piece createMovedPiece(PieceType pieceType, Team team, MoveType moveType) {
//        Initialized.InitializedBuilder builder = getBuilder(pieceType, team);
//        builder.moveType(moveType);
//        return builder.build();
//    }
}
