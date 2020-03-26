package chess.board;

import chess.board.piece.Direction;

import java.util.Collections;
import java.util.Map;

public class ChessBoard {
    private final Map<Coordinate, Tile> chessBoard;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.chessBoard = boardGenerator.generate();
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public boolean move(String sourceKey, String targetKey) {
        Tile sourceTile = chessBoard.get(Coordinate.of(sourceKey));
        Tile targetTile = chessBoard.get(Coordinate.of(targetKey));

        if (sourceTile.isAlliance(targetTile)) {
            return false;
        }

        if (sourceTile.canNotReach(targetTile)) {
            return false;
        }

        Directions directions = sourceTile.findPath(targetTile);

        Coordinate next = Coordinate.of(sourceKey);
        boolean notExist = true;
        while (directions.isNotEmpty() && notExist) {
            Direction direction = directions.poll();
            next = next.move(direction);
            notExist = chessBoard.get(next).isBlank();
        }
        if (!notExist) {
            return false;
        }

        targetTile.replacePiece(sourceTile);
        return true;
    }

}
