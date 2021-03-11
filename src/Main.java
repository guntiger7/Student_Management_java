import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
	static Connection conn;

	public static void main(String[] args) {

		File file = new File("./student.txt");
		conn = DB_Connection.getConnection();
		DB_Procedure procedure = new DB_Procedure(conn);

		// 테스트
		// selectTest();
		// procedureInsertTest();
		// procedure.procedureInsertTest();
		// procedure.insertStudent(40, "paul", "daegu");
		// procedure.selectStudent(3);
		// procedure.updateStudent(40, "sam", "seoul");
		// procedure.deleteStudent(30);

		Scanner scan = new Scanner(System.in);
		Boolean exitFlag = true;
		while (exitFlag) {

			System.out.println(
					"[1]학생 등록  \t[2]모든 학생 조회  \t[3]학번으로 조회 \n[4]수정 \t\t[5]삭제  \t\t[6]텍스트 파일로 저장  \n[다른 모든 입력] 종료");

			//숫자가 아닌 값이 입력으로 들어오면 예외처리는 어떻게??
			int select = 0;
			try {
				if (scan.hasNextInt())
					select = scan.nextInt();
				System.out.println(select + " 선택됨");
			} catch (Exception e) {
				scan.nextLine();
			}

			switch (select) {
			case 0:
				System.out.println("숫자를 입력해주세요");
				break;
			case 1:
				System.out.println("=== 학생 등록 ===");
				System.out.println("학번, 이름, 주소 순으로 입력");
				procedure.insertStudent(scan.nextInt(), scan.next(), scan.next());
				break;
			case 2:
				System.out.println("=== 학생 조회 ===");
				System.out.println(procedure.selectStudent());
				break;

			case 3:
				System.out.println("=== 조회할 학생 학번 입력 ===");
				procedure.selectStudent(scan.nextInt());
				break;

			case 4:
				System.out.println("=== 수정 할 학생 [학번, 이름, 학과] 순으로 입력 입력 ===");
				procedure.updateStudent(scan.nextInt(), scan.next(), scan.next());
				break;

			case 5:
				System.out.println("=== 삭제 할 학생 학번 입력 ===");
				procedure.deleteStudent(scan.nextInt());
				break;

			case 6:
				try {
					FileWriter fw = new FileWriter(file);
					String resString = procedure.selectStudent();
					fw.write(resString);
					fw.close();
					System.out.println("student.txt 파일에 저장됨");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			default:
				System.out.println("종료됩니다.");
				exitFlag = false;
				break;
			}
		}

		scan.close();
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
}
