package chess.domain.board;

import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {

    private Map<Position, PieceState> board;

    private Board(Map<Position, PieceState> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new Board(boardInitializer.create());
    }

    public void move(Position source, Position target) {
        PieceState sourcePiece = board.get(source);
        validateSource(sourcePiece);
        PieceState piece = sourcePiece.move(target, getBoardDto());
        board.remove(source);
        board.put(target, piece);
    }

    public Map<Position, PieceState> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    private void validateSource(PieceState sourcePiece) {
        if (Objects.isNull(sourcePiece)) {
            throw new IllegalArgumentException("잘못된 위치를 선택하셨습니다.");
        }
    }

    private Map<Position, PieceDto> getBoardDto() {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> new PieceDto(entry.getValue().getPlayer())
                ));
    }
}
