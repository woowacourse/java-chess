package chess.domain.chesspiece.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
class RookMoveStrategyTest {

    private final ChessBoard chessBoard = new ChessBoard();
    private ChessPiece chessPiece;
    private final Camp camp = Camp.WHITE;
    private final ChessPieceProperty chessPieceProperty =
            new ChessPieceProperty(ChessPieceType.ROOK, new RookMoveStrategy());

    @BeforeEach
    void setUp() {
        chessBoard.movePiece(new Square(Lettering.A, Numbering.TWO), new Square(Lettering.B, Numbering.THREE));
        chessPiece = new ChessPiece(camp, chessPieceProperty);
    }

    @Test
    void 목적지가_Rook이_움직일_수_있는_범위이면_움직인다() {
        chessPiece.move(chessBoard, new Square(Lettering.A, Numbering.ONE), new Square(Lettering.A, Numbering.SIX));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.A, Numbering.SIX));

        assertThat(destinationChessPiece.getChessPieceType()).isEqualTo(ChessPieceType.ROOK);
    }

    @Test
    void 목적지가_Rook이_움직일_수_있는_범위가_아니면_움직이지_않는다() {
        chessPiece.move(chessBoard, new Square(Lettering.A, Numbering.ONE), new Square(Lettering.B, Numbering.SIX));
        ChessPiece destinationChessPiece = chessBoard.findChessPieceOnSquare(new Square(Lettering.B, Numbering.SIX));

        assertThat(destinationChessPiece.getChessPieceType()).isNotEqualTo(ChessPieceType.ROOK);
    }

}
