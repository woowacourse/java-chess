package chess.model.board;

import chess.model.Color;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.strategy.move.Direction;
import chess.model.strategy.move.MoveType;
import java.util.Map;

public final class Board {

    private static final int VALID_KING_COUNT = 2;
    private static final int PAWN_POINT_DIVIDE_VALUE = 2;

    private final Map<Square, Piece> board;

    private Board(Map<Square, Piece> board) {
        this.board = board;
    }

    public Board(BoardInitializer boardInitializer) {
        this(boardInitializer.initPieces());
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public Piece findPieceBySquare(Square square) {
        if (board.containsKey(square)) {
            return board.get(square);
        }
        throw new IllegalArgumentException("해당 위치의 값을 찾을 수 없습니다.");
    }


    public void move(Square sourceSquare, Square targetSquare) {
        Piece sourcePiece = findPieceBySquare(sourceSquare);
        MoveType moveType = getMoveType(sourcePiece, targetSquare);
        if (!sourcePiece.movable(sourceSquare, targetSquare, moveType)) {
            throw new IllegalArgumentException("해당 칸으로 이동할 수 없습니다.");
        }
        checkRoute(sourceSquare, targetSquare);
        updateBoard(sourceSquare, targetSquare, sourcePiece);
    }

    private MoveType getMoveType(Piece sourcePiece, Square targetSquare) {
        Piece targetPiece = findPieceBySquare(targetSquare);
        return MoveType.of(sourcePiece.isEnemy(targetPiece));
    }

    private void checkRoute(Square sourceSquare, Square targetSquare) {
        Square tempSquare = sourceSquare;
        Direction direction = sourceSquare.findDirection(targetSquare);
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

    private void updateBoard(Square sourceSquare, Square targetSquare, Piece sourcePiece) {
        board.replace(targetSquare, sourcePiece);
        board.replace(sourceSquare, new Empty());
    }

    public boolean aliveTwoKings() {
        return board.keySet().stream()
                .map(board::get)
                .filter(Piece::isKing)
                .count() == VALID_KING_COUNT;
    }

    public double calculatePoint(Color color) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .mapToDouble(entry -> calculateEachPoint(entry.getKey()))
                .sum();
    }

    private double calculateEachPoint(Square square) {
        Piece piece = board.get(square);
        if (piece.isPawn() && hasAllyPawnInSameFile(square, piece)) {
            return piece.getPointValue() / PAWN_POINT_DIVIDE_VALUE;
        }
        return piece.getPointValue();
    }

    private boolean hasAllyPawnInSameFile(Square sourceSquare, Piece sourcePiece) {
        return board.keySet().stream()
                .filter(square -> sourceSquare.isSameFile(square) && sourceSquare.isDifferent(square))
                .map(board::get)
                .anyMatch(piece -> piece.isPawn() && piece.isAlly(sourcePiece));
    }
}
