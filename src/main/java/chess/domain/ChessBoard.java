package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard implements Iterable<ChessBoardPosition>{
    private static final int WHITE_PIECE_ROW = 1;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLACK_PIECE_ROW = 8;
    private static final int BLACK_PAWN_ROW = 7;
    private static final char COLUMN_FIRST_INDEX = 'a';
    private static final char COLUMN_LAST_INDEX = 'h';
    private static final char ROOK_COLUMN = 'a';
    private static final char KNIGHT_COLUMN = 'b';
    private static final char BISHOP_COLUMN = 'c';
    private static final char QUEEN_COLUMN = 'd';
    private static final char KING_COLUMN = 'e';
    private static final int PAIR_COLUMN_SUM = 'a' + 'h';
    private static final int NUMBER_OF_KING_PIECES = 2;
    private static final int NUMBER_OF_FULL_POINT_PAWNS = 1;
    private static final double SCORE_OF_SAME_ROW_PAWN = 0.5;
    private static final String NO_CHESS_PIECE_EXCEPTION = "[ERROR] 움직이려는 위치에 체스피스가 없습니다.";

    private final Map<ChessBoardPosition, ChessPiece> board;

    public ChessBoard(Map<ChessBoardPosition, ChessPiece> board) {
        this.board = board;
    }

    public static ChessBoard create() {
        Map<ChessBoardPosition, ChessPiece> blackChessPieces = initializeChessPiece(Team.BLACK, BLACK_PAWN_ROW,
                BLACK_PIECE_ROW);
        Map<ChessBoardPosition, ChessPiece> whiteChessPieces = initializeChessPiece(Team.WHITE, WHITE_PAWN_ROW,
                WHITE_PIECE_ROW);
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.putAll(blackChessPieces);
        board.putAll(whiteChessPieces);
        return new ChessBoard(board);
    }

    private static Map<ChessBoardPosition, ChessPiece> initializeChessPiece(Team team, int pawnRow, int pieceRow) {
        Map<ChessBoardPosition, ChessPiece> chessPieces = new HashMap<>();
        addPawns(chessPieces, team, pawnRow);
        addRooks(chessPieces, team, pieceRow);
        addKnights(chessPieces, team, pieceRow);
        addBishops(chessPieces, team, pieceRow);
        addKingAndQueen(chessPieces, team, pieceRow);
        return chessPieces;
    }

    private static void addPawns(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        for (char column = COLUMN_FIRST_INDEX; column <= COLUMN_LAST_INDEX; column++) {
            chessPieces.put(ChessBoardPosition.of(column, row), new Pawn(team));
        }
    }

    private static void addRooks(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(ROOK_COLUMN, row), new Rook(team));
        chessPieces.put(ChessBoardPosition.of(calculatePairColumn(ROOK_COLUMN), row), new Rook(team));
    }

    private static void addKnights(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(KNIGHT_COLUMN, row), new Knight(team));
        chessPieces.put(ChessBoardPosition.of(calculatePairColumn(KNIGHT_COLUMN), row), new Knight(team));
    }

    private static void addBishops(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(BISHOP_COLUMN, row), new Bishop(team));
        chessPieces.put(ChessBoardPosition.of(calculatePairColumn(BISHOP_COLUMN), row), new Bishop(team));
    }

    private static void addKingAndQueen(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(QUEEN_COLUMN, row), new Queen(team));
        chessPieces.put(ChessBoardPosition.of(KING_COLUMN, row), new King(team));
    }

    private static char calculatePairColumn(char column) {
        return (char) (PAIR_COLUMN_SUM - column);
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
