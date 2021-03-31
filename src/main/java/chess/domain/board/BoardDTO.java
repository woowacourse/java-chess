package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import chess.domain.Chess;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardDTO {

    public static final String DELIMITER = "_";
    private static final char START_FILE_CHARACTER = 'a';

    private final Map<String, String> pieceDTOs;

    public BoardDTO(Map<String, String> pieceDTOs) {
        this.pieceDTOs = pieceDTOs;
    }

    public static BoardDTO from(Chess chess) {
        final Map<String, String> pieceDTOs = new HashMap<>();
        Map<Position, Piece> board = chess.getBoard().getBoard();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            String position = getPosition(entry);
            String piece = entry.getValue().getColor() + DELIMITER + entry.getValue().getName();
            pieceDTOs.put(position, piece);
        }
        return new BoardDTO(pieceDTOs);
    }

    private static String getPosition(Map.Entry<Position, Piece> entry) {
        char file = (char) (entry.getKey().getX() + START_FILE_CHARACTER);
        int rank = entry.getKey().getY() + 1;
        return Character.toString(file) + rank;
    }

    public Set<Map.Entry<String, String>> getPieceDTOs() {
        return pieceDTOs.entrySet();
    }
}
