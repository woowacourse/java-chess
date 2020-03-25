package chess.board;

import chess.board.piece.Direction;
import chess.board.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessBoard {
    private final Map<Coordinate, Piece> chessBoard;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.chessBoard = boardGenerator.generate();
    }

    public Map<Coordinate, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public boolean move(String sourceKey, String targetKey) {
        Coordinate source = Coordinate.of(sourceKey);
        Coordinate target = Coordinate.of(targetKey);
        if (isAlliance(source, target)) {
            return false;
        }

        Vector vector = target.calculateVector(source);
        Piece piece = chessBoard.get(source);
        if (!piece.canMove(vector)) {
            return false;
        }

        List<Direction> directions = piece.findPath(vector);
        Coordinate next = source;

        for (int i = 0; i < directions.size() - 1; i++) {
            next = next.move(directions.get(i));
            if (!Objects.isNull(chessBoard.get(next))) {
                return false;
            }
        }

        chessBoard.put(source, null);
        chessBoard.put(target, piece);
        return true;
    }

    private boolean isAlliance(final Coordinate source, final Coordinate target) {
        Piece sourcePiece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);
        if (Objects.isNull(sourcePiece) || Objects.isNull(targetPiece)) {
            return false;
        }
        return sourcePiece.isSameTeam(targetPiece);
    }
}
