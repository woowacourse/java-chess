package chess.domain.chessboard;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardFactory {
    static List<Position> create() {
        List<Position> chessBoard = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            addFilesEachRank(chessBoard, rank);
        }
        return chessBoard;
    }

    private static void addFilesEachRank(List<Position> chessBoard, Rank rank) {
        for (File file : File.values()) {
            chessBoard.add(Position.of(file, rank));
        }
    }
}
