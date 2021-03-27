package domain.board;

import domain.chessgame.Score;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.position.ColumnDegree;
import domain.position.Position;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {

    public static final int CHESS_BOARD_SIZE = 8;
    private static final int PAWN_ALLY_COUNT_CONDITION = 2;
    private final Map<Position, Piece> board;

    public Board() {
        board = InitialBoard.emptyBoard();
    }

    public void initChessPieces() {
        InitialBoard.initChessPieces(board);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Piece piece(Position position) {
        return board.getOrDefault(position, new EmptyPiece());
    }

    public void move(Position source, Position target) {
        validateMove(source, target);
        Piece piece = piece(source);
        piece.validateMove(Collections.unmodifiableMap(board), source, target);
        put(source, new EmptyPiece());
        put(target, piece);
    }

    public boolean canMove(Position source, Position target) {
        Piece sourcePiece = piece(source);
        Piece targetPiece = piece(target);
        return sourcePiece.isNotEmpty() && !sourcePiece.isSameColor(targetPiece);
    }

    public void validateMove(Position source, Position target) {
        if (piece(source).isEmpty()) {
            throw new IllegalArgumentException("[Error] source 위치에 기물이 없습니다.");
        }
        if (piece(source).isSameColor(piece(target))) {
            throw new IllegalArgumentException("[Error] 같은 팀 기물이 존재하는 위치로 이동할 수 없습니다.");
        }
    }

    public void put(Position position, Piece piece) {
        board.put(position, piece);
    }

    public Map<Position, Piece> pieces(boolean isBlack) {
        return board.entrySet()
            .stream()
            .filter(entry -> entry.getValue().isNotEmpty() && entry.getValue().isBlack() == isBlack)
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    public Score piecesScore(boolean isBlack) {
        Score score = new Score();
        Map<Position, Piece> pieces = pieces(isBlack);

        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            score = score.sum(entry.getValue().getScore());
        }
        score = minusPawnScore(score, isBlack);
        return score;
    }

    private Score minusPawnScore(Score score, boolean isBlack) {
        int minusCount = 0;
        for (int row = 0; row < CHESS_BOARD_SIZE; row++) {
            minusCount += rowAllyPawnCount(row, isBlack);
        }
        return score.minusPawnScore(minusCount);
    }

    private int rowAllyPawnCount(int column, boolean isBlack) {
        int count = (int) pieces(isBlack).entrySet()
            .stream()
            .filter(entry -> entry.getValue().isPawn()
                && entry.getKey().isColumnEquals(new ColumnDegree(column)))
            .count();

        if (count >= PAWN_ALLY_COUNT_CONDITION) {
            return count;
        }
        return 0;
    }

}
