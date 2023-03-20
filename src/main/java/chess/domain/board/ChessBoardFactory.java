package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.domain.piece.strategy.BishopMovementStrategy;
import chess.domain.piece.strategy.KingMovementStrategy;
import chess.domain.piece.strategy.KnightMovementStrategy;
import chess.domain.piece.strategy.QueenMovementStrategy;
import chess.domain.piece.strategy.RookMovementStrategy;
import chess.domain.piece.strategy.pawn.BlackPawnMoveConstraint;
import chess.domain.piece.strategy.pawn.PawnMovementStrategy;
import chess.domain.piece.strategy.pawn.VerticalTwoMoveAsRankConstraint;
import chess.domain.piece.strategy.pawn.WhitePawnMoveConstraint;

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
                .mapToObj(file -> new Piece(color, PiecePosition.of(rank, (char) file), byColor(color, rank)))
                .collect(Collectors.toList());
    }

    private PawnMovementStrategy byColor(final Color color, final int rank) {
        if (color.isWhite()) {
            return new PawnMovementStrategy(new WhitePawnMoveConstraint(), new VerticalTwoMoveAsRankConstraint(Rank.from(rank)));
        }

        return new PawnMovementStrategy(new BlackPawnMoveConstraint(), new VerticalTwoMoveAsRankConstraint(Rank.from(rank)));
    }

    private List<Piece> createExcludePawn(final int rank, final Color color) {
        List<Piece> pieces = new ArrayList<>(8);
        pieces.add(new Piece(color, PiecePosition.of(rank, 'a'), new RookMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'b'), new KnightMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'c'), new BishopMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'd'), new QueenMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'e'), new KingMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'f'), new BishopMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'g'), new KnightMovementStrategy()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'h'), new RookMovementStrategy()));
        return pieces;
    }
}
