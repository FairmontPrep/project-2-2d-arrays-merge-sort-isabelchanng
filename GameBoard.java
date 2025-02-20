import java.awt.*;
import javax.swing.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    private ImageIcon pawnIcon;
    private ImageIcon queenIcon;
    private ImageIcon rookIcon;
    private ImageIcon bishopIcon;
    private ImageIcon knightIcon;
    private ImageIcon kingIcon;
    public String[][] piecesArray;


    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        // create your 2d Array to store your image variables and assign positions
        // add your code here
        // this line of code initializes a new 2D Array of Strings the size of 1 row and 2 columns
        // your 2D array must be a minimum of 6 rows x 2 columns
        // you may add a row for every image if you'd like to have every square be a different color/image

        // piecesArray = new String[1][2];
        // piecesArray[0][0]= "pawn.png";
        // piecesArray[0][1]= "Pawn";
        // piecesArray= new String[1][2];

        piecesArray = new String[][]{
            {"pawn.png", "pawn"}, 
            {"king.png", "king"},
            {"queen.png", "queen"},
            {"rook.png", "rook"},
            {"bishop.png", "bishop"},
            {"knight.png", "knight"}
        };


        //print the contents of your 2D array
        //this is a requirement to show your 2D array is not sorted at the beginning of your program

        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        pawnIcon = new ImageIcon(piecesArray[0][0]); // Load image file
        kingIcon = new ImageIcon(piecesArray[1][0]); 
        queenIcon = new ImageIcon(piecesArray[2][0]);
        rookIcon = new ImageIcon(piecesArray[3][0]);
        bishopIcon = new ImageIcon(piecesArray[4][0]);
        knightIcon = new ImageIcon(piecesArray[5][0]);


        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());

                // creates the checkered pattern with the two colors
                // you can add more colors or take away any you'd like
            
                if (row >= 2 && row <= 5) {
                    squares[row][col].setBackground(new Color(250, 239, 145)); // yellow
                } else if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(150, 179, 250)); //blue
                } else {
                    squares[row][col].setBackground(new Color(239, 170, 255)); //purple
                }

                // this is where your sorting method will be called 
                // you will use the column 2 values to arrange your images to the board
                // be sure to sort them before you add them onto the board 
                // you will use a loop to add to your 2D Array, below is an example of how to add ONE image to ONE square
                // Adding an image to specific positions (e.g., first row)

                if (row == 1 || row == 6 && col < 8) {
                    Image scaledImage = pawnIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[0][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }

                if (row == 7 && col==4 || row==0 && col==3) {
                    Image scaledImage = kingIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[1][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }


                if ((row == 7 && col == 3) || (row == 0 && col == 4)) {
                    Image scaledImage = queenIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[2][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }
                
                if ((row == 7 && (col == 0 || col == 7)) || (row == 0 && (col == 0 || col == 7))) {
                    Image scaledImage = rookIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[3][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }
                
                if ((row == 7 && (col == 1 || col == 6)) || (row == 0 && (col == 1 || col == 6))) {
                    Image scaledImage = knightIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[5][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }
                
                if ((row == 7 && (col == 2 || col == 5)) || (row == 0 && (col == 2 || col == 5))) {
                    Image scaledImage = bishopIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[4][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }

                add(squares[row][col]);


            }
        }
    }

    // add your merge sort method here
    // add a comment to every line of code that describes what the line is accomplishing
    // your mergeSort method does not have to return any value


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
    //lambda function that creates a new board when it feels like it
}
