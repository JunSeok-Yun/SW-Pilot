import java.util.ArrayList;
import java.util.List;

public class RoadToBiodome04 {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("에너지 소비 값을 입력해주세요.");
			return;
		}

		// ',' 를 구분자로 사용해 여러 배열을 분리
		// 예: java RoadToBiodome04 5 3 8 9 4 , 7 2 10 6
		List<int[]> arrayList = new ArrayList<>();
		List<Integer> current = new ArrayList<>();

		for (String arg : args) {
			if (arg.equals(",")) {
				if (!current.isEmpty()) {
					arrayList.add(toIntArray(current));
					current.clear();
				}
			} else {
				current.add(Integer.parseInt(arg));
			}
		}
		if (!current.isEmpty()) {
			arrayList.add(toIntArray(current));
		}

		if (arrayList.size() == 1) { // 기본 과제 출력
			int[] energyData = arrayList.get(0);
			selectionSort(energyData);
			double average = getAverage(energyData);
			double median = getMedian(energyData);
			System.out.println("평균값 : " + formatNumber(average) + ", 중앙값 : " + formatNumber(median));
		} else { // 보너스 과제 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arrayList.size(); i++) {
				int[] energyData = arrayList.get(i);
				selectionSort(energyData);
				double median = getMedian(energyData);
				sb.append(formatNumber(median));
				if (i != arrayList.size() - 1) {
					sb.append(", ");
				}
			}
			System.out.println(sb.toString());
		}
	}

    // 선택 정렬 (오름차순)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    // 평균값 계산
    public static double getAverage(int[] arr) {
        int sum = 0;
        for (int value : arr) {
            sum += value;
        }
        return (double) sum / arr.length;
    }

    // 중앙값 계산 (정렬된 배열 기준, 짝수/홀수 분기)
    public static double getMedian(int[] sortedArr) {
        int n = sortedArr.length;
        int mid = n / 2;
        if (n % 2 == 0) {
            return (sortedArr[mid - 1] + sortedArr[mid]) / 2.0;
        } else {
            return sortedArr[mid];
        }
    }


    private static int[] toIntArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // 정수면 소수점 없이, 아니면 그대로 출력
    private static String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}