package chess.console.domain.state;

import chess.console.domain.board.Board;
import chess.console.domain.board.Position;
import chess.console.domain.piece.Color;

final class BlackTurn extends Running {

    BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State movePiece(Position source, Position destination) {
        board.move(source, destination, Color.BLACK);
        if (!board.hasKing(Color.WHITE)) {
            return new BlackWin(board);
        }
        return new WhiteTurn(board);
    }
}
