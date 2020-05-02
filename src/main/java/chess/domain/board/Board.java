package chess.domain.board;

import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.util.Map;

public class Board {

    private final PiecesState piecesState;

    private Board(PiecesState piecesState) {
        this.piecesState = piecesState;
    }

    public static Board initialize() {
        PiecesState piecesState = PiecesState.initialize();
        return new Board(piecesState);
    }

    public Board movePiece(MovingFlow movingFlow) {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        PiecesState piecesState = this.piecesState.movePiece(from, to);
        return new Board(piecesState);
    }

    public boolean isNotFinished() {
        return piecesState.isNotFinished();
    }

    public Result concludeResult() {
        return piecesState.concludeResult();
    }

    public Map<String, String> serialize() {
        return piecesState.serialize();
    }
}
