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
import java.util.stream.IntStream;

public class ChessBoardFactory {

    public ChessBoard create() {
        List<Piece> pieces = new ArrayList<>(32);
        pieces.addAll(createPawns(2, Color.WHITE));
        pieces.addAll(createPawns(7, Color.BLACK));
        pieces.addAll(createExcludePawn(1, Color.WHITE));
        pieces.addAll(createExcludePawn(8, Color.BLACK));
        return ChessBoard.from(pieces);
    }

    private List<Piece> createPawns(final int rank, final Color color) {
        return IntStream.rangeClosed(File.MIN, File.MAX)
                .mapToObj(file -> new Pawn(color, PiecePosition.of(rank, (char) file), byColor(color), new InitialPawn()))
                .collect(Collectors.toList());
    }

    private PawnMoveStrategy byColor(final Color color) {
        if (color.isWhite()) {
            return new WhitePawnMoveStrategy();
        }
        return new BlackPawnMoveStrategy();
    }

    private List<Piece> createExcludePawn(final int rank, final Color color) {
        List<Piece> pieces = new ArrayList<>(8);
        pieces.add(new Piece(color, PiecePosition.of(rank, 'a'), new RookMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'b'), new KnightMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'c'), new BishopMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'd'), new QueenMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'e'), new KingMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'f'), new BishopMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'g'), new KnightMoveStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'h'), new RookMoveStrategy()));
        return pieces;
    }
}
