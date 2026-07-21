public class HelloBiodome05 {
	public static void main(String[] args) {
		int[] values = new int[2];
		values = findValues();
		if (values == null) {
			System.out.println("조건을 만족하는 값을 찾을 수 없습니다.");
			return;
		}
		
		int result = calculateFomula(values[0], values[1]);
		System.out.println("g 값 : " + values[0] + ", h 값 : " + values[1]);
		System.out.println("수식 계산 결과: " + result);
	}

	public static int calculateFomula(int g, int h) {
		return (h * h + g) * (h << h) + (g << g);
	}

	public static int[] findValues(){
		for (int g = 0; g <= 15; g++) {
			for (int h = 0; h <= 15; h++) {
				boolean firstFormula = (g & 1 >> g << 2 | h + g ^ h) == 1;
				boolean secondFormula = (g % 2 << h >> g | 1 & 0 ^ 0) == 2;

				if (firstFormula && secondFormula) {
					int[] result = {g, h};
					return result;
				}
			}
		}
		return null;
	}
}
