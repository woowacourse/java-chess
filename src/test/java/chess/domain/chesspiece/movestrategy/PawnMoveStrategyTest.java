package chess.domain.chesspiece.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceProperty;
import chess.domain.chesspiece.ChessPieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnMoveStrategyTest {

    private final ChessBoard chessBoard = new ChessBoard();
    private ChessPiece chessPiece;
    private final Camp camp = Camp.WHITE;
    private final ChessPieceProperty chessPieceProperty =
            new ChessPieceProperty(ChessPieceType.PAWN, new PawnMoveStrategy());

    @BeforeEach
    void setUp() {
        chessPiece = new ChessPiece(camp, chessPieceProperty);
    }

    @Test
    void 목적지가_Pawn이_움직일_수_있는_범위이면_움직인다() {
        chessPiece.move(chessBoard, new Square(Lettering.E, Numbering.TWO), new Square(Lettering.E, Numbering.FOUR));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.E, Numbering.FOUR));

        assertThat(destinationChessPiece.getChessPieceType()).isEqualTo(ChessPieceType.PAWN);
    }

    @Test
    void Pawn이_대각선으로_이동할_경우에_목적지에_다른_진영의_체스_말이_있어야_한다() {
        chessBoard.movePiece(new Square(Lettering.F, Numbering.SEVEN), new Square(Lettering.F, Numbering.THREE));
        chessPiece.move(chessBoard, new Square(Lettering.E, Numbering.TWO), new Square(Lettering.F, Numbering.THREE));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.F, Numbering.THREE));

        assertThat(destinationChessPiece.getChessPieceType()).isEqualTo(ChessPieceType.PAWN);
    }

    @Test
    void Pawn이_처음_움직이는게_아닌_경우_두_칸을_전진할_수_없다() {
        chessPiece.move(chessBoard, new Square(Lettering.E, Numbering.TWO), new Square(Lettering.E, Numbering.FOUR));
        chessPiece.move(chessBoard, new Square(Lettering.E, Numbering.FOUR), new Square(Lettering.E, Numbering.SIX));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.E, Numbering.SIX));

        assertThat(destinationChessPiece.getChessPieceType()).isNotEqualTo(ChessPieceType.PAWN);
    }

    @Test
    void 목적지가_Pawn이_움직일_수_있는_범위가_아니면_움직이지_않는다() {
        chessPiece.move(chessBoard, new Square(Lettering.E, Numbering.TWO), new Square(Lettering.E, Numbering.FIVE));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.E, Numbering.FIVE));

        assertThat(destinationChessPiece.getChessPieceType()).isNotEqualTo(ChessPieceType.PAWN);
    }
}
