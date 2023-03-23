package chess.domain.game.state.started;

import chess.domain.game.state.ChessGame;
import chess.domain.game.state.finished.EndGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.move.PieceMove;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;
import java.util.List;

public class RunGame extends StartedGame {

    private static final String UNABLE_TO_MOVE = "이동할 수 없습니다.";
    private static final String TURN_MISMATCHED = "다른 진영의 기물을 선택할 수 없습니다.";
    private static final String EMPTY_CHOICE = "빈 칸은 선택할 수 없습니다.";
    private static final String UNABLE_TO_EQUAL_POSITION = "출발 지점과 도착 지점은 동일할 수 없습니다";

    public RunGame(ChessBoard chessBoard, Camp turnCamp) {
        super(chessBoard, turnCamp);
    }

    public ChessGame move(Position fromPosition, Position toPosition) {
        validateBeforeMove(fromPosition, toPosition);
        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        validateMovable(chessBoard.isPieceExist(toPosition), pieceMove);
        chessBoard.movePiece(fromPosition, toPosition);
        return new RunGame(chessBoard, turnCamp.convert());
    }

    private PieceMove getPieceMove(Position fromPosition, Position toPosition) {
        Piece fromPiece = chessBoard.peekPiece(fromPosition);
        PieceMove pieceMove = fromPiece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);
        for (Position position : pathPositions) {
            validateMovableBetween(pieceMove, position);
        }

        return pieceMove;
    }

    private void validateMovableBetween(PieceMove pieceMove, Position position) {
        boolean isEmpty = chessBoard.isPieceExist(position);
        if (!pieceMove.isMovable(isEmpty, false)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateBeforeMove(Position fromPosition, Position toPosition) {
        validateEqualPosition(fromPosition, toPosition);
        validatePickExistPiece(fromPosition);
        validateNotMoveToSameCampPiece(fromPosition, toPosition);
        validateTurn(fromPosition);
    }

    private void validateEqualPosition(Position fromPosition, Position toPosition) {
        if (fromPosition.equals(toPosition)) {
            throw new IllegalArgumentException(UNABLE_TO_EQUAL_POSITION);
        }
    }

    private void validatePickExistPiece(Position fromPosition) {
        if (!chessBoard.isPieceExist(fromPosition)) {
            throw new IllegalArgumentException(EMPTY_CHOICE);
        }
    }

    private void validateNotMoveToSameCampPiece(Position fromPosition, Position toPosition) {
        if (!chessBoard.isPieceExist(toPosition)) {
            return;
        }

        Piece fromPiece = chessBoard.peekPiece(fromPosition);
        Piece toPiece = chessBoard.peekPiece(toPosition);
        if (fromPiece.isSameCamp(toPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateTurn(Position fromPosition) {
        Piece fromPiece = chessBoard.peekPiece(fromPosition);

        if (fromPiece.isMismatchedCamp(turnCamp)) {
            throw new IllegalArgumentException(TURN_MISMATCHED);
        }
    }

    private void validateMovable(boolean isEmpty, PieceMove pieceMove) {
        if (!pieceMove.isMovable(isEmpty, true)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    @Override
    public boolean isRunnableGame() {
        return true;
    }

    @Override
    public ChessGame endGame() {
        return new EndGame(chessBoard, turnCamp);
    }
}
