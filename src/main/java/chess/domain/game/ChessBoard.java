package chess.domain.game;

import chess.direction.Direction;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

import static chess.view.ErrorMessage.OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE;

public class ChessBoard {

    Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
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
            checkOtherPieceInTravelRoute(currentPosition, end);
        } while (!currentPosition.equals(end));

    }

    private void movePieceToDestination(Position start, Position end, Piece piece) {
        chessBoard.replace(start, new EmptyPiece());
        chessBoard.replace(end, piece);
        changePawnSetting(piece);
    }

    private void changePawnSetting(Piece piece) {
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            pawn.afterFirstMove();
        }
    }

    private void checkOtherPieceInTravelRoute(Position currentPosition, Position end) {
        if (!currentPosition.equals(end) && chessBoard.get(currentPosition).getColor() != Color.NONE) {
            throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }
}
