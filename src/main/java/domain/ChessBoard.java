package domain;

import static domain.ChessColumn.A;
import static domain.ChessColumn.B;
import static domain.ChessColumn.C;
import static domain.ChessColumn.D;
import static domain.ChessColumn.E;
import static domain.ChessColumn.F;
import static domain.ChessColumn.G;
import static domain.ChessColumn.H;
import static domain.Rank.EIGHT;
import static domain.Rank.FIVE;
import static domain.Rank.FOUR;
import static domain.Rank.ONE;
import static domain.Rank.SEVEN;
import static domain.Rank.SIX;
import static domain.Rank.THREE;
import static domain.Rank.TWO;
import static domain.piece.TeamColor.BLACK;
import static domain.piece.TeamColor.WHITE;

import domain.piece.Blank;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.TeamColor;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class ChessBoard {

    public static final int INITIAL_KING_SIZE = 2;
    public static final int BOARD_SIZE = 64;
    private static final List<Square> BLACK_SQUARES = List.of(
        Square.of(A, EIGHT), Square.of(B, EIGHT), Square.of(C, EIGHT), Square.of(D, EIGHT),
        Square.of(E, EIGHT), Square.of(F, EIGHT), Square.of(G, EIGHT), Square.of(H, EIGHT),
        Square.of(A, SEVEN), Square.of(B, SEVEN), Square.of(C, SEVEN), Square.of(D, SEVEN),
        Square.of(E, SEVEN), Square.of(F, SEVEN), Square.of(G, SEVEN), Square.of(H, SEVEN)
    );
    private static final List<Square> EMPTY_SQUARES = List.of(
        Square.of(A, SIX), Square.of(B, SIX), Square.of(C, SIX), Square.of(D, SIX),
        Square.of(E, SIX), Square.of(F, SIX), Square.of(G, SIX), Square.of(H, SIX),
        Square.of(A, FIVE), Square.of(B, FIVE), Square.of(C, FIVE), Square.of(D, FIVE),
        Square.of(E, FIVE), Square.of(F, FIVE), Square.of(G, FIVE), Square.of(H, FIVE),
        Square.of(A, FOUR), Square.of(B, FOUR), Square.of(C, FOUR), Square.of(D, FOUR),
        Square.of(E, FOUR), Square.of(F, FOUR), Square.of(G, FOUR), Square.of(H, FOUR),
        Square.of(A, THREE), Square.of(B, THREE), Square.of(C, THREE), Square.of(D, THREE),
        Square.of(E, THREE), Square.of(F, THREE), Square.of(G, THREE), Square.of(H, THREE)
    );
    private static final List<Square> WHITE_SQUARES = List.of(
        Square.of(A, ONE), Square.of(B, ONE), Square.of(C, ONE), Square.of(D, ONE),
        Square.of(E, ONE), Square.of(F, ONE), Square.of(G, ONE), Square.of(H, ONE),
        Square.of(A, TWO), Square.of(B, TWO), Square.of(D, TWO), Square.of(E, TWO),
        Square.of(C, TWO), Square.of(F, TWO), Square.of(G, TWO), Square.of(H, TWO)
    );
    private static final List<Piece> BLACK_PIECES = List.of(
        new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK),
        new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK),
        new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
        new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK)
    );
    private static final List<Piece> WHITE_PIECES = List.of(
        new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE),
        new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE),
        new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
        new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE)
    );
    private final Map<Square, Piece> locationInfo;
    private final List<Piece> kings;

    public ChessBoard() {
        locationInfo = new HashMap<>(BOARD_SIZE);
        for (int i = 0; i < BLACK_PIECES.size(); i++) {
            Piece blackPiece = BLACK_PIECES.get(i);
            Piece whitePiece = WHITE_PIECES.get(i);
            locationInfo.put(BLACK_SQUARES.get(i), blackPiece);
            locationInfo.put(WHITE_SQUARES.get(i), whitePiece);
        }
        for (Square emptySquare : EMPTY_SQUARES) {
            locationInfo.put(emptySquare, Blank.getInstance());
        }
        kings = initKings();
    }

    public ChessBoard(Map<Square, Piece> locationInfo) {
        this.locationInfo = locationInfo;
        kings = initKings();
    }

    private List<Piece> initKings() {
        return locationInfo.values()
            .stream()
            .filter(Piece::isKing)
            .collect(Collectors.toList());
    }

    public Piece find(Square square) {
        return locationInfo.get(square);
    }

    public void update(Square source, Square destination) {
        Piece sourcePiece = locationInfo.get(source);
        Piece destinationPiece = locationInfo.get(destination);
        if (destinationPiece.isKing()) {
            kings.remove(destinationPiece);
        }
        locationInfo.replace(destination, sourcePiece);
        locationInfo.replace(source, Blank.getInstance());
    }

    public boolean hasPiece(Square route) {
        Piece piece = locationInfo.get(route);
        return piece.isNotBlank();
    }

    public boolean isBlank(Square route) {
        Piece piece = locationInfo.get(route);
        return piece.isBlank();
    }

    public int getKingSize() {
        return kings.size();
    }

    public boolean hasNotKing(TeamColor team) {
        return kings.stream()
            .noneMatch(king -> king.isSameColor(team));
    }

    public List<Entry<Square, Piece>> getEntriesByTeam(TeamColor team) {
        return locationInfo.entrySet()
            .stream()
            .filter(entry -> entry.getValue().isSameColor(team))
            .collect(Collectors.toList());
    }

    public Set<Entry<Square, Piece>> getEntries() {
        return locationInfo.entrySet();
    }
}
