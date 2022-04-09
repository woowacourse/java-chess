package chess.domain.board;

import chess.domain.board.strategy.BoardGenerationStrategy;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();

    public void initBoard(BoardGenerationStrategy boardGenerator) {
        board.putAll(boardGenerator.create());
    }

    public void removeBoard() {
        board.clear();
    }

    public void validatePath(Position from, Position to, Direction direction) {
        Position current = from.move(direction);

        while (!current.equals(to)) {
            if (board.get(current) != null) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
            current = current.move(direction);
        }
    }

    public void move(Position movePosition, Piece movePiece) {
        board.put(movePosition, movePiece);

    }

    public void remove(Position position) {
        board.remove(position);
    }

    public Position findKingPosition(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team) && entry.getValue().isKing())
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("King 이 존재하지 않습니다."));
    }

    public List<Entry<Position, Piece>> findSameTeamPieces(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    public Piece takePieceByPosition(Position position) {
        return board.get(position);
    }

    public Result createResult() {
        return new Result(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }


    public Map<String, String> toMap() {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey().toString(), m -> m.getValue().toString()));
    }
}

