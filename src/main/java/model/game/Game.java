package model.game;

import model.Position;
import model.board.Board;
import model.board.BoardBuilder;
import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceFactory;
import model.piece.impl.*;
import service.LogVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static model.piece.PieceColor.BLACK;
import static model.piece.PieceColor.WHITE;

public class Game {
    private final Board board;
    private Turn turn;

    public Game() {
        board = initializeBoard();
        turn = new Turn();
    }

    public Game(final List<LogVO> log) {
        board = initializeBoard();
        turn = new Turn();

        Collections.sort(log);
        log.forEach(l -> {
            if (!restore(l.from(), l.to())) {
                throw new FailedToRestoreGameException();
            }
        });
    }

    private boolean restore(final Position src, final Position dest) {
        board.getPieceAt(src).moveTo(dest);
        turn.endTurn();
        return true;
    }

    private Board initializeBoard() {
        BoardBuilder boardBuilder = new BoardBuilder();

        List<Piece> blackPawns = initializePawnsAtLine(6, BLACK);
        List<Piece> whitePawns = initializePawnsAtLine(1, WHITE);
        List<Piece> blackPieces = initializePiecesAtLine(7, BLACK);
        List<Piece> whitePieces = initializePiecesAtLine(0, WHITE);

        return boardBuilder
                .pieces(blackPawns)
                .pieces(blackPieces)
                .pieces(whitePawns)
                .pieces(whitePieces)
                .build();
    }

    private List<Piece> initializePawnsAtLine(int lineNumber, PieceColor pieceColor) {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < Board.SIZE; i++) {
            pieces.add(PieceFactory.create(Pawn.class, pieceColor, Position.of(i, lineNumber)));
        }
        return pieces;
    }

    private List<Piece> initializePiecesAtLine(int lineNumber, PieceColor pieceColor) {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(PieceFactory.create(Rook.class, pieceColor, Position.of(0, lineNumber)));
        pieces.add(PieceFactory.create(Rook.class, pieceColor, Position.of(7, lineNumber)));
        pieces.add(PieceFactory.create(Knight.class, pieceColor, Position.of(1, lineNumber)));
        pieces.add(PieceFactory.create(Knight.class, pieceColor, Position.of(6, lineNumber)));
        pieces.add(PieceFactory.create(Bishop.class, pieceColor, Position.of(2, lineNumber)));
        pieces.add(PieceFactory.create(Bishop.class, pieceColor, Position.of(5, lineNumber)));
        pieces.add(PieceFactory.create(Queen.class, pieceColor, Position.of(3, lineNumber)));
        pieces.add(PieceFactory.create(King.class, pieceColor, Position.of(4, lineNumber)));
        return pieces;
    }

    public Board board() {
        return board;
    }

    public List<Position> getPossibleDestinationsOf(Position position) {
        Piece picked = board.getPieceAt(position);
        return picked.getMovablePositions(board.getBoardView());
    }

    public boolean isTurnOf(PieceColor pieceColor) {
        return turn.pieceColor() == pieceColor;
    }

    public PieceColor turn() {
        return turn.pieceColor();
    }

    public boolean isOwnPiece(final Position position) {
        return this.board.getPieceAt(position).getPieceColor() == turn.pieceColor();
    }

    public boolean movePiece(Position from, Position to) {
        board.getPieceAt(from).moveTo(to);
        GameDAO.holdAndWriteLog(turn, from, to);
        turn.endTurn();
        return true;
    }

    public double getCurrentScore(PieceColor pieceColor) {
        return this.board.getPieces().stream()
                .filter(p -> !p.isPawn())
                .filter(p -> p.getPieceColor() == pieceColor)
                .mapToDouble(Piece::getScore)
                .sum()
                + getPawnScore(pieceColor);
    }

    private double getPawnScore(PieceColor pieceColor) {
                return this.board.getPieces().stream()
                        .filter(p -> p.isPawn())
                        .filter(p -> p.getPieceColor() == pieceColor)
                        .map(p -> (Pawn) p)
                        .collect(Collectors.groupingBy(Piece::x))
                        .values().stream()
                        .flatMap(l ->
                                (l.size() == 1) ? l.stream().map(Pawn::getScore) : l.stream().map(Pawn::getHalfScore)
                        ).reduce(Double::sum)
                        .orElse(.0);
    }
}
