package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.List;

public class BlackTurn extends Running {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = afterStartBoard().pieceByPosition(source);

        if (sourcePiece.isWhite()) {
            throw new IllegalStateException("흑색 차례엔 흑색 말만 이동 가능합니다.");
        }

        afterStartBoard().moveIfValidPosition(source, target);
    }

    @Override
    public State passTurn() {
        if (afterStartBoard().isAliveBothKings()) {
            return new WhiteTurn(afterStartBoard());
        }

        return new BlackWin(afterStartBoard());
    }

    @Override
    public List<Position> movablePath(Position position) {
        Piece sourcePiece = afterStartBoard().pieceByPosition(position);

        if (sourcePiece.isWhite()) {
            throw new IllegalStateException("흑색 차례엔 흑색 말만 이동 가능합니다.");
        }

        return afterStartBoard().movablePath(position);
    }

    @Override
    public String state() {
        return "흑색 차례";
    }
}
