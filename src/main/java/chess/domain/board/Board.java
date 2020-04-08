package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.exception.TakeTurnException;

import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private static final int BOARD_SIZE = 64;
    private static final int FIRST_INDEX = 0;
    private static final int ASCII_GAP = 96;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLACK_PAWN_ROW = 7;
    private static final int BLANK_START_INDEX = 3;
    private static final int BLANK_END_INDEX = 6;

    private List<Piece> board;
    private boolean isFinished = false;
    private Turn turn = Turn.WHITE;

    public Board(final List<Piece> board) {
        if (isNotProperBoardSize(board)) {
            throw new IllegalArgumentException("보드가 제대로 생성되지 못했습니다.");
        }
        this.board = board;
    }

    public static int getBoardIndexByStringPosition(String position) {
        String x = String.valueOf(position.charAt(0));
        String y = String.valueOf(position.charAt(1));

        int col = x.charAt(FIRST_INDEX) - ASCII_GAP;
        int row = Integer.parseInt(y);

        return getBoardIndex(col, row);
    }

    public static int getBoardIndex(final int col, final int row) {
        return (row - 1) * Position.ROW_SIZE + col - 1;
    }

    public void clear() {
        for (int row = Position.START_INDEX; row <= Position.END_INDEX; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                board.set(getBoardIndex(col, row), Blank.create(new Position(col, row)));
            }
        }
    }

    public void initialize() {
        turn = Turn.WHITE;

        board.set(0, Rook.createWhite(new Position(1, 1)));
        board.set(1, Knight.createWhite(new Position(2, 1)));
        board.set(2, Bishop.createWhite(new Position(3, 1)));
        board.set(3, Queen.createWhite(new Position(4, 1)));
        board.set(4, King.createWhite(new Position(5, 1)));
        board.set(5, Bishop.createWhite(new Position(6, 1)));
        board.set(6, Knight.createWhite(new Position(7, 1)));
        board.set(7, Rook.createWhite(new Position(8, 1)));

        IntStream.rangeClosed(Position.START_INDEX, Position.END_INDEX)
                .forEach(col -> board.set(Position.ROW_SIZE * (WHITE_PAWN_ROW - 1) + col - 1,
                        Pawn.createWhite(new Position(col, WHITE_PAWN_ROW))));

        for (int row = BLANK_START_INDEX; row <= BLANK_END_INDEX; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                board.set(getBoardIndex(col, row), Blank.create(new Position(col, row)));
            }
        }

        IntStream.rangeClosed(Position.START_INDEX, Position.END_INDEX)
                .forEach(col -> board.set(Position.ROW_SIZE * (BLACK_PAWN_ROW - 1) + col - 1,
                        Pawn.createBlack(new Position(col, BLACK_PAWN_ROW))));

        board.set(56, Rook.createBlack(new Position(1, 8)));
        board.set(57, Knight.createBlack(new Position(2, 8)));
        board.set(58, Bishop.createBlack(new Position(3, 8)));
        board.set(59, Queen.createBlack(new Position(4, 8)));
        board.set(60, King.createBlack(new Position(5, 8)));
        board.set(61, Bishop.createBlack(new Position(6, 8)));
        board.set(62, Knight.createBlack(new Position(7, 8)));
        board.set(63, Rook.createBlack(new Position(8, 8)));
    }

    public void move(final String from, final String to) {
        Piece fromPiece = findPieceBy(Position.of(from));
        Piece toPiece = findPieceBy(Position.of(to));

        if (!fromPiece.isSameTeam(turn)) {
            throw new TakeTurnException("체스 게임 순서를 지켜주세요.");
        }

        if (fromPiece.isMovable(this, toPiece)) {
            board.set(boardIndexOf(toPiece.getPosition()), fromPiece.moveTo(toPiece.getPosition()));
            board.set(boardIndexOf(fromPiece.getPosition()), Blank.create(fromPiece.getPosition()));
        }
        changeTurn();
        changeFlagWhenKingCaptured(toPiece);
    }

    public void changeTurn() {
        if (turn == Turn.WHITE) {
            turn = Turn.BLACK;
            return;
        }
        turn = Turn.WHITE;
    }

    public Piece findPieceBy(int index) {
        return board.get(index);
    }

    public Piece findPieceBy(Position position) {
        return board.get(getBoardIndex(position.getX(), position.getY()));
    }

    public void changeFlagWhenKingCaptured(final Piece toPiece) {
        if (toPiece.isKing()) {
            isFinished = true;
        }
    }

    private boolean isNotProperBoardSize(final List<Piece> board) {
        return board.size() != BOARD_SIZE;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int boardIndexOf(Position position) {
        return getBoardIndex(position.getX(), position.getY());
    }

    public List<Piece> getBoard() {
        return board;
    }

    public String getTurn() {
        return turn.name();
    }
}
