package Services;

import Models.Student;

import java.util.List;
import java.util.Map;

public interface IManagementActions {
    public Map<Boolean, List<Student>> splitStudentsByPrivilege(List<Student> students);
}
