package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> value;
    private final BoardChecker boardChecker;

    public Board(final Initializable initializable) {
        value = initializable.init();
        this.boardChecker = new BoardChecker(value);
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public void move(final Position from, final Position to, final Color currentColor) {
        boardChecker.checkPosition(from, to, currentColor);
        final var fromPiece = value.get(from);

        fromPiece.checkMoveRange(this, from, to);
        putPiece(from, to);
    }

    private void putPiece(final Position from, final Position to) {
        final var toPiece = value.get(to);

        value.put(to, value.remove(from));

        boardChecker.checkRemovedKing(toPiece);
    }

    public void checkHasPieceInDiagonal(final Position from, final Position to) {
        boardChecker.checkPieceInDiagonal(from, to);
    }

    public boolean hasPiece(final Position position) {
        return value.get(position) != null;
    }

    public Map<Color, Double> sumScore(final Color target) {
        return new BoardCalculator(getValue()).sumScore(target);
    }

    public void checkHasPieceInLiner(final Position from, final Position to) {
        boardChecker.checkHasPieceInLiner(from, to);
    }
}
