package chess.domain.chessBoard.generator;

import chess.domain.chessBoard.Space;
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

public class ChessSpaceGenerator implements SpaceGenerator {

    private final PieceGenerator pieceGenerator;

    public ChessSpaceGenerator() {
        this(new ChessPieceGenerator());
    }

    public ChessSpaceGenerator(PieceGenerator pieceGenerator) {
        this.pieceGenerator = pieceGenerator;
    }

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
        List<Piece> pieces = new ArrayList<>(pieceGenerator.makeSpecialPieces(Color.BLACK));
        pieces.addAll(pieceGenerator.makePawnPieces(Color.BLACK));
        pieces.addAll(pieceGenerator.makeEmptyPieces());
        pieces.addAll(pieceGenerator.makeEmptyPieces());
        pieces.addAll(pieceGenerator.makeEmptyPieces());
        pieces.addAll(pieceGenerator.makeEmptyPieces());
        pieces.addAll(pieceGenerator.makePawnPieces(Color.WHITE));
        pieces.addAll(pieceGenerator.makeSpecialPieces(Color.WHITE));
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
