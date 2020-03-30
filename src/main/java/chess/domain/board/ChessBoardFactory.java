package chess.domain.board;

import chess.domain.Position;
import chess.domain.XPosition;
import chess.domain.YPosition;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardFactory {
    static List<Position> create() {
        List<Position> chessBoard = new ArrayList<>();
        for (YPosition YPosition : YPosition.values()) {
            addFilesEachRank(chessBoard, YPosition);
        }
        return chessBoard;
    }

    private static void addFilesEachRank(List<Position> chessBoard, YPosition YPosition) {
        for (XPosition XPosition : XPosition.values()) {
            chessBoard.add(Position.of(XPosition, YPosition));
        }
    }
}
