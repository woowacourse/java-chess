package chess.domain.board.factory;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.PieceColor.BLACK;
import static chess.domain.piece.PieceColor.EMPTY;
import static chess.domain.piece.PieceColor.WHITE;

import chess.domain.board.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Positions;
import chess.domain.board.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.PieceColor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegularBoardFactory extends BoardFactory {

    private final static BoardFactory CACHE = new RegularBoardFactory();

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);

    private static final Function<PieceColor, List<Piece>> PIECES_CREATOR_BY_COLOR =
            (PieceColor color) -> List.of(
                    new Rook(color), new Knight(color), new Bishop(color), new Queen(color),
                    new King(color), new Bishop(color), new Knight(color), new Rook(color)
            );

    private static final Map<Position, Piece> BOARD = new HashMap<>();

    private RegularBoardFactory() {
    }

    static {
        placeAllEmptyPieces();
        placeBlackPieces();
        placeWhitePieces();
    }

    public static BoardFactory getInstance() {
        return CACHE;
    }

    private static void placeAllEmptyPieces() {
/*
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Position findPosition = Positions.findPositionBy(file, rank);
                BOARD.put(findPosition, EMPTY_PIECE);
            }
        }
*/

        Map<Position, Piece> emptyPiecesByPositions = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values()).map(file -> Positions.findPositionBy(file, rank)))
                .collect(Collectors.toMap((position) -> position, (piece) -> EMPTY_PIECE));

        BOARD.putAll(emptyPiecesByPositions);
    }

    private static void placeWhitePieces() {
        placePawns(WHITE, TWO);
        placeRemainPiecesExceptPawn(WHITE, ONE);
    }

    private static void placeBlackPieces() {
        placePawns(BLACK, SEVEN);
        placeRemainPiecesExceptPawn(BLACK, EIGHT);
    }

    private static void placePawns(PieceColor color, Rank rank) {
        for (File file : File.values()) {
            Position findPosition = Positions.findPositionBy(file, rank);
            BOARD.put(findPosition, new Pawn(color));
        }
    }

    private static void placeRemainPiecesExceptPawn(PieceColor color, Rank rank) {

        ListIterator<Piece> piecesIterator = PIECES_CREATOR_BY_COLOR.apply(color).listIterator();

        for (File file : File.values()) {
            Position findPosition = Positions.findPositionBy(file, rank);
            BOARD.put(findPosition, piecesIterator.next());
        }
    }

    @Override
    public Map<Position, Piece> create() {

        placeAllEmptyPieces();
        placeBlackPieces();
        placeWhitePieces();

        return new HashMap<>(BOARD);
    }
}
