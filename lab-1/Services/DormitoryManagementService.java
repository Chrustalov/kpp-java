package Services;

import java.util.*;
import Models.Student;

public class DormitoryManagementService {
  public static Map<Boolean, List<Student>> splitStudentsByPrivilege(List<Student> students) {
    Map<Boolean, List<Student>> partition = new HashMap<>();
    partition.put(true, new ArrayList<>());
    partition.put(false, new ArrayList<>());

    for (Student student : students) {
        partition.get(student.isPrivileged()).add(student);
    }

    return partition;
  }

  public static Map<String, List<Student>> groupStudentsByDorms(List<Student> students) {
    Map<String, List<Student>> dormGroups = new HashMap<>();
    for (Student student : students) {
      dormGroups.computeIfAbsent(student.getDormitory(), k -> new ArrayList<>()).add(student);
    }

    return dormGroups;
  }

  public static Map<Integer, Integer> countStudentsByRooms(List<Student> students) {
    Map<Integer, Integer> roomCounts = new HashMap<>();
    for (Student student : students) {
      roomCounts.put(student.getRoomNumber(), roomCounts.getOrDefault(student.getRoomNumber(), 0) + 1);
    }

    return roomCounts;
  }

  public static List<Student> sortStudentsByAgeAndPriveleged(List<Student> students) {
    students.sort(Comparator.comparing(Student::getAge).thenComparing(Student::isPrivileged));

    return students;
  }

  public static Set<Integer> uniqueRoomNumbers(List<Student> students) {
    Set<Integer> uniqueRoomNumbers = new HashSet<>();
    for (Student student : students) {
      uniqueRoomNumbers.add(student.getRoomNumber());
    }

    return uniqueRoomNumbers;
  }

  public static Optional<Student> findHighestFeeStudent(List<Student> students) {
    if (students.isEmpty()) {
      return Optional.empty();
    }

    Student studentMaxFee = students.get(0);

    for (Student student : students) {
      if (studentMaxFee.getFee() < student.getFee()) {
        studentMaxFee = student;
      }
    }

    return Optional.of(studentMaxFee);
  }
}

// List a, b, c2 delele all start with c


