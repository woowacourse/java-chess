package chess.domain.piece.pieces;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PiecesFactory {
	public static Pieces create() {
		return Arrays.stream(PieceInitializer.values())
				.map(Piece::new)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Pieces::new));
	}
}
