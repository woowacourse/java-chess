package chess.domain.board;

import chess.domain.game.Result;
import chess.domain.game.Turn;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    public static final int LOWER_BOUNDARY = 1;
    public static final int UPPER_BOUNDARY = 8;
    private static final int OVER_COUNT = 2;
    public static final int DIVIDE_VALUE = 2;
    public static final int ALL_KING_LIVE = 2;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        final Piece sourcePiece = getPiece(source);
        board.put(target, sourcePiece);
        board.put(source, new Empty(Type.EMPTY, Side.NEUTRALITY));
    }

    public Side findSideByPosition(final Position position) {
        return getPiece(position).getSide();
    }

    public Path findMovablePositions(final Position source) {
        return getPiece(source).findMovablePositions(source, this);
    }

    public boolean isRightTurn(final Position source, final Turn turn) {
        final Piece piece = board.get(source);
        return isWhite(turn, piece) || isBlack(turn, piece);
    }

    private boolean isBlack(final Turn turn, final Piece piece) {
        return piece.isBlack() && !turn.isWhite();
    }

    private boolean isWhite(final Turn turn, final Piece piece) {
        return piece.isWhite() && turn.isWhite();
    }

    public boolean isKing(final Position target) {
        final Piece piece = board.get(target);
        return piece.isKing();
    }

    public Result getResult() {
        return new Result(this.getBoard());
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}