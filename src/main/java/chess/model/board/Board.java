package chess.model.board;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import java.util.List;

public final class Board {

    private final List<Piece> board;

    private Board(List<Piece> board) {
        this.board = board;
    }

    public Board(BoardInitializer boardInitializer) {
        this(boardInitializer.initPieces());
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
                .filter(Piece::isKing)
                .count() == 2;
    }

    public double calculatePoint(Color color) {
        return board.stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(this::calculateEachPoint)
                .sum();
    }

    private double calculateEachPoint(Piece piece) {
        if (piece.isPawn() && isPawnInSameFile(piece)) {
            return piece.getPoint().getValue() / 2;
        }
        return piece.getPoint().getValue();
    }

    private boolean isPawnInSameFile(Piece other) {
        return board.stream()
                .filter(piece -> piece.isPawn() && piece.isAlly(other))
                .anyMatch(piece -> piece.isSameFile(other) && !piece.equals(other));
    }
}
