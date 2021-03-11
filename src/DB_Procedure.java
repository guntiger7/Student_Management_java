import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Procedure {
	private static Connection conn = null;

	public DB_Procedure(Connection conn) {
		DB_Procedure.conn = conn;
	}

	public void procedureInsertTest() {

		try {
			CallableStatement cs = conn.prepareCall("begin add_java_student(?,?,?); end;");
			cs.setInt(1, 33);
			cs.setString(2, "SSK");
			cs.setString(3, "운동화");
			int seq = cs.executeUpdate();
			if (seq == 0) {
				System.out.println("입력 실패");
			} else {
				System.out.println(seq + "행 삽입");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public String selectStudent() {
		String res = "";
		Student student;
		ResultSet rs = null;

		CallableStatement cs;
		try {
			cs = conn.prepareCall("begin gt_selectall_java_student(?); end;");

			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();

			rs = (ResultSet) cs.getObject(1);

			while (rs.next()) {
				student = new Student(rs.getInt("sid"), rs.getString("sname"), rs.getString("address"));
				res += student + "\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void selectStudent(int sid) {

		try {
			ResultSet rs = null;
			CallableStatement cs;
			cs = conn.prepareCall("begin gt_select_java_student_by_id(?,?); end;");
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cs.setInt(2, sid);
			cs.execute();

			rs = (ResultSet) cs.getObject(1);

			Student[] students = new Student[100];
			for (int i = 0; rs.next(); i++) {

				students[i] = new Student(rs.getInt("sid"), rs.getString("sname"), rs.getString("address"));
			}
			if (students[0] == null) {
				System.out.println("사람 없음");
			}

			for (int i = 0; students.length > i; i++) {
				if (students[i] != null) {
					System.out.println(students[i]);
				}
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertStudent(int id, String name, String address) {

		try {
			CallableStatement cs = conn.prepareCall("begin add_java_student(?,?,?); end;");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.setString(3, address);
			int seq = cs.executeUpdate();
			// seq가 0이 되는 경우가 없음.. 확인 필요
			if (seq == 0) {
				System.out.println("입력 실패");

			} else {
				System.out.println(seq + "행 삽입");
			}

		} catch (SQLException e1) {
			System.out.println("입력 실패");
		}
	}

	public void updateStudent(int id, String name, String address) {

		try {
			CallableStatement cs = conn.prepareCall("begin gt_update_java_student_by_id(?,?,?); end;");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.setString(3, address);
			int seq = cs.executeUpdate();

			if (seq == 0) {
				System.out.println(seq + "값");
			} else {
				System.out.println(seq + "값");
			}
			cs.close();
		} catch (SQLException e1) {
			System.out.println("수정 실패");
		}
	}

	public void deleteStudent(int id) {

		try {
			CallableStatement cs = conn.prepareCall("begin gt_delete_java_student_by_id(?); end;");
			cs.setInt(1, id);
			int seq = cs.executeUpdate();

			if (seq == 0) {
				System.out.println(seq + "값");
			} else {
				System.out.println(seq + "값");
			}
			cs.close();
		} catch (SQLException e1) {
			System.out.println("삭제 실패");
		}
	}
}
