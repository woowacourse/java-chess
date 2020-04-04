package chess.domain.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.domain.result.ChessResult;
import chess.dto.LineDto;

public class Board {

    private final Map<Position, GamePiece> board;
    private final Status status;

    private Board(Map<Position, GamePiece> board, Status status) {
        this.board = Collections.unmodifiableMap(new TreeMap<>(board));
        this.status = status;
    }

    static Board of(Map<Position, GamePiece> board, Status status) {
        return new Board(board, status);
    }

    public Board move(String sourceInput, String targetInput) {
        validateAll(sourceInput, targetInput);

        Map<Position, GamePiece> board = new HashMap<>(this.board);
        Position source = Position.from(sourceInput);
        Position target = Position.from(targetInput);
        GamePiece sourcePiece = board.get(source);
        GamePiece targetPiece = board.get(target);

        board.put(target, sourcePiece);
        board.put(source, EmptyPiece.getInstance());
        Status nextStatus = status.nextTurn();

        if (targetPiece.isKing()) {
            return new Board(board, nextStatus.finish());
        }
        return new Board(board, nextStatus);
    }

    private void validateAll(String sourceInput, String targetInput) {
        validateStatus();
        GamePiece sourcePiece = board.get(Position.from(sourceInput));
        validateSourcePiece(sourcePiece);

        sourcePiece.validateMoveTo(board, Position.from(sourceInput), Position.from(targetInput));
    }

    private void validateStatus() {
        if (status.isNotProcessing()) {
            throw new UnsupportedOperationException("먼저 게임을 실행해야합니다.");
        }
    }

    private void validateSourcePiece(GamePiece sourcePiece) {
        if (sourcePiece.equals(EmptyPiece.getInstance())) {
            throw new InvalidMovementException("기물이 존재하지 않습니다.");
        }
        if (status.isWhiteTurn() && sourcePiece.is(PlayerColor.BLACK)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
        if (status.isBlackTurn() && sourcePiece.is(PlayerColor.WHITE)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    public boolean isNotFinished() {
        return status.isNotFinished();
    }

    public ChessResult calculateResult() {
        return ChessResult.from(board);
    }

    public List<LineDto> getRows() {
        return Line.listByRow(board);
    }

    public Map<Position, GamePiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public int getTurn() {
        return status.getTurn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Board))
            return false;
        Board board1 = (Board)o;
        return Objects.equals(board, board1.board) &&
                Objects.equals(status, board1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, status);
    }
}
