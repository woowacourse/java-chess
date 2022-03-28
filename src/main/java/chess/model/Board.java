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

public final class Board {

    private static final String CANNOT_FIND_LOCATION_ERROR_MESSAGE = "해당 위치의 값을 찾을 수 없습니다.";
    private static final String CANNOT_MOVE_LOCATION_ERROR_MESSAGE = "해당 칸으로 이동할 수 없습니다.";
    private static final String PIECE_EXIST_ON_ROUTE_ERROR_MESSAGE = "경로 중 기물이 존재하여 이동할 수 없습니다.";
    private static final int KING_ALIVE_COUNT = 2;
    private static final int SEVERAL_PAWN_ON_FILE_DIVIDE_POINT = 2;

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
                .orElseThrow(() -> new IllegalArgumentException(CANNOT_FIND_LOCATION_ERROR_MESSAGE));
    }

    public void move(Square sourceSquare, Square targetSquare) {
        Piece sourcePiece = findPieceBySquare(sourceSquare);
        Piece targetPiece = findPieceBySquare(targetSquare);
        if (!sourcePiece.movable(targetPiece)) {
            throw new IllegalArgumentException(CANNOT_MOVE_LOCATION_ERROR_MESSAGE);
        }
        Direction direction = sourceSquare.findDirection(targetSquare);
        checkSquare(sourceSquare, targetSquare, direction);
        updateBoard(sourceSquare, targetSquare, sourcePiece, targetPiece);
    }

    private void checkSquare(Square sourceSquare, Square targetSquare, Direction direction) {
        Square tempSquare = sourceSquare;
        while (!tempSquare.equals(targetSquare)) {
            tempSquare = tempSquare.tryToMove(direction);
            checkHasPieceInSquare(targetSquare, tempSquare);
        }
    }

    private void checkHasPieceInSquare(Square targetSquare, Square tempSquare) {
        if (!findPieceBySquare(tempSquare).isEmpty() && !tempSquare.equals(targetSquare)) {
            throw new IllegalArgumentException(PIECE_EXIST_ON_ROUTE_ERROR_MESSAGE);
        }
    }

    private void updateBoard(Square sourceSquare, Square targetSquare, Piece sourcePiece, Piece targetPiece) {
        board.set(board.indexOf(sourcePiece), new Empty(sourceSquare));
        board.set(board.indexOf(targetPiece), sourcePiece);
        sourcePiece.changeLocation(targetSquare);
    }

    public boolean aliveTwoKings() {
        return board.stream()
                .filter(Piece::isKing)
                .count() == KING_ALIVE_COUNT;
    }

    public double calculatePoint(Color color) {
        return board.stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(this::calculateEachPoint)
                .sum();
    }

    private double calculateEachPoint(Piece piece) {
        if (piece.isPawn() && isPawnInSameFile(piece)) {
            return piece.getPoint().getValue() / SEVERAL_PAWN_ON_FILE_DIVIDE_POINT;
        }
        return piece.getPoint().getValue();
    }

    private boolean isPawnInSameFile(Piece other) {
        return board.stream()
                .filter(piece -> piece.isPawn() && piece.isAlly(other))
                .anyMatch(piece -> piece.isSameFile(other) && !piece.equals(other));
    }
}
