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
                new Rook(Color.BLACK),
                new Knight(Color.BLACK),
                new Bishop(Color.BLACK),
                new Queen(Color.BLACK),
                new King(Color.BLACK),
                new Bishop(Color.BLACK),
                new Knight(Color.BLACK),
                new Rook(Color.BLACK)
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK)
            ))
        );
    }

    private void initWhite() {
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE)
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Rook(Color.WHITE),
                new Knight(Color.WHITE),
                new Bishop(Color.WHITE),
                new Queen(Color.WHITE),
                new King(Color.WHITE),
                new Bishop(Color.WHITE),
                new Knight(Color.WHITE),
                new Rook(Color.WHITE)
            ))
        );

    }

    private void initBlank() {
        for (int i = 0; i < 4; i++) {
            chessBoard.add(
                new ArrayList<>(Arrays.asList(
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR),
                    new Blank(Color.NO_COLOR)
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

        Piece piece = findPiece(sourcePosition);
        // 색 같으면 오류 발생하는 부분

    }

    private Piece findPiece(Position position) {
        return chessBoard.get(position.getRow()).get(position.getCol());
    }
}
