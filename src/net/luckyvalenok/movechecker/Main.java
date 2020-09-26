package net.luckyvalenok.movechecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    
    private static final Pattern PATTERN = Pattern.compile("^[ABCDEFGH][12345678]$");
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите тип фигуры: \n" +
            "1.Король\n" +
            "2.Ферзь\n" +
            "3.Ладья\n" +
            "4.Слон\n" +
            "5.Конь\n" +
            "6.Пешка");
        int type;
        while (true) {
            try {
                type = Integer.parseInt(reader.readLine());
                if (type > 0 && type < 7) {
                    break;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели неверно тип фигуры. Введите число в диапазоне от 1 до 6.");
            }
        }
        String coord1 = readCoord(reader, "Вы ввели неверно координаты начала. Попробуйте снова");
        String coord2 = readCoord(reader, "Вы ввели неверно координаты конца. Попробуйте снова");
        if (moveFigures(type, coord1, coord2)) {
            System.out.println("Такой ход возможен.");
        } else {
            System.out.println("Такой ход невозможен.");
        }
    }
    
    private static boolean moveFigures(int type, String coord1, String coord2) {
        char startColumn = coord1.charAt(0);
        char endColumn = coord2.charAt(0);
        char startRow = coord1.charAt(1);
        char endRow = coord2.charAt(1);
        int absRow = Math.abs(startRow - endRow);
        int absColumn = Math.abs(startColumn - endColumn);
        switch (type) {
            case 1: // Король
                return absColumn == 1 && absRow < 2;
            case 2: // Ферзь
                return startColumn == endColumn || startRow == endRow || absColumn == absRow;
            case 3: // Ладья
                return startColumn == endColumn || startRow == endRow;
            case 4: // Слон
                return absColumn == absRow;
            case 5: // Конь
                return absColumn == 2 && absRow == 1 || absColumn == 1 && absRow == 2;
            case 6: // Пешка
                return startColumn == endColumn && absRow < 2;
            default:
                return false;
        }
    }
    
    private static String readCoord(BufferedReader reader, String msg) throws IOException {
        String coord = reader.readLine().toUpperCase();
        while (!PATTERN.matcher(coord).matches()) {
            System.out.println(msg);
            coord = reader.readLine().toUpperCase();
        }
        return coord;
    }
}
