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

public class ChessBoard {

    public static final int ONE_PAWN = 1;
    public static final double PAWN_SCORE = 0.5;
    public static final int INITIAL_VALUE = 0;
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePieceOn(Position fromPosition, Position toPosition) {
        board.put(toPosition, board.get(fromPosition));
        board.put(fromPosition, new Empty());
    }

    public Piece choicePiece(Position position) {
        return board.get(position);
    }

    public List<Piece> choiceBetweenPiece(List<Position> betweenPosition) {
        List<Piece> betweenPiece = new ArrayList<>();
        for (Position position : betweenPosition) {
            betweenPiece.add(board.get(position));
        }
        return betweenPiece;
    }

    public double calculateTotalScoreByCamp(Camp camp) {
        double sum = INITIAL_VALUE;
        for (File file : File.values()) {
            sum += calculateOneLine(camp, file);
        }

        return sum;
    }

    private double calculateOneLine(final Camp camp, final File file) {
        if (!divideByCamp(getLineByFile(file)).containsKey(camp)) {
            return 0;
        }
        return calculateByPawnCount(divideByCamp(getLineByFile(file)), camp);
    }

    private double calculateByPawnCount(Map<Camp, List<Piece>> groupingByCamp, Camp camp) {
        if (countPawn(groupingByCamp, camp) == ONE_PAWN) {
            return calculateByCamp(groupingByCamp, camp) + PAWN_SCORE;
        }
        return calculateByCamp(groupingByCamp, camp);
    }

    private static Double calculateByCamp(final Map<Camp, List<Piece>> groupingByCamp, final Camp camp) {
        return groupingByCamp.get(camp).stream()
                .map(piece -> piece.getPieceSymbol().getPieceValue())
                .reduce((double) INITIAL_VALUE, Double::sum);
    }

    private Map<Camp, List<Piece>> divideByCamp(List<Position> positions) {
        List<Piece> pieces = new ArrayList<>();
        for (Position position : positions) {
            pieces.add(board.get(position));
        }
        return pieces.stream().collect(Collectors.groupingBy(Piece::getCamp));
    }

    private List<Position> getLineByFile(File file) {
        List<Position> positions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            positions.add(new Position(file, rank));
        }
        return positions;
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