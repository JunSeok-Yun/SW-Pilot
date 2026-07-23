import java.util.*;
public class RoadToBiodome01{
	public static void main(String[] args) {
		int[] arr = new int[1001];

		if (args.length == 0){
			System.out.println("-> 입력된 값이 없습니다. 0에서 1000까지의 값을 입력해주세요.");
			return;
		}

		ArrayList<Integer> list = new ArrayList<>();
		try{
			for (int i = 0 ; i < args.length; i++){
				int num = Integer.parseInt(args[i]);
				if (num < 0 || num > 1000){
					System.out.println("-> 입력된 값의 범위가 올바르지 않습니다. 0에서 1000까지의 값을 입력해주세요.");
					return;
				}
				list.add(num);
				arr[num] += 1;
			}

			int result = findNumber(arr);
			
			if (result == -1){
				System.out.println("-> 1회만 입력된 값이 없습니다.");
			}else{
				System.out.println(list);
				System.out.println("-> " + result);
			}
		}catch(NumberFormatException e){
			System.out.println("-> 입력된 값이 숫자가 아닙니다. 0에서 1000까지의 값을 입력해주세요.");
		}
	}

	public static int findNumber(int[] arr){
		for (int i = 0; i < 1001; i++){
			if (arr[i] == 1){
				return i;
			}
		}
		return -1;
	}
}