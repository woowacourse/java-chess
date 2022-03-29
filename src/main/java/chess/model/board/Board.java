package chess.model.board;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import java.util.List;

public final class Board {

    private static final int VALID_KING_COUNT = 2;
    private static final int PAWN_POINT_DIVIDE_VALUE = 2;
    private final List<Piece> board;

    private Board(List<Piece> board) {
        this.board = board;
    }

    public Board(BoardInitializer boardInitializer) {
        this(boardInitializer.initPieces());
    }

    public List<Piece> getBoard() {
        return board;
    }

    public Piece findPieceBySquare(Square square) {
        return board.stream()
                .filter(piece -> piece.isAt(square))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치의 값을 찾을 수 없습니다."));
    }

    public void move(Square sourceSquare, Square targetSquare) {
        Piece sourcePiece = findPieceBySquare(sourceSquare);
        Piece targetPiece = findPieceBySquare(targetSquare);
        if (!sourcePiece.movable(targetPiece)) {
            throw new IllegalArgumentException("해당 칸으로 이동할 수 없습니다.");
        }
        Direction direction = sourceSquare.findDirection(targetSquare);
        checkRoute(sourceSquare, targetSquare, direction);
        updateBoard(sourceSquare, targetSquare, sourcePiece, targetPiece);
    }

    private void checkRoute(Square sourceSquare, Square targetSquare, Direction direction) {
        Square tempSquare = sourceSquare;
        while (tempSquare.isDifferent(targetSquare)) {
            tempSquare = tempSquare.move(direction);
            checkHasPieceInSquare(targetSquare, tempSquare);
        }
    }

    private void checkHasPieceInSquare(Square targetSquare, Square tempSquare) {
        if (findPieceBySquare(tempSquare).isPlayerPiece() && tempSquare.isDifferent(targetSquare)) {
            throw new IllegalArgumentException("경로 중 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private void updateBoard(Square sourceSquare, Square targetSquare, Piece sourcePiece, Piece targetPiece) {
        board.set(board.indexOf(sourcePiece), new Empty(sourceSquare));
        board.set(board.indexOf(targetPiece), sourcePiece);
        sourcePiece.changeLocation(targetSquare);
    }

    public boolean aliveTwoKings() {
        return board.stream()
                .filter(Piece::isKing)
                .count() == VALID_KING_COUNT;
    }

    public double calculatePoint(Color color) {
        return board.stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(this::calculateEachPoint)
                .sum();
    }

    private double calculateEachPoint(Piece piece) {
        if (piece.isPawn() && isPawnInSameFile(piece)) {
            return piece.getPoint().getValue() / PAWN_POINT_DIVIDE_VALUE;
        }
        return piece.getPoint().getValue();
    }

    private boolean isPawnInSameFile(Piece other) {
        return board.stream()
                .filter(piece -> piece.isPawn() && piece.isAlly(other))
                .anyMatch(piece -> piece.isSameFile(other) && piece.isDifferent(other));
    }
}
