package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.PieceType.*;

public final class ChessBoardFactory {
    public Map<Position, Piece> createBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        createWhiteArea(board);
        createBlackArea(board);
        return board;
    }

    private void createWhiteArea(final Map<Position, Piece> board) {
        createPieces(board, 0, TeamColor.WHITE);
        createWhitePawnPieces(board);
    }

    private void createBlackArea(final Map<Position, Piece> board) {
        createBlackPawnPieces(board);
        createPieces(board, 7, TeamColor.BLACK);
    }

    private void createPieces(final Map<Position, Piece> board, final int rank, final TeamColor teamColor) {
        board.put(Position.of(rank, 0), new Rook(ROOK, teamColor));
        board.put(Position.of(rank, 1), new Knight(KNIGHT, teamColor));
        board.put(Position.of(rank, 2), new Bishop(BISHOP, teamColor));
        board.put(Position.of(rank, 3), new Queen(QUEEN, teamColor));
        board.put(Position.of(rank, 4), new King(KING, teamColor));
        board.put(Position.of(rank, 5), new Bishop(BISHOP, teamColor));
        board.put(Position.of(rank, 6), new Knight(KNIGHT, teamColor));
        board.put(Position.of(rank, 7), new Rook(ROOK, teamColor));
    }

    private void createWhitePawnPieces(final Map<Position, Piece> board) {
        for (int file = 0; file < 8; file++) {
            board.put(Position.of(1, file), new WhitePawn(PAWN));
        }

    }

    private void createBlackPawnPieces(final Map<Position, Piece> board) {
        for (int file = 0; file < 8; file++) {
            board.put(Position.of(6, file), new BlackPawn(PAWN));
        }
    }
}
