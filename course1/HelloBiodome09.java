public class HelloBiodome09 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {

		if (args.length != 1 && args.length != 2){
			System.out.println("입력값으로 하나 또는 두개의 값을 입력해주세요.");
			return;
		}

		int height;
		try{
			height = Integer.parseInt(args[0]);
			if (height < 3 || height > 100) {
				System.out.println("잘못된 입력입니다. 3 ~ 100 사이의 숫자를 입력하세요.");
				return;
			}
		} catch (NumberFormatException e){
			System.out.println("잘못된 입력입니다. 3 ~ 100 사이의 숫자를 입력하세요.");
			return;
		}

		char fillChar = '*';
		if (args.length == 2) {
			String special = args[1];
			if (special.length() != 1 || !special.matches("[!@~`#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+")) {
				System.out.println("두 번째 입력값은 1글자 혹은 특수문자여야 합니다.");
				return;
			}
			fillChar = special.charAt(0);
		}

		printTree(height, fillChar);
		System.out.println(">>>");
		System.out.println(sb.toString());
	}

	public static void printTree(int height, char fillChar) {
		for (int i = 1; i <= height; i++) {
			for (int j = 0; j < height - i; j++) {
				sb.append(' ');
			}
			for (int j = 0; j < 2 * i - 1; j++){
				if (j == (2*i - 1) / 2){
					sb.append(fillChar);
				}else{
					sb.append('*');
				}
			}
			sb.append('\n');
		}
		for(int i = 0; i < height - 1; i++){
			sb.append(' ');
		}
		sb.append('|');
	}
}