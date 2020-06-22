package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.Result;
import chess.domain.position.Position;

import java.util.Map;

public class Board {
    public static final int LINE_START = 1;
    public static final int LINE_END = 8;
    private final PiecesState piecesState;

    private Board(PiecesState piecesState) {
        this.piecesState = piecesState;
    }

    public static Board of(PiecesState piecesState) {
        return new Board(piecesState);
    }

    public boolean isNotFinished() {
        return piecesState.isNotFinished();
    }

    public Result concludeResult() {
        return Result.conclude(piecesState);
    }

    public Map<String, String> serialize() {
        return piecesState.serialize();
    }

    Piece getPiece(Position position) {
        return piecesState.findPiece(position);
    }
}
