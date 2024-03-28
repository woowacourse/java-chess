package chess.model.board;

import chess.model.evaluation.PositionEvaluation;
import chess.model.piece.Blank;
import chess.model.piece.King;
import chess.model.piece.Piece;
import chess.model.piece.Side;
import chess.model.position.Position;
import chess.model.position.Path;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void move(Position sourcePosition, Position targetPosition, Turn turn) {
        Piece sourcePiece = board.get(sourcePosition);
        validateSource(sourcePiece, turn);
        Piece targetPiece = board.get(targetPosition);
        validateTargetPiece(sourcePiece, targetPiece);
        Path path = sourcePiece.findPath(sourcePosition, targetPosition, targetPiece);
        validatePathIsEmpty(path);
        validatePathContainsPiece(path);
        replacePiece(sourcePiece, sourcePosition, targetPosition);
    }

    private void validateSource(Piece sourcePiece, Turn turn) {
        if (sourcePiece.equals(Blank.INSTANCE)) {
            throw new IllegalArgumentException("소스 위치에 기물이 존재하지 않습니다.");
        }
        if (turn.isNotCorrect(sourcePiece)) {
            throw new IllegalArgumentException("올바른 게임 차례가 아닙니다.");
        }
    }

    private void validateTargetPiece(Piece sourcePiece, Piece targetPiece) {
        if (!targetPiece.equals(Blank.INSTANCE) && sourcePiece.isSameSide(targetPiece)) {
            throw new IllegalArgumentException("타겟 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validatePathIsEmpty(Path path) {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("타겟 위치로 이동할 수 없습니다.");
        }
    }

    private void validatePathContainsPiece(Path path) {
        if (path.containsPiece(board)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재하여 움직일 수 없습니다.");
        }
    }

    private void replacePiece(Piece sourcePiece, Position sourcePosition, Position targetPosition) {
        board.put(sourcePosition, Blank.INSTANCE);
        board.put(targetPosition, sourcePiece);
    }

    public boolean canContinue() {
        return Side.colors()
                .stream()
                .allMatch(side -> board.containsValue(King.from(side)));
    }

    public PositionEvaluation evaluateCurrentBoard() {
        return new PositionEvaluation(board);
    }

    public Map<Position, Piece> getBoard() {
        return unmodifiableMap(board);
    }
}
