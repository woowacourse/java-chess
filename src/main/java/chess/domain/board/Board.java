package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final String BOARD_CREATION_FAIL_MESSAGE = "보드가 제대로 생성되지 못했습니다.";
    private static final int BOARD_SIZE = 64;
    private static final int COLUMN_VALUE_OF_STRING_COMMAND = 0;
    private static final int ROW_VALUE_OF_STRING_COMMAND = 1;
    private static final int FIRST_INDEX = 0;
    private static final int ASCII_GAP = 96;
    private static final String TAKE_TURN_EXCEPTION_MESSAGE = "체스 게임 순서를 지켜주세요.";
    private static final char BLANK_REPRESENTATION = '.';
    private static final int BOARD_ROW_SIZE = 8;

    private static boolean isFinished;

    private final List<Piece> board;

    public Board(final List<Piece> board) {
        if (isNotProperBoardSize(board)) {
            throw new IllegalArgumentException(BOARD_CREATION_FAIL_MESSAGE);
        }
        this.board = board;
    }

    private boolean isNotProperBoardSize(final List<Piece> board) {
        return board.size() != BOARD_SIZE;
    }

    public Piece findPieceBy(int index) {
        return board.get(index);
    }

    public int getBoardIndexByStringPosition(String position) {
        String x = String.valueOf(position.charAt(COLUMN_VALUE_OF_STRING_COMMAND));
        String y = String.valueOf(position.charAt(ROW_VALUE_OF_STRING_COMMAND));

        int col = x.charAt(FIRST_INDEX) - ASCII_GAP;
        int row = Integer.parseInt(y);

        return getBoardIndex(col, row);
    }

    private int getBoardIndex(final int col, final int row) {
        return (row - 1) * BOARD_ROW_SIZE + col - 1;
    }

    public boolean isBlank(final Position nextPosition) {
        return findPieceBy(getBoardIndex(nextPosition.getX(), nextPosition.getY()))
                .getClass().equals(Blank.class);
    }

    public boolean isOtherTeam(final Position position, final Position nextPosition) {
        return findPieceBy(getBoardIndex(position.getX(), position.getY()))
                .isOtherTeam(findPieceBy(getBoardIndex(nextPosition.getX(), nextPosition.getY())));
    }

    public Board movePiece(final String from, final String to, final int turnFlag) throws IllegalAccessException {
        List<Piece> movedBoard = new ArrayList<>(board);

        int fromIndex = getBoardIndexByStringPosition(from);
        int toIndex = getBoardIndexByStringPosition(to);
        if (Team.isSameTeam(turnFlag, findPieceBy(fromIndex))) {
            throw new IllegalArgumentException(TAKE_TURN_EXCEPTION_MESSAGE);
        }

        movePieceWithValidation(movedBoard, fromIndex, toIndex);
        return new Board(movedBoard);
    }

    private void movePieceWithValidation(final List<Piece> movedBoard, final int fromIndex, final int toIndex) throws IllegalAccessException {
        Piece fromPiece = findPieceBy(fromIndex);
        Piece toPiece = findPieceBy(toIndex);

        if (fromPiece.isMovable(this, toPiece.getPosition())) {
            movedBoard.set(toIndex, fromPiece.movePiecePosition(toPiece.getPosition()));
            movedBoard.set(fromIndex, new Blank(BLANK_REPRESENTATION, Team.BLANK, fromPiece.getPosition()));
            changeFlagWhenKingCaptured(toPiece);
        }
    }

    private void changeFlagWhenKingCaptured(final Piece toPiece) {
        if (toPiece.isKing()) {
            isFinished = true;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public List<Piece> getBoard() {
        return board;
    }
}
