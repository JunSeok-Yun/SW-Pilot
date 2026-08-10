import java.util.ArrayList;
import java.util.List;

public class RoadToBiodome05 {
	public static void main(String[] args) {
		// 예: java RoadToBiodome05 11 69 41 3 10 65 7 8 , 30 5 79 57 1 13 28 88 18 24
		if (!isValid(args)) {
			System.out.println("잘못된 입력입니다. 물 높이 값(0 이상의 정수)을 ',' 로 구분된 두 그룹으로 입력해주세요.");
			return;
		}

		List<int[]> arrayList = parseArrays(args);
		int[] first = arrayList.get(0);
		int[] second = arrayList.get(1);
		int[] merged = mergeArrays(first, second);

		quickSort(merged, 0, merged.length - 1);

		System.out.println(toResultString(merged));
	}

    // 퀵 정렬 (오름차순)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 입력값 유효성 검사
    private static boolean isValid(String[] args) {
        if (args.length == 0) {
            return false;
        }

        int arrayCount = 0;
        boolean currentGroupEmpty = true;

        for (String arg : args) {
            if (arg.equals(",")) { 
                if (currentGroupEmpty) { // 입력값중에 빈 값이 있는지 판별
                    return false; 
                }
                arrayCount++;
                currentGroupEmpty = true;
            } else {
                try {
                    int value = Integer.parseInt(arg);
                    if (value < 0) { //// 물 높이는 0 이상인지 판별
                        return false; 
                    }
                } catch (NumberFormatException e) {
                    return false; // 숫자가 아닌 값
                }
                currentGroupEmpty = false;
            }
        }

		// 입력된 배열이 정상적인 입력인지. 즉 2개의 배열을 입력받았는지 판별
        if (currentGroupEmpty) {
            return false; 
        }
        arrayCount++;

        return arrayCount == 2;
    }

    // ',' 를 구분자로 두 개의 배열로 파싱
    private static List<int[]> parseArrays(String[] args) {
        List<int[]> arrayList = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        for (String arg : args) {
            if (arg.equals(",")) {
                arrayList.add(toIntArray(current));
                current.clear();
            } else {
                current.add(Integer.parseInt(arg));
            }
        }
        arrayList.add(toIntArray(current));

        return arrayList;
    }

    private static int[] toIntArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static int[] mergeArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        System.arraycopy(a, 0, merged, 0, a.length);
        System.arraycopy(b, 0, merged, a.length, b.length);
        return merged;
    }

    private static String toResultString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}