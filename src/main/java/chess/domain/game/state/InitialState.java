package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.result.Result;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Color;
import chess.domain.piece.RealPiece;
import chess.domain.piece.strategy.BishopStrategy;
import chess.domain.piece.strategy.BlackPawnStrategy;
import chess.domain.piece.strategy.KingStrategy;
import chess.domain.piece.strategy.KnightStrategy;
import chess.domain.piece.strategy.QueenStrategy;
import chess.domain.piece.strategy.RookStrategy;
import chess.domain.piece.strategy.WhitePawnStrategy;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.BoardDto;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class InitialState implements GameState {

    private static final Row WHITE_SPECIAL_ROW = Row.ONE;
    private static final Row BLACK_SPECIAL_ROW = Row.EIGHT;
    private static final Row WHITE_PAWN_ROW = Row.TWO;
    private static final Row BLACK_PAWN_ROW = Row.SEVEN;

    public InitialState() {
    }

    @Override
    public GameState execute(final CommandAsString command) {
        if (command.isEnd()) {
            return new EndState(initiateBoard());
        }
        if (command.isStart()) {
            return new WhiteTurnState(initiateBoard());
        }
        throw new IllegalArgumentException("가능한 명령이 아닙니다.");
    }

    private Board initiateBoard() {
        final Map<Position, Piece> coordinates = new HashMap<>();
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                coordinates.put(Position.of(column, row), EmptyPiece.getInstance());
            }
        }
        placePawns(coordinates);
        placeSpecialPieces(WHITE_SPECIAL_ROW, Color.WHITE, coordinates);
        placeSpecialPieces(BLACK_SPECIAL_ROW, Color.BLACK, coordinates);
        return new Board(coordinates);
    }


    @Override
    public Result turnResult() {
        throw new IllegalArgumentException("게임이 시작하기 전에는 게임 상황을 볼 수 없습니다.");
    }

    @Override
    public Result statusResult() {
        throw new IllegalArgumentException("게임이 시작하기 전에는 게임 상황을 볼 수 없습니다.");
    }

    @Override
    public Result pathResult(Position source) {
        throw new IllegalArgumentException("게임이 시작되기 전에는 체스말의 경로를 확인할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String currentState() {
        return "not started";
    }

    @Override
    public BoardDto boardDto() {
        return new BoardDto(initiateBoard());
    }

    private void placePawns(final Map<Position, Piece> coordinates) {
        Arrays.stream(Column.values()).forEach(column -> {
            coordinates.replace(
                    Position.of(column, WHITE_PAWN_ROW),
                    new RealPiece(Color.WHITE, new WhitePawnStrategy()));
            coordinates.replace(
                    Position.of(column, BLACK_PAWN_ROW),
                    new RealPiece(Color.BLACK, new BlackPawnStrategy()));
        });
    }

    private void placeSpecialPieces(final Row row, final Color color,
            final Map<Position, Piece> coordinates) {
        Map<Column, Piece> pieces = createSpecialPieces(color);
        Arrays.stream(Column.values()).forEach(
                column ->  coordinates.replace(Position.of(column, row), pieces.get(column))
        );
    }

    private Map<Column, Piece> createSpecialPieces(Color color) {
        Map<Column, Piece> pieces = new LinkedHashMap<>();
        pieces.put(Column.A, new RealPiece(color, new RookStrategy()));
        pieces.put(Column.B, new RealPiece(color, new KnightStrategy()));
        pieces.put(Column.C, new RealPiece(color, new BishopStrategy()));
        pieces.put(Column.D, new RealPiece(color, new QueenStrategy()));
        pieces.put(Column.E, new RealPiece(color, new KingStrategy()));
        pieces.put(Column.F, new RealPiece(color, new BishopStrategy()));
        pieces.put(Column.G, new RealPiece(color, new KnightStrategy()));
        pieces.put(Column.H, new RealPiece(color, new RookStrategy()));
        return pieces;
    }
}
