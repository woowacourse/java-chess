package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record BoardStatus(List<PieceInfo> pieceInfos) {
    public static BoardStatus from(final Map<Position, Piece> board) {
        List<PieceInfo> pieceInfos = new ArrayList<>();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            pieceInfos.add(PieceInfo.of(entry.getKey(), entry.getValue()));
        }
        return new BoardStatus(pieceInfos);
    }
}
