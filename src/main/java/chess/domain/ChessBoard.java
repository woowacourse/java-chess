package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard implements Iterable<ChessBoardPosition>{
    private static final char COLUMN_FIRST_INDEX = 'a';
    private static final char COLUMN_LAST_INDEX = 'h';
    private static final int NUMBER_OF_KING_PIECES = 2;
    private static final int NUMBER_OF_FULL_POINT_PAWNS = 1;
    private static final double SCORE_OF_SAME_ROW_PAWN = 0.5;
    private static final String NO_CHESS_PIECE_EXCEPTION = "[ERROR] 움직이려는 위치에 체스피스가 없습니다.";

    private final Map<ChessBoardPosition, ChessPiece> board;

    public ChessBoard(Map<ChessBoardPosition, ChessPiece> board) {
        this.board = board;
    }

    public static ChessBoard create() {
        return new ChessBoard(ChessBoardInitializer.generate());
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece removedChessPiece = board.remove(sourcePosition);
        board.put(targetPosition, removedChessPiece);
    }

    public void removeChessPieceAt(ChessBoardPosition targetPosition) {
        board.remove(targetPosition);
    }

    public double calculateScore(Team team) {
        double sum = board.values().stream()
                .filter(it -> it.isSameTeam(team))
                .mapToDouble(ChessPiece::getScore)
                .sum();
        return sum - countSameRowPawn(team) * SCORE_OF_SAME_ROW_PAWN;
    }

    private int countSameRowPawn(Team team) {
        List<Character> pawnColumns = collectPawnColumns(team);
        return IntStream.rangeClosed(COLUMN_FIRST_INDEX, COLUMN_LAST_INDEX)
                .boxed()
                .map(it -> Collections.frequency(pawnColumns, (char) it.intValue()))
                .collect(Collectors.toList())
                .stream()
                .filter(it -> it > NUMBER_OF_FULL_POINT_PAWNS)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Character> collectPawnColumns(Team team) {
        return board.entrySet().stream()
                .filter(it -> it.getValue() instanceof Pawn)
                .filter(it -> it.getValue().isSameTeam(team))
                .map(it -> it.getKey().getColumn())
                .collect(Collectors.toList());
    }

    public boolean isKingDead() {
        return board.values().stream()
                .filter(King.class::isInstance)
                .count() < NUMBER_OF_KING_PIECES;
    }

    public Team judgeWinner() {
        double blackTeamScore = calculateScore(Team.BLACK);
        double whiteTeamScore = calculateScore(Team.WHITE);
        if (blackTeamScore == whiteTeamScore) {
            return Team.NONE;
        }
        if (blackTeamScore > whiteTeamScore) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public boolean existChessPieceOf(ChessBoardPosition targetPosition, Team team) {
        return Optional.ofNullable(board.get(targetPosition))
                .stream().anyMatch(it -> it.isSameTeam(team));
    }

    public boolean existChessPieceAt(ChessBoardPosition position) {
        return board.containsKey(position);
    }

    public ChessPiece getChessPiece(ChessBoardPosition sourcePosition, Team team) {
        return Optional.ofNullable(board.get(ChessBoardPosition.of(sourcePosition.getColumn(), sourcePosition.getRow())))
                .filter(it -> it.isSameTeam(team))
                .orElseThrow(() -> new IllegalArgumentException(NO_CHESS_PIECE_EXCEPTION));
    }

    public Map<ChessBoardPosition, ChessPiece> getBoard() {
        return Map.copyOf(board);
    }

    @Override
    public Iterator<ChessBoardPosition> iterator() {
        return board.keySet().iterator();
    }
}
