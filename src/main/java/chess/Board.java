package chess;

import chess.model.piece.Bishop;
import chess.model.piece.Color;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Square, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        final List<File> files = Arrays.asList(File.values());

        initChivalry(Color.WHITE, Rank.ONE, files);
        initChivalry(Color.BLACK, Rank.EIGHT, files);
        initPawns(Color.WHITE, Rank.TWO, files);
        initPawns(Color.BLACK, Rank.SEVEN, files);
        initEmpty();
    }

    public void move(String source, String target) {
        Square sourceSquare = Square.fromString(source);
        Square targetSquare = Square.fromString(target);
        Piece piece = board.get(sourceSquare);
        if (!piece.movable(this, sourceSquare, targetSquare)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        if (!piece.isObstacleOnRoute(this, sourceSquare, targetSquare)) {
            throw new IllegalArgumentException("경로 중 기물이 있습니다.");
        }
        board.put(targetSquare, piece);
        board.put(sourceSquare, new Empty());
    }

    public ScoreResult calculateScore() {
        return ScoreResult.of(board);
    }

    private void initEmpty() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Square square = Square.of(file, rank);
                if (!board.containsKey(square)) {
                    board.put(square, new Empty());
                }
            }
        }
    }

    private void initPawns(Color color, Rank rank, List<File> files) {
        for (int i = 0; i < 8; i++) {
            board.put(Square.of(files.get(i), rank), new Pawn(color));
        }
    }

    private void initChivalry(Color color, Rank rank, List<File> files) {
        List<Piece> chivalryLineup = chivalryLineup(color);
        for (int i = 0; i < chivalryLineup.size(); i++) {
            board.put(Square.of(files.get(i), rank), chivalryLineup.get(i));
        }
    }

    private List<Piece> chivalryLineup(final Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }

    public Piece get(Square square) {
        return board.get(square);
    }
}
