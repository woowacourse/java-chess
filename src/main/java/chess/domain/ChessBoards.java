package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class ChessBoards {
    private Map<Integer, ChessBoard> chessBoards = new HashMap<>();

    public void putChessBoard(int roomNo, ChessBoard chessBoard) {
        chessBoards.put(roomNo, chessBoard);
    }

    public ChessBoard findChessBoardByRoomNo(int roomNo) {
        return chessBoards.getOrDefault(roomNo, ChessBoard.generate());
    }
}
