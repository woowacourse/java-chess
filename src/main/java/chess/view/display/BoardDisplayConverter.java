package chess.view.display;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDisplayConverter {

    public List<RankDisplay> convert(Map<Position, Piece> pieces) {
        List<RankDisplay> rankDisplays = new ArrayList<>();
        Arrays.stream(Rank.values())
                .map(rank -> convertNotationRankOf(rank, pieces))
                .forEach(rankDisplays::add);
        return rankDisplays;
    }

    private RankDisplay convertNotationRankOf(Rank rank, Map<Position, Piece> pieces) {
        List<PieceDisplay> pieceDisplays = Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .filter(pieces::containsKey)
                .map(pieces::get)
                .map(PieceDisplay::getNotationByPiece)
                .collect(Collectors.toList());

        return new RankDisplay(pieceDisplays);

    }
}
