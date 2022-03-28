package chess.domain.board;

import static chess.domain.piece.constant.PieceColor.*;
import static chess.domain.board.Rank.*;

import chess.constant.MoveType;
import java.util.Map;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.turndecider.GameFlow;

public class Board {

    static final String SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);

    private final Map<Position, Piece> board;
    private final GameFlow gameFlow;

    public Board(Map<Position, Piece> board, GameFlow gameFlow) {
        this.board = board;
        this.gameFlow = gameFlow;
    }

    public void move(Position source, Position target) {
        turnDecide(source);
        validateSourceNotEmpty(source);
        boolean isGameFinished = board.get(target) instanceof King;
        changePieces(source, target);
        gameFlow.nextState(isGameFinished);
    }

    private void turnDecide(Position source) {
        if (!gameFlow.isCorrectTurn(board.get(source))) {
            throw new IllegalArgumentException("[ERROR] 현재 올바르지 않은 팀 선택입니다. ");
        }
    }

    private void changePieces(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        MoveType moveType = decideMoveType(targetPiece);
        if (!sourcePiece.isMovable(source, target, moveType) || isBlocked(source, target) || targetPiece.isMyTeam(
            sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }

        board.put(target, sourcePiece);
        board.put(source, EMPTY_PIECE);
    }

    private MoveType decideMoveType(Piece targetPiece) {
        if (targetPiece.equals(EMPTY_PIECE)) {
            return MoveType.EMPTY;
        }
        if (gameFlow.isCorrectTurn(targetPiece)) {
            return MoveType.FRIENDLY;
        }
        return MoveType.ENEMY;
    }

    private boolean isBlocked(Position source, Position target) {
        if (board.get(source) instanceof Knight) {
            return false;
        }

        for (Position position : source.positionsToMove(target)) {
            if (!board.get(position).equals(EMPTY_PIECE)) {
                return true;
            }
        }
        return false;
    }

    private void validateSourceNotEmpty(Position source) {
        if (board.get(source).equals(EMPTY_PIECE)) {
            throw new IllegalArgumentException(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
        }
    }

    public double calculateScore() {
        return board.values()
            .stream()
            .filter(gameFlow::isCorrectTurn)
            .mapToDouble(Piece::getScore)
            .sum() - adjustPawnScore();
    }

    public double adjustPawnScore() {
        int adjustingScore = 0;
        for (File file : File.values()) {
            long count = reverseValues().stream()
                .map(rank -> new Position(file, rank))
                .filter(position -> board.get(position) instanceof Pawn
                    && gameFlow.isCorrectTurn(board.get(position)))
                .count();

            if (count > 1) {
                adjustingScore += count * 0.5;
            }
        }
        return adjustingScore;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
