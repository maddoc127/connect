import java.util.Scanner;

public class connect_four {

public static  int row = 6;
public static  int column = 7;
public static int next_move = 0;
public static  char empty = '.';
public static  char PLAYER_ONE = 'R';
public static  char PLAYER_TWO = 'Y';
public static char board[][] = new char[row][column];
public static Scanner input = new Scanner(System.in);
public static boolean gameWon = false;
    
public static void printboard(char board[][]){
    for(int i = 0; i < row; i++){
        for(int j = 0; j < column; j++){
            System.out.print(board[i][j] + " "); 
}
System.out.println(""); 
}
System.out.println("0 1 2 3 4 5 6");
}

public static void setup_board(char board[][]){
    for(int i = 0; i < row; i++){
        for(int j = 0; j < column; j++){
            board[i][j]= empty;
        }
    }

}

public static int dropDisc(char player, char board[][]){
    next_move = input.nextInt();
    if(next_move > board.length){
        System.out.println("oyun alanı içinde bir sayı seçin");
        return -1;
    }
    for (int i = row - 1; i >= 0; i--) {

        if (board[i][next_move] == empty) {
            connect_four.board[i][next_move] = player;
            return i;
        }
    }
    System.out.println("Boş bir kolon seçin");
    return -1;
}

public static boolean isWon(char[][] board){
    char temp_memo = empty;
    int temp_count = 0;
    for(int i = 0; i < row; i++){
        temp_memo = board[i][0];
        for(int j = 1; j < column ; j++){
            if(temp_memo == board[i][j] && board[i][j] != empty){
                temp_count += 1;
            }
            else{
                temp_count = 0;
            }
            temp_memo = board[i][j];
            if(temp_count == 3){
                return true;
            }
        }
    }
    temp_count= 0;
    for(int i = 0; i < column; i++){
        temp_memo = board[0][i];
        for(int j = 1; j < row ; j++){
            if(temp_memo == board[j][i] && board[j][i] != empty){
                temp_count += 1;
            }
            else{
                temp_count = 0;
            }
            temp_memo = board[j][i];
            if(temp_count == 3){
                return true;
            }
        }
    }
    temp_count = 0;

    for(int j = 0; j <3 ; j++){
    temp_memo = board[j][0];
    for(int i = 1; i < row-j; i++){
        if(temp_memo == board[i+j][i] && board[i+j][i] != empty){
            temp_count += 1;
        }
        else{
            temp_count = 0;
        }
        temp_memo = board[i+j][i];
        if(temp_count == 3){
            return true;
        }  
    }
    temp_count = 0;
    temp_memo = board[0][j+1];
    for(int i = 1; i < row-j; i++){
        if(temp_memo == board[i][i+j] && board[i][i+j] != empty){
            temp_count += 1;
        }
        else{
            temp_count = 0;
        }
        temp_memo = board[i][i+j];
        if(temp_count == 3){
            return true;
        }  
    }
}

for (int j = 0; j < 4; j++) {
    temp_memo = board[0][6 - j];
    for (int i = 1; i < row - j; i++) {
        if (temp_memo == board[i][6 - (j + i)] && board[i][6 - (j + i)] != empty) {
            temp_count += 1;
        } else {
            temp_count = 0;
        }
        temp_memo = board[i][6 - (j + i)];
        if (temp_count == 3) {
            return true;
        }
    }
    temp_count = 0;
    temp_memo = board[j + 1][6];
    for (int i = 1; i < row - j; i++) {
        if (temp_memo == board[i + j][6 - i] && board[i + j][6 - i] != empty) {
            temp_count += 1;
        } else {
            temp_count = 0;
        }
        temp_memo = board[i + j][6 - i];
        if (temp_count == 3) {
            return true;
        }
    }
}

    return false;
}

public static boolean isDraw(char[][] board){
    for(int i = 0; i < row; i++){
        for(int j = 0; j < column; j++){
            if(board[i][j] == empty){
                return false;
            }
        }
    }
    return true;
}

public static void game_loop(){
    char player = PLAYER_ONE;
    int x = 0;
    printboard(board);
   while(!gameWon){
    System.out.println("hamle sırası: " + player);
    x = dropDisc(player,board);
    printboard(board);
    if (x != -1){
    if (isWon(board)){
        System.out.println(player +" Kazandı");
        gameWon = true;
    }
    if (isDraw(board)){
        System.out.println("Beraberlik");
        gameWon = true;
    }
    player = player == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE ;
}

   }
}

    public static void main(String[] args){
    setup_board(board);
    game_loop();
}
}