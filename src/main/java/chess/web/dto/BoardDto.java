package chess.web.dto;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private final String state;

    public BoardDto(String state) {
        this.state = state;
    }

    public static List<PieceDto> newInstance(Board board) {
        List<PieceDto> pieces = new ArrayList<>();

        for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
            for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
                Position position = new Position(fileIndex, rankIndex);
                Piece piece = board.findPiece(position);
                PieceDto pieceDto = new PieceDto(piece, position, piece.getColor());
                pieces.add(pieceDto);
            }
        }

        return pieces;
    }

    public String getState() {
        return state;
    }
}
