package domain;

import static domain.Fixture.Pieces.*;
import static domain.Fixture.Positions.*;
import static domain.position.UnitVector.*;

import domain.game.Piece;
import domain.game.PieceFactory;
import domain.game.PieceType;
import domain.game.TeamColor;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import domain.position.UnitVector;
import domain.strategy.BlackPawnMoveStrategy;
import domain.strategy.ContinuousMoveStrategy;
import domain.strategy.KnightMoveStrategy;
import domain.strategy.MoveStrategy;
import domain.strategy.PawnMoveStrategy;
import domain.strategy.WhitePawnMoveStrategy;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class Fixture {
    private Fixture() {
    }

    public static class Positions {
        public static final Position A1 = Position.of(File.A, Rank.ONE);
        public static final Position A2 = Position.of(File.A, Rank.TWO);
        public static final Position A3 = Position.of(File.A, Rank.THREE);
        public static final Position A4 = Position.of(File.A, Rank.FOUR);
        public static final Position A5 = Position.of(File.A, Rank.FIVE);
        public static final Position A6 = Position.of(File.A, Rank.SIX);
        public static final Position A7 = Position.of(File.A, Rank.SEVEN);
        public static final Position A8 = Position.of(File.A, Rank.EIGHT);

        public static final Position B1 = Position.of(File.B, Rank.ONE);
        public static final Position B2 = Position.of(File.B, Rank.TWO);
        public static final Position B3 = Position.of(File.B, Rank.THREE);
        public static final Position B4 = Position.of(File.B, Rank.FOUR);
        public static final Position B5 = Position.of(File.B, Rank.FIVE);
        public static final Position B6 = Position.of(File.B, Rank.SIX);
        public static final Position B7 = Position.of(File.B, Rank.SEVEN);
        public static final Position B8 = Position.of(File.B, Rank.EIGHT);

        public static final Position C1 = Position.of(File.C, Rank.ONE);
        public static final Position C2 = Position.of(File.C, Rank.TWO);
        public static final Position C3 = Position.of(File.C, Rank.THREE);
        public static final Position C4 = Position.of(File.C, Rank.FOUR);
        public static final Position C5 = Position.of(File.C, Rank.FIVE);
        public static final Position C6 = Position.of(File.C, Rank.SIX);
        public static final Position C7 = Position.of(File.C, Rank.SEVEN);
        public static final Position C8 = Position.of(File.C, Rank.EIGHT);

        public static final Position D1 = Position.of(File.D, Rank.ONE);
        public static final Position D2 = Position.of(File.D, Rank.TWO);
        public static final Position D3 = Position.of(File.D, Rank.THREE);
        public static final Position D4 = Position.of(File.D, Rank.FOUR);
        public static final Position D5 = Position.of(File.D, Rank.FIVE);
        public static final Position D6 = Position.of(File.D, Rank.SIX);
        public static final Position D7 = Position.of(File.D, Rank.SEVEN);
        public static final Position D8 = Position.of(File.D, Rank.EIGHT);

        public static final Position E1 = Position.of(File.E, Rank.ONE);
        public static final Position E2 = Position.of(File.E, Rank.TWO);
        public static final Position E3 = Position.of(File.E, Rank.THREE);
        public static final Position E4 = Position.of(File.E, Rank.FOUR);
        public static final Position E5 = Position.of(File.E, Rank.FIVE);
        public static final Position E6 = Position.of(File.E, Rank.SIX);
        public static final Position E7 = Position.of(File.E, Rank.SEVEN);
        public static final Position E8 = Position.of(File.E, Rank.EIGHT);

        public static final Position F1 = Position.of(File.F, Rank.ONE);
        public static final Position F2 = Position.of(File.F, Rank.TWO);
        public static final Position F3 = Position.of(File.F, Rank.THREE);
        public static final Position F4 = Position.of(File.F, Rank.FOUR);
        public static final Position F5 = Position.of(File.F, Rank.FIVE);
        public static final Position F6 = Position.of(File.F, Rank.SIX);
        public static final Position F7 = Position.of(File.F, Rank.SEVEN);
        public static final Position F8 = Position.of(File.F, Rank.EIGHT);

        public static final Position G1 = Position.of(File.G, Rank.ONE);
        public static final Position G2 = Position.of(File.G, Rank.TWO);
        public static final Position G3 = Position.of(File.G, Rank.THREE);
        public static final Position G4 = Position.of(File.G, Rank.FOUR);
        public static final Position G5 = Position.of(File.G, Rank.FIVE);
        public static final Position G6 = Position.of(File.G, Rank.SIX);
        public static final Position G7 = Position.of(File.G, Rank.SEVEN);
        public static final Position G8 = Position.of(File.G, Rank.EIGHT);

        public static final Position H1 = Position.of(File.H, Rank.ONE);
        public static final Position H2 = Position.of(File.H, Rank.TWO);
        public static final Position H3 = Position.of(File.H, Rank.THREE);
        public static final Position H4 = Position.of(File.H, Rank.FOUR);
        public static final Position H5 = Position.of(File.H, Rank.FIVE);
        public static final Position H6 = Position.of(File.H, Rank.SIX);
        public static final Position H7 = Position.of(File.H, Rank.SEVEN);
        public static final Position H8 = Position.of(File.H, Rank.EIGHT);

    }

    public static class Vectors {
        public static final Set<UnitVector> ORTHOGONAL_VECTORS = Set.of(UP, RIGHT, DOWN, LEFT);
        public static final Set<UnitVector> DIAGONAL_VECTORS = Set.of(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
        public static final Set<UnitVector> OMNIDIRECTIONAL_VECTORS = Set.of(UP, RIGHT, DOWN, LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public static class Pieces {
        public static final Piece BLACK_PAWN_PIECE = PieceFactory.create(PieceType.BLACK_PAWN);
        public static final Piece BLACK_KNIGHT_PIECE = PieceFactory.create(PieceType.BLACK_KNIGHT);
        public static final Piece BLACK_BISHOP_PIECE = PieceFactory.create(PieceType.BLACK_BISHOP);
        public static final Piece BLACK_ROOK_PIECE = PieceFactory.create(PieceType.BLACK_ROOK);
        public static final Piece BLACK_QUEEN_PIECE = PieceFactory.create(PieceType.BLACK_QUEEN);
        public static final Piece BLACK_KING_PIECE = PieceFactory.create(PieceType.BLACK_KING);
        public static final Piece WHITE_PAWN_PIECE = PieceFactory.create(PieceType.WHITE_PAWN);
        public static final Piece WHITE_KNIGHT_PIECE = PieceFactory.create(PieceType.WHITE_KNIGHT);
        public static final Piece WHITE_BISHOP_PIECE = PieceFactory.create(PieceType.WHITE_BISHOP);
        public static final Piece WHITE_ROOK_PIECE = PieceFactory.create(PieceType.WHITE_ROOK);
        public static final Piece WHITE_QUEEN_PIECE = PieceFactory.create(PieceType.WHITE_QUEEN);
        public static final Piece WHITE_KING_PIECE = PieceFactory.create(PieceType.WHITE_KING);

    }

    public static class Strategies {
        public static final MoveStrategy KING_MOVE_STRATEGY = new ContinuousMoveStrategy(Vectors.OMNIDIRECTIONAL_VECTORS, 1);
        public static final MoveStrategy QUEEN_MOVE_STRATEGY = new ContinuousMoveStrategy(Vectors.OMNIDIRECTIONAL_VECTORS, 8);
        public static final MoveStrategy BISHOP_MOVE_STRATEGY = new ContinuousMoveStrategy(Vectors.DIAGONAL_VECTORS, 8);
        public static final MoveStrategy ROOK_MOVE_STRATEGY = new ContinuousMoveStrategy(Vectors.ORTHOGONAL_VECTORS, 8);
        public static final MoveStrategy KNIGHT_MOVE_STRATEGY = new KnightMoveStrategy();
        public static final MoveStrategy WHITE_PAWN_MOVE_STRATEGY = new WhitePawnMoveStrategy();
        public static final MoveStrategy BLACK_PAWN_MOVE_STRATEGY = new BlackPawnMoveStrategy();
    }

    public static PawnMoveStrategy pawnMoveStrategyOf(TeamColor teamColor) {
        if (teamColor.equals(TeamColor.WHITE)) {
            return new WhitePawnMoveStrategy();
        }
        return new BlackPawnMoveStrategy();
    }

    public static class PredefinedBoardsOfEachScore {
        public static final Map<Position, Piece> BOARD_WHITE_20_5_BLACK_20 = Map.ofEntries(
                Map.entry(A7, BLACK_PAWN_PIECE),
                Map.entry(B6, BLACK_PAWN_PIECE),
                Map.entry(B8, BLACK_KING_PIECE),
                Map.entry(C7, BLACK_PAWN_PIECE),
                Map.entry(C8, BLACK_ROOK_PIECE),
                Map.entry(D7, BLACK_BISHOP_PIECE),
                Map.entry(E6, BLACK_QUEEN_PIECE),

                Map.entry(E1, WHITE_ROOK_PIECE),
                Map.entry(E3, WHITE_PAWN_PIECE),
                Map.entry(F1, WHITE_KING_PIECE),
                Map.entry(F2, WHITE_PAWN_PIECE),
                Map.entry(F4, WHITE_KNIGHT_PIECE),
                Map.entry(G2, WHITE_PAWN_PIECE),
                Map.entry(G4, WHITE_QUEEN_PIECE),
                Map.entry(H3, WHITE_PAWN_PIECE)
        );

        public static final Map<Position, Piece> BOARD_WHITE_19_5_BLACK_20 = Map.ofEntries(
                Map.entry(E1, WHITE_ROOK_PIECE),
                Map.entry(F1, WHITE_KING_PIECE),
                Map.entry(F2, WHITE_PAWN_PIECE),  // 0.5
                Map.entry(F3, WHITE_PAWN_PIECE),  // 0.5
                Map.entry(F4, WHITE_KNIGHT_PIECE),
                Map.entry(G2, WHITE_PAWN_PIECE),
                Map.entry(G4, WHITE_QUEEN_PIECE),
                Map.entry(H3, WHITE_PAWN_PIECE),
                Map.entry(A7, BLACK_PAWN_PIECE),
                Map.entry(B6, BLACK_PAWN_PIECE),
                Map.entry(B8, BLACK_KING_PIECE),
                Map.entry(C7, BLACK_PAWN_PIECE),
                Map.entry(C8, BLACK_ROOK_PIECE),
                Map.entry(D7, BLACK_BISHOP_PIECE),
                Map.entry(E6, BLACK_QUEEN_PIECE)
        );

        public static final Map<Position, Piece> BOARD_WHITE_2_BLACK_2_5 = Map.ofEntries(
                Map.entry(A2, WHITE_PAWN_PIECE),  // 0.5
                Map.entry(A3, WHITE_PAWN_PIECE),  // 0.5
                Map.entry(C2, WHITE_PAWN_PIECE),
                Map.entry(D7, BLACK_PAWN_PIECE),  // 0.5
                Map.entry(D6, BLACK_PAWN_PIECE),  // 0.5
                Map.entry(D5, BLACK_PAWN_PIECE),  // 0.5
                Map.entry(C7, BLACK_PAWN_PIECE)
        );

        public static final Map<Position, Piece> BOARD_WHITE_0_BLACK_3_5 = Map.ofEntries(
                Map.entry(A2, BLACK_PAWN_PIECE),
                Map.entry(A3, BLACK_PAWN_PIECE),
                Map.entry(A4, BLACK_PAWN_PIECE),
                Map.entry(A5, BLACK_PAWN_PIECE),
                Map.entry(A6, BLACK_PAWN_PIECE),
                Map.entry(A7, BLACK_PAWN_PIECE),
                Map.entry(A8, BLACK_PAWN_PIECE)
        );
    }
}
