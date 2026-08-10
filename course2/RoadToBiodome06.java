import java.util.ArrayList;
import java.util.List;

public class RoadToBiodome06 {

    public static void main(String[] args) {
        // 예: java RoadToBiodome06 2 30 10 , 42 1 40 27 9
        if (!isValid(args)) {
            System.out.println("잘못된 입력입니다. 물 높이 값(0 이상의 정수)을 ',' 로 구분된 두 그룹으로 입력해주세요.");
            return;
        }

        List<int[]> arrayList = parseArrays(args);
        int[] lake = arrayList.get(0);   // 크기 n
        int[] river = arrayList.get(1);  // 크기 m
        int n = lake.length;
        int m = river.length;

        // 기본 과제: 전체 데이터 기준
        double mean = calculateMean(lake, river);
        double median = findMedian(lake, river, n, m);
        System.out.println("Mean : " + roundToOneDecimal(mean) + ", Median : " + roundToOneDecimal(median));

        // 보너스 과제: 30 이상인 값만 필터링 
        int[] filteredLake = filterAtLeast(lake, 30);
        int[] filteredRiver = filterAtLeast(river, 30);
        int filteredN = filteredLake.length;
        int filteredM = filteredRiver.length;

        if (filteredN + filteredM == 0) {
            System.out.println("30 이상인 물 높이 값이 없습니다.");
        } else {
            double bonusMean = calculateMean(filteredLake, filteredRiver);
            double bonusMedian = findMedian(filteredLake, filteredRiver, filteredN, filteredM);
            System.out.println("Mean(30 이상) : " + roundToOneDecimal(bonusMean)
                    + ", Median(30 이상) : " + roundToOneDecimal(bonusMedian));
        }
    }

    private static boolean isValid(String[] args) {
        if (args.length == 0) {
            return false;
        }

        int groupCount = 0;
        boolean currentGroupEmpty = true;

        for (String arg : args) {
            if (arg.equals(",")) {
                if (currentGroupEmpty) {
                    return false;
                }
                groupCount++;
                currentGroupEmpty = true;
            } else {
                try {
                    int value = Integer.parseInt(arg);
                    if (value < 0) {
                        return false; // 물 높이는 0 이상
                    }
                } catch (NumberFormatException e) {
                    return false; // 숫자가 아닌 값
                }
                currentGroupEmpty = false;
            }
        }

        if (currentGroupEmpty) {
            return false;
        }
        groupCount++;

        return groupCount == 2;
    }

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

    // 평균값 
    // 시간복잡도 O(n+m)
    private static double calculateMean(int[] a, int[] b) {
        long sum = 0;
        for (int value : a) {
            sum += value;
        }
        for (int value : b) {
            sum += value;
        }
        int totalCount = a.length + b.length;
        return (double) sum / totalCount;
    }

    // 중앙값
    // 더 작은 배열을 기준으로 이진 탐색하므로 시간복잡도 O(log(min(n,m))).
    private static double findMedian(int[] a, int[] b, int n, int m) {
        if (n > m) {
            return findMedian(b, a, m, n); // 항상 더 작은 배열(a)을 기준으로 탐색
        }

        int low = 0;
        int high = n;
        int halfLen = (n + m + 1) / 2;

        while (low <= high) {
            int partitionA = (low + high) / 2;
            int partitionB = halfLen - partitionA;

            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : a[partitionA - 1];
            int minRightA = (partitionA == n) ? Integer.MAX_VALUE : a[partitionA];

            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : b[partitionB - 1];
            int minRightB = (partitionB == m) ? Integer.MAX_VALUE : b[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                high = partitionA - 1;
            } else {
                low = partitionA + 1;
            }
        }

        throw new IllegalStateException("입력 배열이 정렬되어 있지 않습니다.");
    }

    private static int[] filterAtLeast(int[] arr, int threshold) {
        int count = 0;
        for (int value : arr) {
            if (value >= threshold) {
                count++;
            }
        }

        int[] result = new int[count];
        int idx = 0;
        for (int value : arr) {
            if (value >= threshold) {
                result[idx++] = value;
            }
        }
        return result;
    }

    private static String roundToOneDecimal(double value) {
        double rounded = Math.round(value * 10) / 10.0;
        return String.format("%.1f", rounded);
    }
}