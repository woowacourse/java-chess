package chess.domain.board;

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

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
    }

    public void initialize(Map<Position, Piece> chessBoard) {
        this.chessBoard.putAll(chessBoard);
    }

    public Color findColorOfPieceInPosition(final Position position) {
        Piece pieceInPosition = findPieceInBoardByPosition(position);
        return pieceInPosition.getColor();
    }

    public void move(Position start, Position end) {
        checkIfMoveToSamePosition(start, end);
        Piece pieceToMove = findPieceInBoardByPosition(start);
        checkIfPiecesExistInRoute(start, end);
        Color colorOfDestination = findPieceInBoardByPosition(end).getColor();
        if (pieceToMove.isMovable(start, end, colorOfDestination)) {
            movePieceToDestination(start, end, pieceToMove);
        }
    }

    private static void checkIfMoveToSamePosition(Position start, Position end) {
        if(start.equals(end)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다");
        }
    }

    private Piece findPieceInBoardByPosition(final Position position) {
        if(!chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("해당하는 위치가 체스 보드에 존재하지 않습니다");
        }
        return chessBoard.get(position);
    }

    /**
     * 시작 포지션 도착 포지션으로 방향을 결정하고, 루트LIst<Postion>를 만들어서 반환하기 이동 거리
     */
    private void checkIfPiecesExistInRoute(Position start, Position end) {
        Direction direction = Direction.findDirectionByGap(start, end);
        Position currentPosition = start;
        do {
            currentPosition = currentPosition.moveDirection(direction);
            checkOtherPieceInMovedPosition(currentPosition, end);
        } while (!currentPosition.equals(end));

    }

    private void checkOtherPieceInMovedPosition(Position currentPosition, Position end) {
        if (!currentPosition.equals(end) && findPieceInBoardByPosition(currentPosition).getColor() != Color.NONE) {
            throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void movePieceToDestination(Position start, Position end, Piece piece) {
        chessBoard.replace(start, EmptyPiece.of());
        chessBoard.replace(end, piece);
    }

    public boolean isInitialized() {
        return chessBoard.size() != 0;
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

}
