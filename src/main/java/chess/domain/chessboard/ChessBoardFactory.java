package chess.domain.chessboard;

import chess.domain.Position;
import chess.domain.XAxis;
import chess.domain.YAxis;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardFactory {
    static List<Position> create() {
        List<Position> chessBoard = new ArrayList<>();
        for (YAxis YAxis : YAxis.values()) {
            addFilesEachRank(chessBoard, YAxis);
        }
        return chessBoard;
    }

    private static void addFilesEachRank(List<Position> chessBoard, YAxis YAxis) {
        for (XAxis XAxis : XAxis.values()) {
            chessBoard.add(Position.of(XAxis, YAxis));
        }
    }
}
