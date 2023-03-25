package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    public static final double DEDUCT_PAWN_SCORE = 0.5;
    public static final int INITIAL_VALUE = 0;
    private final Map<Position, Piece> board;

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public int countKing() {
        return (int) board.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    public boolean isKingLiveByCamp(final Camp camp) {
        return board.values().stream()
                .anyMatch(piece -> piece.isMyCamp(camp) && piece.isKing());
    }

    public void movePieceOn(final Position fromPosition, final Position toPosition) {
        board.put(toPosition, board.get(fromPosition));
        board.put(fromPosition, new Empty());
    }

    public Piece choosePiece(final Position position) {
        return board.get(position);
    }

    public List<Piece> choiceBetweenPiece(final List<Position> betweenPosition) {
        List<Piece> betweenPiece = new ArrayList<>();
        for (Position position : betweenPosition) {
            betweenPiece.add(board.get(position));
        }
        return betweenPiece;
    }

    public double calculateTotalScoreByCamp(final Camp camp) {
        double sum = INITIAL_VALUE;
        for (File file : File.values()) {
            sum += calculateOneLine(camp, file);
        }

        return sum;
    }

    private List<Position> getLineByFile(final File file) {
        List<Position> positions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            positions.add(new Position(file, rank));
        }
        return positions;
    }

    private Map<Camp, List<Piece>> divideByCamp(final List<Position> positions) {
        List<Piece> pieces = new ArrayList<>();
        for (Position position : positions) {
            pieces.add(board.get(position));
        }
        return pieces.stream().collect(Collectors.groupingBy(Piece::getCamp));
    }

    private double calculateOneLine(final Camp camp, final File file) {
        if (!divideByCamp(getLineByFile(file)).containsKey(camp)) {
            return 0;
        }
        return calculateByPawnCount(divideByCamp(getLineByFile(file)), camp);
    }

    private double calculateByPawnCount(final Map<Camp, List<Piece>> groupingByCamp, final Camp camp) {
        int pawnCount = countPawn(groupingByCamp, camp);

        if (pawnCount != 1) {
            return calculateByCamp(groupingByCamp, camp) - DEDUCT_PAWN_SCORE * pawnCount;
        }
        return calculateByCamp(groupingByCamp, camp);
    }

    private double calculateByCamp(final Map<Camp, List<Piece>> groupingByCamp, final Camp camp) {
        return groupingByCamp.get(camp).stream()
                .mapToDouble(piece -> piece.getPieceSymbol().getPieceScore())
                .sum();
    }

    private int countPawn(final Map<Camp, List<Piece>> groupingByCamp, final Camp camp) {
        return (int) groupingByCamp.get(camp).stream()
                .filter(piece -> piece.equals(new Pawn(camp)))
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}