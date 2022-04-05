package chess.domain.game.state;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.property.Color;
import chess.web.dto.PieceDto;

public class ChessBoard {
    private static final int DUPLICATED_NUMBER = 2;

    private final Map<Position, Piece> board;

    public ChessBoard() {
        board = new HashMap<>();
    }

    private ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard of(List<PieceDto> pieces) {
        Map<Position, Piece> board = new HashMap<>();
        for (PieceDto piece : pieces) {
            board.put(Position.of(piece.getPosition()), PieceFactory.createBy(piece.getName()));
        }
        return new ChessBoard(board);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = getPiece(source);
        Piece targetPiece = getPiece(target);

        validateExist(source);
        validateMove(source, target);

        changeState(sourcePiece, targetPiece);
        changePosition(source, target);
    }

    public void validateExist(Position position) {
        if (!isExist(getPiece(position))) {
            throw new IllegalArgumentException("해당 위치에 체스말이 존재하지 않습니다.");
        }
    }

    private boolean isExist(Piece piece) {
        return piece != null;
    }

    private void validateMove(Position source, Position target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
    }

    private boolean canMove(Position source, Position target) {
        Piece piece = getPiece(source);
        List<Position> movablePositions = piece.findMovablePositions(source, board);

        return movablePositions.contains(target);
    }

    private void changeState(Piece sourcePiece, Piece targetPiece) {
        kill(targetPiece);
        sourcePiece.updateState();
    }

    private void kill(Piece piece) {
        if (isExist(piece)) {
            piece.killed();
        }
    }

    private void changePosition(Position source, Position target) {
        board.put(target, getPiece(source));
        board.remove(source);
    }

    public void putPiece(Position position, Piece piece) {
        board.put(position, piece);
    }

    public Piece getPiece(Position target) {
        return board.get(target);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Map<Color, Double> computeScore() {
        Map<Color, Double> scores = new HashMap<>();
        scores.put(Color.White, score(Color.White));
        scores.put(Color.Black, score(Color.Black));

        return scores;
    }

    private double score(Color color) {
        List<Piece> pieces = board.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toList());
        double total = Piece.computeScore(pieces);

        return total - calculateDuplicatePawn(color);
    }

    private double calculateDuplicatePawn(Color color) {
        int sum = 0;
        for (File file : File.values()) {
            int pawnCount = (int) Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank))
                .map(board::get)
                .filter(piece -> new Pawn(color).isSame(piece))
                .count();

            sum += getDuplicateCount(pawnCount);
        }
        return sum * 0.5;
    }

    private int getDuplicateCount(int count) {
        if (count >= DUPLICATED_NUMBER) {
            return count;
        }

        return 0;
    }

    public boolean isKing(Piece piece) {
        return piece != null && King.canCreate(piece);
    }
}
