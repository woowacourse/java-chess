package model.game;

import model.Position;
import model.board.Board;
import model.board.BoardBuilder;
import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceFactory;
import model.piece.impl.*;
import service.LogVO;

import java.util.Collections;
import java.util.List;

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
        return movePiece(src, dest);
    }
    private Board initializeBoard() {
        BoardBuilder boardBuilder = new BoardBuilder();

        for (int i = 0; i < Board.SIZE; i++) {
            boardBuilder.piece(PieceFactory.create(Pawn.class, BLACK, Position.of(i, 6)));
            boardBuilder.piece(PieceFactory.create(Pawn.class, WHITE, Position.of(i, 1)));
        }

        boardBuilder.piece(PieceFactory.create(Rook.class, BLACK, Position.of(0, 7)));
        boardBuilder.piece(PieceFactory.create(Rook.class, BLACK, Position.of(7, 7)));
        boardBuilder.piece(PieceFactory.create(Knight.class, BLACK, Position.of(1, 7)));
        boardBuilder.piece(PieceFactory.create(Knight.class, BLACK, Position.of(6, 7)));
        boardBuilder.piece(PieceFactory.create(Bishop.class, BLACK, Position.of(2, 7)));
        boardBuilder.piece(PieceFactory.create(Bishop.class, BLACK, Position.of(5, 7)));
        boardBuilder.piece(PieceFactory.create(Queen.class, BLACK, Position.of(3, 7)));
        boardBuilder.piece(PieceFactory.create(King.class, BLACK, Position.of(4, 7)));

        boardBuilder.piece(PieceFactory.create(Rook.class, WHITE, Position.of(0, 0)));
        boardBuilder.piece(PieceFactory.create(Rook.class, WHITE, Position.of(7, 0)));
        boardBuilder.piece(PieceFactory.create(Knight.class, WHITE, Position.of(1, 0)));
        boardBuilder.piece(PieceFactory.create(Knight.class, WHITE, Position.of(6, 0)));
        boardBuilder.piece(PieceFactory.create(Bishop.class, WHITE, Position.of(2, 0)));
        boardBuilder.piece(PieceFactory.create(Bishop.class, WHITE, Position.of(5, 0)));
        boardBuilder.piece(PieceFactory.create(Queen.class, WHITE, Position.of(3, 0)));
        boardBuilder.piece(PieceFactory.create(King.class, WHITE, Position.of(4, 0)));

        return boardBuilder.build();
    }

    public Board board() {
        return board;
    }

    public List<Position> getPossibleDestinationsOf(Position position) {
        Piece picked = board.getPieceAt(position);
        return picked.getMovablePositions(board.getBoardView());
    }

    public boolean isTurnOf(PieceColor pieceColor) {
        return turn.team() == pieceColor;
    }

    public boolean movePiece(Position from, Position to) {
        board.getPieceAt(from).moveTo(to);
        turn.endTurn();
        GameDAO.holdAndWriteLog(turn, from, to);
        return true;
    }

    public boolean isOwnPiece(Position position) {
        return true;
    }

    public String getCurrentScore(PieceColor white) {
        return "기능 추가중";
    }
}
