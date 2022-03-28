package chess.dto;

import chess.domain.ChessMen;
import chess.domain.piece.ChessPiece;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChessMenDto implements Iterable<ChessPieceDto> {
    private final List<ChessPieceDto> chessPieces;

    public ChessMenDto(List<ChessPieceDto> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static ChessMenDto of(ChessMen chessMen) {
        List<ChessPieceDto> chessPieces = new ArrayList<>();
        for (ChessPiece chessPiece : chessMen) {
            chessPieces.add(ChessPieceDto.of(chessPiece));
        }
        return new ChessMenDto(chessPieces);
    }

    @Override
    public Iterator<ChessPieceDto> iterator() {
        return chessPieces.iterator();
    }
}
