package domain.board;

import domain.piece.Color;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.position.Position;
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
        return board;
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

    public boolean canMove(Position source, Position target) {
        Piece sourcePiece = piece(source);
        Piece targetPiece = piece(target);
        return sourcePiece.isNotEmpty() && !sourcePiece.isSameColor(targetPiece);
    }

    public void validateMove(Position source, Position target) {
        if (piece(source).isEmpty()) {
            throw new IllegalArgumentException("[Error] source 위치에 기물이 없습니다.");
        }
        if (piece(source).isSameColor(piece(target))) {
            throw new IllegalArgumentException("[Error] 같은 팀 기물이 존재하는 위치로 이동할 수 없습니다.");
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

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
