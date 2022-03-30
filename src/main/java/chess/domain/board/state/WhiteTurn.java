package chess.domain.board.state;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public final class WhiteTurn extends Playing {

    public WhiteTurn(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isBlackTurn() {
        return false;
    }

    @Override
    public BoardState move(Position start, Position target) {
        Piece selected = getPiece(start);

        if (selected.isBlack()) {
            throw new IllegalArgumentException(IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE);
        }

        return movePiece(start, target);
    }

    @Override
    public End judgeWinner() {
        return new WhiteWin(ranks);
    }

    @Override
    public Playing judgeTurn() {
        return new BlackTurn(ranks);
    }

    @Override
    public String findTurn() {
        return "백";
    }
}
