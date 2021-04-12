package chess.domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.chess.Chess;
import chess.domain.chess.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDTO;
import chess.domain.position.Position;

public class BoardDTO {

    private static final char START_FILE_CHARACTER = 'a';

    private final double blackScore;
    private final double whiteScore;
    private final List<PieceDTO> pieceDTOS;

    public BoardDTO(double blackScore, double whiteScore,
                    List<PieceDTO> pieceDTOS) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.pieceDTOS = pieceDTOS;
    }

    public static BoardDTO from(Chess chess) {
        final List<PieceDTO> pieceDTOS = new ArrayList<>();
        final Map<Position, Piece> boardMap = chess.getBoard()
                                                   .getBoard();
        for (Map.Entry<Position, Piece> entry : boardMap.entrySet()) {
            String position = getPosition(entry);
            String color = entry.getValue()
                                .getColor()
                                .name();
            String name = entry.getValue()
                               .getName();
            pieceDTOS.add(new PieceDTO(position, color, name));
        }

        return from(pieceDTOS);
    }

    public static BoardDTO from(final List<PieceDTO> pieceDTOS) {
        Board board = Board.from(pieceDTOS);
        return new BoardDTO(board.score(Color.WHITE), board.score(Color.BLACK), pieceDTOS);
    }

    private static String getPosition(Map.Entry<Position, Piece> entry) {
        char file = (char) (entry.getKey()
                                 .getX() + START_FILE_CHARACTER);
        int rank = entry.getKey()
                        .getY() + 1;
        return Character.toString(file) + rank;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public List<PieceDTO> getPieceDTOS() {
        return pieceDTOS;
    }
}
