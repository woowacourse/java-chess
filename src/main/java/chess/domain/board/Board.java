package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.piece.implementation.King;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {

    private static final int RUNNING_KING_COUNT = 2;
    private Map<Position, PieceState> board;

    private Board(Map<Position, PieceState> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new Board(boardInitializer.create());
    }

    public void move(Position source, Position target, Turn turn) {
        PieceState sourcePiece = board.get(source);
        validateSource(sourcePiece, turn);
        PieceState piece = sourcePiece.move(target, getBoardDto());
        board.remove(source);
        board.put(target, piece);
        turn.switchTurn();
    }

    public Map<Position, PieceState> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    private void validateSource(PieceState sourcePiece, Turn turn) {
        if (Objects.isNull(sourcePiece)) {
            throw new IllegalArgumentException("잘못된 위치를 선택하셨습니다.");
        }
        if (!turn.isSameTeam(sourcePiece.getTeam())) {
            throw new IllegalArgumentException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    private BoardState getBoardDto() {
        Map<Position, PieceDto> boardState = board.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> new PieceDto(entry.getValue().getTeam())
                ));
        return BoardState.of(boardState);
    }

    public Map<Position, PieceState> getRemainPieces(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> team.equals(entry.getValue().getTeam()))
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                ));
    }

    public boolean isEnd() {
        return board.values()
                .stream()
                .filter(piece -> piece instanceof King)
                .count() < RUNNING_KING_COUNT;
    }
}
