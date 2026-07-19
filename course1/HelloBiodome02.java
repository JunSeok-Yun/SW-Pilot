public class HelloBiodome02 {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("입력된 값이 없거나, 3개의 값이 아닙니다. 다시 입력해주세요.");
			return;
		}

		try{
			int solarEnergy = Integer.parseInt(args[0]);
			int windEnergy = Integer.parseInt(args[1]);
			int geothermalEnergy = Integer.parseInt(args[2]);

			if ((solarEnergy < 0 || windEnergy < 0 || geothermalEnergy < 0) || (solarEnergy > 30000 || windEnergy > 30000 || geothermalEnergy > 30000)) {
				System.out.println("입력된 값은 음수 또는 30000 을 초과할 수 없습니다. 다시 입력해주세요.");
				return;
			}

			int sumEnergy = solarEnergy + windEnergy + geothermalEnergy;
			System.out.println("총 에너지 생산량은 " + sumEnergy + " 입니다.");

			if (sumEnergy == 0){
				System.out.println("태양광 0.0%, 풍력 0.0%, 지열 0.0%");
			}else{
				double solarRate = ((double) solarEnergy / sumEnergy) * 100;
				double windRate = ((double) windEnergy / sumEnergy) * 100;
				double geothermalRate = ((double) geothermalEnergy / sumEnergy) * 100;
				System.out.printf("태양광 %f%%, 풍력 %f%%, 지열 %f%%\n", solarRate, windRate, geothermalRate);
			}
		}catch (NumberFormatException e) {
			System.out.println("입력된 값이 숫자가 아닙니다. 다시 입력해주세요.");
			return;
		}
	}
}
