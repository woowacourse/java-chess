package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.service.PieceService;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.util.Map;

public class Board {
    public static final int LINE_START = 1;
    public static final int LINE_END = 8;
    private final PieceService pieceService;
    private final PiecesState piecesState;

    private Board(PieceService pieceService, PiecesState piecesState) {
        this.pieceService = pieceService;
        this.piecesState = piecesState;
    }

    public static Board initialize(PieceService pieceService) {
        PiecesState piecesState = pieceService.initialize();
        return new Board(pieceService, piecesState);
    }

    public static Board resume(PieceService pieceService) {
        PiecesState piecesState = pieceService.getAll();
        return new Board(pieceService, piecesState);
    }

    public Board movePiece(MovingFlow movingFlow) {
        PiecesState piecesState = pieceService.movePiece(this.piecesState, movingFlow);
        return new Board(pieceService, piecesState);
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
