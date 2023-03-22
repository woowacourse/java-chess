package chess.domain.game;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.practiceMove.Direction;

import java.time.temporal.ChronoUnit;
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


    public void move(Position start, Position end) {
        Piece startPiece = findStartPiece(start);
        Color colorOfDestination = chessBoard.get(end).getColor();

        if (startPiece.isMovable(start, end, colorOfDestination)) {
            checkMovableRoute(start, end, startPiece);
            movePieceToDestination(start, end, startPiece);
        }
    }

    private Piece findStartPiece(Position start) {
        return chessBoard.get(start);
    }


    private void checkMovableRoute(Position start, Position end, Piece startPiece) {
        Position currentPosition = start;
        Direction direction = Direction.findDirectionByGap(start, end, startPiece);

        do {
            currentPosition = currentPosition.moveDirection(direction);
            checkOtherPieceInTravelRoute(currentPosition, end, findStartPiece(start));
        } while (!currentPosition.equals(end));

    }

    private void movePieceToDestination(Position start, Position end, Piece piece) {
        chessBoard.replace(start, new EmptyPiece());
        chessBoard.replace(end, piece);
    }

    private void checkOtherPieceInTravelRoute(Position currentPosition, Position end, Piece startPiece) {
        if (!currentPosition.equals(end) && chessBoard.get(currentPosition).getColor()!=Color.NONE) {
            throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE);
        }
    }
}
