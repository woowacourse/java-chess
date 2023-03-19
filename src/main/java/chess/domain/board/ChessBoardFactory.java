package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.BishopMoveStrategy;
import chess.domain.piece.strategy.KingMoveStrategy;
import chess.domain.piece.strategy.KnightMoveStrategy;
import chess.domain.piece.strategy.QueenMoveStrategy;
import chess.domain.piece.strategy.RookMoveStrategy;
import chess.domain.piece.strategy.pawn.BlackPawnMoveStrategy;
import chess.domain.piece.strategy.pawn.PawnMoveStrategy;
import chess.domain.piece.strategy.pawn.WhitePawnMoveStrategy;
import chess.domain.piece.type.pawn.Pawn;
import chess.domain.piece.type.pawn.state.InitialPawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardFactory {

    private static final List<Piece> pieces = new ArrayList<>();

    static {
        createExcludePawn(1, Color.WHITE);
        createExcludePawn(8, Color.BLACK);
        createPawns(2, Color.WHITE);
        createPawns(7, Color.BLACK);
    }

    private static void createPawns(final int rank, final Color color) {
        for (char file = File.MIN; file <= File.MAX; file++) {
            pieces.add(new Pawn(color, PiecePosition.of(rank, file), byColor(color), new InitialPawn()));
        }
    }

    private static PawnMoveStrategy byColor(final Color color) {
        if (color.isWhite()) {
            return new WhitePawnMoveStrategy();
        }
        return new BlackPawnMoveStrategy();
    }

    private static void createExcludePawn(final int rank, final Color color) {
        pieces.add(new Piece(color, PiecePosition.of(rank, 'a'), new RookMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'b'), new KnightMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'c'), new BishopMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'd'), new QueenMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'e'), new KingMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'f'), new BishopMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'g'), new KnightMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'h'), new RookMoveStrategy()));
    }

    public static ChessBoard create() {
        return ChessBoard.from(pieces.stream()
                .map(Piece::clone)
                .collect(Collectors.toList()));
    }
}
