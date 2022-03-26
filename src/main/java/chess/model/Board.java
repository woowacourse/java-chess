package chess.model;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Point;
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
        initPawns(Rank.SEVEN, Color.BLACK);
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
                new Rook(color, new Square(File.H, rank))
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
        initPawns(Rank.TWO, Color.WHITE);
        initBaseLine(Rank.ONE, Color.WHITE);
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

    public void move(Square sourceSquare, Square targetSquare) {
        Piece sourcePiece = findPieceBySquare(sourceSquare);
        Piece targetPiece = findPieceBySquare(targetSquare);
        if (!sourcePiece.movable(targetPiece)) {
            throw new IllegalArgumentException("해당 칸으로 이동할 수 없습니다.");
        }
        Direction direction = sourceSquare.findDirection(targetSquare);
        checkSquare(sourceSquare, targetSquare, direction);
        updateBoard(sourceSquare, targetSquare, sourcePiece, targetPiece);
    }

    //TODO : 방향은 쉽게 구할 수 있으니까, 해당 방향으로 몇번 가야하는지를 알면 더 쉽게 만들 수 있을 듯
    //TODO : 방향을 갈 수 있는지 + 해당 기물이 해당 방향으로 그 칸만큼 갈 수 있는지 -> 이걸 Piece에서 해결하면 좋을듯
    //TODO : Piece를 거리 무제한(퀸,룩,비숍) vs 거리 제한(폰, 킹, 나이트) 로 나누면 어떨까?
    private void checkSquare(Square sourceSquare, Square targetSquare, Direction direction) {
        Square tempSquare = sourceSquare;
        while (!tempSquare.equals(targetSquare)) {
            tempSquare = tempSquare.tryToMove(direction);
            checkHasPieceInSquare(targetSquare, tempSquare);
        }
    }

    private void checkHasPieceInSquare(Square targetSquare, Square tempSquare) {
        if (!findPieceBySquare(tempSquare).isEmpty() && !tempSquare.equals(targetSquare)) {
            throw new IllegalArgumentException("경로 중 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private void updateBoard(Square sourceSquare, Square targetSquare, Piece sourcePiece, Piece targetPiece) {
        board.set(board.indexOf(sourcePiece), new Empty(sourceSquare));
        board.set(board.indexOf(targetPiece), sourcePiece);
        sourcePiece.changeLocation(targetSquare);
    }

    public boolean aliveTwoKings() {
        return board.stream()
                .filter(piece -> piece.getPoint().equals(Point.KING))
                .count() == 2;
    }
}
