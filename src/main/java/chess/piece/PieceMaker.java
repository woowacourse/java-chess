package chess.piece;

public class PieceMaker implements PieceRepository {

    private final SetPieceRule setPieceRule;

    public PieceMaker(SetPieceRule setPieceRule) {
        this.setPieceRule = setPieceRule;
    }

    @Override
    public PieceSet create(boolean isBlack) {
        return new PieceSet(isBlack);
    }
}
