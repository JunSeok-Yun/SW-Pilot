public class HelloBiodome06 {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("두 개의 유전자 코드를 입력해주세요.");
			return;
		}

		String geneCode1 = args[0];
		String geneCode2 = args[1];

		int length1 = geneCode1.length();
		int length2 = geneCode2.length();

		if (!geneCode1.matches("[a-z0-9]+") || !geneCode2.matches("[a-z0-9]+")) {
			System.out.println("유전자 코드는 숫자와 영어 소문자로 이루어져야 합니다.");
			return;
		}

		if ((length1 < 5 || length2 < 5) || (length1 > 20 || length2 > 20)) {
			System.out.println("유전자 코드는 최소 5자리 이상이고 최대 20자리 이하여야 합니다.");
			return;
		}
		
		boolean isSameCode = true; // 동일한 유전 코드를 가진지 판별하기 위한 boolean 변수
		if (length1 != length2){
			isSameCode = false;
		} else {
			int i = 0;
			while (i < length1) {
				if (geneCode1.charAt(i) != geneCode2.charAt(i)) {
					isSameCode = false;
					break;
				}
				i++;
			}
			if(isSameCode) {
				System.out.println("동일한 유전자 코드입니다.");
				return;
			}
		}

		boolean isContainedCode = false; // 한 유전자 코드가 다른 유전자 코드에 포함되어 있는지 판별하기 위한 boolean 변수
		if (length1 <= length2) {
			int i = 0;
			while (i <= length2 - length1) {
				int j = 0;
				int cnt = 0;
				while (j < length1) {
					if (geneCode1.charAt(j) == geneCode2.charAt(i + j)) {
						cnt++;
					}
					j++;
				}
				if (cnt == length1) {
					isContainedCode = true;
					break;
				}
				i++;
			}
		}else {
			int i = 0;
			while (i <= length1 - length2) {
				int j = 0;
				int cnt = 0;
				while (j < length2) {
					if (geneCode2.charAt(j) == geneCode1.charAt(i + j)) {
						cnt++;
					}
					j++;
				}
				if (cnt == length2) {
					isContainedCode = true;
					break;
				}
				i++;
			}
		}

		if (isContainedCode) {
			System.out.println("부분적으로 포함됩니다.");
		} else {
			System.out.println("포함되지 않습니다.");
		}
	}
}
