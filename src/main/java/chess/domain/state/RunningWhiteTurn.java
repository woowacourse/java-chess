package chess.domain.state;

import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

import chess.domain.position.Position;

public class RunningWhiteTurn extends Running {

    public RunningWhiteTurn(Map<Position, Piece> pieces) {
        super(pieces);
    }

    @Override
    public State proceed(Command command) {
        if (!command.isMove()) {
            return runByCommand(command);
        }
        validateMoveOpponent(command);
        board.movePiece(command.getFromPosition(), command.getToPosition());
        return checkBlackKingExist();
    }

    private void validateMoveOpponent(Command command) {
        if (!board.isWhite(command.getFromPosition())) {
            throw new IllegalArgumentException(CANNOT_MOVE_OPPONENT_PIECE);
        }
    }

    private State checkBlackKingExist() {
        if (board.isKingNotExist(Color.BLACK)) {
            return new Finished(this.board.getPieces());
        }
        return new RunningBlackTurn(board.getPieces());
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }
}
