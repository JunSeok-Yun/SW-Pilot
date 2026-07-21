public class HelloBiodome04 {
	static final double PI = 3.14;
	static final double BEO_COEFFICIENT = 0.415;
	
	public static void main(String[] args) {
		if (args.length != 3){
			System.out.println("입력되지 않은 값이 있습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요.");
			return;
		}

		try{
			double temperature = Double.parseDouble(args[0]);
			double humidity = Double.parseDouble(args[1]);
			double oxygen = Double.parseDouble(args[2]);
			
			if (Double.isNaN(temperature) || Double.isInfinite(temperature)
				|| Double.isNaN(humidity) || Double.isInfinite(humidity)
				|| Double.isNaN(oxygen) || Double.isInfinite(oxygen)) {
				
				System.out.println("입력된 값이 올바르지 않습니다. [온도][습도][산소농도] 순서 대로 숫자 값을 입력해주세요");
				return;
			}

			if (!(isValidInput(temperature, humidity, oxygen))) {
				if (temperature < 10 || temperature >= 27.5) {
					System.out.println("온도값이 정상범위를 벗어났습니다. 확인이 필요합니다.");
				}else if(humidity <= 40 || humidity >= 60) {
					System.out.println("습도값이 정상범위를 벗어났습니다. 확인이 필요합니다.");
				}else if(oxygen < 19.5 || oxygen > 23.5) {
					System.out.println("산소농도값이 정상범위를 벗어났습니다. 확인이 필요합니다.");
				}
			}else {
				double hValue = calculateHvalue(humidity, temperature, oxygen);
				System.out.printf("생명의 나무는 안정적인 상태입니다. 건강지수는 %.2f입니다%n", hValue);
			}

		}catch (NumberFormatException e){
			System.out.println("입력된 값이 올바르지 않습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요.");
		}
	}

	public static double calculateSquareRoot(double num) {
		if (num == 0)
			return 0;

		double guess = num / 2.0;
		return recursiveSquareRoot(num, guess);
	}

	public static double recursiveSquareRoot(double num, double guess) { // 반복문을 사용하지 않고 재귀로 구현
		double epsilon = 0.0001; // 오차범위

		if (calculateAbsoluteValue(guess * guess - num) <= epsilon) {
			return guess;
		}
		
		double newGuess = (guess + (num / guess)) / 2.0;
		return recursiveSquareRoot(num, newGuess);
	}

	public static boolean isValidInput(double temperature, double humidity, double oxygen) {
		if ((temperature < 10 || temperature >= 27.5) || (humidity <= 40 || humidity >= 60) || (oxygen < 19.5 || oxygen > 23.5)) {
			return false;
		}
		return true;
	}

	public static double calculateAbsoluteValue(double num) {
		double result = num  > 0 ? num : -1 * num;
		
		return result;
	}

	public static double calculateHvalue(double humidity, double temperature ,double oxygen) {
		double squareRootHumidity = calculateSquareRoot(humidity); // 습도 제곱근값
		double absoluteValue = calculateAbsoluteValue(squareRootHumidity - temperature); // 절대값
		double result = (BEO_COEFFICIENT * absoluteValue) + (oxygen / (PI * PI)); // 생명지수 계산식
		
		return result;
	}
}
