package chess.view.display;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDisplayConverter {

    public List<RankDisplay> convert(Map<Position, Piece> pieces) {
        return Arrays.stream(Rank.values())
                .map(rank -> createRankDisplay(pieces, rank))
                .collect(Collectors.toList());
    }

    public RankDisplay createRankDisplay(Map<Position, Piece> pieces, Rank rank) {
        List<PieceDisplay> pieceDisplays = Arrays.stream(File.values())
                .map(file -> createPieceDisplay(pieces, file, rank))
                .collect(Collectors.toList());

        return new RankDisplay(pieceDisplays);
    }

    private PieceDisplay createPieceDisplay(Map<Position, Piece> pieces, File file, Rank rank) {
        Position position = Position.of(file, rank);
        if (pieces.containsKey(position)) {
            return PieceDisplay.getNotationByPiece(pieces.get(position));
        }
        return PieceDisplay.EMPTY;
    }
}
