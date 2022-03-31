package chess.domain.board;

import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFactory {
    private static final List<Piece> BLACK_PIECE_ORDER =
            List.of(new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK), new Queen(Color.BLACK),
                    new King(Color.BLACK), new Bishop(Color.BLACK), new Knight(Color.BLACK), new Rook(Color.BLACK),
                    new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK),
                    new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK)
            );
    private static final List<Piece> WHITE_PIECE_ORDER =
            List.of(new Pawn(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE),
                    new Pawn(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE),
                    new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE),
                    new King(Color.WHITE), new Bishop(Color.WHITE), new Knight(Color.WHITE), new Rook(Color.WHITE)
            );

    private static final List<Rank> WHITE_RANKS = List.of(Rank.TWO, Rank.ONE);
    private static final List<Rank> BLACK_RANKS = List.of(Rank.EIGHT, Rank.SEVEN);

    private static final Map<Position, Piece> startBoard = new LinkedHashMap<>();

    public BoardFactory() {
    }

    static {
        List<Position> whitePositions = getPositions(WHITE_RANKS);
        List<Position> blackPositions = getPositions(BLACK_RANKS);

        for (int i = 0; i < whitePositions.size(); i++) {
            startBoard.put(whitePositions.get(i), WHITE_PIECE_ORDER.get(i));
            startBoard.put(blackPositions.get(i), BLACK_PIECE_ORDER.get(i));
        }
    }

    public static Board createBoard() {
        return new Board(startBoard);
    }

    private static List<Position> getPositions(List<Rank> ranks) {
        return ranks.stream()
                .flatMap(BoardFactory::getPositionStream)
                .collect(Collectors.toList());
    }

    private static Stream<Position> getPositionStream(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(rank, file));
    }
}
