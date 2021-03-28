package chess.domain.team;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.AlphaColumns;
import chess.domain.position.NumberRows;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackSet implements PieceSet {
    public static final String INITIAL_PAWN_LINE = "7";
    public static final int INITIAL_CAPACITY = 16;
    public static final double MINUS_HALF_POINT = -0.5;
    private static final TeamColor BLACK = TeamColor.BLACK;
    private final List<Piece> pieces;

    public BlackSet() {
        this.pieces = new ArrayList<>(INITIAL_CAPACITY);
        initSet();
    }

    public BlackSet(List<Piece> pieces) {
        this.pieces = pieces;
    }

    private void initSet() {
        setUniquePieces();
        setPawns();
    }

    private void setPawns() {
        for (AlphaColumns alphaColumn : AlphaColumns.values()) {
            NumberRows numberRow = NumberRows.getInstance(INITIAL_PAWN_LINE);
            pieces.add(new Pawn(BLACK, Position.valueOf(alphaColumn, numberRow)));

        }
    }

    private void setUniquePieces() {
        pieces.add(new Rook(BLACK, Position.valueOf("a8")));
        pieces.add(new Knight(BLACK, Position.valueOf("b8")));
        pieces.add(new Bishop(BLACK, Position.valueOf("c8")));
        pieces.add(new Queen(BLACK, Position.valueOf("d8")));
        pieces.add(new King(BLACK, Position.valueOf("e8")));
        pieces.add(new Bishop(BLACK, Position.valueOf("f8")));
        pieces.add(new Knight(BLACK, Position.valueOf("g8")));
        pieces.add(new Rook(BLACK, Position.valueOf("h8")));
    }

    @Override
    public Iterator<Piece> values() {
        return pieces.iterator();
    }

    @Override
    public Score calculateScore() {
        Score sum = Score.ZERO;
        Map<Character, Integer> pawnCount = new HashMap<>();

        for (Piece piece : alives()) {
            sum = sum.add(piece.getScore());
            recordPawns(pawnCount, piece);
        }

        return sum.add(subtractWhenOnSameLine(pawnCount));
    }

    private Score subtractWhenOnSameLine(Map<Character, Integer> pawnCount) {
        return pawnCount.values().stream()
            .filter(number -> number > 1)
            .map(number -> new Score(MINUS_HALF_POINT * number))
            .reduce(Score.ZERO, Score::add);
    }

    private List<Piece> alives() {
        return pieces.stream()
            .filter(Piece::isAlive)
            .collect(Collectors.toList());
    }

    private void recordPawns(Map<Character, Integer> pawnCount, Piece piece) {
        if (piece.isPawn()) {
            pawnCount.put(piece.getColumn(),
                pawnCount.getOrDefault(piece.getColumn(), 0) + 1);
        }
    }

}
