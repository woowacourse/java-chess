package chess.domain.testutil;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPairsBuilder {
    public static Map<ChessPoint, ChessPiece> build(List<ChessPair> chessPairs) {
        Map<ChessPoint, ChessPiece> pairs = new HashMap<>();
        for (ChessPair chessPair : chessPairs) {
            pairs.put(chessPair.chessPoint, chessPair.chessPiece);
        }
        return pairs;
    }
}
