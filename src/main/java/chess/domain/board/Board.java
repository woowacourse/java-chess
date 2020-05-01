package chess.domain.board;

import chess.domain.piece.state.Pieces;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.util.Map;

public class Board {

    private final Pieces pieces;

    private Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public static Board initialize() {
        Pieces pieces = Pieces.initialize();
        return new Board(pieces);
    }

    public Board movePiece(MovingFlow movingFlow) {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        Pieces pieces = this.pieces.movePiece(from, to);
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
