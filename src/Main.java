import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main
{
// to test copy this: the quick the brown fox and the white dog jumps over the lazy dog mother father zulu tango foxtrot baby

    public static void main(String[] args) throws IOException
    {
        String inputString;// строка для текста с конвейера

        if (args.length == 0)
        {
            InputStream in = System.in; // забираем текст с конвейера
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            inputString = bufferedReader.readLine(); // получаем строку из потока ввода

            if (inputString.isEmpty())
                System.out.println("-----------------------------\nОтстутствует текст для поиска\n-----------------------------");
            else
                topTenWordsShow(inputString.trim().split("\\s+"));// выполняем поиск по тексту из строки
        }
        else
            topTenWordsShow(args);// выполняем поиск по словам из параметров запуска
    }

    private static void topTenWordsShow(String[] words)
    {
        HashMap<String, Integer> resultList = new HashMap<>(); // создаем список (ключ(слово) - значение(количество повторений))
        for (String word : words)
        {
            if (!resultList.containsKey(word))
                resultList.put(word, 1);                            // добавляем если новое
            else resultList.put(word, resultList.get(word) + 1);    // считаем повторение
        }
        resultList.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed() // сортировка по совпадениям
                .thenComparing(Map.Entry::getKey))                                // потом по алфавиту
                .limit(10).                                                       // top10
                forEach((entry) -> System.out.printf("%-10s- %d%n", entry.getKey(), entry.getValue())); // вывод результата
    }
}
