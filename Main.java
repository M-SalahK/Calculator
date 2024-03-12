import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        System.out.println(calc(inputLine));
    }

    private static String calc (String inputLine) {
        int operandIndex = detectIndex(inputLine);
        String[] split = arraySplit(inputLine);
        int[] operandArray;
        if (Convert.isRoman(split[0]) && Convert.isRoman(split[1])) {
            operandArray = parseToRoman(split);
            return Convert.convertArabianToRoman(result(operandArray, operandIndex));
        }
        operandArray = parseInt(split);
        return String.valueOf(result(operandArray, operandIndex));
    }

    private static int detectIndex(String inputLine) {
        String[] operands = {"+","-","/","*"};
        int index;
        for (int i = 0; i < operands.length; i++) {
            if (inputLine.contains(operands[i])) {
                index = i;
                return index;
            }
        } return 0;
    }


    private static String[] arraySplit(String inputLine){
        String[] regexActions = new String[]{"\\+","-","/","\\*"};
        String[] split = inputLine.split(regexActions[detectIndex(inputLine)]);
    return split;
    }


    private static int[] parseInt(String[] inputLine){
        int[] intArray = new int[2];
        if (Convert.isRoman(inputLine[0]) != Convert.isRoman(inputLine[1])) throw new RuntimeException("Неверный формат");
        if (inputLine.length != 2) throw new RuntimeException("Больше или меньше двух операндов");
        if (!Convert.isRoman(inputLine[0]) && !Convert.isRoman(inputLine[1])){
            intArray[0] = Integer.parseInt(inputLine[0].trim());
            intArray[1] = Integer.parseInt(inputLine[1].trim());
            if (intArray[0] > 10 || intArray[1] > 10) throw new RuntimeException("Число больше 10");
        }
        return intArray;
    }
    private static int[] parseToRoman(String[] inputLine){
        int[] intArray = new int[2];
        if (inputLine.length !=2) throw new RuntimeException("Больше двух операндов");
        if (Convert.isRoman(inputLine[0]) && Convert.isRoman(inputLine[1])) {
            intArray[0] = Convert.convertToArabian(inputLine[0].trim());
            intArray[1] = Convert.convertToArabian(inputLine[1].trim());
            if (intArray[0] > 10 || intArray[1] > 10) throw new RuntimeException("Число больше 10");
        }
        return intArray;
    }


    private static int result(int[] intArray, int index) {
        int result;
        String[] operands = {"+","-","/","*"};
        switch (operands[index]) {
            case "+":
                result = intArray[0] + intArray[1];
                break;
            case "-":
                result = intArray[0] - intArray[1];
                break;
            case "*":
                result = intArray[0] * intArray[1];
                break;
            default:
                result = intArray[0] / intArray[1];
        }
        return result;
    }

}