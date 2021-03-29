package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.board.Game;
import chess.domain.game.GameVisual;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
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
        System.out.println("launched execute from initial state");
        if (command.isEnd()) {
            return new EndState(new Game(initiateBoard()));
        }
        if (command.isStart()) {
            System.out.println("launched start from initial state");
            return new WhiteTurnState(new Game(initiateBoard()));
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
        placeSpecialPieces(WHITE_SPECIAL_ROW, PieceColor.WHITE, coordinates);
        placeSpecialPieces(BLACK_SPECIAL_ROW, PieceColor.BLACK, coordinates);
        return new Board(coordinates);
    }


    @Override
    public GameVisual gameVisual() {
        throw new IllegalArgumentException("게임이 시작하기 전에는 게임 상황을 볼 수 없습니다.");
    }

    @Override
    public GameVisual statusVisual() {
        throw new IllegalArgumentException("게임이 시작하기 전에는 게임 상황을 볼 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private void placePawns(final Map<Position, Piece> coordinates) {
        Arrays.stream(Column.values()).forEach(column -> {
            coordinates.replace(
                    Position.of(column, WHITE_PAWN_ROW),
                    new RealPiece(PieceColor.WHITE, new WhitePawnStrategy()));
            coordinates.replace(
                    Position.of(column, BLACK_PAWN_ROW),
                    new RealPiece(PieceColor.BLACK, new BlackPawnStrategy()));
        });
    }

    private void placeSpecialPieces(final Row row, final PieceColor color,
            final Map<Position, Piece> coordinates) {
        Map<Column, Piece> pieces = createSpecialPieces(color);
        Arrays.stream(Column.values()).forEach(
                column ->  coordinates.replace(Position.of(column, row), pieces.get(column))
        );
    }

    private Map<Column, Piece> createSpecialPieces(PieceColor color) {
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
