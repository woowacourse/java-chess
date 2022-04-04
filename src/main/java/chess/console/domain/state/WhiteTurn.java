package chess.console.domain.state;

import chess.console.domain.board.Board;
import chess.console.domain.board.Position;
import chess.console.domain.piece.Color;

final class WhiteTurn extends Running {

    public WhiteTurn(Board board) {
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

    @Override
    public Color getTurn() {
        return Color.WHITE;
    }
}
