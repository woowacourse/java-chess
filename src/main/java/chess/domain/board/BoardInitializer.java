package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;
import chess.domain.position.Vertical;

import java.util.*;
import java.util.stream.Collectors;

public final class BoardInitializer {
    private static final List<LocationInitializer> locationInitializers;
    private static final List<String> HORIZONTAL_RANGE = Arrays.stream(Horizontal.values())
            .map(Horizontal::symbol)
            .collect(Collectors.toList());
    private static final List<String> VERTICAL_RANGE = Arrays.stream(Vertical.values())
            .map(Vertical::symbol)
            .collect(Collectors.toList());
    private static final List<Piece> PIECE_CACHE = Arrays.asList(
            new Pawn(Team.WHITE), new Rook(Team.WHITE), new Bishop(Team.WHITE), new Knight(Team.WHITE),
            new Queen(Team.WHITE), new King(Team.WHITE),
            new Pawn(Team.BLACK), new Rook(Team.BLACK), new Bishop(Team.BLACK), new Knight(Team.BLACK),
            new Queen(Team.BLACK), new King(Team.BLACK),
            Blank.getInstance()
    );


    static {
        locationInitializers = Arrays.asList(new PawnInitializer(), new RookInitializer(), new KingInitializer(),
                new QueenInitializer(), new BishopInitializer(), new KnightInitializer());
    }

    public static Map<Position, Piece> initializeBoard() {
        final Map<Position, Piece> chessBoard = emptyBoard();
        locationInitializers.forEach(initializer -> chessBoard.putAll(initializer.initialize()));
        return chessBoard;
    }

    public static Board boardFromString(String pieces) {
        Map<Position, Piece> board = emptyBoard();
        Queue<String> pieceNames = new LinkedList<>(Arrays.asList(pieces.split(",")));
        board.replaceAll((position, piece) -> matchingPiece(pieceNames.poll()));

        return new Board(board);
    }

    private static Piece matchingPiece(String pieceName) {
        return PIECE_CACHE.stream()
                .filter(piece -> piece.name().equals(pieceName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 말이 없습니다."));
    }

    public static Map<Position, Piece> emptyBoard() {
        final Map<Position, Piece> chessBoard = new TreeMap<>();
        for (String horizontal : HORIZONTAL_RANGE) {
            VERTICAL_RANGE.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), Blank.getInstance()));
        }
        return chessBoard;
    }
}
