package chess.view.display;

import chess.board.Square;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoardDisplayConverter {

    public List<RankDisplay> convert(Map<Position, Square> pieces) {
        return Arrays.stream(Rank.values())
                .map(rank -> convertNotationRankOf(rank, pieces))
                .toList();
    }

    private RankDisplay convertNotationRankOf(Rank rank, Map<Position, Square> pieces) {
        List<PieceDisplay> pieceDisplays = Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(position -> pieces.getOrDefault(position, Square.empty()))
                .map(Square::getPiece)
                .map(PieceDisplay::getNotationByPiece)
                .toList();
        return new RankDisplay(pieceDisplays);
    }
}
