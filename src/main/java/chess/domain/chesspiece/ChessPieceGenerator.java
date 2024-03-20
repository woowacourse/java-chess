package chess.domain.chesspiece;

import chess.domain.chessboard.Numbering;

public class ChessPieceGenerator {

    private static final ChessPieceGenerator instance = new ChessPieceGenerator();

    private final ChessPieceCampAssignment chessPieceCampAssignment = ChessPieceCampAssignment.getInstance();

    private ChessPieceGenerator() {
    }

    public static ChessPieceGenerator getInstance() {
        return instance;
    }

    public ChessPiece generate(ChessPieceType chessPieceType, Numbering numbering) {
        Camp camp = chessPieceCampAssignment.determineCamp(numbering);
        ChessPieceProperty chessPieceProperty = createChessPieceProperty(chessPieceType);
        return new ChessPiece(camp, chessPieceProperty);
    }

    private ChessPieceProperty createChessPieceProperty(ChessPieceType chessPieceType) {
        return new ChessPieceProperty(chessPieceType, chessPieceType.getMoveStrategy());
    }
}
