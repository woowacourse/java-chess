package chess.domain.board;

import chess.domain.piece.position.Position;
import chess.domain.piece.position.XPosition;
import chess.domain.piece.position.YPosition;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardFactory {
    static List<Position> create() {
        List<Position> chessBoard = new ArrayList<>();
        for (YPosition yPosition : YPosition.values()) {
            addXPositionsEachYPosition(chessBoard, yPosition);
        }
        return chessBoard;
    }

    private static void addXPositionsEachYPosition(List<Position> chessBoard, YPosition yPosition) {
        for (XPosition xPosition : XPosition.values()) {
            chessBoard.add(Position.of(xPosition, yPosition));
        }
    }
}
