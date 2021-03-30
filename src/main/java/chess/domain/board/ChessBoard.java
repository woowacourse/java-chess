package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {

    private static final int BOARD_SIZE = 8;
    private static final int NUMBER_OF_KINGS = 2;
    private static final String NOT_MOVABLE_POSITION = "[ERROR] 이동할 수 없는 위치입니다.";
    private static final String SAME_POSITION = "[ERROR] 같은 위치로 이동할 수 없습니다.";

    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        initBlank();
    }

    public void initBoard() {
        initBlack();
        initWhite();
    }

    private void initBlank() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                chessBoard.put(Position.of(i, j), new Blank(Color.NO_COLOR));
            }
        }
    }

    private void initBlack() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            chessBoard.put(Position.of(1, i), new Pawn(Color.BLACK));
        }
        chessBoard.put(Position.of(0, 0), new Rook(Color.BLACK));
        chessBoard.put(Position.of(0, 1), new Knight(Color.BLACK));
        chessBoard.put(Position.of(0, 2), new Bishop(Color.BLACK));
        chessBoard.put(Position.of(0, 3), new Queen(Color.BLACK));
        chessBoard.put(Position.of(0, 4), new King(Color.BLACK));
        chessBoard.put(Position.of(0, 5), new Bishop(Color.BLACK));
        chessBoard.put(Position.of(0, 6), new Knight(Color.BLACK));
        chessBoard.put(Position.of(0, 7), new Rook(Color.BLACK));
    }

    private void initWhite() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            chessBoard.put(Position.of(6, i), new Pawn(Color.WHITE));
        }
        chessBoard.put(Position.of(7, 0), new Rook(Color.WHITE));
        chessBoard.put(Position.of(7, 1), new Knight(Color.WHITE));
        chessBoard.put(Position.of(7, 2), new Bishop(Color.WHITE));
        chessBoard.put(Position.of(7, 3), new Queen(Color.WHITE));
        chessBoard.put(Position.of(7, 4), new King(Color.WHITE));
        chessBoard.put(Position.of(7, 5), new Bishop(Color.WHITE));
        chessBoard.put(Position.of(7, 6), new Knight(Color.WHITE));
        chessBoard.put(Position.of(7, 7), new Rook(Color.WHITE));
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public void move(String source, String target) {
        Position sourcePosition = getPosition(Position.of(source));
        Position targetPosition = getPosition(Position.of(target));

        validateMove(sourcePosition, targetPosition);

        Piece sourcePiece = chessBoard.get(sourcePosition);

        if (sourcePiece.isMovable(this, sourcePosition, targetPosition)) {
            chessBoard.put(sourcePosition, new Blank(Color.NO_COLOR));
            chessBoard.put(targetPosition, sourcePiece);
            return;
        }
        throw new IllegalArgumentException(NOT_MOVABLE_POSITION);
    }

    private void validateMove(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException(SAME_POSITION);
        }
    }

    public Position getPosition(Position target) {
        return chessBoard.keySet()
            .stream()
            .filter(position -> position.equals(target))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public Piece getPiece(Position position) {
        return chessBoard.get(position);
    }

    public boolean isBlank(Position position) {
        return chessBoard.get(position).isBlank();
    }

    public boolean isOver() {
        long kingCount = chessBoard.values()
            .stream()
            .filter(Piece::isKing)
            .count();
        return kingCount < NUMBER_OF_KINGS;
    }
}
