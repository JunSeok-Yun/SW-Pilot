import java.util.ArrayList;
import java.util.List;

public class RoadToBiodome07 {

    public static void main(String[] args) {
        if (!isValid(args)) {
            System.out.println("잘못된 입력입니다. 숫자값 없이 동물 이름을 하나 이상 입력해주세요.");
            return;
        }

        String[] uniqueNames = extractUniqueNames(args);
        int[] frequencies = new int[uniqueNames.length];
        for (int i = 0; i < uniqueNames.length; i++) {
            frequencies[i] = countFrequency(args, uniqueNames[i]);
        }

        bubbleSort(uniqueNames, frequencies);

        System.out.println(toResultString(uniqueNames));
    }

    private static boolean isValid(String[] args) {
        if (args.length == 0) {
            return false;
        }
        for (String arg : args) {
            if (isNumeric(arg)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true; // 예외 없이 변환되면 숫자값
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String[] extractUniqueNames(String[] args) {
        List<String> unique = new ArrayList<>();
        for (String name : args) {
            if (!contains(unique, name)) {
                unique.add(name);
            }
        }
        return unique.toArray(new String[0]);
    }

    private static boolean contains(List<String> list, String target) {
        for (String value : list) {
            if (value.equals(target)) {
                return true;
            }
        }
        return false;
    }
    private static int countFrequency(String[] data, String target) {
        int count = 0;
        for (String value : data) {
            if (value.equals(target)) {
                count++;
            }
        }
        return count;
    }

    // 버블 정렬
    // 빈도수 내림차순, 빈도수가 같으면 compareTo()로 가나다순(오름차순) 정렬
    private static void bubbleSort(String[] names, int[] frequencies) {
        int n = names.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (shouldSwap(names, frequencies, j)) {
                    swap(names, frequencies, j, j + 1);
                }
            }
        }
    }

    private static boolean shouldSwap(String[] names, int[] frequencies, int j) {
        if (frequencies[j] != frequencies[j + 1]) {
            return frequencies[j] < frequencies[j + 1]; // 빈도수가 낮은 쪽이 뒤로
        }
        return names[j].compareTo(names[j + 1]) > 0; // 빈도수가 같으면 가나다순
    }

    private static void swap(String[] names, int[] frequencies, int i, int j) {
        String tempName = names[i];
        names[i] = names[j];
        names[j] = tempName;

        int tempFreq = frequencies[i];
        frequencies[i] = frequencies[j];
        frequencies[j] = tempFreq;
    }

    private static String toResultString(String[] names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]);
            if (i != names.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}