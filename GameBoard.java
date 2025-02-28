// I used mergesort 
import java.awt.*;
import javax.swing.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private final JPanel[][] squares = new JPanel[SIZE][SIZE];
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

        piecesArray = new String[][] {
            {"bishop.png", "Bishop", "3"}, 
            {"bishop.png", "Bishop", "6"}, 
            {"bishop.png", "Bishop", "59"}, 
            {"bishop.png", "Bishop", "62"},
            {"king.png", "King", "5"}, 
            {"king.png", "King", "61"},
            {"knight.png", "Knight", "2"}, 
            {"knight.png", "Knight", "7"}, 
            {"knight.png", "Knight", "58"}, 
            {"knight.png", "Knight", "63"},
            {"pawn.png", "Pawn", "9"}, 
            {"pawn.png", "Pawn", "10"},
            {"pawn.png", "Pawn", "11"},
            {"pawn.png", "Pawn", "12"},
            {"pawn.png", "Pawn", "13"}, 
            {"pawn.png", "Pawn", "14"}, 
            {"pawn.png", "Pawn", "15"}, 
            {"pawn.png", "Pawn", "16"},
            {"pawn.png", "Pawn", "49"},
            {"pawn.png", "Pawn", "50"}, 
            {"pawn.png", "Pawn", "51"}, 
            {"pawn.png", "Pawn", "52"},
            {"pawn.png", "Pawn", "53"}, 
            {"pawn.png", "Pawn", "54"}, 
            {"pawn.png", "Pawn", "55"}, 
            {"pawn.png", "Pawn", "56"},
            {"queen.png", "Queen", "4"},
            {"queen.png", "Queen", "60"},
            {"rook.png", "Rook", "1"}, 
            {"rook.png", "Rook", "8"}, 
            {"rook.png", "Rook", "57"}, 
            {"rook.png", "Rook", "64"}
        };

        //print the contents of your 2D array
        //this is a requirement to show your 2D array is not sorted at the beginning of your program
        System.out.println("Unsorted array:");
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }
        mergeSort(piecesArray);

        // System.out.println("sorted array:");
        // for (int j = 0; j < piecesArray.length; j++) {
        //     for (int k = 0; k < piecesArray[j].length; k++) {
        //         System.out.println("piecesArray[" + j + "][" + k + "] = " + piecesArray[j][k]);
        //     }
        // }

        populateBoard();
    }

    private void populateBoard() {
        int pieceRow = 0; //keeps track of number of images by row should be minimum of 32
        int squareName = 1; //all squares are numbered 1-64 top left is 1, bottom right is 64

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());

                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(150, 179, 250));
                } else {
                    squares[row][col].setBackground(new Color(239, 170, 255));
                }

                add(squares[row][col]);
            }
        }

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int piecePosition = Integer.parseInt(piecesArray[pieceRow][2]);
            
                if(squareName == piecePosition){
                    String imagePath = piecesArray[pieceRow][0];  //save the file name to a string variable
                    String hpText = piecesArray[pieceRow][1]; //save the hp value to a string variable

                    ImageIcon icon = new ImageIcon(imagePath);
                    Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);

                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(hpText, SwingConstants.CENTER);
                    textLabel.setForeground(Color.BLACK); // Make text black and center bottom
                    JPanel piecePanel = new JPanel(new BorderLayout());
                    piecePanel.setOpaque(false); // false sets img bg transparent, image must already be a transparent png
                    piecePanel.add(pieceLabel, BorderLayout.CENTER);
                    piecePanel.add(textLabel, BorderLayout.SOUTH);

                    squares[row][col].setLayout(new BorderLayout());
                    squares[row][col].add(piecePanel, BorderLayout.CENTER);
                
                pieceRow++;
                }
                squareName++;
            }
        }

        revalidate(); // Ensure layout updates
        repaint(); // Refresh view
    }

    // add your merge sort method here
    // add a comment to every line of code that describes what the line is accomplishing
    // your mergeSort method does not have to return any value

    public static void mergeSort(String[][] arr) {
        int rows = arr.length; //get the number of rows in the 2D arrays 
        if (rows <= 1) { // if statements checking if it is empty of not
            return; // Already sorted or empty
        }

        int mid = rows / 2; //finds a middle point
        String[][] left = new String[mid][]; //left half after splitting 
        String[][] right = new String[rows - mid][]; //right half after splitting 

        // Divide the array
        System.arraycopy(arr, 0, left, 0, mid); //create copies of the array. copy of left 
        System.arraycopy(arr, mid, right, 0, rows - mid); //copy of rigth 

        // Recursive calls
        mergeSort(left); //sort left
        mergeSort(right); //sort right

        // Merge the sorted halves
        merge(arr, left, right); //put them back together. combinashiun. 
    }

    private static void merge(String[][] arr, String[][] left, String[][] right) {
        int i = 0, j = 0, k = 0;
        //initialize pointers for left, right, and merged arrays 

        while (i < left.length && j < right.length) { //merg the arrays while there are elements in both halves 
            if (compareRows(left[i], right[j]) <= 0) { //compare the left and right rows 
                arr[k++] = left[i++]; //if the left array <= add it to the merged array
            } else { //if its not... 
                arr[k++] = right[j++]; //if the right array is smaller, add it to the merged array 
            }
        }

        while (i < left.length) { // If there are remaining elements in the left array
            arr[k++] = left[i++]; // add them to the merged array
        }

        while (j < right.length) { //if there's stuff in right array 
            arr[k++] = right[j++]; //add it 
        }
    }

    private static int compareRows(String[] row1, String[] row2) {
        // Compare rows based on the sum of their elements
        int pos1 = Integer.parseInt(row1[2]);
        int pos2 = Integer.parseInt(row2[2]);
        return Integer.compare(pos1, pos2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
    //lambda function that creates a new board when it feels like it
}