package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static boolean isFinished;

    private final List<Piece> board;

    public Board(final List<Piece> board) {
        if (isNotProperBoardSize(board)) {
            throw new IllegalArgumentException("보드가 제대로 생성되지 못했습니다.");
        }
        this.board = board;
    }

    private boolean isNotProperBoardSize(final List<Piece> board) {
        return board.size() != 64;
    }

    public Piece findPieceBy(int index) {
        return board.get(index);
    }

    public int getBoardIndexByStringPosition(String position) {
        String x = String.valueOf(position.charAt(0));
        String y = String.valueOf(position.charAt(1));

        int col = x.charAt(0) - 96;
        int row = Integer.parseInt(y);

        return getBoardIndex(col, row);
    }

    private int getBoardIndex(int col, int row) {
        return (row - 1) * 8 + col - 1;
    }

    public boolean isBlank(Position nextPosition) {
        return findPieceBy(getBoardIndex(nextPosition.getX(), nextPosition.getY()))
                .getClass().equals(Blank.class);
    }

    public boolean isOtherTeam(Position position, Position nextPosition) {
        return findPieceBy(getBoardIndex(position.getX(), position.getY()))
                .isOtherTeam(findPieceBy(getBoardIndex(nextPosition.getX(), nextPosition.getY())));
    }

    public Board movePiece(String from, String to, int turnFlag) throws IllegalAccessException {
        List<Piece> movedBoard = new ArrayList<>(board);

        int fromIndex = getBoardIndexByStringPosition(from);
        int toIndex = getBoardIndexByStringPosition(to);
        if (Team.isSameTeam(turnFlag, findPieceBy(fromIndex))) {
            throw new IllegalAccessException("체스 게임 순서를 지켜주세요.");
        }

        movePieceWithValidation(movedBoard, fromIndex, toIndex);
        return new Board(movedBoard);
    }

    private void movePieceWithValidation(List<Piece> movedBoard, int fromIndex, int toIndex) {
        Piece fromPiece = findPieceBy(fromIndex);
        Piece toPiece = findPieceBy(toIndex);

        if (fromPiece.isMovable(this, toPiece.getPosition())) {
            movedBoard.set(toIndex, fromPiece.movePiecePosition(toPiece.getPosition()));
            movedBoard.set(fromIndex, new Blank('.', Team.BLANK, fromPiece.getPosition()));
            changeFlagWhenKingCaptured(toPiece);
        }
    }

    private void changeFlagWhenKingCaptured(Piece toPiece) {
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
