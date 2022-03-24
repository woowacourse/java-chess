package chess;


import chess.piece.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Piece> board;

    private Board(List<Piece> board) {
        this.board = new ArrayList<>(board);
    }

    public static Board create() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(makePieces('8', Team.BLACK));
        pieces.addAll(makePawns('7', Team.BLACK));
        pieces.addAll(makePawns('2',Team.WHITE));
        pieces.addAll(makePieces('1',Team.WHITE));

        return new Board(pieces);
    }

    private static List<Piece> makePawns(char rank, Team team) {
        char[] files = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        List<Piece> pawns = new ArrayList<>();
        for (char file : files) {
            pawns.add(new Pawn(Position.of(file, rank), team));
        }
        return pawns;
    }

    private static List<Piece> makePieces(char rank, Team team) {
        return List.of(new Rook(Position.of('a', rank), team), new Knight(Position.of('b', rank), team),
                new Bishop(Position.of('c', rank), team), new Queen(Position.of('d', rank), team),
                new King(Position.of('e', rank), team), new Bishop(Position.of('f', rank), team),
                new Knight(Position.of('g', rank), team), new Rook(Position.of('h', rank), team));
    }

//    public void move(Position source, Position target) {
//        Piece piece = board.get(source);
//        if (piece.isMovable(target) && board.hasNotBlock(source, target)) {
//
//        }
//    }

//    public Piece getPiece(Position position) {
//        return board.stream()
//                .filter(piece -> piece.findPosition(position))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말"))
//    }

    public List<Piece> getBoard() {
        return board;
    }
}

