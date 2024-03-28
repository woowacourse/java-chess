package chess.domain.chessGame;

import chess.domain.chessGame.generator.ChessPieceGenerator;
import chess.domain.chessGame.generator.PieceGenerator;
import chess.domain.chessGame.generator.SpaceGenerator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class StickSpaceGenerator implements SpaceGenerator {
    private static final PieceGenerator pieceGenerator = new ChessPieceGenerator();

    @Override
    public List<Space> generateSpaces() {
        List<Space> spaces = new ArrayList<>();
        Iterator<Piece> pieceIterator = makeAllPieces().iterator();

        for (Rank rank : backwardRanks()) {
            spaces.addAll(makeLineSpaces(rank, pieceIterator));
        }
        return spaces;
    }

    private List<Piece> makeAllPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.addAll(pieceGenerator.makeSpecialPieces(Color.BLACK));

        pieces.addAll(pieceGenerator.makeSpecialPieces(Color.WHITE));

        pieces.addAll(pieceGenerator.makeEmptyPieces(48));

        return pieces;
    }

    private List<Rank> backwardRanks() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        return ranks;
    }

    private List<Space> makeLineSpaces(Rank rank, Iterator<Piece> pieceIterator) {
        List<Space> rankSpaces = new ArrayList<>();
        for (File file : File.values()) {
            rankSpaces.add(new Space(pieceIterator.next(), new Position(file, rank)));
        }
        return rankSpaces;
    }
}
