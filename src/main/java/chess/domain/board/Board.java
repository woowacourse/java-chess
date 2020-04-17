package chess.domain.board;

import chess.controller.dto.PieceDto;
import chess.domain.game.Turn;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.File;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    public static final double DEFAULT_PAWN_POINT = 1d;
    public static final double DUPLICATED_PAWN_POINT = 0.5d;
    private static final int PAWN_DUPLICATION_COUNT = 1;

    private Map<Position, PieceState> board;

    private Board(Map<Position, PieceState> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new Board(boardInitializer.create());
    }

    public static Board of(Map<Position, PieceState> board) {
        return new Board(board);
    }

    public void move(Position source, Position target, Turn turn) {
        PieceState sourcePiece = board.get(source);
        validateSource(sourcePiece, turn);
        PieceState piece = sourcePiece.move(target, getBoardAndTeam());
        board.remove(source);
        board.put(target, piece);
        turn.switchTurn();
    }

    private void validateSource(PieceState sourcePiece, Turn turn) {
        if (Objects.isNull(sourcePiece)) {
            throw new IllegalArgumentException("잘못된 위치를 선택하셨습니다.");
        }
        if (!turn.isSamePlayer(sourcePiece.getPlayer())) {
            throw new IllegalArgumentException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    private Map<Position, PieceDto> getBoardAndTeam() {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new PieceDto(entry.getValue().getPlayer())
                ));
    }

    public Map<Position, String> getBoardAndString() {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getPlayer().toString() + entry.getValue().toString()
                ));
    }

    public Map<Position, PieceState> getBoard() {
        return board;
    }

    public boolean isLost(Player player) {
        return board.values()
                .stream()
                .noneMatch(piece -> player.equals(piece.getPlayer()) && piece instanceof King);
    }

    public Map<Position, PieceState> getRemainPieces(Player player) {
        return board.entrySet()
                .stream()
                .filter(entry -> player.equals(entry.getValue().getPlayer()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public double getPlayerSumWithoutPawn(final Player player) {
        return getRemainPieces(player).values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();
    }

    public double getPawnPointsByFile(File file, Player player) {
        double duplicatedPawnCount = getDuplicatedPawnCount(file, player);
        if (duplicatedPawnCount > PAWN_DUPLICATION_COUNT) {
            return duplicatedPawnCount * DUPLICATED_PAWN_POINT;
        }
        return duplicatedPawnCount * DEFAULT_PAWN_POINT;
    }

    private double getDuplicatedPawnCount(File file, Player player) {
        return getRemainPieces(player)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Pawn)
                .filter(entry -> entry.getKey().isSameFile(file))
                .mapToDouble(entry -> entry.getValue().getPoint())
                .sum();
    }

    public boolean isEnd() {
        return isLost(Player.WHITE) || isLost(Player.BLACK);
    }
}
