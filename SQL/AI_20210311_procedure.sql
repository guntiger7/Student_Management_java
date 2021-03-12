CREATE OR REPLACE PROCEDURE add_buytbl (
    p_idnum      buytbl.idnum%TYPE,
    p_userid     buytbl.userid%TYPE,
    p_prodname   buytbl.prodname%TYPE,
    p_groupname  buytbl.groupname%TYPE,
    p_price      buytbl.price%TYPE,
    p_amount     buytbl.amount%TYPE,
    p_sss        buytbl.sss%TYPE
) IS
BEGIN
    INSERT INTO buytbl (
        idnum,
        userid,
        prodname,
        groupname,
        price,
        amount,
        sss
    ) VALUES (
        p_idnum,
        p_userid,
        p_prodname,
        p_groupname,
        p_price,
        p_amount,
        p_sss
    );

    COMMIT;
END add_buytbl;

CREATE OR REPLACE PROCEDURE del_buytbl (
    p_idnum buytbl.idnum%TYPE
) IS
    zerodelete EXCEPTION;
BEGIN
    DELETE FROM buytbl
    WHERE
        idnum = p_idnum;

    COMMIT;
END del_buytbl;

EXECUTE add_buytbl(28, 'CGH', '운동화', '서적', 30,
                  2, NULL);

EXECUTE del_buytbl(26);

CREATE OR REPLACE PROCEDURE del_buytbl (
    p_idnum buytbl.idnum%TYPE
) IS
    zerodelete EXCEPTION;
BEGIN
    DELETE FROM buytbl
    WHERE
        idnum = p_idnum;

    COMMIT;
END del_buytbl;

DECLARE
    emp_id      NUMBER(4);
    emp_name    VARCHAR2(10);
    emp_salary  NUMBER(10);
    CURSOR cu1 IS
    SELECT
        idnum,
        userid,
        price
    FROM
        buytbl
    WHERE
        deptno = 30;

BEGIN
    OPEN cu1;
    dbms_output.put_line('사번        이름         급여');
    LOOP
        FETCH cu1 INTO
            emp_id,
            emp_name,
            emp_salary;      --fetch 커서에서 값을 꺼내오는 것
        EXIT WHEN cu1%notfound; --커서에 있는 데이터를 fatch하여 넣어주는데
                                                   --더이상 변수에 할당할 레코드가 없을 때 빠져나간다
        dbms_output.put_line(emp_id
                             || ' '
                             || emp_name
                             || ' '
                             || emp_salary);

    END LOOP;

    CLOSE cu1;
END;

CREATE OR REPLACE PROCEDURE selectallcolumn_buytbl (
    p_cursor OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_cursor FOR SELECT
                          *
                      FROM
                          buytbl;

END selectallcolumn_buytbl;

CREATE OR REPLACE PROCEDURE gt_add_java_student (
    p_sid      java_student.sid%TYPE,
    p_sname    java_student.sname%TYPE,
    p_address  java_student.address%TYPE
) IS
BEGIN
    INSERT INTO java_student (
        sid,
        sname,
        address
    ) VALUES (
        p_sid,
        p_sname,
        p_address
    );

    COMMIT;
END gt_add_java_student;

CREATE OR REPLACE PROCEDURE gt_update_java_student_by_id (
    p_sid      java_student.sid%TYPE,
    p_sname    java_student.sname%TYPE,
    p_address  java_student.address%TYPE
) IS
BEGIN
    UPDATE java_student
    SET
        sid = p_sid,
        sname = p_sname,
        address = p_address
    WHERE
        sid = p_sid;

    COMMIT;
END gt_update_java_student_by_id;

---------------------------------------------------------------
CREATE OR REPLACE PROCEDURE gt_delete_java_student_by_id (
    p_sid java_student.sid%TYPE
) IS
    zerodelete EXCEPTION;
BEGIN
    DELETE FROM java_student
    WHERE
        sid = p_sid;

    COMMIT;
END gt_delete_java_student_by_id;

----------------------------------------

CREATE OR REPLACE PROCEDURE gt_selectall_java_student (
    p_cursor OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_cursor FOR SELECT
                          *
                      FROM
                          java_student;

END gt_selectall_java_student;

----------------------------------------------
CREATE OR REPLACE PROCEDURE gt_select_java_student_by_id (
    p_cursor  OUT SYS_REFCURSOR,
    p_sid     java_student.sid%TYPE
) IS
BEGIN
    OPEN p_cursor FOR SELECT
                          *
                      FROM
                          java_student
                      WHERE
                          sid = p_sid;

END gt_select_java_student_by_id;

delete java_student where  sid = 111;