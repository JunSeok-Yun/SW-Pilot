public class HelloBiodome01 {
	public static void main(String[] args) {
		if(args.length == 0 ) {
			System.out.println("입력된 값이 없습니다. 다시 입력해주세요.");
			return;
		}
		String input = String.join(" ", args);
		String name = String.format("%.10s", input);
		System.out.println("반갑습니다, \"" + name + "\"님");
	}
}
