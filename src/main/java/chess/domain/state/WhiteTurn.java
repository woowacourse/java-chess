package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;

final class WhiteTurn extends Running {

    WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public State movePiece(Position source, Position destination) {
        board.move(source, destination, Color.WHITE);
        if (!board.hasKing(Color.BLACK)) {
            return new WhiteWin(board);
        }
        return new BlackTurn(board);
    }
}
