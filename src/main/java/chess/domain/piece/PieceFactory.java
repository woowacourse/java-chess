package chess.domain.piece;

import chess.domain.piece.state.BishopState;
import chess.domain.piece.state.EmptyState;
import chess.domain.piece.state.InitialPawnState;
import chess.domain.piece.state.KingState;
import chess.domain.piece.state.KnightState;
import chess.domain.piece.state.MoveState;
import chess.domain.piece.state.QueenState;
import chess.domain.piece.state.RookState;
import java.util.Map;

public class PieceFactory {

    private static final Map<PieceType, MoveState> pieceToInitialState = Map.of(
            PieceType.PAWN, InitialPawnState.getInstance(),
            PieceType.KNIGHT, KnightState.getInstance(),
            PieceType.ROOK, RookState.getInstance(),
            PieceType.BISHOP, BishopState.getInstance(),
            PieceType.QUEEN, QueenState.getInstance(),
            PieceType.KING, KingState.getInstance(),
            PieceType.EMPTY, EmptyState.getInstance()
    );

    private PieceFactory() {
    }

    public static Piece getInstance(PieceType pieceType, Color color) {
        return new Piece(color, pieceToInitialState.get(pieceType));
    }
}
