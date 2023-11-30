package chess.logic.classicGame;

import common.models.*;
import common.moves.Move;
import common.moves.DiagonalMove;
import chess.logic.moves.HorizontalMove;
import chess.logic.moves.JumpMove;
import chess.logic.moves.VerticalMove;

import java.util.ArrayList;
import java.util.List;

    public class ClassicGame {
        public Game CreateGame() {
            Player player1 = new Player();
            Player player2 = new Player();
            List<Piece> blackPieces = new ArrayList<>();
            List<Piece> whitePieces = new ArrayList<>();
            PieceFactory pieceFactory = new PieceFactory();

            createWhitePawns(whitePieces, pieceFactory);
            createWhiteRooks(whitePieces, pieceFactory);
            createWhiteBishops(whitePieces, pieceFactory);
            createWhiteKnight(whitePieces, pieceFactory);
            createWhiteKingAndQueen(whitePieces, pieceFactory);

            createBlackPawns(blackPieces, pieceFactory);
            createBlackRooks(blackPieces, pieceFactory);
            createBlackBishops(blackPieces, pieceFactory);
            createBlackKnight(blackPieces, pieceFactory);
            CreateBlackQueenAndKing(blackPieces, pieceFactory);

            Board board = new Board(8, 8, blackPieces, whitePieces, pieceFactory);
            return new Game(player1, player2, board,SideColor.White, new ClassicWinCondition(), new ChessLegalMove());
        }

        private static void CreateBlackQueenAndKing(List<Piece> blackPieces, PieceFactory pieceFactory) {
            blackPieces.add(pieceFactory.clonePiece("queen", new Coordinate(4, 8), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("king", new Coordinate(5, 8), SideColor.Black));
        }

        private static void createBlackKnight(List<Piece> blackPieces, PieceFactory pieceFactory) {
            blackPieces.add(pieceFactory.clonePiece("knight", new Coordinate(2, 8), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("knight", new Coordinate(7, 8), SideColor.Black));
        }

        private static void createBlackBishops(List<Piece> blackPieces, PieceFactory pieceFactory) {
            blackPieces.add(pieceFactory.clonePiece("bishop", new Coordinate(3, 8), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("bishop", new Coordinate(6, 8), SideColor.Black));
        }

        private static void createBlackRooks(List<Piece> blackPieces, PieceFactory pieceFactory) {
            blackPieces.add(pieceFactory.clonePiece("rook", new Coordinate(1, 8), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("rook", new Coordinate(8, 8), SideColor.Black));
        }

        private static void createBlackPawns(List<Piece> blackPieces, PieceFactory pieceFactory) {
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(1, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(2, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(3, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(4, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(5, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(6, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(7, 7), SideColor.Black));
            blackPieces.add(pieceFactory.clonePiece("pawn", new Coordinate(8, 7), SideColor.Black));
        }

        private void createWhiteKingAndQueen(List<Piece> whitePieces, PieceFactory pieceFactory) {
            List<Move> kingMovements = new ArrayList<>();
            kingMovements.add(new VerticalMove(1, true));
            kingMovements.add(new HorizontalMove(1));
            kingMovements.add(new DiagonalMove(1, 1,true));
            kingMovements.add(new DiagonalMove(1, -1,true));

            List<Move> queenMovements = new ArrayList<>();
            queenMovements.add(new VerticalMove(true));
            queenMovements.add(new HorizontalMove());
            queenMovements.add(new DiagonalMove());

            whitePieces.add(pieceFactory.createPiece("queen", new Coordinate(4, 1), queenMovements, false, SideColor.White));
            whitePieces.add(pieceFactory.createPiece("king", new Coordinate(5, 1), kingMovements, true, SideColor.White));
        }

        private void createWhiteKnight(List<Piece> whitePieces, PieceFactory pieceFactory) {

            List<Move> knightMovements = new ArrayList<>();
            knightMovements.add(new JumpMove(2, 1));
            knightMovements.add(new JumpMove(2, -1));
            knightMovements.add(new JumpMove(-2, 1));
            knightMovements.add(new JumpMove(-2, -1));
            knightMovements.add(new JumpMove(1, 2));
            knightMovements.add(new JumpMove(1, -2));
            knightMovements.add(new JumpMove(-1, 2));
            knightMovements.add(new JumpMove(-1, -2));

            whitePieces.add(pieceFactory.createPiece("knight", new Coordinate(2, 1), knightMovements, false, SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("knight", new Coordinate(7, 1), SideColor.White));
        }

        private void createWhiteBishops(List<Piece> whitePieces, PieceFactory pieceFactory) {
            List<Move> bishopMovements = new ArrayList<>();
            bishopMovements.add(new DiagonalMove());

            whitePieces.add(pieceFactory.createPiece("bishop", new Coordinate(3, 1), bishopMovements, false, SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("bishop", new Coordinate(6, 1), SideColor.White));
        }

        private void createWhiteRooks(List<Piece> whitePieces, PieceFactory pieceFactory) {
            List<Move> rookMovements = new ArrayList<>();
            rookMovements.add(new VerticalMove(true));
            rookMovements.add(new HorizontalMove());

            whitePieces.add(pieceFactory.createPiece("rook", new Coordinate(1, 1), rookMovements, false, SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("rook", new Coordinate(8, 1), SideColor.White));
        }

        private void createWhitePawns(List<Piece> whitePieces, PieceFactory pieceFactory) {
            List<Move> pawnMovements = new ArrayList<>();
            pawnMovements.add(new VerticalMove(1, false));
            pawnMovements.add(new VerticalMove(2, false));

            List<Move> pawnEatMoves = new ArrayList<>();
            pawnEatMoves.add(new DiagonalMove(1, -1,false));
            pawnEatMoves.add(new DiagonalMove(1, 1,false));

            whitePieces.add(pieceFactory.createPiece("pawn", new Coordinate(1, 2), pawnMovements, pawnEatMoves, false, SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(2, 2), SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(3, 2), SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(4, 2), SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(5, 2), SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(6, 2), SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(7, 2), SideColor.White));
            whitePieces.add(pieceFactory.clonePiece("pawn", new Coordinate(8, 2), SideColor.White));
        }
    }

