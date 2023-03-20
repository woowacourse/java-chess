package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int LINE_SIZE = 8;

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        HashMap<Position, Piece> board = new HashMap<>();
        initializeWhitePieces(board);
        initializeEmptyPieces(board);
        initializeBlackPieces(board);
        return new Board(board);
    }

    private static void initializeWhitePieces(HashMap<Position, Piece> board) {
        List<Piece> whitePieces = new ArrayList<>(
                List.of(new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE), new Queen(Team.WHITE),
                        new King(Team.WHITE), new Bishop(Team.WHITE), new Knight(Team.WHITE), new Rook(Team.WHITE))
        );
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, 0), whitePieces.get(i));
        }
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, 1), new Pawn(Team.WHITE));
        }
    }

    private static void initializeEmptyPieces(HashMap<Position, Piece> board) {
        for (int i = 0; i < LINE_SIZE; i++) {
            for (int j = 2; j < 6; j++) {
                board.put(new Position(i, j), new Empty());
            }
        }
    }

    private static void initializeBlackPieces(HashMap<Position, Piece> board) {
        List<Piece> blackPieces = new ArrayList<>(
                List.of(new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK), new Queen(Team.BLACK),
                        new King(Team.BLACK), new Bishop(Team.BLACK), new Knight(Team.BLACK), new Rook(Team.BLACK))
        );
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, 6), new Pawn(Team.BLACK));
        }
        for (int i = 7; i >= 0; i--) {
            board.put(new Position(i, 7), blackPieces.get(i));
        }
    }

    public void movePiece(Position source, Position target) {
        MoveValidator.validate(board, source, target);

        Piece piece = board.get(source);
        board.put(target, piece);
        board.put(source, new Empty());
    }

    public List<List<Piece>> getBoard() {
        return sortBoard();
    }

    private List<List<Piece>> sortBoard() {
        List<Position> positions = sortPosition();
        List<List<Piece>> sortedBoard = new ArrayList<>();
        for (int i = 0; i < LINE_SIZE; i++) {
            List<Piece> line = sortLine(positions, i);
            sortedBoard.add(line);
        }
        return sortedBoard;
    }

    private List<Piece> sortLine(List<Position> positions, int i) {
        List<Piece> line = new ArrayList<>();
        for (int j = 0; j < LINE_SIZE; j++) {
            Piece piece = board.get(positions.get(j + LINE_SIZE * i));
            line.add(piece);
        }
        return line;
    }

    private List<Position> sortPosition() {
        List<Position> positions = new ArrayList<>(board.keySet());
        positions.sort((p1, p2) -> {
            if (p1.getRow() == p2.getRow()) {
                return p1.getColumn() - p2.getColumn();
            }
            return p2.getRow() - p1.getRow();
        });
        return positions;
    }

}
