package chess.domain.game.state;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.property.Color;

public class Waiting implements GameState {

    private final ChessBoard board = new ChessBoard();

    @Override
    public GameState start() {
        initializePieces(Rank.Eight, Color.Black);
        initializePieces(Rank.One, Color.White);
        initializePawn();

        return new Running(board, Player.White);
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("start를 해야만 move를 할 수 있습니다.");
    }

    @Override
    public Map<Color, Double> status() {
        throw new UnsupportedOperationException("start를 해야만 status를 할 수 있습니다.");
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Player getPlayer() {
        return Player.White;
    }

    private void initializePieces(Rank rank, Color color) {
        board.putPiece(Position.of(File.a, rank), new Rook(color));
        board.putPiece(Position.of(File.b, rank), new Knight(color));
        board.putPiece(Position.of(File.c, rank), new Bishop(color));
        board.putPiece(Position.of(File.d, rank), new Queen(color));
        board.putPiece(Position.of(File.e, rank), new King(color));
        board.putPiece(Position.of(File.f, rank), new Bishop(color));
        board.putPiece(Position.of(File.g, rank), new Knight(color));
        board.putPiece(Position.of(File.h, rank), new Rook(color));
    }

    private void initializePawn() {
        putPawn(Rank.Seven, Color.Black);
        putPawn(Rank.Two, Color.White);
    }

    private void putPawn(Rank rank, Color color) {
        List<Position> positions = Arrays.stream(File.values())
            .filter(file -> file != File.Out)
            .map(file -> Position.of(file, rank))
            .collect(Collectors.toList());

        for (Position position : positions) {
            board.putPiece(position, new Pawn(color));
        }
    }
}