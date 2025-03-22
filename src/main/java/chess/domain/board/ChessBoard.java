package chess.domain.board;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.limited_moving_chess_piece.BlackPawn;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.limited_moving_chess_piece.King;
import chess.domain.piece.limited_moving_chess_piece.None;
import chess.domain.piece.limited_moving_chess_piece.Pawn;
import chess.domain.piece.limited_moving_chess_piece.WhitePawn;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, ChessPiece> board;
    private final ChessPiece blackKing;
    private final ChessPiece whiteKing;

    public ChessBoard(ChessBoardInitializer initializer) {
        blackKing = new King(Color.BLACK);
        whiteKing = new King(Color.WHITE);
        board = initializer.initialize(blackKing, whiteKing);
    }

    public ChessPiece getPieceOfPosition(Position position) {
        return board.get(position);
    }

    public void moveAndCapturePiece(Position origin, Position destination) {
        ChessPiece movePiece = getPieceOfPosition(origin);
        List<Movement> route = movePiece.findRoute(origin, destination);
        boolean isExistHurdleOnRoute = checkHurdleExistOnRouteWithoutDestination(origin, route);
        ChessPiece targetPiece = getPieceOfPosition(destination);
        movePiece.validateCanMove(route, isExistHurdleOnRoute, targetPiece);

        if (movePiece.getClass().equals(BlackPawn.class) || movePiece.getClass().equals(WhitePawn.class)) {
            ((Pawn) movePiece).isMoved();
        }

        targetPiece.capture();
        board.put(origin, new None());
        board.put(destination, movePiece);
    }

    private boolean checkHurdleExistOnRouteWithoutDestination(Position origin, List<Movement> route) {
        List<Movement> routeWithoutDestination = route.subList(0, route.size() - 1);
        Position origin2 = origin;
        for (Movement movement : routeWithoutDestination) {
            if (origin2.canMove(movement)) {
                origin2 = origin2.move(movement);
                if (!getPieceOfPosition(origin2).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkOppositeKingCaptured(Color color) {
        if (color == Color.BLACK) {
            return whiteKing.isCaptured();
        }
        if (color == Color.WHITE) {
            return blackKing.isCaptured();
        }
        return false;
    }
}
