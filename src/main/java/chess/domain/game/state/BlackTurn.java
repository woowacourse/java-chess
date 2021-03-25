package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

public class BlackTurn extends Running {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State passTurn() {
        return new WhiteTurn(board());
    }

    @Override
    public State end() {
        if (!isRunning()) {
            return new BlackWin(board());
        }
        return new Draw(board());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = board().pieceByPosition(source);
        if (sourcePiece.isWhite()) {
            throw new IllegalStateException("검정색 차례엔 검정색 말만 이동 가능합니다.");
        }
        board().moveIfValidPosition(source, target);
    }
}
