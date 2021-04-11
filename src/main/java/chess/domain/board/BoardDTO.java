package chess.domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.chess.Chess;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDTO;
import chess.domain.position.Position;

public class BoardDTO {

    private static final char START_FILE_CHARACTER = 'a';

    private final List<PieceDTO> pieceDTOS;

    public BoardDTO(List<PieceDTO> pieceDTOS) {
        this.pieceDTOS = pieceDTOS;
    }

    public static BoardDTO from(Chess chess) {
        final List<PieceDTO> pieceDTOS = new ArrayList<>();
        final Map<Position, Piece> board = chess.getBoard()
                                                .getBoard();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            String position = getPosition(entry);
            String color = entry.getValue()
                                .getColor()
                                .name();
            String name = entry.getValue()
                               .getName();
            pieceDTOS.add(new PieceDTO(position, color, name));
        }
        return new BoardDTO(pieceDTOS);
    }

    private static String getPosition(Map.Entry<Position, Piece> entry) {
        char file = (char) (entry.getKey()
                                 .getX() + START_FILE_CHARACTER);
        int rank = entry.getKey()
                        .getY() + 1;
        return Character.toString(file) + rank;
    }

    public List<PieceDTO> getPieceDTOS() {
        return pieceDTOS;
    }
}
