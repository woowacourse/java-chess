package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Piece;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private static final String OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE = "이동 경로에 기물이 있으므로 이동할 수 없습니다";

    private final Map<Position, Piece> chessBoard;
    private Color turn = Color.WHITE;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
    }

    public void initialize(Map<Position, Piece> chessBoard) {
        this.chessBoard.putAll(chessBoard);
    }

    public void move(Position start, Position end) {
        checkIfMoveToSamePosition(start, end);
        Piece pieceToMove = chessBoard.get(start);
        checkTurn(pieceToMove.getColor());
        checkIfPiecesExistInRoute(start, end);
        Color colorOfDestination = chessBoard.get(end).getColor();
        if (pieceToMove.isMovable(start, end, colorOfDestination)) {
            movePieceToDestination(start, end, pieceToMove);
        }
    }

    private static void checkIfMoveToSamePosition(Position start, Position end) {
        if(start.equals(end)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다");
        }
    }


    private void checkTurn(Color colorOfStartPiece) {
        if(colorOfStartPiece.isOpponent(turn)) {
            throw new IllegalArgumentException("상대편의 기물을 움직일 수 없습니다");
        }
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
        chessBoard.replace(start, EmptyPiece.of());
        chessBoard.replace(end, piece);
        turn = piece.getColor().getOpponent();
    }

    public boolean isInitialized() {
        return chessBoard.size() != 0;
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }
}
