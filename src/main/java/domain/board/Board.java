package domain.board;

import domain.piece.Color;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.position.Position;
import exception.AllyPiecePositionException;
import exception.SourcePositionException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class Board implements Serializable {

    public static final int CHESS_BOARD_SIZE = 8;
    public static final int PAWN_ALLY_COUNT_CONDITION = 2;
    private Map<Position, Piece> board;

    public Board() {
        board = InitialBoard.emptyBoard();
    }

    public void initChessPieces() {
        board = InitialBoard.emptyBoard();
        InitialBoard.initChessPieces(board);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }

    public Piece piece(Position position) {
        return board.getOrDefault(position, new EmptyPiece());
    }

    public void move(Position source, Position target) {
        validateMove(source, target);
        Piece piece = piece(source);
        piece.validateMove(Collections.unmodifiableMap(board), source, target);
        put(source, new EmptyPiece());
        put(target, piece);
    }

    public void validateMove(Position source, Position target) {
        if (piece(source).isEmpty()) {
            throw new SourcePositionException();
        }
        if (piece(source).isSameColor(piece(target))) {
            throw new AllyPiecePositionException();
        }
    }

    public void put(Position position, Piece piece) {
        board.put(position, piece);
    }

    public Score piecesScore(Color color) {
        BoardResult boardResult = new BoardResult(board);
        return boardResult.piecesScore(color);
    }

    public boolean isKingAlive(Color color) {
        BoardResult boardResult = new BoardResult(board);
        return boardResult.isKingAlive(color);
    }
}
