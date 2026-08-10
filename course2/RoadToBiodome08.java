public class RoadToBiodome08 {

    private static final int CAPACITY = 100;
    private static final int[] queue = new int[CAPACITY];
    private static int front = 0;   // 다음에 꺼낼(dequeue) 위치
    private static int rear = -1;   // 마지막으로 넣은(enqueue) 위치

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("처리할 자원 요청 값을 입력해주세요.");
            return;
        }

        // 1. 입력받은 순서대로 큐에 저장
        for (String arg : args) {
            try {
                int amount = Integer.parseInt(arg);
                enqueue(amount);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요. (입력값: " + arg + ")");
                return;
            }
        }

        // 2. 큐에 쌓인 순서대로(먼저 들어온 것부터) 자원 요청을 처리
        while (!isEmpty()) {
            int amount = dequeue();
            System.out.println("자원 " + amount + "을 제공했습니다.");
        }

        System.out.println("모든 요청이 처리되었습니다.");
    }

    // 큐의 맨 뒤에 자원 요청량을 추가
    private static void enqueue(int amount) {
        if (rear + 1 >= CAPACITY) {
            System.out.println("큐가 가득 찼습니다. (최대 " + CAPACITY + "개) 자원 " + amount + "은 처리할 수 없습니다.");
            return;
        }
        rear++;
        queue[rear] = amount;
    }

    // 큐의 맨 앞 값을 꺼내면서 제거
    private static int dequeue() {
        int value = peek();
        front++;
        return value;
    }

    // 큐의 맨 앞 값을 확인
    private static int peek() {
        return queue[front];
    }

    // 큐에 처리할 요청이 남아있는지 확인
    private static boolean isEmpty() {
        return front > rear;
    }
}