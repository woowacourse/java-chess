package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultMoveStrategy implements MoveStrategy {
    @Override
    public Board movePieceWithTurnValidation(Board board, final Piece fromPiece, final Piece toPiece, final Team currentTurn) {
        List<Piece> movedBoard = new ArrayList<>(board.getBoard());

        if (!fromPiece.isSameTeam(currentTurn)) {
            throw new TakeTurnException("체스 게임 순서를 지켜주세요.");
        }

        movePiece(movedBoard, fromPiece, toPiece);
        return new Board(movedBoard);
    }

    private void movePiece(final List<Piece> board, final Piece fromPiece, final Piece toPiece) {
        if (getPossiblePositions(board, fromPiece).contains(toPiece.getPosition())) {
            board.set(boardIndexOf(toPiece.getPosition()), fromPiece.move(toPiece.getPosition()));
            board.set(boardIndexOf(fromPiece.getPosition()), Piece.createBlank(fromPiece.getPosition()));
            Board.changeFlagWhenKingCaptured(toPiece);
            return;
        }
        throw new PieceImpossibleMoveException("해당 포지션으로 이동할 수 없습니다.");
    }

    protected boolean isBlankPieceInsideBoard(List<Piece> board, Position nextPosition) {
        return isInBoardRange(nextPosition) && board.get(boardIndexOf(nextPosition)).isBlank();
    }

    protected boolean isOpponentPieceInsideBoard(List<Piece> board, Piece piece, Position nextPosition) {
        return isInBoardRange(nextPosition) && piece.isOtherTeam(board.get(boardIndexOf(nextPosition)));
    }

    @Override
    public boolean isInBoardRange(Position position) {
        return position.getX() <= Position.END_INDEX && position.getX() >= Position.START_INDEX &&
                position.getY() <= Position.END_INDEX && position.getY() >= Position.START_INDEX;
    }

    @Override
    public int boardIndexOf(Position position) {
        return (position.getY() - 1) * Position.ROW_SIZE + position.getX() - 1;
    }
}
