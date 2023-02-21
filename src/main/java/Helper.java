import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Helper {
    private static final Logger logger = Logger.getLogger(Helper.class.getName());
    public static int inputIntBetween(int left, int right) {
        int result;
        try {
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextInt();
            if (result < left || result > right) {
                System.out.println("Недопустимый ввод, повторите попытку.\n Введите число между " + left + " и " + right);
                result = inputIntBetween(left, right);
            }
        } catch (InputMismatchException exception) {
            System.out.println("Введите число.");
            logger.log(Level.SEVERE, "Несоответствие типов.");
            result = inputIntBetween(left, right);
        }
        return result;
    }
 }

