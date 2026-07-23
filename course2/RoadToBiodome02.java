import java.util.*;
public class RoadToBiodome02 {
	private static ArrayList<String> stack = new ArrayList<>();
	public static void main(String[] args) {
		if (args.length == 0){
			System.out.println("올바른 문장을 입력해주세요.");
			return;
		}

		String str = String.join(" ", args);

		if (str.trim().isEmpty()) {
			System.out.println("올바른 문장을 입력해주세요.");
			return ;
		}

		if (!str.matches("[a-zA-Z0-9가-힣ㄱ-ㅣ\\s]+") || (str.length() < 2 || str.length() > 1000000)){
			System.out.println("입력된 메시지가 올바르지 않습니다. 다시 한번 확인해주세요.");
			return;
		}

		boolean isPalindrome = true;
		for (int i = 0; i < str.length() / 2; i++){
			if (str.charAt(i) != str.charAt(str.length() - 1 - i)){
				isPalindrome = false;
				break;
			}
		}

		if (isPalindrome){ // 회문이라면 입력 값 출력 후 프로그램 종료
			System.out.println(str);
			return;
		}

		for (int i = 0; i < str.length(); i++){
			push(String.valueOf(str.charAt(i)));
		}

		char[] result = new char[str.length()];
		int idx = 0;
		while (!isEmpty()) {
			result[idx++] = pop().charAt(0);
		}

		System.out.println(new String(result));
	}

	public static boolean isEmpty() {
		return stack.size() == 0;
	}

	public static void push(String str) {
		stack.add(str);
	}

	public static String pop() {
		if (isEmpty()){
			return null;
		}
		return stack.remove(stack.size() - 1);
	}

	public static String peek() {
		if (isEmpty()){
			return null;
		}
		return stack.get(stack.size() - 1);
	}
}