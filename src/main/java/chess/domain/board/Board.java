package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.exception.InvalidPositionException;

import java.util.Map;

public class Board {
    private static final int BOARD_SIZE = 64;
    private static final int BLANK_START_INDEX = 3;
    private static final int BLANK_END_INDEX = 6;

    private Map<Position, Piece> board;
    private boolean isFinished = false;

    public Board(final Map<Position, Piece> board) {
        if (isNotProperBoardSize(board)) {
            throw new IllegalArgumentException("보드가 제대로 생성되지 못했습니다.");
        }
        this.board = board;
    }

    public void initialize() {
        isFinished = false;

        board.put(Position.of("a1"), Piece.of(PieceType.WHITE_ROOK));
        board.put(Position.of("b1"), Piece.of(PieceType.WHITE_KNIGHT));
        board.put(Position.of("c1"), Piece.of(PieceType.WHITE_BISHOP));
        board.put(Position.of("d1"), Piece.of(PieceType.WHITE_QUEEN));
        board.put(Position.of("e1"), Piece.of(PieceType.WHITE_KING));
        board.put(Position.of("f1"), Piece.of(PieceType.WHITE_BISHOP));
        board.put(Position.of("g1"), Piece.of(PieceType.WHITE_KNIGHT));
        board.put(Position.of("h1"), Piece.of(PieceType.WHITE_ROOK));

        board.put(Position.of("a2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("b2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("c2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("d2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("e2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("f2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("g2"), Piece.of(PieceType.FIRST_WHITE_PAWN));
        board.put(Position.of("h2"), Piece.of(PieceType.FIRST_WHITE_PAWN));

        for (int row = BLANK_START_INDEX; row <= BLANK_END_INDEX; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                board.put(Position.of(Position.convertToStringPosition(col, row)), Piece.of(PieceType.BLANK));
            }
        }

        board.put(Position.of("a7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("b7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("c7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("d7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("e7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("f7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("g7"), Piece.of(PieceType.FIRST_BLACK_PAWN));
        board.put(Position.of("h7"), Piece.of(PieceType.FIRST_BLACK_PAWN));

        board.put(Position.of("a8"), Piece.of(PieceType.BLACK_ROOK));
        board.put(Position.of("b8"), Piece.of(PieceType.BLACK_KNIGHT));
        board.put(Position.of("c8"), Piece.of(PieceType.BLACK_BISHOP));
        board.put(Position.of("d8"), Piece.of(PieceType.BLACK_QUEEN));
        board.put(Position.of("e8"), Piece.of(PieceType.BLACK_KING));
        board.put(Position.of("f8"), Piece.of(PieceType.BLACK_BISHOP));
        board.put(Position.of("g8"), Piece.of(PieceType.BLACK_KNIGHT));
        board.put(Position.of("h8"), Piece.of(PieceType.BLACK_ROOK));
    }

    public Piece findBy(final Position position) {
        return board.keySet().stream()
                .filter(key -> key.equals(position))
                .map(key -> board.get(key))
                .findFirst()
                .orElseThrow(() -> new InvalidPositionException("존재하지 않는 포지션입니다."));
    }

    public void move(final String from, final String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);

        Piece fromPiece = board.get(fromPosition);
        Piece toPiece = board.get(toPosition);

        if (fromPiece.isMovable(this, fromPosition, toPosition)) {
            board.put(toPosition, fromPiece.getNextPiece());
            board.put(fromPosition, Piece.of(PieceType.BLANK));
        }
        changeFlagWhenKingCaptured(toPiece);
    }

    public void changeFlagWhenKingCaptured(final Piece toPiece) {
        if (toPiece.isKing()) {
            isFinished = true;
        }
    }

    private boolean isNotProperBoardSize(final Map<Position, Piece> board) {
        return board.size() != BOARD_SIZE;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
