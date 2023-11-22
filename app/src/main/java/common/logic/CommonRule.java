package common.logic;


import common.models.Coordinate;
import common.models.Board;
import common.models.Piece;

import java.util.Objects;

public class CommonRule {
    public static Boolean checkRule(Board board, Piece piece, Coordinate toSquare) {
        if (isInBoard(board, toSquare)) {
            return false;
        }
        if (isNotSamePlace(board, piece, toSquare)){
            return false;
        }
        return isNotNull(board, toSquare) || isNotSameColor(board, piece, toSquare);
    }

    private static boolean isNotSameColor(Board board, Piece piece, Coordinate toSquare) {
        return !Objects.equals(board.getSquare(toSquare).getPiece().getColor(), piece.getColor());
    }

    private static boolean isNotNull(Board board, Coordinate toSquare) {
        return Objects.equals(board.getSquare(toSquare).getPiece().getName(), "null");
    }

    private static boolean isNotSamePlace(Board board, Piece piece, Coordinate toSquare) {
        return toSquare.column() == board.getSquareOfPiece(piece).successfulResult().get().column() && toSquare.row() == board.getSquareOfPiece(piece).successfulResult().get().row();
    }

    private static boolean isInBoard(Board board, Coordinate toSquare) {
        return toSquare.column() > board.getColumns() || toSquare.row() > board.getRows() || toSquare.column() <= 0 || toSquare.row() <= 0;
    }

}
