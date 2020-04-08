package chess.model.domain.piece;

import chess.model.domain.board.BoardSquare;

public abstract class OneTimeMovePiece extends Piece {

    protected OneTimeMovePiece(Color color, Type type, String letter) {
        super(color, type, letter);
    }

    @Override
    protected int getRepeatCount() {
        return BoardSquare.MIN_FILE_AND_RANK_COUNT;
    }

}
