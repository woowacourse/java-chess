package chess.domain.board;

import chess.domain.piece.PiecesState;
import chess.domain.piece.Result;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;

import java.util.Map;

public class Board {

    private final PiecesState pieces;

    private Board(PiecesState pieces) {
        this.pieces = pieces;
    }

    public static Board initiaize() {
        PiecesState pieces = PiecesState.initialize();
        return new Board(pieces);
    }

    public Board movePiece(MovingFlow movingFlow) {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        PiecesState pieces =this.pieces.movePiece(from, to);
        return new Board(pieces);
    }

    public boolean isNotFinished() {
        return pieces.isNotFinished();
    }

    public Result concludeResult() {
        return pieces.concludeResult();
    }

    public Map<String, String> serialize() {
        return pieces.serialize();
    }
}
