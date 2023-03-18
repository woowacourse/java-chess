package chess.domain.game;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private static final String OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE = "이동 경로에 기물이 있으므로 이동할 수 없습니다";

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }


    public void move(Position start, Position end) {
        checkIfPiecesExistInRoute(start, end);
        Piece pieceToMove = findPieceAtStart(start);
        Color colorOfDestination = chessBoard.get(end).getColor();
        if (pieceToMove.isMovable(start, end, colorOfDestination)) {
            movePieceToDestination(start, end, pieceToMove);
        }
    }

    private Piece findPieceAtStart(Position start) {
        return chessBoard.get(start);
    }


    private void checkIfPiecesExistInRoute(Position start, Position end) {
        Direction direction = Direction.findDirectionByGap(start, end);
        Position currentPosition = start;
        do {
            currentPosition = currentPosition.moveDirection(direction);
            checkOtherPieceInMovedPosition(currentPosition, end);
        } while (!currentPosition.equals(end));

    }

    private void checkOtherPieceInMovedPosition(Position currentPosition, Position end) {
        if (!currentPosition.equals(end) && chessBoard.get(currentPosition).getColor() != Color.NONE) {
            throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void movePieceToDestination(Position start, Position end, Piece piece) {
        chessBoard.replace(start, new EmptyPiece());
        chessBoard.replace(end, piece);
    }

}
