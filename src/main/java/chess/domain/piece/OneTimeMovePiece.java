package chess.domain.piece;

import chess.domain.board.BoardSquare;

public abstract class OneTimeMovePiece extends Piece {

    protected OneTimeMovePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    protected int getRepeatCount() {
        return BoardSquare.MIN_FILE_AND_RANK_COUNT;
    }

}
