package chess.domain.game;

import chess.domain.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.practiceMove.Direction;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private static final String NO_PIECE_ERROR_GUIDE_MESSAGE = "이동할 수 있는 기물이 없습니다.";
    private static final String DIRECTION_ERROR_GUIDE_MESSAGE = "기물이 해당 방향으로 이동할 수 없습니다";
    private static final String MOVE_ERROR_GUIDE_MESSAGE = "기물이 이동할 수 없습니다";
    private static final String OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE = "이동 경로에 기물이 있으므로 이동할 수 없습니다";
    private static final String SAME_COLOR_PIECE_EXIST_ERROR_GUIDE_MESSAGE = "같은 팀이라 기물이 이동할 수 없습니다";
    Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

    public boolean hasPiece(Position start) {
        if (chessBoard.get(start) instanceof EmptyPiece) {
            return false;
        }
        return true;
    }

    public void move(Position start, Position end) {
        checkStartPieceExist(start);
        Piece piece = findStartPiece(start);
        Direction direction = findDirectionToMove(start, end, piece);
        checkPieceHasDirection(piece, direction);
        checkMovablelRoute(start, end, piece, direction);

        movePieceToDestination(start, end, piece);
    }


    private void checkStartPieceExist(Position start) {
        if (!hasPiece(start)) {
            throw new IllegalArgumentException(NO_PIECE_ERROR_GUIDE_MESSAGE);
        }
    }

    private Piece findStartPiece(Position start) {
        return chessBoard.get(start);
    }

    private Direction findDirectionToMove(Position start, Position end, Piece piece) {
        int gapOfRank = start.findGapOfRank(end);
        int gapOfColumn = start.findGapOfColum(end);
        checkPieceCanMoveAtOnce(piece, gapOfRank, gapOfColumn);

        return Direction.findDirectionByPlusMinusSign(piece, gapOfColumn, gapOfRank);
    }

    private void checkPieceHasDirection(Piece piece, Direction direction) {
        if (!piece.isMovableDirection(direction)) {
            throw new IllegalArgumentException(DIRECTION_ERROR_GUIDE_MESSAGE);
        }
    }

    private void checkPieceCanMoveAtOnce(Piece piece, int gapOfRank, int gapOfColumn) {
        if (!piece.isMovableAtOnce(Math.abs(gapOfColumn), Math.abs(gapOfRank))) {
            throw new IllegalArgumentException(MOVE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void checkMovablelRoute(Position start, Position end, Piece piece, Direction direction) {
        Position currentPosition = start;

        //todo: method extract
        while (!currentPosition.toString().equals(end.toString())) {
            currentPosition = currentPosition.moveDirection(direction);
            checkPawnMove(piece, direction, currentPosition);
            checkOtherPieceInTravelRoute(end, currentPosition);
        }

        checkSameColorPieceExist(end, piece, currentPosition);
    }

    private void movePieceToDestination(Position start, Position end, Piece piece) {
        chessBoard.replace(start, new EmptyPiece());
        chessBoard.replace(end, piece);

        changePawnSetting(piece);
    }

    private void checkOtherPieceInTravelRoute(Position end, Position currentPosition) {
        if (!currentPosition.equals(end) && hasPiece(currentPosition)) {
            throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void checkSameColorPieceExist(Position end, Piece piece, Position currentPosition) {
        if (currentPosition == end && piece.isSameColor(piece)) {
            throw new IllegalArgumentException(SAME_COLOR_PIECE_EXIST_ERROR_GUIDE_MESSAGE);
        }
    }

    private void checkPawnMove(Piece piece, Direction direction, Position currentPosition) {
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            boolean isPositionToMoveEmpty = isEmptyPiece(chessBoard.get(currentPosition));
            pawn.checkMoveForwardByPawn(direction, isPositionToMoveEmpty);
            pawn.checkMoveDiagonalByPawn(direction, isPositionToMoveEmpty);
        }
    }

    private boolean isEmptyPiece(Piece piece) {
        return piece instanceof EmptyPiece;
    }

    private void changePawnSetting(Piece piece) {
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            pawn.setFirstMove(false);
        }
    }
}
