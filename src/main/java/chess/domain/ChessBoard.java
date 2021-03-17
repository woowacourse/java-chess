package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChessBoard {

    private final List<List<Piece>> chessBoard = new ArrayList<>(8);

    public ChessBoard() {
        initBlack();
        initBlank();
        initWhite();
    }

    private void initBlack() {
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Rook(Color.BLACK, new Position("a8")),
                new Knight(Color.BLACK, new Position("b8")),
                new Bishop(Color.BLACK, new Position("c8")),
                new Queen(Color.BLACK, new Position("d8")),
                new King(Color.BLACK, new Position("e8")),
                new Bishop(Color.BLACK, new Position("f8")),
                new Knight(Color.BLACK, new Position("g8")),
                new Rook(Color.BLACK, new Position("h8"))
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Pawn(Color.BLACK, new Position("a7")),
                new Pawn(Color.BLACK, new Position("b7")),
                new Pawn(Color.BLACK, new Position("c7")),
                new Pawn(Color.BLACK, new Position("d7")),
                new Pawn(Color.BLACK, new Position("e7")),
                new Pawn(Color.BLACK, new Position("f7")),
                new Pawn(Color.BLACK, new Position("g7")),
                new Pawn(Color.BLACK, new Position("h7"))
            ))
        );
    }

    private void initWhite() {
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Pawn(Color.WHITE, new Position("a2")),
                new Pawn(Color.WHITE, new Position("b2")),
                new Pawn(Color.WHITE, new Position("c2")),
                new Pawn(Color.WHITE, new Position("d2")),
                new Pawn(Color.WHITE, new Position("e2")),
                new Pawn(Color.WHITE, new Position("f2")),
                new Pawn(Color.WHITE, new Position("g2")),
                new Pawn(Color.WHITE, new Position("h2"))
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Rook(Color.WHITE, new Position("a1")),
                new Knight(Color.WHITE, new Position("b1")),
                new Bishop(Color.WHITE, new Position("c1")),
                new Queen(Color.WHITE, new Position("d1")),
                new King(Color.WHITE, new Position("e1")),
                new Bishop(Color.WHITE, new Position("f1")),
                new Knight(Color.WHITE, new Position("g1")),
                new Rook(Color.WHITE, new Position("h1"))
            ))
        );

    }

    private void initBlank() {
        for (int i = 0; i < 4; i++) {
            chessBoard.add(
                new ArrayList<>(Arrays.asList(
                    new Blank(Color.NO_COLOR, new Position(0, i)),
                    new Blank(Color.NO_COLOR, new Position(1, i)),
                    new Blank(Color.NO_COLOR, new Position(2, i)),
                    new Blank(Color.NO_COLOR, new Position(3, i)),
                    new Blank(Color.NO_COLOR, new Position(4, i)),
                    new Blank(Color.NO_COLOR, new Position(5, i)),
                    new Blank(Color.NO_COLOR, new Position(6, i)),
                    new Blank(Color.NO_COLOR, new Position(7, i))
                ))
            );
        }
    }

    public List<List<Piece>> getChessBoard() {
        return chessBoard;
    }

    public void move(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException();
        }
        Piece sourcePiece = findPiece(sourcePosition);
        Piece targetPiece = findPiece(targetPosition);

        List<Position> existingPositions = getPieceExistingPositions();
        List<Position> movablePositions = sourcePiece.getMovablePositions(existingPositions);

        if (!movablePositions.contains(targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (targetPiece.isSameColor(sourcePiece)) {
            throw new IllegalArgumentException("같은 진영의 말이 있는 위치로 이동할 수 없습니다.");
        }
        sourcePiece.move(targetPosition);

        chessBoard.get(sourcePosition.getRow()).remove(sourcePosition.getCol());
        chessBoard.get(sourcePosition.getRow())
            .add(sourcePosition.getCol(), new Blank(Color.NO_COLOR, sourcePosition));
        chessBoard.get(targetPosition.getRow()).remove(targetPosition.getCol());
        chessBoard.get(targetPosition.getRow()).add(targetPosition.getCol(), sourcePiece);
    }

    private Piece findPiece(Position position) {
        return chessBoard.get(position.getRow()).get(position.getCol());
    }

    public List<Position> getPieceExistingPositions() {
        List<Position> existingPositions = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(chessBoard.get(i).get(j) instanceof Blank)) {
                    existingPositions.add(new Position(i, j));
                }
            }
        }
        return Collections.unmodifiableList(existingPositions);
    }
}
