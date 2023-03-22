package chess.gamecommand;

import java.util.List;

import chess.board.Board;
import chess.board.Position;
import chess.piece.Piece;
import chess.piece.Pieces;

public class Play implements CommandStatus {

    private final Board board;

    public Play(final Board board) {
        this.board = board;
    }

    @Override
    public CommandStatus start() {
        return new Play(new Board(new Pieces()));
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
        return new Play(board);
    }

    @Override
    public CommandStatus end() {
        return new End();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
