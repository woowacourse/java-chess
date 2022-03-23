package chess.model;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class Board {

    private final List<Piece> board;

    public Board() {
        this.board = new ArrayList<>();
        initBlack();
        initEmpty();
        initWhite();
    }

    private void initBlack() {
        initBaseLine(Rank.EIGHT, Color.BLACK);
        initPawns(Rank.TWO, Color.BLACK);
    }

    private void initBaseLine(Rank rank, Color color) {
        Iterator<Piece> pieces = lineUp(color, rank).iterator();
        while (pieces.hasNext()) {
            board.add(pieces.next());
        }
    }

    private List<Piece> lineUp(Color color, Rank rank) {
        return List.of(
                new Rook(color, new Square(File.A, rank)),
                new Knight(color, new Square(File.B, rank)),
                new Bishop(color, new Square(File.C, rank)),
                new Queen(color, new Square(File.D, rank)), 
                new King(color, new Square(File.E, rank)),
                new Bishop(color, new Square(File.F, rank)), 
                new Knight(color, new Square(File.G, rank)), 
                new Rook(color, new Square(File.F, rank))
        );
    }

    private void initPawns(Rank rank, Color color) {
        for (File file : File.values()) {
            board.add(new Pawn(color, new Square(file, rank)));
        }
    }

    private void initEmpty() {
        for (Rank rank : Rank.emptyBaseLine()) {
            initEmptiesInRank(rank);
        }
    }

    private void initEmptiesInRank(Rank rank) {
        for (File file : File.values()) {
            board.add(new Empty(new Square(file, rank)));
        }
    }

    private void initWhite() {
        initPawns(Rank.SEVEN, Color.WHITE);
        initBaseLine(Rank.EIGHT, Color.WHITE);
    }

    public List<Piece> getBoard() {
        return board;
    }

    public Piece findPieceBySquare(Square square) {
        return board.stream()
                .filter(piece -> piece.isAt(square))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치의 값을 찾을 수 없습니다."));
    }
}
