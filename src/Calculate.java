import java.util.Scanner;


public class Calculate {
    public static void main(String[] args) {
        Convert convert = new Convert();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exception = scanner.nextLine();

        //Определение арифметического действия
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exception.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        // Если нет арифметического действия завершаем работу программы
        if (actionIndex == -1) {
            System.out.println("Вы ввели неправильное выражение");
        }
        String[] data = exception.split(regexActions[actionIndex]);
        //Определяем, одинаковые ли числа
        if (convert.isCalc(data[0]) == convert.isCalc(data[1])) {
            int a, b;
            //Определяем римское ли это число, если римское конвертируем в арабское
            boolean isCalc = convert.isCalc(data[0]);
            if (isCalc) {
                a = convert.rimToInt(data[0]);
                b = convert.rimToInt(data[1]);
            } else {
                //Если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            //Выполняем с числами арифметические операции

            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            if (isCalc) {
                //Если числа были римские, возвращаем результат в римском числе
                System.out.println(convert.intToRim(result));

            } else {
                //Если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}