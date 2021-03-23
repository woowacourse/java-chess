package domain.board;

import domain.chessgame.Score;
import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = emptyBoard();
    }

    private Map<Position, Piece> emptyBoard() {
        Map<Position, Piece> emptyBoard = new HashMap<>();
        for (int row = 0; row < 8; row++) {
            putInitialPiece(emptyBoard, row);
        }
        return emptyBoard;
    }

    private void putInitialPiece(Map<Position, Piece> emptyBoard, int row) {
        for (int column = 0; column < 8; column++) {
            emptyBoard.put(new Position(row, column), new EmptyPiece());
        }
    }

    public void initChessPieces() {
        board.putAll(King.createInitialKing());
        board.putAll(Queen.createInitialQueen());
        board.putAll(Bishop.createInitialBishop());
        board.putAll(Knight.createInitialKnight());
        board.putAll(Rook.createInitialRook());
        board.putAll(Pawn.createInitialPawn());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Piece piece(Position position) {
        return board.get(position);
    }

    public boolean move(Position source, Position target) {
        Piece piece = board.getOrDefault(source, new EmptyPiece());
        if (piece.isNotEmpty() && piece.canMove(this, source, target)) {
            put(source, new EmptyPiece());
            put(target, piece);
            return true;
        }
        return false;
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
        for (int row = 0; row < 8; row++) {
            minusCount += rowAllyPawnCount(row, isBlack);
        }
        return score.minusPawnScore(minusCount);
    }

    private int rowAllyPawnCount(int column, boolean isBlack) {
        int count = (int) pieces(isBlack).entrySet()
            .stream()
            .filter(entry -> entry.getValue().isPawn()
                && column == entry.getKey().getColumnDegree())
            .count();

        if (count >= 2) {
            return count;
        }
        return 0;
    }

}